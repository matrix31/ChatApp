
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

/* class which read input from csv file to configure addresses needed*/

package Config;

import static View.View.* ; 
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
        
        BufferedReader br = new BufferedReader(new FileReader("/home/ubiquity/Downloads/adr_config.csv"));
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
       
       String remote_adr = jBoxModem.getSelectedItem().toString();
       String adr1 = "192.168.0.186"; 
       String adr2 = "192.168.0.188"; 
       String adr3= "192.168.0.189"; 
       String adr4 = "192.168.0.190";
       String er = "erreur";
       
       if ( remote_adr.equals("1")){
           return adr1;
       }
       if ( remote_adr.equals("2")){
           return adr2;
       }
       if ( remote_adr.equals("3")){
           return adr3;
       }
       if ( remote_adr.equals("4")){
           return adr4;
       }
       else { 
           return er ;
       }
   }
   public int numberOfModem(){
       
       int element = 0 ; 
       for ( List<String> str : list){
           element++;
       }
       return element;
   }
}
   
   
 
  


    
