package Controller;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Model.Client;
import Model.Coada;
import View.InterfataUtilizator;

public class SystemControll implements Runnable{
	private InterfataUtilizator interfata;
	private int intervalMin;
	private int intervalMax;
	private int serviceMin;
	private int serviceMax;
	private int noQueues;
	private int simulationTime;
	private int clientNumber = 1;
    private	int arriveClient = 0;
    private int[] busy;
    private int mediumWaitingTime;
    private int maxWaitingTime;
    private int[] rushHour;
    private Thread[] t;
	
	public SystemControll(int intervalMin, int intervalMax, int serviceMin, int serviceMax, int noQueues, int simulationTime) {
		this.intervalMin = intervalMin;
		this.intervalMax = intervalMax;
		this.serviceMin = serviceMin;
		this.serviceMax = serviceMax;
		this.noQueues = noQueues;
		this.simulationTime = simulationTime;
		interfata = new InterfataUtilizator(noQueues);
		busy = new int[noQueues];
		mediumWaitingTime=0;
		maxWaitingTime=0;
		rushHour = new int[simulationTime+1];
		rushHour[0]=0;
	}
	
	private Coada[] createQueues() {
		Coada[] cozi = new Coada[noQueues];
		t = new Thread[noQueues];
		for(int i=0;i<noQueues;i++) {
			cozi[i] = new Coada();
			t[i] = new Thread(cozi[i]);
			t[i].start();
		}
		return cozi;
	}
	
	//proceseaza timpii de asteptare
	private void processWaitingTime(int time) {
		mediumWaitingTime+=time;
		if(time>maxWaitingTime)
			maxWaitingTime=time;
	}
	
	//genereaza un client random si actualizeaza timpul la care va veni urmatorul client
		private String newClient(int time, Coada[] cozi) {
			if(time==arriveClient) {
				String s = new String();
				Client c = Client.clientGenerator(arriveClient, serviceMin, serviceMax);
				c.setNumber(clientNumber);
				clientNumber++;
				arriveClient = arriveClient +(int)(Math.random()*(intervalMax-intervalMin)+intervalMin);
				int min=Integer.MAX_VALUE;
				int index = 0;
				for(int b = 0; b<noQueues; b++) {
					if(min>busy[b]) {
						min=busy[b];
						index = b;
					}	
				}
				cozi[index].addClient(c);
				s = c.toString()+" a fost introdus in coada "+(index+1);
				processWaitingTime(busy[index]);
				System.out.println(busy[index]);
				rushHour[time]++;
				if(time==arriveClient) {
					s+="\n"+newClient(time,cozi);
				}
				return s;
			}
			return new String("");
		}
		
	public void run() {
		Coada[] cozi = createQueues();
		JTextField[] fields = interfata.getQueuesFields();
		JLabel[] waitingL = interfata.getWaitingLabels();
		String log = new String("");
		for(int time=0;time<=simulationTime;time++) {
			if(time!=0) rushHour[time]=rushHour[time-1];
			String s = newClient(time, cozi);
			if(!s.equals("")) {
				log += "t+"+time+": " + s+"\n";
			}
			//actualizare labelul de asteptare si fieldul ptr fiecare coada
			for(int i=0;i<noQueues;i++) {
				busy[i] = cozi[i].getBusyTime();
				waitingL[i].setText("Busy for: "+ busy[i]);
				if(cozi[i].serviceTimeLeft()>=busy[i]) {
					s = "";
					s = cozi[i].clientDone();
					if(!s.equals("")) {
						log += "t+"+time+": " + s+" a fost eliminat din coada "+(i+1)+"\n";
						rushHour[time]--;
					}
				}	
				fields[i].setText(cozi[i].toString());
				fields[i].repaint();
			}
			interfata.getLogArea().setText(log);
		}
		stats();
	}
	
	private void stats() {
		float medWaitingTime = mediumWaitingTime*1.0f/(clientNumber-1);
		int max = Integer.MIN_VALUE;
		int index=0;
		for(int i = 0;i<=simulationTime;i++) {
			if(max<rushHour[i]) {
				max = rushHour[i];
				index = i;
			}
		}
		JOptionPane.showMessageDialog(interfata, "Statistici:\n"
				+ "Timp mediu de asteptare la coada: "+medWaitingTime+"\n"
				+ "Timp maxim de asteptare la coada: "+maxWaitingTime+"\n"
				+ "Ora de varf: "+ index + " cand s-au inregistrat "+ max + " clienti");
	}
	
}
