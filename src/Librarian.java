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
	
	
}
//thn terminate booklending tha thn balw sthn booklending