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
	
	public void requestBook(Member person)
	{
		/*
		 * 
		 * epeidi einai me gui tin afisa etsi
		 */
	}
	
	public void toggleAvailability()
	{
		if(borrowed = false)
			borrowed = true;
	}
	
	public void commentBook(Member person)//to sxolio tha einai string se morfh "o xrhsths person.name eggrapse sxolio"
	//epishs tha pernei orisma kai to mhnyma giati ayth h methodos tha kaleitai mesw koumpiou
	{//Xwris orisma to person?Nomizw den thelei orisma
		/*
		
		System.out.println("Sxolia: ");
		String commentary = scanner.nextLine();
		
		comments.add(commentary);
		*/
		
	}
	
	public void rateBook(Member person)//den xreiazetai ton person
	{
		/*
		 * public void rateBook(float rate)
		 * {
		 * 		float[]  avgRating; 
		 * 		float total=0;
		 *  
		 * 		if(rate>=0 && rate<=5) {
		 * 			rating = rate;
		 *      	avgRating.add(rate);
		 *      }
		 * 		else
		 * 			System.out.println("Parakalw balte mia swsti bathmologia");		
		 * 		
		 * 		for(int i=0; i<avgRating.length()< i++)
		 *  		total += avgRating[i];
		 * 		
		 * 		float average = total/avgRating.length();
		 * 		System.out.println("Mesos oros kritikis bibliou: " + average);
		 * 
		 */
	}
}
