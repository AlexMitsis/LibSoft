import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main 
{  
	static Librarian librarian=null;  //ousiastika autos einai o bibliothhkarios gia opou xriazetai omoia kai h librarydata
	static LibraryData librarydata=null;
	
	
	//Diavasma apo arxeio
	//antigrfh antikeimenou librarian apo to arxeio sthn statikh metablhth
	//antigrafh antikeimenou LibradyDaata apo to arxeio sthn statikh metablhth librarydata
			

	public static void main(String[] args) 
	{    		
		// creating an instance of timer class 
        Timer timer = new Timer(); 
          
        // creating an instance of task to be scheduled 
        TimerTask check=new SystemNotification();
        
	 // scheduling the timer instance for every 24 hours
      timer.schedule(check,100,1000*60*60*24); 
     

		Main.librarydata.Login();
		
		char menu_option;
		do{
		    System.out.println("Main Menu");
		    System.out.println("a. Login as Member");
		    System.out.println("b. Login as Librarian");
		    System.out.println("c. Quit");
		    
		
		    Scanner input = new Scanner(System.in);
		    menu_option = input.next().charAt(0);
		    switch(menu_option){
		   
		    case 'a':
		    	System.out.println("Logging as Member GUI");
		        break;
		    case 'b':
		        System.out.println("Logging as Librarian GUI");
		        break;
		    case 'c':
		        System.out.println("Quitting");
		        break;
		    }

		   } while(menu_option !='a' && menu_option!='b' && menu_option!='c');
	   
}}