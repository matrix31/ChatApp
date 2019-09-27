
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

/* This class is to make all the displays of the bash console */


package ConsoleDisplay;

import static Network.TCPreceiver.first_fragment;
import Config.csv_read;
import java.text.DecimalFormat;


public class display {
    public String fileName ;
    public int Size;
    public long beginningTime;
    public long endTime;
    public float ratio ;
   
    public DecimalFormat df = new DecimalFormat("#.##");
    public csv_read read = new csv_read();
    
    /* Constructors */
    public display( String fileName){
        this.fileName= fileName ;
        
    }
    
    public display(String fileName, int Size ,long beginningTime, long endTime){
        this.fileName=fileName;
        this.Size=Size;
        this.beginningTime=beginningTime;
        this.endTime=endTime;
        
    }
    public display( float ratio){
        this.ratio = ratio ; 
        
    }
    
    public display(){
 
    }
    
    /* end of constructors */ 
    
    
    public void welcome(){
        System.out.println("\033[H\033[2J");  // clean the console 
        System.out.flush();
        System.out.println(" ------- Welcome to ChatApp ------- ");
    }
    
    public void SelectRemAdr(){
        System.out.println("\n");
        System.out.println(" ChatApp > Select a remote Address and hit begin\n");
        
    }
    
    public void adrSetOk(){
        System.out.println(" ChatApp > Remote Address has been set correctly");
        System.out.println(" ChatApp > You can chat and send files\n");
             
       
    }
    public void ConsoleSendFile(){
        System.out.println("");
        System.out.println(" ChatApp > You are sending a file...");
    }
    
    public void FileSucess(){
         System.out.println(" ChatApp > File "+fileName+" sucessfully sent to "+read.getAdr());
         System.out.println(" ChatApp > A message will be send notifying the state of the file, please wait");
         System.out.println("");
         
        
    }
    public void FileFeatures(String fileName, int size ,long beginningTime, long endTime){
        
       
        
        System.out.println(" ChatApp > Reception sucessfull");                          
        System.out.print("\n");
        System.out.println("    -- Name : "+fileName);
        System.out.println("    -- Size : "+df.format(size/1000.0)+" Kbytes");
        System.out.println("    -- Transmission time : "+df.format((endTime-beginningTime)/1000.0)+" s");
        System.out.println("    -- Rate : "+df.format((size/1000.0) / ((endTime-beginningTime)/1000.0))+" Kbytes/s") ;
        System.out.print("\n");
        System.out.println(" ChatApp > File saved on your disc");
        System.out.println("\n");
        
    }
    public void FilePercent(float ratio){
       
        
        if (first_fragment){
        System.out.println(" ChatApp > "+read.getAdr()+" is sending you a file" );
        }
        
        System.out.print(" ChatApp > "+df.format(ratio*100)+" % of the file received\r");
        
  
    }
    
    public void PacketLost(){
        System.out.println("\n");
        System.out.println(" ChatApp > Timeout has been reach due to packet losses");
        System.out.println(" ChatApp > File can't be received");
        System.out.println(" ChatApp > If you can't reach the user - it can take few minutes - reboot the modem");
        System.out.println("");
    }
    
    
    
}
