package Default;
//Inherit from Person class
public class Admin extends Person
{	//Default constructor
	public Admin()
	{
		// use Person constructor  
		super();
	}//Parameterized constructor
	public Admin(String username, String password)
	{
		super(username, password);
	}
}