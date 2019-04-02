package Model;

public class Client implements Comparable<Object> {
	private int arrivingTime;
	private int serviceTime;
	private int finishingTime;
	private int number;

	public Client(int arrivingTime, int serviceTime) {
		super();
		this.arrivingTime = arrivingTime;
		this.serviceTime = serviceTime;
		finishingTime = this.arrivingTime + this.serviceTime;
	}

	public int getArrivingTime() {
		return arrivingTime;
	}

	public void setArrivingTime(int arrivingTime) {
		this.arrivingTime = arrivingTime;
	}

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getFinishingTime() {
		return finishingTime;
	}

	public void setFinishingTime(int finishingTime) {
		this.finishingTime = finishingTime;
	}

	public int compareTo(Object arg0) {
		Client c = (Client) arg0;
		return this.arrivingTime - c.arrivingTime;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Clientul cu numarul "+number+" si timpul de servire "+serviceTime;
	}

	public static Client clientGenerator(int arivingTime, int minService, int maxService) {
		return new Client(arivingTime, (int) (Math.random() * (maxService - minService) + minService));
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
