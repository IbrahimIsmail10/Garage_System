package Default;
//inherit from loggin and Authenticate
public class LoginOwner extends Loggin implements Authenticate{
	private Owner[] owners;
	public LoginOwner(){ //Default constructor
		owners = new Owner[4];
		Owner ob1 = new Owner("hamed","123456");
		Owner ob2 = new Owner("ibrahim","123456");
		Owner ob3 = new Owner("nada","123456");
		Owner ob4 = new Owner("fatma","123456");
		owners[0] = (ob1);
		owners[1] = (ob2);
		owners[2] = (ob3);
		owners[3] = (ob4);
	}
	// override authentication function
	@Override public boolean authentication(String username , String password) {
		for (int i = 0; i < 4; i++)
		{
			if (owners[i].username.equals(username))
			{
				if (owners[i].password.equals(password))
				{
					displayApprovalMessage(1);
					return logged = true;
				}else
				{
					displayApprovalMessage(2);
					return logged = false;
				}
			}
		}
		displayApprovalMessage(3);
		return logged = false;
	}
}
