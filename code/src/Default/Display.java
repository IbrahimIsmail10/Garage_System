package Default;
import java.util.Scanner;
public class Display {
	public Authenticate master;
	private boolean created ;
	private boolean access ;
	Parking ob ;
	private static Display display = new Display();  
	private Display() {//Default constructor
		created = false; 
		access = false;
	}
	public static Display getObject() {
		return display;
	}
	// Show free slots
	public void displayFreeSlots() {
		ob.displayAvalibale();
	}
	// Get the total income
	public void showTotalIncome() {
		System.out.print("Total Income Of The Day "+ ob.getTotalIncome()+" EGP \n");
	}
	// Display total cars entered the garage today
	public void displayTotalCars() {
		System.out.print("Total Cars Parked Today " + ob.getTotalCars() + "\n");
	}
	// Car park in garage 
	public void StartParkIn() {
		ob.parkIn();
	}
	// Car park out the garage
	public void StartParkout() {
		ob.parkIn();
	}
	// Display application mode 
	public void enterChoices() {
		System.out.print("1: continue As Owner\n");
		System.out.print("2: Login As Admin\n");
		System.out.print("3: Continue As Customer\n");
		System.out.print("4: Exit Program\n");
	}
	// Get username and password from the user 
	public String[] setUserData() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Username\n");
		String username = sc.next();
		System.out.print("Enter Password\n");
		String password = sc.next();
		String data[] = {username,password};
		return data;
	}
	public void showOwnerOptions() {
		System.out.print("1: Display free slots\n");
		System.out.print("2: Display total income\n");
		System.out.print("3: Display total cars\n");
		System.out.print("4: Switch To Admin (To Test)\n");
		System.out.print("5: Switch To Customer (To Test)\n");
		System.out.print("6: Exit Program\n");
	}
	public void showAdminOptions() {
		System.out.print("1: Calculate Fees\n");
		System.out.print("2: display free slots\n");
		System.out.print("3: Switch To Owner (To Test)\n");
		System.out.print("4: Switch To Customer (To Test)\n");
		System.out.print("5: Exit Program\n");
	}
	public void showCustomerOptions() {
		System.out.print("1: Park In\n");
		System.out.print("2: Park Out\n");
		System.out.print("3: Switch To Owner (To Test)\n");
		System.out.print("4: Switch To Admin (To Test)\n");
		System.out.print("5: Exit Program\n");
	}
	public void customerOptions(int options) {
		Scanner sc = new Scanner(System.in);
		if(options == 1) 
			StartParkIn();
		else if(options == 2) {
			System.out.print("Enter Ticket Number To Exit\n");
			int t = sc.nextInt();
			ob.parkOut(t);
		}
		else if(options == 3) {
			 System.out.print("Switched to Owner\n");
			 runAsOwner();
		 }
		 else if(options == 4) {
			 System.out.print("Switched to Admin\n");
			 runAsadmin();
		 }
		else if(options == 5)
			Exit();
	}
	public void ownerOptions(int options) {
		Scanner sc = new Scanner(System.in);
		if(options == 1 ) 
			 displayFreeSlots();
		 else if(options == 2 ) 
			 showTotalIncome();
		 else if(options == 3)
			 displayTotalCars();
		 else if(options == 4) {
			 access = true;
			 System.out.print("Switched to Admin\n");
			 runAsadmin();
		 }
		 else if(options == 5) {
			 System.out.print("Switched to Customer\n");
			 runAsCusomer();
		 }
		 else if(options == 6)
			 Exit();
	}
	public void adminOptions(int options) {
		if(options == 1)
			getFees();
		else if(options == 2)
			displayFreeSlots();
		else if(options == 3) {
			access = false;
			System.out.print("Switched to Owner\n");
			runAsOwner();
		}
		else if(options == 4) {
			System.out.print("Switched to Customer\n");
			runAsCusomer();
		}
		else if(options == 5)
			Exit();
	}
	// Owner Mode
	public void runAsOwner() {
		Scanner sc = new Scanner(System.in);
		int options = 0;
		while (options != 6) {		
			showOwnerOptions();
			options = sc.nextInt();
			ownerOptions(options);
		}
	}
	// Admin Mode
	public void runAsadmin() {
		Scanner sc = new Scanner(System.in);
		if(!access) {
			String data[] = setUserData();
			master = new loginAdmin();
			boolean in = master.authentication(data[0],data[1]);
			if(!in)
				click();
		}
		int options = 0;
		while (options != 5) {
			showAdminOptions();
			options = sc.nextInt();
			adminOptions(options);	
		}
	}
	// Customer Mode
	public void runAsCusomer() {
		Scanner sc = new Scanner(System.in);
		int option = 0;
		while(option != 3) {				
		showCustomerOptions();
		option = sc.nextInt();
		 customerOptions(option);
	}
	}
	// Calculate from ticket number 
	public int getFees() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Ticket Number To Exit\n");
		int t = sc.nextInt();
		int x= ob.validateTicket(t);
		int y=ob.calculateFees(t);
		if(y== 32768 || x == 32768) {
			System.out.print("Wrong Ticket Number Please Check it Again\n");
			return -1;
		}
		return y;
	}
	// Start the Application By creating the Garage
	public void createGarage() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Welcome Back\n");
		String data[] = setUserData();
		master = new LoginOwner();
		boolean in = master.authentication(data[0],data[1]);
		if(!in)
			createGarage();
		else 
			created = true;
		System.out.print("Enter Number of slots\n");
		int slotsNumber = sc.nextInt();
		ob = new Parking(slotsNumber);
		ob.wayOfPark();
		click();
	}
	// Terminate Program
	public void Exit() {
		System.out.println("Terminating Your Program");
		System.exit(0);
	}
	// Call all the functions in shape of program
public void click() {
		if(created) {
		enterChoices();
		Scanner sc = new Scanner(System.in);
		int choose = sc.nextInt();
		if(choose == 1) 
			runAsOwner();
		else if(choose == 2)
			runAsadmin();
		else if(choose == 3)
			runAsCusomer();
		else if(choose == 4)
			Exit();
		}
	}
}
