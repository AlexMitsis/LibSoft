import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
		//CONSTRUCTOR
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
	
	public void setListOfFollowingAuthors(Author x) {
		x.addFollower(this);
	}
	
	public  void addToReadList(Book b) {
		toReadList.add(b);
	}
	
	public void addToFavourites(Book b) {
		favouriteList.add(b);
	}
	
	public  void printToReadList() {
		for (Book b:toReadList) {
			System.out.println(b.getTitle());
		}
	}
	
	public  void printFavouritesList() {
		for (Book b:favouriteList) {
			System.out.println(b.getTitle());
		}
	}
	
	public  void donateBook() {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Type the information of the book you want to donate");
    	
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
    	
    	Book donatedBook = new Book(code,title,author,category,year,language,rating,publisher,null,false);
		Message donationMessage = new Message(this,donatedBook);
		Message.messageToLibrary(donationMessage);
	}
	
	public  void menuBorrower() {
		int option;
		
		do{
		    System.out.println("Welcome");
		    System.out.println("1. Search a Book by title");
		    System.out.println("2. Search by category");
		    System.out.println("3. Donate Book");
		    System.out.println("4. See Favourites List");
		    System.out.println("5. See To Read List");
		  
		    System.out.println("6. Quit");
		    
		    
		    Scanner input = new Scanner(System.in);
		    option = input.nextInt();


		    switch(option){
		   
		    case 1:
		    	{System.out.println("Search a Book");
		    	
		    	System.out.println("Enter booktitle");
		        String title=input.next();
		        LibraryData.searchBook(title);
		    	}
		        break;
		    case 2:
		    	System.out.println("Enter category");
		    
		        String category=input.next();
		        LibraryData.searchByFilter(category);
		        break;
		    case 3:
		        this.donateBook();
		        break;
		    case 4:
		    	this.printFavouritesList();
		        break;
		    case 5:
		    	this.printToReadList();
		        break;
		   
		    case 6:
		        System.out.println("Quitting");
		        break;
		    }

		    } while(option<=0 && option >6);
	}
	
	
	
	//getters & setters
	
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