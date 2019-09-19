/*
 * 
 */
package ConsoleDisplay;

import Config.csv_read;
import static Network.TCPreceiver.first_fragment;
import java.text.DecimalFormat;


public class display {
    public String fileName ;
    public int Size;
    public long beginningTime;
    public long endTime;
    public float ratio ;
 
    
    
    public DecimalFormat df = new DecimalFormat("#.##");
    public csv_read read = new csv_read();
    
    public static boolean set = true ;
    
    
    
    /* Constructors */
    public display( String filename){
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
        if (set){
           System.out.println(" ChatApp > You can chat and send files\n");
             
       }
    }
    public void ConsoleSendFile(){
        System.out.println("");
        System.out.println(" ChatApp > You are sending a file please wait...");
    }
    
    public void FileSucess(String filename){
         System.out.println(" ChatApp > File "+filename+" sucessfully sent to "+read.getAdr());
        
    }
    public void FileFeatures(String fileName, int size ,long beginningTime, long endTime){
        
       
        
        System.out.println(" ChatApp > Reception sucessfull");                          
        System.out.print("\n");
        System.out.println("    -- Name : "+fileName);
        System.out.println("    -- Size : "+df.format(size/1000.0)+" Kbytes");
        System.out.println("    -- Transmission time : "+df.format((endTime-beginningTime)/1000.0)+" s");
        System.out.println("    -- Rate : "+df.format((size/1000.0) / ((endTime-beginningTime)/1000.0))+" Kb/s") ;
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
    
    public void FileSent(){
        
        
         System.out.println(" ChatApp > File sucessfully sent to "+read.getAdr());
        
    }
}
