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
