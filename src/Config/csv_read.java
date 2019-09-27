
/* UnderWater Chat App | Franck Bourzat | IMDEA Networks */

/* Class which read input from csv file to configure addresses needed*/

package Config;

import static View.MainInterface.* ; 
import java.util.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class csv_read {
    public static List<List<String>> list = new ArrayList<>();
    
    
    public static void read() throws FileNotFoundException{
        
        BufferedReader br = new BufferedReader(new FileReader("./adr_config.csv"));
        String line ="";
        try {
            while ((line = br.readLine()) != null) {
                String[] values = line.split("::");
                list.add(Arrays.asList(values));
            }
        } 
        catch (IOException ex) {
            System.out.println(" ChatApp > Impossible to read input from csv file");
        }
    }
    
   public String getAdr(){
       
       /* Get the corresponding IP address of the remote address */
       /* if more than 4 modems, change the NumberOfAdr value and add in the JboxModem the correponding remote adr */
       csv_read r = new csv_read();
       String IPadr = "";
       int NumberOfAdr = r.numberOfModem();
       
       for( int i = 0 ; i < NumberOfAdr ; i++){
           if ( remoteAdr.equals(list.get(i).get(1))){
               IPadr = list.get(i).get(0);
           }
         
           
       }
       return IPadr; 
   }
   
   
   public int numberOfModem(){
       
       int element = 0 ; 
       for ( List<String> str : list){
           element++;
       }
       return element;
   }
}
   
   
 
  


    
