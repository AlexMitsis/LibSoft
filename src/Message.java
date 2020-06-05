import java.util.ArrayList;

public class Message {
	String message;
	Member sender;
	Book bookDonated;
	//constructor
	public Message(String message, Member sender) {
	
		this.message = message;
		this.sender = sender;
		}
		
	public Message(Borrower sender, Book bookDonated) {
		this.sender = sender;
		this.bookDonated = bookDonated;
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
	//Static methods are called to send messages
	//used by librarian for messages to all members.For each member personalized suggestions will be generated
	
	public static void MessageToAllMembersWithRecommendations(Message message) {
		for(Borrower recipient:Main.librarydata.getListOfBorrowers()) 
		{ArrayList<Book> list=Main.librarydata.createSuggestions(recipient);
		String books="Here are some books based on your history:";
		for(Book book:list) {books=books+book.getTitle()+"\n";}//!
		String finalmessage="Library message:"+message.getMessage()+" Your personalized suggestions: "+books;
		Message.messageToBorrower(recipient,new Message(finalmessage,Main.librarian));
		}
		}
			
	
	//used by librarian for personalized messanges
	public static void messageToBorrower(Borrower recipient,Message messagefromlibrary)
	{
		recipient.setPersonalMessage(messagefromlibrary);
	}

}
