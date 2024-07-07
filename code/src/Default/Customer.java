package Default;

public class Customer{
	public Ticket ob = new Ticket();
	public int carID;
	public Customer(){ //Default constructor
		carID = 0;
	}//Parameterized constructor
	public Customer(Ticket t, int lisence){
		this.carID = lisence;
		this.ob = (t);
	}

}