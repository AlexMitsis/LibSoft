import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookLending implements Comparable<BookLending>
{
  private Borrower borrower;
  private Book book;
  private int renewalCounter;//when a book is lent
  //private int timeLeft;
  private LocalDate dateborrowed;
  
  
public  long getTimeLeft(LocalDate now)//before the book shall be returned
  { long diff;
    diff= this.dateborrowed.until(now,ChronoUnit.DAYS);
    return diff;
  }
//--------------Lending Actions-------------------------------//

public void initiateBookLending(String username,int bookcode)//ayth ypotithetai kaleitai mono apo ton bibliothhkario,tha phgainei sthn selida tou bibliou
{   Book book=Book.findBook(bookcode);                       //ypotithetai oti ginetai me fysikh paousia

    Borrower borrower=Borrower.FindBorrower(username);
    
	book.toggleAvailability();
	if(borrower.isAbleToBorrow()&&borrower.getNumberOfBorrowedBooks()<3) {
		//creating a booklending instance
		BookLending bkl=new BookLending(borrower,book,0,LocalDate.now());
		//updating borrower's info
		borrower.setNumberOfBorrowedBooks(borrower.getNumberOfBorrowedBooks()+1);
		//updates list of borrowed books
	Main.librarydata.getBorrowedBooks().add(bkl);//booklending
	Main.librarydata.getListOfBorrowers().add(borrower);
	
	}
	
}	
    
  public void RenewLendingPeriod(String username,int bookcode)//upotithetai oti bazei swsta ,kaleitai apo ton bibliothhkario
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
  
  
//SThn terminate booklending prepei na enhmerwnei to 	private ArrayList<Borrower> pastBorrowers; tou kathe book
  public void terminateBookLending(String username,int bookcode)
  {BookLending bl=BookLending.findBookLending(username, bookcode);
  if(bl!=null) {
	  //enhmerwsh olwn twn listwn

	//SThn terminate booklending prepei na enhmerwnei to 	private ArrayList<Borrower> pastBorrowers; tou kathe book
  }
  else System.out.println("De brethke o daneismos");
  }

  public static BookLending findBookLending(String username,int bookcode)//briskei to daneismo
  { 
 Borrower borrower=Borrower.FindBorrower(username);
 Book book=Book.findBook(bookcode);
 BookLending searching=new BookLending(borrower,book);
 for(BookLending bl:Main.librarydata.getBorrowedBooks())
 {
	 if(searching==bl) {return bl;}
 }
  
	  return null;
  }
  
  
  
 //---------getters ,setters and constructors--------------------// 
  
  
  
  public BookLending(Borrower borrower, Book book) {
	super();
	this.borrower = borrower;
	this.book = book;
}


public BookLending(Borrower borrower, Book book, int renewalCounter, LocalDate dateborrowed) {
	super();
	this.borrower = borrower;
	this.book = book;
	this.renewalCounter = renewalCounter;
	this.dateborrowed = dateborrowed;
}
  
public LocalDate getDateborrowed() {
	return dateborrowed;
}

public int getRenewalCounter() {
	return renewalCounter;
}


public void setRenewalCounter(int renewalCounter) {
	this.renewalCounter = renewalCounter;
}


public void setDateborrowed(LocalDate dateborrowed) {
	this.dateborrowed = dateborrowed;
}

public Book getBook() {
	return book;
}

public void setBook(Book book) {
	this.book = book;
}

public Borrower getBorrower() {
	return borrower;
}

public void setBorrower(Borrower borrower) {
	this.borrower = borrower;
}


@Override
public int compareTo(BookLending o) {
	if(this.borrower==o.getBorrower()&&this.book==o.getBook()) {
	return 0;}
	else return -1;
}
  
  
}
