package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.Conditions;

public class ConditionsListener {
	Conditions c;
	
	public ConditionsListener(Conditions cond) {
		c = cond;
		c.getTrimiteB().addActionListener(new ButtonListener());
	}
	
	
	class ButtonListener implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			try {
				final int intervalMin = Integer.parseInt(c.getIntervalMinT().getText());
				final int intervalMax = Integer.parseInt(c.getIntervalMaxT().getText());
				final int serviceMin = Integer.parseInt(c.getServiceMinT().getText());
				final int serviceMax = Integer.parseInt(c.getServiceMaxT().getText());
				final int noQueues = Integer.parseInt(c.getNoQueuesT().getText());
				final int simulationTime = Integer.parseInt(c.getSimulationT().getText());
				if(intervalMin>intervalMax||intervalMin<0||intervalMax<=0)
					throw new Exception("Intervalul de venire al clientilor invalid");
				if(serviceMin>serviceMax||serviceMin<=0||serviceMax<=0)
					throw new Exception("Timpul de servire minim mai mare decat cel maxim");
				if(noQueues>10)
					throw new Exception("Numarul de cozi este prea mare");
				if(noQueues<=0)
					throw new Exception("Numarul de cozi este prea mic");
				if(simulationTime>600||simulationTime<=0)
					throw new Exception("Timpul de simulare invalid");
				c.setVisible(false);
				java.awt.EventQueue.invokeLater(new Runnable(){
					public void run() {
						SystemControll s = new SystemControll(intervalMin, intervalMax, serviceMin, serviceMax, noQueues, simulationTime);
						(new Thread(s)).start();
					}
				});
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(c,"Input gresit!\n"+e.getMessage());
			}
		}
		
	}
}
