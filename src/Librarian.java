import java.time.LocalDate;
import java.util.ArrayList;

public class Librarian extends Member
{

	
	public Librarian(String username, String password)
	{
		super(username, password);
		
	}
	
	
	public void addBook(Book aBook)
	{
		ArrayList<Book> list=Main.librarydata.getListOfBooks();
		Author booksAuthor=aBook.getAuthor();
		
		booksAuthor.updateListOfBooks(aBook);
		list.add(aBook);
		Main.librarydata.setListOfBooks(list);

	}
	
	
	public void bookDiscarding(Book aBook)
	{
		ArrayList<Book> list=Main.librarydata.getListOfBooks();
		
		list.remove(aBook);
		Main.librarydata.setListOfBooks(list);
	}
	
	
	public void terminateMembership(Borrower b)
	{
		ArrayList<Borrower> list=Main.librarydata.getListOfBorrowers();
		ArrayList<Borrower> list2=Main.librarydata.getPenaltyList();
		ArrayList<Author> list3=b.getAuthorsFollowing();
		
		list.remove(b);
		Main.librarydata.setListOfBorrowers(list);
		if(list2.contains(b))
			{
				list2.remove(b);
				Main.librarydata.setPenaltyList(list2);
			}
		for(Author a:list3)
		{
			ArrayList<Borrower> list4=a.getListOfFollowers();
			list4.remove(b);
			a.setListOfFollowers(list4);
		}
		
	}
	
	public void setBookBorrowed(Book b,Borrower m)
	{
		ArrayList<BookLending> list=Main.librarydata.getBorrowedBooks();
		
		if(m.isAbleToBorrow() && !b.getBorrowed() && m.getNumberOfBorrowedBooks()<3)
		{
			BookLending lending= new BookLending(m,b,0,LocalDate.now());
			
			list.add(lending);
			Main.librarydata.setBorrowedBooks(list);
			m.setNumberOfBorrowedBooks(m.getNumberOfBorrowedBooks()+1);
			b.toggleAvailability();
		}
		else
			System.out.println("Unable to borrow at the moment.Book is not availiable, you may have a penalty or you already have borrowed the maximum number of books");
	}
	/*public void RenewLendingPeriod(String username,int bookcode)//upotithetai oti bazei swsta ,kaleitai apo ton bibliothhkario
	  {BookLending bl=BookLending.findBookLending(username, bookcode);
	   if(bl!=null) {
	     if(bl.getRenewalCounter()<3) {
	    	 bl.setRenewalCounter(bl.getRenewalCounter()+1);
	    	 bl.setDateborrowed(LocalDate.now());
	     }//o xrhsths ews 3 fores tha to daneistei
	     else {
	    	 System.out.println("Den epitrepetai epipleon daneismos");
	     }}
	   else System.out.println("De brethke o daneismos");
	  }
	*/
	/*  public void terminateBookLending(String username,int bookcode)
  {BookLending bl=BookLending.findBookLending(username, bookcode);
  if(bl!=null) {
	  //enhmerwsh olwn twn listwn

	//SThn terminate booklending prepei na enhmerwnei to 	private ArrayList<Borrower> pastBorrowers; tou kathe book
  }
  else System.out.println("De brethke o daneismos");
  }
*/
	
	public void bookMonitoring()
	{
		ArrayList<BookLending> list=Main.librarydata.getBorrowedBooks();
		LocalDate d=LocalDate.now();
		
		for(BookLending bl:list)
		{
			System.out.println(bl.getBorrower().getUsername()+" has borrowed "+bl.getBook().getTitle()+","+bl.getBook().getCode()+" Time left: "+bl.getTimeLeft(d));
		}
	}
	
}
//thn terminate booklending tha thn balw sthn booklending