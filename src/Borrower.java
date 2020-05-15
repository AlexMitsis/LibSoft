import java.util.ArrayList;

public class Borrower extends Member{
	private ArrayList<Book> currentBorrowedBooks;
	private ArrayList<Book> favouriteList;
	private ArrayList<Book> toReadList;
	private ArrayList<Book> history;
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