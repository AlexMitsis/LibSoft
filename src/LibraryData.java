import javax.swing.*;
import java.util.*;

public class LibraryData {
	private ArrayList<Borrower> ListOfBorrowers ;
	private ArrayList<Borrower> PenaltyList ;
	//private ArrayList<Librarian> ListOfLibrarians = new ArrayList<Librarian>();
	private ArrayList<Book> ListOfBooks;
	private ArrayList<Borrower> WithPenalty;
	private ArrayList<BookLending> DelayedBooks;
	private ArrayList<BookLending> BorrowedBooks;
	private ArrayList<Author> Authors;
	

       
    	public ArrayList<Book> getListOfBooks() 
    	{
    		return ListOfBooks;
    	}
    	
		public ArrayList<Borrower> getWithPenalty() {
			return WithPenalty;
		}

		public void setWithPenalty(Borrower withPenalty) {
			WithPenalty.add(withPenalty);/********/		}

		public void setListOfBooks(ArrayList<Book> listOfBooks) 
    	{
		ListOfBooks = listOfBooks;
    	}

   public ArrayList<Borrower> getListOfBorrowers() {
		return ListOfBorrowers;
	}

	public void setListOfBorrowers(ArrayList<Borrower> listOfBorrowers) {
		ListOfBorrowers = listOfBorrowers;
	}	
	
	      
	
    		
    		 public void setDelayedBooks(ArrayList<BookLending> delayedBooks) {
		DelayedBooks = delayedBooks;
	}

			public ArrayList<BookLending> getDelayedBooks() {
				return DelayedBooks;
			}

			public ArrayList<Borrower> getPenaltyList() {
    			return PenaltyList;
    		}

    		public void setPenaltyList(ArrayList<Borrower> penaltyList) {
    			PenaltyList = penaltyList;
    		}

   
    		public ArrayList<BookLending> getBorrowedBooks() {
    			return BorrowedBooks;
    		}

    		public void setBorrowedBooks(ArrayList<BookLending> borrowedBooks) {
    			BorrowedBooks = borrowedBooks;
    		}

    		public ArrayList<Author> getAuthors() {
    			return Authors;
    		}

    		public void setAuthors(ArrayList<Author> authors) {
    			Authors = authors;
    		}
    		
    		
    		
    		
    		public void BookDiscarding(int Code)
    	    { //ψαχνει το βιβλιο βασει σειριακου book.code
    	    	
    	    	  for(Book  book : ListOfBooks)
    	    	  {
    	    	 	if(book.code=Code){
    	    	  		ListOfBooks.delete(book); 
    	    	  		
    	    	  }
    	    	 
    	    }}
    		
	private void createMembership()//void?nai
	{
		String username = JOptionPane.showInputDialog("Onoma kainourgioy xrhsth: ");
		String password = JOptionPane.showInputDialog("Kwdikos kainourgiou xrhsth: ");
		
		String ans = JOptionPane.showInputDialog("Gia bibliothikario patiste 1, gia daneizomeno patiste 2: ");
		int answer = Integer.parseInt(ans);
		if(answer==2) {
			String pen = JOptionPane.showInputDialog("Posa penalties exei?: ");
			int penalties = Integer.parseInt(pen);
			Borrower newBorrower = new Borrower(username, password, penalties);
			ListOfBorrowers.add(newBorrower);
		}else {
			Librarian newLibrarian = new Librarian(username, password);
			ListOfLibrarians.add(newLibrarian);
		}
		
	}
	
	public void terminateMembership(Borrower b) {
	/*public void terminateMembership(String Username)
	 * {
	 * 		for(Borrower : aBorrower : ListOfBorrowers)
	 *  	{
	 *  		if(aBorrower.username=Username)
	 *  		{
	 *  			ListOfBorrowers.delete(aBorrowerr);
	 *  			System.out.println("H diagrafh epiteuxthi!"); 
	 * 			}
	 * 		}
	 * 
	 * 		for(Borrower : borrower : PenaltyList)
	 * 		{
	 * 			if(borrower.username=Username)
	 * 			{
	 * 				PenaltyList.delete(borrower);
	 * 				System.out.prinyln("H diagrafh epiteuxthi!)"
	 * 			}
	 * 		}
	 * 
	 */ }
	 
		
	
	//ψαχνει βασει ονοματος ,επιστρεφει το αντικειμενο ολοκληρο public book,γιατι εβαλες ιντ;
	//ebala int gia na epistrefei ton kwdiko tou bibliou
	public Book searchBook(String Title)
	{
		boolean found = false;
		
		for(Book  aBook : ListOfBooks)
		{
			if(aBook.title=Title){ 
				return aBook;
				found = true;
			}	
		}
		if(found == false)
			System.out.println("Den uparxei biblio me tetoio titlo.");
	}
	
	public ArrayList<Book> searchByFilter(String WantedCategory)
	{
		 ArrayList<Book> wantedCategoryBooks = new ArrayList<Book>();
		for(Book  aBook : ListOfBooks)
		{
			if(aBook.category=WantedCategory) { ///DEN sygkrinei me = kai xreiazontai gettersss
				wantedCategoryBooks.add(aBook);
			}	
		}
		for(Book  Abook : wantedCategoryBooks)
		{
			System.out.println(Abook.getTitle());
			System.out.println(Abook.getCategory());
			
		}
		
      }
	public Member Login(String username, String password)
	{
		Member member = new Member(username, password);
		if(isuser(member)) 
			return member;
		
		else
			System.out.println("Something happened.Please try again");
		
	}
	
	
	


	//---------------------------------------------------------------------------------------------------------------------------------------------------//
	public ArrayList<Book> createSuggestions(Borrower m)
	{
		ArrayList<Book> suggest=null;
		ArrayList<Book> readbooks=null;
		ArrayList<Book> history=m.getHistory();
		int size=history.size();//size of borrowers history
		
		//--------initialize books---------------------//checks if books are the same
		if(size==0) {suggest=null;}
		else if(size<=2) {int i=0;
		   readbooks.add(history.get(i));
		   i++;
		   if(history.get(i).getCode()!=history.get(i-1).getCode())
		   {readbooks.add(history.get(i));
		   i++;}}
		else { int i=0;
			   readbooks.add(history.get(i));
			   i++;
			   if(history.get(i).getCode()!=history.get(i-1).getCode())
			   {readbooks.add(history.get(i));
			   i++;}
			   if(i==2) {
				  if(history.get(i).getCode()!=history.get(i-1).getCode()&&history.get(i).getCode()!=history.get(i-2).getCode())
			         {readbooks.add(history.get(i));
			           i++;}}                                          
			   else {
				   if(history.get(i).getCode()!=history.get(i-1).getCode())
			          {readbooks.add(history.get(i));
			           i++;}
				   
			   }
			 }
	
		
		//------------collecting read books of same category from one borrower and setting recommendation score--------------------//
	   ArrayList<Book> recs=null;
	   for(Book rbook:readbooks) {
		ArrayList<Borrower> pastborrowers=rbook.getPastBorrowers();
		
		    for(Borrower pastBorrower:pastborrowers)
		    {
		   
		
			for(Book book:pastBorrower.getHistory())
			{   
				if(book.getCategory().equalsIgnoreCase(rbook.getCategory())) //if it is of same genre
				{
					if(recs.indexOf(book)==-1)                               //and doesn't already exist in the list
					{          
			          recs.add(book);
			        //initial rating
			          book.setRecommendationscore(0.0);
					  
					 if(pastBorrower.getFavouriteList().indexOf(book)!=-1)          //if the book belongs to favorite list it gets extra 0.5 points
					  {
				      book.setRecommendationscore(book.getRecommendationscore()+0.5);}
					 
		            
					//extra recommendation points according to its rating
					float rating=book.getRating();
					
					 if(rating>=2 && rating<3) {book.setRecommendationscore(book.getRecommendationscore()+0.05);}
					else if(rating>=3 && rating<4) { book.setRecommendationscore(book.getRecommendationscore()+0.4);}
					else if(rating>=4 && rating<=5) { book.setRecommendationscore(book.getRecommendationscore()+0.5);}
								
					book.setRating(rating);
					}
					else                                                   //if the book already exists
					{
						book.setRecommendationscore(book.getRecommendationscore()+0.1);//for each time the book was lent it gets extra recommendation points
					}
				}
		    }	
		
		
		    }}
		   //------------------------------------------------------------------------------------------------------------------// 
		    
		  //-----------------------Sorting recs by recommendation score after every book is processed-----------------//
		    
		   Collections.sort(recs);
		   //add best recommendations to suggest list
		   int i=0;
		   while(i<=recs.size()-1&&i<=10)
		   {   suggest.add(recs.get(i));
			   i++;
		   }
		   //0 recommendation points for next time
		   for(Book book:recs) {book.setRecommendationscore(0);}
		   
	   
		   
		    
		    
		    
		return suggest;}
}
