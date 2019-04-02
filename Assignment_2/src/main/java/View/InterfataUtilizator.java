package View;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class InterfataUtilizator extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private int[] qWaitingTime;
	JPanel queuesP = new JPanel();
	JPanel[] queuesList;
	JPanel log = new JPanel();
	
	JLabel[] queuesLabels;
	JLabel[] waitingLabels;
	JTextField[] queuesFields;
	JTextArea logArea;
	JScrollPane scroll;
	
	public InterfataUtilizator(int queuesNumber) {
		this.setLayout(new GridLayout(1,2));
		queuesP.setLayout(new GridLayout(queuesNumber,3));
		if(queuesNumber<=3)
		{
			this.setSize(900,200);
			logArea=new JTextArea(15,38);
		}
		else {
			this.setSize(900, queuesNumber*50);
			logArea=new JTextArea(queuesNumber*2+3,38);
		}
		this.add(queuesP);
		logArea.setEditable(false);
		scroll = new JScrollPane(logArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//log.add(logArea);
		log.add(scroll);
		this.add(log);
		createQueues(queuesNumber);
		this.setVisible(true);
	}
	
	public void createQueues(int number) {
		queuesList = new JPanel[number];
		queuesLabels = new JLabel[number];
		waitingLabels = new JLabel[number];
		queuesFields = new JTextField[number];
		qWaitingTime = new int[number];
		for(int i=0;i<number;i++) {
			queuesList[i]=new JPanel();
			queuesLabels[i]=new JLabel();
			queuesFields[i]=new JTextField(20);
			queuesFields[i].setEditable(false);
			queuesList[i].add(queuesLabels[i]);
			queuesList[i].add(queuesFields[i]);
			queuesLabels[i].setText("Q"+(i+1)+":");
			qWaitingTime[i]=0;
			waitingLabels[i]= new JLabel();
			waitingLabels[i].setText("Busy for: "+qWaitingTime[i]);
			queuesList[i].add(waitingLabels[i]);
			queuesP.add(queuesList[i]);
		}
	}

	public JTextField[] getQueuesFields() {
		return queuesFields;
	}

	public JLabel[] getWaitingLabels() {
		return waitingLabels;
	}

	public void actualizeaza(int[] busy, int number) {
		for(int i=0;i<number;i++) {
			waitingLabels[i].setText("Busy for: "+busy[i]);
			waitingLabels[i].repaint();
		}
	}

	public JTextArea getLogArea() {
		return logArea;
	}
	
}
