
/* UnderWater Chat App | Franck Bourzat | IMDEA Networks */

package Network;


import Config.csv_read;
import static Config.csv_read.read;
import ConsoleDisplay.display;
import static View.ATConsole.jATdisplay;
import static View.MainInterface.jAreaConv;
import ImageProcessing.ImageDisplay;
import static View.MainInterface.jAdr;
import static View.MainInterface.jBoxModem;
import static View.MainInterface.remoteAdr;
import static View.MainInterface.state;
import static View.MainInterface.tcpclient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.lang.Object.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Timer;
import java.util.TimerTask;



public class TCPreceiver extends Thread {

    public Socket socket;

   
    
    private final String checkAT = "+++AT!AR:2:OK\r\n";
    private final String BuffnotEmpty = "+++AT!AR:27:ERROR"+" "+"BUFFERS"+" "+"ARE"+" "+"NOT"+" "+"EMPTY\r\n";
    private final String autoSetAdr = "+++AT:7:RADDR,1\r\n";
    private String str;
    private String str_sub ;
    private String fileName ; 
    private final String[] imgExt = {"ai", "eps","pdf","psd","jpg","jpeg","gif","tif","png","svg"} ;
    
   
    private final byte txt_byte = 0x01 ;
    private final byte file_byte = 0x02 ;
    private final byte AT_byte = 0x2b;
    private byte byteType;
 
    public static boolean first_fragment = true ;
    public static boolean clickable = true ;
    public static boolean stateFileLocal = true ; 
    public boolean received ;
    public boolean oneDipslay = true;
    
    /* boolean to disable the action that a user can send a file 
    while he/she is receving one */
    public static boolean stateFile = true ;  
    
    public long end = 0 ; 
    
    private int size ;
    private int i = 0 ; 
    private int byteFileSize;
    private int ATcpt = 0 ;
    private long beginningTime; 
    private float ratio; 

  
   
    
 

    

    // Constructor : take current socket in argument
    public TCPreceiver(Socket socket) {
        this.socket=socket;
    }
    
    // Listening Thread
   @Override
    public void run(){
        
        ByteArrayOutputStream recept = new ByteArrayOutputStream();
        byte [] fileSizeArray = new byte[4];

        while (true){
            
            /* set time for each new packet */ 
            long start = System.currentTimeMillis();
            
            try {
                while  ( socket.getInputStream().available()>0){
                    
                    DataInputStream  input = new DataInputStream(socket.getInputStream());
                    byte[] ByteArray = new byte[socket.getInputStream().available()];
                    received = false ;
              
                    input.read(ByteArray);
                    if (first_fragment){
                    byteType = ByteArray[0];
                    }
 
                    /* first packet received */
                    if (first_fragment){
                        
                        /* Data received from file */
                        if (byteType == file_byte){
                            
                            stateFile = false ; 
                           
                         
                            
                            /*
                            
                            java.util.Timer time = new java.util.Timer();
                            time.schedule(new TimerTask() {          
                            public void run(){
       
                                System.out.print("pertes");
  
                            }
                        },10000);
       
                            i++;
               */
                            
   
                            beginningTime = System.currentTimeMillis();
                            /* Get de size of the file from the Header */
                            System.arraycopy(ByteArray,1,fileSizeArray,0,4);
                            ByteBuffer bytebuff = ByteBuffer.allocate(4);
                            bytebuff = ByteBuffer.wrap(fileSizeArray);
                            
                            
                            size = bytebuff.getInt();
                            
                            /* Get de name of the file from the Header */
                            byteFileSize = ByteArray[5];
                            byte [] byteFileName = new byte[byteFileSize];
                            System.arraycopy(ByteArray,6,byteFileName,0,byteFileSize);
                            fileName = new String(byteFileName);
                            
                            /* Reception */
                            recept.write(ByteArray, byteFileSize+6,ByteArray.length-(byteFileSize+6));
                            byte[] FILE = recept.toByteArray();
                            
                            /* Displays file tranfert progress in %tage */
                            ratio = ((float) FILE.length ) / ((float) size);
                            display Dfile = new display(ratio);
                            Dfile.FilePercent(ratio);
                            
                            first_fragment = false ;
  
                            
                            /* if no fragmantation induced by the hardware */
                            if ( FILE.length == size){
                                
                                long endTime1 = System.currentTimeMillis();
                                received = true;
                               
                                
                                /* File creation */
                                File file = new File("./Files/Received",fileName);
                                FileOutputStream recvFile = new FileOutputStream(file);
                                recvFile.write(FILE);
                                recvFile.close();
                                
                                
                                recept.close();
                                recept.reset();
                                
                                /* Displays */
                                display Displ = new display(fileName,size,beginningTime,endTime1);
                                Displ.FileFeatures(fileName, size, beginningTime, endTime1);
                                
                                /* Send a message to confirm that file successfully received */
                                String text = "File successfully received\n";
                                
                                
                                tcpclient.SendMessage(text+"\n");
                      
                               jAreaConv.append("[Me] : "+text+"\n");

                               jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text
                        
   
                                size = 0;
                                ratio = 0;
                                first_fragment = true ;
                                end = 0 ;
                                stateFile = true ; 
                                stateFileLocal = true ; // state to send nothing when receiveing
                                
                                
      
                                
                            /* Pop up window with the image file */   
                            
                       
                            /* if not an image ->  not displayed */
                            String ext = "";
                            int point = fileName.lastIndexOf('.');
                            if (i > 0) {
                                ext = fileName.substring(point+1);
                            }
                            for ( String extt : imgExt ){       
                                if ( ext.equals(extt)){
                                    
                                    ImageDisplay display =new ImageDisplay();
                                    display.displayImage("./Files/Received",fileName);
                                
                                }
                                else {}
                            }
                   
                        }
                        }
                        
                        /* Data for conversation to be displayed */
                        
                        if (byteType == txt_byte){
                           
                            
                            // avoid 
                            for ( int i = 0 ; i < ByteArray.length ; i++){
                                if (ByteArray[i]== 0x26){
                                    if (ByteArray[i+1]== 0x25){
                                        if (ByteArray[i+2]== 0x24){

                                            ByteArray[i] = 0x2b ;
                                            ByteArray[i+1] = 0x2b ;
                                            ByteArray[i+2] = 0x2b ;
                                            
                                        }
                                        
                                    }
                                    
                                }
                            }
                            str = new String(ByteArray) ; // Byte to String
                            
                           
                   
                   
             
                            
                            str_sub = str.substring(1); // Delete the type byte (first byte)
                            
                           
                            jAreaConv.append("["+remoteAdr+"] : "+str_sub+"\n"); // display text
                            jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text
                            
                           if ( str_sub.equals("File successfully received\n")){
                               System.out.print("ok");
                               stateFileLocal = true ;
                               
                           }
                           else {
                               
                                if( str_sub.equals("File packets have been lost\n\n")){
                                     System.out.print("okok");
                               
                               stateFileLocal = true ;
                               
                           }
                           }
                           
                           
                        }
                        
                        
                        /* AT command for setting the remote address */               
                        if (byteType == AT_byte){
                             
                             ATcpt++; 
       
                            str = new String(ByteArray) ; // convert byte to string
                            

                            if (str.equals(checkAT)){
                           
                                
                                display d = new display();
                                d.adrSetOk();
                            }                     
                            if ( str.equals((BuffnotEmpty))){                     
                                System.out.println(" ChatApp > Transmission buffer not empty please try again or restart");         
                            }
                            /* command for ATconsole  or error messages */ 
                            if (ATcpt > 2){
                           
                                 if(!str.equals(checkAT)){
                                     
                                
                                        
                                       
                                       if(str.length() < 14){
                                           jATdisplay.append("    Modem >  "+str+"\n");   
                                       }
                                       else{
                                        String s = str.substring(0,14);
                                    
                                        if ( s.equals("+++AT:7:RADDR,")){
                                             String RemAdr = str.substring(14,15);
                                             jAreaConv.setText("    Remote modem "+RemAdr+" want to communicate to you\n");
                                             remoteAdr = RemAdr ;
                                             jBoxModem.setSelectedItem(RemAdr);
                                             
                                             
                                            
                                             
                                             csv_read csv = new csv_read();
                                             jAdr.setText(csv.getAdr());
                                             
                                            
                                        
                                             
                                             
                                        }
                                        else{
                                     
                                        jATdisplay.append("    Modem >  "+str+"\n");
                                        }
                                       }
                                     
                                     
                                 
                                     
                                    
                                 }
                                 if(str.equals(BuffnotEmpty)){
                                     System.out.println(" ChatApp > Transmission buffer not empty please try again or restart");
                                     
                                 }
                                
      
                            }
                            }
                                     
                                     
                                 }
                            
            
                                 
                            
              
                    
                
                    
                    
                    /* fragments number x received */
                    else {
                          
                        i++;
                        /* set time after receving a fragment */ 
                        end = System.currentTimeMillis();
                        
                        
                        
                     
                            
            
                        /* Reception */
                        recept.write(ByteArray, 0,ByteArray.length);
                        byte[] FILE = recept.toByteArray();
                        
                    
                      /* Displays file tranfert progress in %tage */
                         ratio = ((float) FILE.length ) / ((float) size);
                         display Dfile = new display(ratio);
                         csv_read read = new csv_read();
                         Dfile.FilePercent(ratio);
                       
                
                        /* Wait the last fragment */
                        if ( FILE.length == size){
                            
                            long endTime2 = System.currentTimeMillis();
                            received = true; 
                            
                            /* File creation */
                            File file = new File("./Files/Received",fileName);
                            FileOutputStream recvFile = new FileOutputStream(file);
                            recvFile.write(FILE);                         
                            recvFile.close();
                            
                            /* Reset ByteArrayOutputStream */
                            recept.close();
                            recept.reset();
                            
                            /* Displays */
                           display displa = new display(fileName,size,beginningTime,endTime2);
                           displa.FileFeatures(fileName, size, beginningTime, endTime2);
                           
                           /* Send a message to confirm that file has been received */
                           String text = "File successfully received\n";
                           tcpclient.SendMessage(text);
                           jAreaConv.append("[Me] : "+text+"\n");
                         
                           
       
                            first_fragment = true ;
                            size = 0;
                            ratio = 0;
                            end=0;
                            stateFile = true; 
                           
               
                            
                            /* Pop up window with the file */
                            
                            String ext = "";
                            int point = fileName.lastIndexOf('.');
                            if (i > 0) {
                                ext = fileName.substring(point+1);
                            }
                            for ( String extt : imgExt ){       
                                if ( ext.equals(extt)){
                                    
                                    ImageDisplay display =new ImageDisplay();
                                    display.displayImage("./Files/Received",fileName);
                                    
                                }
                                else {}
                            }
                               
                            
                        }
                    }
                   
            } 
                
                }  catch (IOException ex) {
                Logger.getLogger(TCPreceiver.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
           /* Timer of 15 seconds to inform the user of packet losses for file transfer */
            
            if (end!=0){
                    if ( start-end > 15000){
                    
                        try {
                            /* Displays */
                            display dis = new display();
                            dis.PacketLost();
                            
                            /* Reset ByteArrayOutputStream */
                            recept.close();
                            recept.reset();

                            /* set initial paramters */
                            first_fragment = true ;
                            size = 0;
                            ratio = 0;
                            end = 0 ;
                            stateFile = true; 
                       //     stateFileLocal = true;
                            
                            /* Send a message to inform that file packets have been lost  */
                            String text = "File packets have been lost"; 
                            tcpclient.SendMessage(text+"\n");
                            jAreaConv.append("[Me] : "+text+"\n");  
                            jAreaConv.setCaretPosition(jAreaConv.getDocument().getLength()); // auto scroll when adding text
                            
                            
                             } catch (IOException ex) {
                            Logger.getLogger(TCPreceiver.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   
                    }
                        
                       
                           
                        
            
            }
            

            
            
        } 
    }
}
        
        
        
       
   
                   
            

             
                    

                    
    

   

             
             
            
            


           
    
  

  
       
                  
                  
                  
                  
                  
                  
              
                     
                     
      


        
            
                    
                    
                    
                    
                    
                    
                    
                    
                    
  
        

    

                
                
                
                
                
                
   
        
    
 


