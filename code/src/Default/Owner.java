package Default;
//Inherit from Person class
public class Owner extends Person
{
	public Owner(){
		// use Person constructor
		super();
	}//Parameterized constructor
	public Owner(String username, String password){
		super(username, password);
	}
}