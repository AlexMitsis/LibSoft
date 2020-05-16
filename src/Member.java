import java.util.ArrayList;

public class Member 
{
	private String username;
	private String password;

	private ArrayList<Message> PersonalMessages; 
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Member(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public ArrayList<Message> getPersonalMessages()
	{
	 return PersonalMessages;
	}
	
	public void setPersonalMessage(Message received) 
	{
		PersonalMessages.add(received);
		
		
		//prosthhkh & afairesh mesw ths get ,h set allazei olo ton pinaka
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	
}
