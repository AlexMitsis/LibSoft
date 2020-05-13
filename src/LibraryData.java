import javax.swing.*;
import java.util.*;

public class LibraryData {
	private ArrayList<Borrower> ListOfBorrowers = new ArrayList<Borrower>();
	private ArrayList<Borrower> PenaltyList = new ArrayList<Borrower>();
	private ArrayList<Librarian> ListOfLibrarians = new ArrayList<Librarian>();
	private ArrayList<Book> ListOfBooks = new ArrayList<Book>();
	
	private ArrayList<Book> DelayedBooks = new ArrayList<Book>();
	private ArrayList<BookLending> BorrowedBooks = new ArrayList<BookLending>();
	private ArrayList<Author> Authors = new ArrayList<Author>();
	
	
	
	
	public ArrayList<Book> getListOfBooks() 
	{
		return ListOfBooks;
	}
	
    public void setListOfBooks(ArrayList<Book> listOfBooks) 
    {
		ListOfBooks = listOfBooks;
	}

	public void BookDiscarding(Book b)
    {
    	/*public void BookDiscarding(String Title){
    	 *
    	   for(Book : book : ListOfBooks)
    	 * {
    	 * 		if(book.title=Title)
    	 * 		{      
    	 * 			     ListOfBooks.delete(book);
    	 *      }
    	 * }
    	 */
    }
	
	private Member createMembership()//void?
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
	
	public void terminateMembership(Borrower b)
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
	 * }
	 */
		
	
	
	public int searchBook(String Title)
	{
		for(Book : aBook : ListOfBooks)
		{
			if(aBook.title=Title)
				return aBook.code;
			//System.out.println("There is no book with this title.");
		}	
	}
	
	public ArrayList<Book> searchByFilter(String WantedCategory)
	{
		private ArrayList<Book> wantedCategoryBooks = new ArrayList<Book>;
		for(Book : aBook : ListOfBooks)
		{
			if(aBook.category=WantedCategory) {
				wantedCategoryBooks.add(aBook);
			}	
		}
		for(Book : Abook : wantedCategoryBooks)
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
	
	public ArrayList<Book> CreateSuggetsions(Borrower m)
	{
		/*
		 * m.printFavoriteList();
		 *
		 * etsi opws to skeftika itan na 
		 * tupwsoume tin favoritelist tou borrower
		 * kai 5 apo auta ta biblia na ta prosthesoume
		 * stin lista twn protasewn.TH lista auth prepei
		 * na thn dhmiourghsoume?Nai/oxi an ginetai etsi
		 * 
		 */
		
		
	}
	
	/*methodos isuser den uparxei
	 * 
	 * 
	*/
	
	
	
}
