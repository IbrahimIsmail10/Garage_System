package Default;
import java.util.Scanner;
import java.time.LocalTime;
public class Parking
{
	private static int counter = 0;
	private int slotsNumber;
	private int wayOfParking;
	private int currentCars;
	private int tickets;
	private int totalIncome;
	private Slots[] slot;
	private Vehicale[] v;
	private Customer[] c;
		public Parking(){ //Default constructor
			slotsNumber = 0;
			wayOfParking = 0;
			tickets = 0;
			currentCars = 0;
			totalIncome = 0;
		}//Parameterized constructor
		public Parking(int slotsNumber) {
			tickets = totalIncome = currentCars = 0;
			slot = new Slots[slotsNumber];
			this.slotsNumber = slotsNumber;
			addSlot();
			v = new Vehicale[slotsNumber * 5];
			c = new Customer[slotsNumber * 5];
		}
		// Set Vehicale Object Data 
		public Vehicale setVechicle(int lis) {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Model Name of The Vehicale \n");
			String name =  sc.next();
			System.out.print("Enter Model Year of The Vehicale \n");
			int year =  sc.nextInt();
			System.out.print("Enter Width of Vehicale \n");
			int w = sc.nextInt();
			System.out.print("Enter depth of Vehicale \n");
			int d = sc.nextInt();
			Vehicale ob = new Vehicale(name, year, w, d, lis);
			return ob;
		}
		// Check if car is already parked (handling error) 
		public int checkSlot() {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter Lisence of Vehicale \n");
			int lis = sc.nextInt();
					for (int i = 0; i < currentCars ; i++){
						if (lis == v[i].lisence){
							System.out.print("Car Is Already Parked");
							System.out.print("\n");
							return -1;
						}
					}
				return lis;	
		}
		public void parkIn(){
	Scanner sc = new Scanner(System.in);
	if (!isAvaliable()){
		System.out.print("All Slots Is Full\n");
		return;
	}
		int lis = checkSlot();
		if(lis == -1)
			return;
		Vehicale ob = setVechicle(lis);
		v[counter] = (ob);
		int n = chooseSutibleSlot(ob);
		if (n != 32768)
		{
			System.out.print("Parked");
			System.out.print("\n");
			int lisence = v[counter].lisence;
			slot[n].carLisence = lisence;
			slot[n].sensor = "red";
			Ticket t = new Ticket((++tickets),slot[n].slotNumber);
			Customer newCustomer = new Customer(t, lisence);
			t.printTicket();
			c[counter] = (newCustomer);
			v[counter] = (ob);
			counter++;
			currentCars++;
		} else System.out.print("No avaialbe place for the car\n");
}
		// Get the best Slot for the car to park 
		public int chooseSutibleSlot(Vehicale ob) {
			int minWidth = 32768;
			int minDepth = 32768;
			int n = 32768;
			if (wayOfParking == 1)
			{
				for (int i = 0; i < slotsNumber; i++)
				{
					if (ob.depth <= slot[i].depth && ob.width <= slot[i].width && slot[i].sensor == "green")
					{
						n = i;
						break;
					}
				}
			}
			else if (wayOfParking == 2)
			{
				for (int i = 0; i < slotsNumber; i++)
				{
					if ((v[counter].depth <= slot[i].depth && v[counter].width <= slot[i].width) && slot[i].sensor == "green")
					{
						if (slot[i].depth * slot[i].width < minDepth * minWidth)
						{
							minWidth = slot[i].width;
							minDepth = slot[i].depth;
							n = i;
						}
					}
				}
			}
			
			return n;
		}
		public void parkOut(int currentTicket) 
		{
			boolean key = false;
			int customerNumber = 32768;
			 customerNumber = validateTicket(currentTicket);
				if(customerNumber != 32768) {
					key = true;
				}
			if (!key)
			{
				System.out.print("Wrong Ticket Number Please Check it Again\n");
				return;
			}
			int id = c[customerNumber].ob.slotID;
			freeSlot(id);
			slot[id-1].carLisence = 0;
			slot[id-1].sensor = "green";
			calculateFees(customerNumber);
			System.out.println("Parked Out\n");
			currentCars--;
		}
		// Remove Car from the slot
		public void freeSlot(int customerNumber) {
			for (int i = customerNumber; i < tickets - 1; i++)
			{
				Vehicale vec = new Vehicale();
				v[i] = vec;
				v[i] = v[i + 1];
				Customer cus = new Customer();
				c[i] = cus;
				c[i] = c[i + 1];
			}
		}
		//Set the slots Size
		public void addSlot() {
			int w,d;
		for (int i = 0; i < slotsNumber; i++)
		{
			System.out.print("Enter Width of slot "+ (i + 1) + "\n");
			Scanner sc = new Scanner(System.in);
			String scanned = sc.next();
			w = Integer.parseInt(scanned);
			System.out.print("Enter depth of slot " + (i+ 1) + "\n");
			String scanned2 = sc.next();
			d = Integer.parseInt(scanned2);
			Slots ob = new Slots(w, d);
			slot[i] = (ob);
		}
		}
		// Check If ticket Printed or no
		public int validateTicket(int currentTicket) {
			int customerNumber = 32768;
			boolean check = false;
			for (int i = 0; i < tickets; i++){
				if (currentTicket == c[i].ob.ticketID ){
					customerNumber = i;
					check = true;
					break;
				}
			}
			if(check) {
				int tempSlot = c[customerNumber].ob.slotID;
				if(slot[customerNumber].sensor == "green") {
					customerNumber =  32768;				
			}
			}
			return customerNumber;
		}
		// Get the Enter time of the customer
		public float getTime(int customerNumber) {
			float enterTime = c[customerNumber].ob.time;
			return enterTime;
		}
		// increase the total income
		public int calculateFees(int customerNumber) {
			LocalTime myObj = LocalTime.now();
			float x = myObj.getMinute() + ((myObj.getHour()-12)*60);
			x /= 60;
			float outTime = x;
			double dif = 0;
			int total = 0;
			float enterTime = getTime(customerNumber);
			dif = outTime - enterTime;
			dif = Math.ceil(dif);
			total = (int)(dif * 5);
			totalIncome += total;
			return total;
		}
		// Choose The Way of parking
		public void wayOfPark() {
			System.out.print("Choose Way Of Park\n1: First Come\n2: Best Fit\n");
			Scanner sc = new Scanner(System.in);
			int scanned = sc.nextInt();
				int n = scanned;
			if (n == 1 || n == 2)
				wayOfParking = n;
			else{
				System.out.print("Enter Valid Number\n");
				wayOfPark();
			}
		}
		// check is there is free slot or no
		public boolean isAvaliable(){
			boolean key = false;
			int c = 0;
			while (c < slotsNumber){
				if (slot[c].sensor == "green"){
					key = true;
					break;
				}
				c++;
			}
			return key;
		}
		// Show all available slots
		public void displayAvalibale(){
			int c = 0;
			System.out.print("Avaliable Slots: \n");
			while (c < slotsNumber){
				if (this.slot[c].sensor == "green"){
					this.slot[c].display();
				}
				c++;
			}
		}
		// Show the total income of the day
		public int getTotalIncome()
		{
			return totalIncome;
		}
		// Show Total Cars Parked today
		public int getTotalCars()
		{
			return tickets;
		}
		};