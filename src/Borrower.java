import java.time.LocalDate;
import java.util.ArrayList;

public class Borrower extends Member implements Comparable<Borrower>{
    public final int MAxBorrowedBooks=3;
	private ArrayList<Book> currentBorrowedBooks;
	private ArrayList<Book> favouriteList;
	private ArrayList<Book> toReadList;
	private ArrayList<Book> history;
	private boolean ableToBorrow;
	private int numberOfPenalties;
	private LocalDate dateOfLastPenlty;
	private int numberOfBorrowedBooks;
	private ArrayList<Author> authorsFollowing;
		
	public Borrower(String username, String password, ArrayList<Book> currentBorrowedBooks,
			ArrayList<Book> favouriteList, ArrayList<Book> toReadList, ArrayList<Book> history, boolean ableToBorrow,
			int numberOfPenalties, LocalDate dateOfLastPenlty, int numberOfBorrowedBooks, ArrayList<Author> authorsFollowing) {
		super(username, password);
		this.currentBorrowedBooks = currentBorrowedBooks;
		this.favouriteList = favouriteList;
		this.toReadList = toReadList;
		this.history = history;
		this.ableToBorrow = ableToBorrow;
		this.numberOfPenalties = numberOfPenalties;
		this.dateOfLastPenlty = dateOfLastPenlty;
		this.numberOfBorrowedBooks = numberOfBorrowedBooks;
		 this.authorsFollowing=authorsFollowing;
	}
	
	public static Borrower FindBorrower(String username) {//mono gia to systhma
		ArrayList<Borrower> list=Main.librarydata.getListOfBorrowers();
	
	        for(Borrower b:list) {if(b.getUsername().equalsIgnoreCase(username))
	 {return b;}}
	 return null;
	 }
	
	public ArrayList<Author> getAuthorsFollowing() {
		return authorsFollowing;
	}



	public void setAuthorsFollowing(ArrayList<Author> authorsFollowing) {
		this.authorsFollowing = authorsFollowing;
	}



	public int getMAxBorrowedBooks() {
		return MAxBorrowedBooks;
	}

	public int getNumberOfBorrowedBooks() {
		return numberOfBorrowedBooks;
	}
	public void setNumberOfBorrowedBooks(int numberOfBorrowedBooks) {
		this.numberOfBorrowedBooks = numberOfBorrowedBooks;
	}
	public LocalDate getDateOfLastPenlty() {
		return dateOfLastPenlty;
	}
	public void setDateOfLastPenlty(LocalDate dateOfLastPenlty) {
		this.dateOfLastPenlty = dateOfLastPenlty;
	}
	public int getNumberOfPenalties() {
		return numberOfPenalties;
	}
	public void setNumberOfPenalties(int numberOfPenalties) {
		this.numberOfPenalties = numberOfPenalties;
	}
	public boolean isAbleToBorrow() {
		return ableToBorrow;
	}
	public void setAbleToBorrow(boolean ableToBorrow) {
		this.ableToBorrow = ableToBorrow;
	}
	public ArrayList<Book> getHistory() {
		return history;
	}
	public ArrayList<Book> getCurrentBorrowedBooks() {
		return currentBorrowedBooks;
	}
	public void setCurrentBorrowedBooks(ArrayList<Book> currentBorrowedBooks) {
		this.currentBorrowedBooks = currentBorrowedBooks;
	}
	public ArrayList<Book> getFavouriteList() {
		return favouriteList;
	}
	public void setFavouriteList(ArrayList<Book> favouriteList) {
		this.favouriteList = favouriteList;
	}
	public ArrayList<Book> getToReadList() {
		return toReadList;
	}
	public void setToReadList(ArrayList<Book> toReadList) {
		this.toReadList = toReadList;
	}
	public void setHistory(ArrayList<Book> history) {
		this.history = history;
	}

	public Borrower(String username, String password) {
		super(username, password);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int compareTo(Borrower o) {
		// TODO Auto-generated method stub
		if(this.getUsername().equalsIgnoreCase(o.getUsername())&&this.getPassword().equalsIgnoreCase(o.getPassword()))
		{return 0;}else return -1;
	}
	
	
}