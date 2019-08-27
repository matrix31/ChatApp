
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package Network;


import Config.csv_read;
import static View.View.jAreaConv;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import static jdk.nashorn.internal.objects.NativeRegExp.test;




public class TCPreceiver extends Thread implements Serializable{

    public Socket socket;
    
    public String str;
    public String checkAT = "+++AT!AR:2:OK\r\n";
    public String adoptedRemoteAdr="+++AT:7:RADDR";
    public String str_sub ;
    
    public byte[] txt_header = {0x01} ;
    public byte[] file_header = {0x02} ;
    public byte element;

    // Constructor : take current socket in argument
    public TCPreceiver(Socket socket) {
        this.socket=socket;
    }
    
    // Listening Thread
   @Override
    
    
    public void run(){
        
           try{
            while (true){
                 

                
                    while  ( socket.getInputStream().available()>0){
                        
                        
                        
                        DataInputStream  input = new DataInputStream(socket.getInputStream());
                        byte[] ByteArray = new byte[socket.getInputStream().available()];
                        
                        int i = 0 ;
                            for ( byte a : ByteArray){
                                i++;
                              
                            }
                    
                        input.read(ByteArray);
                    //    System.out.println(Arrays.toString(ByteArray)); // compressed array
                        element = ByteArray[0];
                        System.out.println(element); 
 
                        
                        if (element == txt_header[0]){
                            System.out.println("I have received a message");
                            str = new String(ByteArray) ; // convert it in string
                            str_sub = str.substring(1); // delete the header character
                            csv_read adr = new csv_read();
                            jAreaConv.append("\n");
                            jAreaConv.append("["+adr.getAdr()+"] : "+str_sub+"\n"); // display text
                               
                        }
                      
                      if ( element == file_header[0]){
                            /*
                            int t = ByteArray.length -1 ;
                            byte [] test = new byte[t] ;
                            System.arraycopy(ByteArray,1,test,0,t); // new array without the header
                            Inflater decompressor = new Inflater(); // decompression of the file
                            decompressor.setInput(test);
                            ByteArrayOutputStream bos = new ByteArrayOutputStream(test.length);
                               
                            byte[] buf = new byte[1024];
                            while (!decompressor.finished()) {
                                try {
                                    int count = decompressor.inflate(buf);
                                    bos.write(buf, 0, count);
                                } catch (DataFormatException e) {
                                }
                            }
                            try {
                                bos.close();
                            } catch (IOException e) {
                            }
                            byte[] decompressedData = bos.toByteArray();      
                            FileOutputStream fileOut = new FileOutputStream("/home/ubiquity/Downloads/img.txt"); 
                            System.out.println("I have received a file");
                            fileOut.write(decompressedData);
                            fileOut.close();   
                        }
                        
                        else {
                             str = new String(ByteArray) ; // convert it in string
                             if (str.equals(checkAT)){
                                System.out.println(" ChatApp > Remote Address has been set correctly");
                                System.out.println(" ChatApp > You can Chat\n");
                            }
                     
                            /*
                            if(str.substring(0,13).equals(adoptedRemoteAdr)){
                            System.out.println();
                            
                            System.out.println("Remote modem "+str.substring(14,15)+" send you a message while you were not connected");
                            jAreaConv.append("["+socket.getInetAddress()+"] : "+str.substring(17)+"\n"); // display text     
                            }
                            */
                            int t = ByteArray.length -1 ;
                            byte [] test = new byte[t] ;
                            System.arraycopy(ByteArray,1,test,0,t); // new array without the header
                            
                            
                            
                            ByteArrayOutputStream bos = new ByteArrayOutputStream(test.length);
                            System.out.println("bos created");
                   
                        //  while(socket.getInputStream().available() >0) {
                                bos.write(test);
                               
                                
                                // System.out.println("la");
                           // }
                                  // System.out.println("ici");
                            byte[] decompressedData = bos.toByteArray();
                            System.out.println("I have received a file");
                            System.out.println(Arrays.toString(decompressedData));
                            FileOutputStream fileOut = new FileOutputStream("/home/ubiquity/Downloads/img.jpeg");
                            fileOut.write(decompressedData);
                            
                            
                            System.out.println("nb de byte recu : "+ i);
                           
                           
                            
                            
                            
                            
                            
                            
                            
                            
                                    
                    }
                } 
            }     
            } catch (FileNotFoundException ex) {
            Logger.getLogger(TCPreceiver.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TCPreceiver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

           
    
  

  
       
                  
                  
                  
                  
                  
                  
              
                     
                     
      


        
            
                    
                    
                    
                    
                    
                    
                    
                    
                    
  
        

    

                
                
                
                
                
                
   
        
    
 


