import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

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
		ArrayList<Borrower> list=Main.librarydata.getPenaltyList();
		ArrayList<BookLending> list2=Main.librarydata.getBorrowedBooks();
		
		if(m.isAbleToBorrow() && !b.getBorrowed() && m.getNumberOfBorrowedBooks()<3)
		{
			BookLending lending= new BookLending(m,b);
			
			list2.add(lending);
			Main.librarydata.setBorrowedBooks(list2);
			m.setNumberOfBorrowedBooks(m.getNumberOfBorrowedBooks()+1);
			b.toggleAvailability();
		}
	}
	
	private void bookMonitoring()
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