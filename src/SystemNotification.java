import java.util.ArrayList;
import java.time.LocalDate;

public class SystemNotification {
	
	public static void checkforActions() {
		Warning.checkforWarning();
		Penalty.checkforPenalty();
		Penalty.undoPenalties();
	}

	private static class Warning{
		public static void checkforWarning() 
		{
			ArrayList<BookLending> borrowed=Main.librarydata.getBorrowedBooks();
			
			for(BookLending booklending:borrowed)
			{if(booklending.getTimeLeft(LocalDate.now().plusDays(3))==3){//NA FTIA3W THN TIMELEFT
				String text="To vivlio"+booklending.getBook().getTitle()+"prepei na epistrafei se 3 meres";
				Borrower recipient=booklending.getBorrower();
				Message warning=new Message(text,Main.librarian);
				Message.messageToSome(recipient, warning);
			}
				
			}
			
		}
		
	                              }
	private static class Penalty{
		public static void checkforPenalty()
		{  
			ArrayList<BookLending> borrowed=Main.librarydata.getBorrowedBooks();
			ArrayList<BookLending> delayed=Main.librarydata.getDelayedBooks();
			for(BookLending booklending:borrowed) {
				if(booklending.getTimeLeft(LocalDate.now())==-1) {
					delayed.add(booklending);/*******/
					borrowed.remove(booklending);/******/
					//implementing the right penalty
					Borrower recipient=booklending.getBorrower();
					recipient.setNumberOfPenalties((recipient.getNumberOfPenalties()+1)%3);
					boolean severe=false;
					if(recipient.getNumberOfPenalties()==0) {
						recipient.setAbleToBorrow(false);
						recipient.setDateOfLastPenlty(LocalDate.now());
                       /**add to withpenalty list**/			
						severe=true;
					}	
										
					//message to be sent
					String text1="To vivlio"+booklending.getBook().getTitle()+" exei kathysterhsei ,na epistrafei amesa";
					String text2="To vivlio"+booklending.getBook().getTitle()+" exei kathysterhsei ,na epistrafei amesa.Logw epanalambanomenwn kathisterhsewn"
							+ "den mporeite na daneisteite gia 30 meres";
					Message warning=null;
					if(!severe) {warning=new Message(text1,Main.librarian);}else {warning=new Message(text2,Main.librarian);}
					Message.messageToSome(recipient, warning);
					
				}
			}
		}
		public static void undoPenalties() {
			
			
		}
		
		
		
		
		
	}
	

}
