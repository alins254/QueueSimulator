package Model;

import java.util.LinkedList;

public class Coada implements Runnable{
	private LinkedList<Client> queue;
	private int busyTime;
	private boolean available;

	public Coada() {
		super();
		busyTime = 0;
		queue = new LinkedList<Client>();
	}
	
	public Coada(int time) {
		super();
		busyTime = time;
		queue = new LinkedList<Client>();
	}

	public LinkedList<Client> getQueue() {
		return queue;
	}
	
	public synchronized void addClient(Client c) {
		queue.add(c);
		busyTime+=c.getServiceTime();
		available = true;
		notifyAll();
	}

	public synchronized int getBusyTime() {
		while(available == false) {
			try {
				wait(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		available = false;
		return busyTime;
	}

	public void setBusyTime(int busyTime) {
		this.busyTime = busyTime;
	}
	
	public synchronized void timePass() {
		available=true;
		this.busyTime--;
		notifyAll();
	}
	
	public synchronized void zero() {
		available = true;
		notifyAll();
	}

	public void run() {
		try {
			while(true) {
				if(busyTime>0) {
					timePass();
				}
				else zero();
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		String s = "";
		for(Client c: queue) {
			s+="c"+c.getNumber();
		}
		return s;
	}	
	
	public int serviceTimeLeft(){
		if(queue.isEmpty())
			return 0;
		int a = 0;
		for(Client c:queue)
			a+=c.getServiceTime();
		return a-=queue.getFirst().getServiceTime();
	}
	
	public String clientDone() {
		if(!queue.isEmpty()) {
			String s = queue.getFirst().toString();
			queue.removeFirst();
			return s;
		}
		return "";
	}
	
}
