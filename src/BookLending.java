import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookLending 
{
  private Borrower borrower;
  private Book book;
  private int renewalCounter;//when a book is lent
  //private int timeLeft;
  private LocalDate dateborrowed;
  
  
  
  
  public  long getTimeLeft(LocalDate now)
  { long diff;
    diff= this.dateborrowed.until(now,ChronoUnit.DAYS);
    return diff;
  }
    
  public void RenewLendingPeriod(Borrower b)
  {
    
  }

public LocalDate getDateborrowed() {
	return dateborrowed;
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
  
  
}
