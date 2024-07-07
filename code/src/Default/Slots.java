package Default;

public class Slots
{
	public int width;
	public int depth;
	public int carLisence;
	public int slotNumber;
	public String  sensor;
	public static int totalSlots = 0;
	public Slots(){ //Default constructor
		width = 0;
		depth = 0;
		carLisence = 0;
		slotNumber = -1;
		sensor = "green";
	}//Parameterized constructor
	public Slots(int w, int d){
		width = w;
		depth = d;
		slotNumber = ++totalSlots;
		carLisence = 0;
		sensor = "green";
	}//Print function
	public void display(){
		System.out.print("Slot Number: "+ slotNumber + "\n");
		System.out.print("Width: "+ width + "\n");
		System.out.print("Depth: " + depth + "\n");
	}

}