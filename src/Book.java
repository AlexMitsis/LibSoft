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
	private String description;
	private ArrayList<String> comments;
	private Boolean borrowed = false;
	private ArrayList<Borrower> pastBorrowers;
	private double recommendationscore;
	
	public void viewBook(Member person)
	{
		System.out.println(getTitle());
		System.out.println(getAuthor());
		System.out.println(getPublisher());
		System.out.println(getDescription());
		System.out.println(getYear());
		System.out.println(getCategory());
		System.out.println(getRating());
		System.out.println(getLanguage());
		
		System.out.println("Do you want to borrow this Book? Y/N");
		Scanner input = new Scanner(System.in);
		String option=input.next();
		
		if (option.equals("Y"))
			requestBook(person);
		/*
		 * edw prepei na emfanistei kai to koumpi
		 * gia ton daneismo tou bibliou
		 */
	}
	
	public static Book findBook(int code){
		
		for(Book book:Main.librarydata.getListOfBooks()) {
			if(code==book.getCode()){return book;}
		}	return null;
	}
	
	public void requestBook(Member person) {
		  String req = ("The user" + person.getUsername() + "wants me.");
		  Message requestMessage = new Message(req,person);
			Message.messageToLibrary(requestMessage);
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
	
	@Override
	public int compareTo(Book o) {
		// TODO Auto-generated method stub
		if(this.getRecommendationscore()>o.getRecommendationscore()) {return 1;}
		else if(this.getRecommendationscore()==o.getRecommendationscore()) {return 0;}
		else
		return -1;
	}
	
	
//----------------------------------------getters setters and constructors---------------------------------------------------------//
	
	
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

	public Boolean getBorrowed() 
	{
		return borrowed;
	}

	public void setBorrowed(Boolean borrowed) 
	{
		this.borrowed = borrowed;
	}

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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String Description) {
		this.description = description;
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
	
}
