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
	
	   
}}