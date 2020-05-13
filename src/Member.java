import java.util.ArrayList;

public class Member 
{
	private String username;
	private String password;

	private ArrayList<Message> PersonalMessages; 
	
	public ArrayList<Message> getPersonalMessages()
	{
	 return PersonalMessages;
	}
	
	public void setPersonalMessage(Message received) 
	{
		PersonalMessages.add(received);
		
		
		
	}
}
