import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LibraryData {
	private ArrayList<Borrower> ListOfBorrowers ;
	private ArrayList<Borrower> PenaltyList ;
	//private ArrayList<Librarian> ListOfLibrarians = new ArrayList<Librarian>();
	private ArrayList<Book> ListOfBooks;
	private ArrayList<Borrower> WithPenalty;
	private ArrayList<BookLending> DelayedBooks;
	private ArrayList<BookLending> BorrowedBooks;
	private ArrayList<Author> Authors;
	
 
    //========Methodoi pou aforoun melh  		
	public void createMembership()//kaleitai apo bibliothhkario
	{  
		String username = JOptionPane.showInputDialog("Onoma kainourgioy xrhsth: ");
		String password = JOptionPane.showInputDialog("Kwdikos kainourgiou xrhsth: ");
		
		Borrower newb=new Borrower(username,password,null,null,null,null,true,0,null,0,null);
		//ehmerwsh listwn;
		Main.librarydata.getListOfBorrowers().add(newb);	
	}
	

	public void terminateMembership(String username)
	{
		Borrower b=Borrower.FindBorrower(username);	    
		if(b!=null) 
		{
			Main.librarydata.getListOfBorrowers().remove(b);	  	
	        //removes borrower from other lists
            if(!b.isAbleToBorrow())
            {
            	Main.librarydata.getPenaltyList().remove(b);
            	//removes borrower from authors lists
            	for(Author author:b.getAuthorsFollowing())
            		author.deleteFollower(b);
        	} 
		    else
		    	System.out.println("O xrhsths de brethhke");
	}
	
	public void Login()//kaleitai otan oxrhsths pathsei to login
	{ 
		Scanner keyboard = new Scanner(System.in);
        boolean login=false;
        boolean islibrarian=false;
    	//protreptiko mhynma apo grafikh diepaffh
    	System.out.println("Doste onoma kai kwdiko");
	    String username=keyboard.nextLine();
        String password=keyboard.nextLine();
       
        Borrower potential=new Borrower(username,password);
       
        while(!login) 
        {
	       if(username.equalsIgnoreCase("Librarian")&&password.equalsIgnoreCase("LibrarianPassword")) 
	       {   
	    	   islibrarian=true;
	    	   login=true;
	       }
	       
	       else if(!username.equalsIgnoreCase("Librarian")) 
	       {
	    	  for(Borrower user:Main.librarydata.getListOfBorrowers()) 
	    	  {
	    		  if(user==potential)
	    		  {
	    			  login=true;
	    			  break;
	    		  }
	    	  }	    	   	    	
	       }
	       else
	    	   System.out.println("Kati den phge kala");
		}
        
        if(islibrarian) 
        	/*dei3e th diepafh bibliothhkariou*/
        else 
        	/*dei3e thn diepafh xrhsth*/
        
	}
   	
   // ======Methodoi gia biblia======	
	public void BookDiscarding(int code)//tha zhtaei to kwdiko tou bibliou
    { 
		Book xbook=Book.findBook(code);
		ListOfBooks.remove(xbook);
		Author author=xbook.getAuthor();//afairei apo th lista ergwn tou syggrafea
		author.removeBook(xbook);
    }
    	
	public ArrayList<Book> searchBook(String Title)
	{   
		ArrayList<Book> blist=new ArrayList<Book>();
		boolean found = false;
		
		for(Book  aBook : ListOfBooks)
		{
			if(aBook.getTitle().equalsIgnoreCase(Title))
			{   
				found=true;
				blist.add(aBook);
			}	
		}
		if(found == false)
			System.out.println("Den uparxei biblio me tetoio titlo.");//grafikhs diepafhs
		return blist;//prepei na emfanizetai h lista me ta biblia.anoigei diepafh apo edw kai tha einia void
	}
	
	public ArrayList<Book> searchByFilter(String WantedCategory)
	{
		ArrayList<Book> wantedCategoryBooks = new ArrayList<Book>();
		for(Book  aBook : ListOfBooks)
		{
			if(aBook.getCategory().equalsIgnoreCase(WantedCategory)) 
			{ 
				wantedCategoryBooks.add(aBook);
			}	
		}
		//prepei na emfanizetai h lista me ta biblia.anoigei diepafh apo edw kai tha einia void
		return wantedCategoryBooks;
    }
    
	public ArrayList<Book> createSuggestions(Borrower borrower)
	{
		ArrayList<Book> suggest=null;
		ArrayList<Book> last3books=new ArrayList<Book>();//books in her history record
		ArrayList<Book> history=borrower.getHistory();
		
		//initialize books
		for(Book abook:history) 
		{
			if(last3books.indexOf(abook) == -1)//checks if books are the same 
				last3books.add(abook);
			
			if(last3books.size() == 3)
				break;	
		}
			
		//collecting read books of same category from one borrower and setting recommendation score
	    if(last3books.size() != 0)
			ArrayList<Book> recs=null;
	    
	   	for(Book rbook:last3books) 
		{
			ArrayList<Borrower> pastborrowers=rbook.getPastBorrowers();
		
		    for(Borrower pastBorrower:pastborrowers)
				for(Book book:pastBorrower.getHistory())  
					if(book.getCategory().equalsIgnoreCase(rbook.getCategory())) //if it is of same genre
					{
						if(recs.indexOf(book) == -1)//and doesn't already exist in the list          
			        	    recs.add(book);
			        	//initial rating
			          	book.setRecommendationscore(0.0);
					  
					    if(pastBorrower.getFavouriteList().indexOf(book)!=-1)//if the book belongs to favorite list it gets extra 0.5 points
				            book.setRecommendationscore(book.getRecommendationscore()+0.5);
		            
					    //extra recommendation points according to its rating
					    float rating=book.getRating();
					
					    //epishs tha mporousame na valoume arnhtiko recscore an to rating isoutai me 1
					    if(rating == 2)
					    	book.setRecommendationscore(book.getRecommendationscore() + 0.1);
					    else if(rating == 3)
					    	book.setRecommendationscore(book.getRecommendationscore() + 0.3);
					    else if(rating == 4)
					    	book.setRecommendationscore(book.getRecommendationscore() + 0.4);
					    else if(rating == 5)
					    	book.setRecommendationscore(book.getRecommendationscore() + 0.5);
								
					    book.setRating(rating);
					}
					else  //if the book already exists
					{
						book.setRecommendationscore(book.getRecommendationscore()+0.1);
						//for each time the book was lent it gets extra recommendation points
					}
		    //allh mia idea tha htan na vazei pontous (0.1-0.2) automata se ola ta vivlia pou anhkoun sto eidos kathe prosfata daneismenou vivliou
		}	
		    
		//Sorting recs by recommendation score after every book is processed
		Collections.sort(recs);
		//add 10 best recommendations to suggest list
		int i=0;
		while(i<=recs.size()-1&&i<=10)
		{   
			suggest.add(recs.get(i));
			i++;
		}
		//0 recommendation points for next time
		for(Book book:recs)
			book.setRecommendationscore(0); 
	    
		return suggest;
	}
	
	
	
	
	//============================setters and getters==============
	public ArrayList<Book> getListOfBooks() 
	{
		return ListOfBooks;
	}
	
public ArrayList<Borrower> getWithPenalty() {
	return WithPenalty;
}



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
}
