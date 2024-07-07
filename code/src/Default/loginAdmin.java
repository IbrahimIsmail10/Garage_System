package Default;
// inherit from loggin and Authenticate
public class loginAdmin extends Loggin implements Authenticate{
	private Admin[] admins;
	public loginAdmin() { //Default constructor
		admins = new Admin[2];
		Admin admin1 = new Admin("ahmed","123456");
		Admin admin2 = new Admin("mohamed","123456");
		admins[0] = admin1;
		admins[1] = admin2;
	}
	// override authentication function
	@Override public boolean authentication(String username , String password) {
		for (int i = 0; i < 2; i++) {
			if(admins[i].username.equals(username)) {
				if(admins[i].password.equals(password)) {
					displayApprovalMessage(1);
					return logged = true;
				}
				else {
					displayApprovalMessage(2);
					return logged = false;
				}
			}
		}
		displayApprovalMessage(3);
		return logged = false;
	}
}
