import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Librarian extends Member
{

	
	public Librarian(String username, String password)
	{
		super(username, password);
		
	}
	
	public void menuLibrarian() {
		int option;
		
		do{
		    System.out.println("Execute a task");
		    System.out.println("1. Add a Book");
		    System.out.println("2. Discard a Book");
		    System.out.println("3. Terminate Membership");
		    System.out.println("4. Set a Book Borrowed");
		    System.out.println("5. Terminate a Book Lending");
		    System.out.println("6. Monitor all the books");
		    System.out.println("7. Quit");
		    
		    
		    Scanner input = new Scanner(System.in);
		    option = input.nextInt();


		    switch(option){
		   
		    case 1:
		    	{System.out.println("Adding a Book");
		    	
		    	System.out.println("Enter bookcode");
		    	int code=input.nextInt();
		    	System.out.println("Enter title");
		    	String title=input.next();
		    	System.out.println("Enter author");
				String author_name=input.next();
				Author author= new Author(author_name);
				System.out.println("Enter category");
				String category=input.next();
				System.out.println("Enter year");
				int year=input.nextInt();
				System.out.println("Enter language");
				String language=input.next();
				System.out.println("Enter rating");
				float rating=input.nextFloat();
				System.out.println("Enter publisher");
				String publisher=input.next();
				
				System.out.println("Enter number of comments");
				int numberOfComments = input.nextInt();
			    ArrayList<String> comments = new ArrayList<String>();
			    for (int i = 0; i< numberOfComments; i++){
			    	System.out.println("Enter comment");
			    	String comment=input.next();
			    	comments.add(comment);
			    }
			    System.out.println("Enter if book is borrowed");
				boolean borrowed=input.nextBoolean();
				
		    	
		    	Book aBook = new Book(code,title,author,category,year,language,rating,publisher,comments,borrowed);
		    	
		    	addBook(aBook);
		    	}
		        break;
		    case 2:
		    	System.out.println("Enter bookcode");
		    	int code=input.nextInt();
		    	Book aBook=Book.findBook(code);
		    	bookDiscarding(aBook);
		        System.out.println("Discarding the Book");
		        break;
		    case 3:
		    	System.out.println("Enter name");
		    	String username=input.next();
		    	Borrower b = Borrower.FindBorrower(username);
		        System.out.println("Terminating the Membership");
		        break;
		    case 4:
		    	System.out.println("Enter bookcode");
		    	int code1=input.nextInt();
		    	Book aBook1=Book.findBook(code1);
		    	aBook1.toggleAvailability();
		        System.out.println("Setting the Book Borrowed");
		        break;
		    case 5:
		    	System.out.println("Enter bookcode");
		    	int bookcode=input.nextInt();
		    	System.out.println("Enter borrower's name");
		    	String username1=input.next();
		    	BookLending aBl=BookLending.findBookLending(username1,bookcode);
		        System.out.println("Terminating a Book Lending");
		        break;
		    case 6:
		    	bookMonitoring();
		        System.out.println("Monitoring Books");
		    case 7:
		        System.out.println("Quitting");
		        break;
		    }

		    } while(option<=0 && option >8);
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
	public void terminateBookLending(BookLending abooklending)
	{
		ArrayList<BookLending> list1=Main.librarydata.getBorrowedBooks();
		ArrayList<BookLending> list2=Main.librarydata.getDelayedBooks();
		
		list1.remove(abooklending);
		if(list2.contains(abooklending))
			{
				list2.remove(abooklending);
				Main.librarydata.setDelayedBooks(list2);
			}
		
		Main.librarydata.setBorrowedBooks(list1);
		
	}
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