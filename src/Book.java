import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Book implements Comparable<Book>{
	private int code;
	private String title;
	private Author author;
	private String category;
	private int year;
	private String language;
	private float rating;
	private String publisher;
	private ArrayList<String> comments;
	private Boolean borrowed = false;
//	private Borrower personWithBook;
	 //private Scanner scanner = new Scanner(System.in);
	//CreateSuggestions uses
		private ArrayList<Borrower> pastBorrowers;
		private double recommendationscore;
	

	
	public Book(int code, String title, Author author, String category, int year, String language, float rating,
				String publisher, ArrayList<String> comments, Boolean borrowed/*, Borrower personWithBook
				*/) {
			super();
			this.code = code;
			this.title = title;
			this.author = author;
			this.category = category;
			this.year = year;
			this.language = language;
			this.rating = rating;
			this.publisher = publisher;
			this.comments = comments;
			this.borrowed = borrowed;
			//this.personWithBook = personWithBook;
			
			this.pastBorrowers = null;
			this.recommendationscore = 0;
		}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public ArrayList<String> getComments() {
		return comments;
	}

	public void setComments(ArrayList<String> comments) {
		this.comments = comments;
	}
	
	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public void viewBook()
	{
		System.out.println(getTitle());
		System.out.println(getAuthor());
		System.out.println(getPublisher());
		System.out.println(getYear());
		System.out.println(getCategory());
		System.out.println(getRating());
		System.out.println(getLanguage());
		/*
		 * edw prepei na emfanistei kai to koumpi
		 * gia ton daneismo tou bibliou
		 * diaforetiki klasi?
		 */
	}
	
	/*public void setBorrower(Borrower person)
	{
		personWithBook = person
		pastBorrowers.add(person)
	}///den xreiazetai ,an nai giati*/
	
	public void requestBook(Borrower person)//ayth ypotithetai kaleitai mono apo ton bibliothhkario,tha phgainei sthn selida tou bibliou
	{   
		this.toggleAvailability();
		if(person.isAbleToBorrow()&&person.getNumberOfBorrowedBooks()<3) {
			//creating a booklending instance
			BookLending bkl=new BookLending(person,this,0,LocalDate.now());
			//updating borrower's info
			person.setNumberOfBorrowedBooks(person.getNumberOfBorrowedBooks()+1);
			//updates list of borrowed books
		Main.librarydata.getBorrowedBooks().add(bkl);//booklending
		Main.librarydata.getListOfBorrowers().add(person);
		
		}
		
	}	
	
	public void toggleAvailability()
	{
		if(!borrowed)
			borrowed = true;
		else 
			borrowed = false;
	}
	
	public void commentBook(Borrower person, String comment)//to sxolio tha einai string se morfh "o xrhsths person.name eggrapse sxolio"
	//epishs tha pernei orisma kai to mhnyma giati ayth h methodos tha kaleitai mesw koumpiou
	{
		
		comments.add(comment);
		System.out.println("O xrhsths " + person.getUsername() + " egrapse sxolio: " + comment);
		
	}
	
	public void rateBook()//den xreiazetai ton person
	{	
		float rate;
		float avgRating;
		float total=0;
		int numOfRates = 0;
		   
		while(true){
			System.out.println("Bathmologia gia to biblio: ");
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			rate=keyboard.nextInt();
			if(rate>=0 && rate<=5){
				total += rate;
				numOfRates++;
				break;
			}
			else
				System.out.println("Parakalw dwste mia egkurh bathmologia(0-5)"); 		
		}
		avgRating = total / numOfRates;
		rating = avgRating;
		System.out.println("H sunolikh bathmologia gia to biblio einai " + rating);
		//Theloume na baloume epilogh na ksanabathmologei to biblio???
	}
//----------------------------------------------------------------------------------------------------------------//
	
	
	public double getRecommendationscore() {
		return recommendationscore;
	}

	public ArrayList<Borrower> getPastBorrowers() {
		return pastBorrowers;
	}

	public void setPastBorrowers(ArrayList<Borrower> pastBorrowers) {
		this.pastBorrowers = pastBorrowers;
	}

	public void setRecommendationscore(double recommendationscore) {
		this.recommendationscore = recommendationscore;
	}

	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		if(this.getRecommendationscore()>o.getRecommendationscore()) {return 1;}
		else if(this.getRecommendationscore()==o.getRecommendationscore()) {return 0;}
		else
		return -1;
	}
}
