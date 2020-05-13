import java.util.ArrayList;

public class Member 
{
	private String username;
	private String password;

	private ArrayList<Message> PersonalMessages; //Η λίστα στην οποία αποθηκεύονται οι ειδοποιήσεις των μελών.
		 	 	 	
	private ArrayList<Book> currentBorrowedBooks;
	private ArrayList<Book> favouriteList;
	private ArrayList<Book> toReadList;
	
	public ArrayList<Message> getPersonalMessages()
	{
	/*Επιστρέφει την λίστα των προσωπικών ειδοποιήσεων για εμφάνιση.

	Εξυπηρετεί περιπτώσεις χρήσης:
	Contact Administration-Use Case Code:14
	Book State Updates - Use Case Code: 19
	Administration Announcements - Use Case Code: 20
	Newsletter - Use Case Code: 22 (X01)
	Penalty - Use Case Code: 26 (X02)
	Warning - Use Case Code: 25 (X03)*/
	}
	
	public void setPersonalMessage(Message message) 
	{
	/*Αλλάζει το περιεχόμενο της λίστας προσωπικών μηνυμάτων κάθε φορά που καλείται. Συγκεκριμένα προσθέτει μια ακόμη εγγραφη στην υπάρχουσα λίστα.

	Εξυπηρετεί περιπτώσεις χρήσης:
	Contact Administration-Use Case Code: 14
	Book State Updates - Use Case Code: 19
	Administration Announcements - Use Case Code: 20
	Newsletter - Use Case Code: 22 (X01)
	Penalty - Use Case Code: 26 (X02)
	Warning - Use Case Code: 25 (X03)*/
	}
}
