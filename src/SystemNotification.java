import java.time.LocalDate;
import java.util.ArrayList;
import java.util.TimerTask;


public class SystemNotification extends TimerTask  {
	
	public static void checkforActions() {
		Warning.checkforWarning();
		Penalty.checkforPenalty();
		Penalty.undoPenalties();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		SystemNotification.checkforActions();
	}


	
	//------------------------------------------------------//
	
	private static class Warning{
		public static void checkforWarning() 
		{
			ArrayList<BookLending> borrowed=Main.librarydata.getBorrowedBooks();
			
			for(BookLending booklending:borrowed)
			{if(booklending.getTimeLeft(LocalDate.now().plusDays(3))==3){//NA FTIA3W THN TIMELEFT
				String text="To vivlio"+booklending.getBook().getTitle()+"prepei na epistrafei se 3 meres";
				Borrower recipient=booklending.getBorrower();
				Message warning=new Message(text,Main.librarian);
				Message.messageToBorrower(recipient, warning);
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
					delayed.add(booklending);
					borrowed.remove(booklending);
					Borrower recipient=booklending.getBorrower();
					recipient.setNumberOfPenalties((recipient.getNumberOfPenalties()+1)%3);
					boolean severe=false;
					if(recipient.getNumberOfPenalties()==0) {
						recipient.setAbleToBorrow(false);
						recipient.setDateOfLastPenlty(LocalDate.now());
                       /**add to withpenalty list**/	
						ArrayList<Borrower> p=Main.librarydata.getWithPenalty();
						if(p.indexOf(recipient)==-1)//if doesn't exist
						{p.add(recipient);}
						severe=true;
					}	
										
					//message to be sent
					String text1="To vivlio"+booklending.getBook().getTitle()+" exei kathysterhsei ,na epistrafei amesa";
					String text2="To vivlio"+booklending.getBook().getTitle()+" exei kathysterhsei ,na epistrafei amesa.Logw epanalambanomenwn kathisterhsewn"
							+ "den mporeite na daneisteite gia 30 meres";
					Message warning=null;
					if(!severe) {warning=new Message(text1,Main.librarian);}else {warning=new Message(text2,Main.librarian);}
					Message.messageToBorrower(recipient, warning);
					
				}
			}
		}
		public static void undoPenalties() {
			ArrayList<Borrower> penalties=Main.librarydata.getWithPenalty();
			for(Borrower b:penalties) {
				if(b.getDateOfLastPenlty().plusDays(14)==LocalDate.now()) { 
				b.setAbleToBorrow(true);
				penalties.remove(b);
				String text="Mporeite na daneisteite 3ana";
				Message inform=new Message(text,Main.librarian);
				Message.messageToBorrower(b, inform);
				}
			}
		}
		
		
		
		
		
	}
	
	

}
