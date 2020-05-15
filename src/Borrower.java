import java.time.LocalDate;
import java.util.ArrayList;

public class Borrower extends Member{
	private ArrayList<Book> currentBorrowedBooks;
	private ArrayList<Book> favouriteList;
	private ArrayList<Book> toReadList;
	private ArrayList<Book> history;
	private boolean ableToBorrow;
	private int numberOfPenalties;
	private LocalDate dateOfLastPenlty;
	
	
	
	
	
	
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
	
	
}