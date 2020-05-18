import java.util.ArrayList;

public class Author {
  
  private String name;
  private ArrayList<Book> ListOfBooks;
  private ArrayList<Borrower> ListOfFollowers;
  
  public Author(String name)
  {
      this.name = name;
      ListOfBooks = new ArrayList<Book>();
      ListOfFollowers = new ArrayList<Borrower>();
  }  
  
  public void addBook(Book b)
  {
    ListOfBooks.add(b);    
  }
  public void removeBook(Book b) {
	  ListOfBooks.remove(b);
  }  
  public void deleteFollower(Borrower b)
  {
      ListOfFollowers.remove(b) ;
  }
  
  public void addFollower(Borrower b)
  {
      ListOfFollowers.add(b);
  }
  
  public void updateListOfBooks(Book newBook)
    //ton paralhpth pws ton bazw?
  {
      String mhnuma = "Neo biblio enos suggrafea "+this.name+" pou akoloutheite prosthethike!" ;///ououououou teleiaaa
   //   Member apostoleas = new Librarian(username, password);
      
      //xrhsimopoieitai monadikos bibliothhkarios pou dhlwthhke sthn main
      
    //mas noiazei na baloume username kai password ston librarian?no
    
      ListOfBooks.add(newBook);
      Message message = new Message(mhnuma, Main.librarian);
      
      for(Borrower follower : ListOfFollowers)
      {
          Message.messageToBorrower(follower, message); 
      }
    
  }

public ArrayList<Borrower> getListOfFollowers() 
{
	return ListOfFollowers;
}

public void setListOfFollowers(ArrayList<Borrower> listOfFollowers) 
{
	ListOfFollowers = listOfFollowers;
}
  
  

}

