package View;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Conditions extends JFrame{

	private static final long serialVersionUID = 1L;
	
	JPanel interval = new JPanel();
	JPanel service = new JPanel();
	JPanel queues = new JPanel();
	JPanel simulation = new JPanel();
	JPanel trimite = new JPanel();
	
	JLabel intervalL = new JLabel("Intervalul de sosire al clientilor:", JLabel.CENTER);
	JLabel intervalMinL = new JLabel("Minim:");
	JLabel intervalMaxL = new JLabel("Maxim:");
	JLabel serviceL = new JLabel("Timpul de servire:", JLabel.CENTER);
	JLabel serviceMinL = new JLabel("Minim:");
	JLabel serviceMaxL = new JLabel("Maxim:");
	JLabel noQueuesL = new JLabel("Numarul de cozi:");
	JLabel simulationL = new JLabel("Timpul de simulare:");
	
	JTextField intervalMinT = new JTextField(5);
	JTextField intervalMaxT = new JTextField(5);
	JTextField serviceMinT = new JTextField(5);
	JTextField serviceMaxT = new JTextField(5);
	JTextField noQueuesT = new JTextField(10);
	JTextField simulationT = new JTextField(10);
	
	JButton trimiteB = new JButton("Trimite");
	
	public Conditions() {
		setSize(400,400);
		setLayout(new GridLayout(7,1));
		setTitle("Conditii Initiale");
		
		interval.add(intervalMinL);
		interval.add(intervalMinT);
		interval.add(intervalMaxL);
		interval.add(intervalMaxT);
		service.add(serviceMinL);
		service.add(serviceMinT);
		service.add(serviceMaxL);
		service.add(serviceMaxT);
		queues.add(noQueuesL);
		queues.add(noQueuesT);
		simulation.add(simulationL);
		simulation.add(simulationT);
		add(intervalL);
		add(interval);
		add(serviceL);
		add(service);
		add(queues);
		add(simulation);
		trimite.add(trimiteB);
		add(trimite);
		setVisible(true);
	}

	public JTextField getIntervalMinT() {
		return intervalMinT;
	}

	public JTextField getIntervalMaxT() {
		return intervalMaxT;
	}

	public JTextField getServiceMinT() {
		return serviceMinT;
	}

	public JTextField getServiceMaxT() {
		return serviceMaxT;
	}

	public JTextField getSimulationT() {
		return simulationT;
	}

	public JButton getTrimiteB() {
		return trimiteB;
	}

	public JTextField getNoQueuesT() {
		return noQueuesT;
	}

}
