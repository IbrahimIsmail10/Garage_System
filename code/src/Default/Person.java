package Default;

public abstract class Person{
	public String username = "";
	public String password = "";
	public Person(){ //Default constructor
		username = "";
		password = "";
	}//Parameterized constructor
	public Person(String username, String password){
		this.username = username;
		this.password = password;
	}
}