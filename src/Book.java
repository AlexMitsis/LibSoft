import java.util.*;

public class Book {
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
	private Borrower personWithBook;
	private Scanner scanner = new Scanner(System.in);
	private ArrayList<Borrower> pastBorrowers;
	
	
	public Book(int code, String title, Author author, String category, int year, String language, float rating, String publisher)
	{
		this.code = code;
		this.title = title;
		this.author = author;
		this.category  =category;
		this.year = year;
		this.language = language;
		this.rating = rating;
		this.publisher = publisher;
		comments = new ArrayList<String>();
		pastBorrowers = new ArrayList<Borrower>;
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
	
	/*public void borrowBook(Borrower person)
	{
		personWithBook = person
		pastBorrowers.add(person)
	}*/
	
	public void toggleAvailability()
	{
		if(borrowed = false)
			borrowed = true;
		else 
			borrowed = false;
	}
	
	public void commentBook(Member person, String comment)//to sxolio tha einai string se morfh "o xrhsths person.name eggrapse sxolio"
	//epishs tha pernei orisma kai to mhnyma giati ayth h methodos tha kaleitai mesw koumpiou
	{
		
		comments.add(comment);
		System.out.println("O xrhsths " + person.name + " egrapse sxolio: " + comment);
		
	}
	
	public void rateBook()//den xreiazetai ton person
	{
		float avgRating;
		float total=0;
		int numOfRates = 0;
		   
		System.out.println("Bathmologia gia to biblio: ");
		rate = scanner.nextFloat();
		
		while(TRUE){
			if(rate>=0 || rate<=5){
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
		 
	}
}
