import java.util.ArrayList;

public class Message {
	String message;
	Member sender;
	//constructor
	public Message(String message, Member sender) {
	
		this.message = message;
		this.sender = sender;
		}
		
	public String getMessage() {
		return message;
	}
	public Member getSender() {
		return sender;
	}

	//used by borrower for messages to library
	public static void messageToLibrary(Message messagefromborrower)
	{  
		Main.librarian.setPersonalMessage(messagefromborrower);

	}
	
	//used by librarian for messages to all members.For each member personalized suggestions will be generated
	public static void MessageToAllMembers(Message message) {
		for(Borrower recipient:Main.librarydata.getListOfBorrowers()) 
		{ArrayList<Book> list=Main.librarydata.createSuggestions(recipient);
		String books="Here are some books based on your history:";
		for(Book book:list) {books=books+book.getTitle()+"\n";}//!
		String finalmessage="Library message:"+message.getMessage()+" Your personalized suggestions: "+books;
		Message.messageToSome(recipient,new Message(finalmessage,Main.librarian));
		}
		}
			
	
	//used by librarian for personalized messanges
	public static void messageToSome(Borrower recipient,Message messagefromlibrary)
	{
		recipient.setPersonalMessage(messagefromlibrary);
	}

}
