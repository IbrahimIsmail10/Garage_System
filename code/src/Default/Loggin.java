package Default;

public abstract class Loggin {
	public boolean logged = false;
	public void displayApprovalMessage(int code) {
		if(code == 1)
			System.out.print("Logged In Successfully\n");
		else if(code == 2)
			System.out.print("Wrong Password\n");
		else if(code == 3)
		System.out.print("Wrong Username\n");
	}
}
