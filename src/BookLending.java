import java.time.LocalDate;

public class BookLending 
{
  private Borrower borrower;
  private Book book;
  private int renewalCounter;
  //private int timeLeft;
  private LocalDate dateborrowed;
  
  
  
  
  public  int getTimeLeft(LocalDate now)
  { int diff;
  
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
