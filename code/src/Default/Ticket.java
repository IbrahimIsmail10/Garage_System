package Default;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Ticket
{
	public LocalDateTime enterTime;
	public Float time ;
	public int ticketID;
	public int slotID;
	//Default constructor
	public Ticket(){
		enterTime = LocalDateTime.now();
		ticketID = 0;
		slotID = 0;
		time = (float) (enterTime.getMinute() + ((enterTime.getHour()-12)*60));
		time /=60;
	}//Parameterized constructor
	public Ticket( int ticketID, int slotID){
		this.ticketID = ticketID;
		this.slotID = slotID;
		this.enterTime = LocalDateTime.now();
		time = (float) (enterTime.getMinute() + ((enterTime.getHour()-12)*60));
		time /=60;
	}
	// print current ticket
	public void printTicket() {
		DateTimeFormatter  myFormatObj = DateTimeFormatter .ofPattern("dd-MM-yyyy HH:mm:ss");
		System.out.println("Ticket Number: "+ ticketID);
		System.out.println("Slot ID: "+ slotID);
		System.out.println("Enter Time: " + enterTime.format(myFormatObj));
	}
}
