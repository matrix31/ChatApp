
/* UnderWater Chat App | Franck Bourzat | IMDEA Networks */


package Network;

import Config.csv_read;
import ConsoleDisplay.display;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.Arrays;
import javax.imageio.ImageIO;
import static Network.TCPreceiver.stateFileLocal;


public class TCPclient {
    
    public Socket socket ;
    private int fileSizeInt; 
    private int cpt = 0 ;
    private int  plusCpt = 0; 
    private int i = 0 ; 
    private boolean triplePlus = false ;
    private byte plusByte = 0x2b;

    
    public TCPclient(String adr, int port) throws ClassNotFoundException{
        try {
            csv_read read = new csv_read();
            System.out.print("\n");
            socket = new Socket(adr,port);     // socket creation
        }
        catch (IOException e){
        }
    }
    
    
    
  
    
  public void SendAT(String message) throws IOException{
      
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
           // byte[] Type_file ={0x0B};
            byte[] Message = message.getBytes();
           // byte[] Packet = new byte[Type_file.length + Message.length];
            
            
            /* Construction of the array */
            // System.arraycopy(Type_file,0,Packet,0,Type_file.length);
            //System.arraycopy(Message, 0,Packet, Type_file.length, Message.length);
      
            out.flush();
            out.write(Message);
            out.flush();
          
            /* Temporary store data to send in a buffer and clear it so data can be sent in one go : 
            improve performances */
         
  }
    
   public void SendMessage(String message) throws IOException{

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            byte[] Type_file ={0x01};
            byte[] Message = message.getBytes();
            byte[] Packet = new byte[Type_file.length + Message.length];
            
            /* avoid +++ comand to be interpreted */
            int cpt = 0 ; 
            while ( cpt != Message.length){
                if (Message[cpt] == plusByte){
                    
                    if (Message[cpt+1] == plusByte){
                        if (Message[cpt+2] == plusByte){
                            
                            Message[cpt] = 0x26 ;
                            Message[cpt+1] = 0x25;
                            Message[cpt+2] = 0x24;    
                        }
                    }   
                    }
                cpt++;
                        
            }
            
             /* Construction of the array */
                        System.arraycopy(Type_file,0,Packet,0,Type_file.length);
                        System.arraycopy(Message, 0,Packet, Type_file.length, Message.length);

                        /* Sending */ 
                        out.flush();
                        out.write(Packet);
                        out.flush();
                }

   
   public void SendFile(File file) throws IOException, InterruptedException{
       
            stateFileLocal = false ;
            display dis = new display();
            dis.ConsoleSendFile();
      
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
  
            byte[] FILE = Files.readAllBytes(file.toPath());

            /* Header Construction */
            Integer fileSize =  FILE.length;
            String fileName = file.getName();

            fileSizeInt = Integer.parseInt(Integer.toHexString(fileSize),16);
            
            
            /* Integer.toHexString(fileSize) Size of the File -->  String Hexadecimal
               Integer.parseInt(String) String Hexa --> Int */
            
            
            ByteBuffer byteBuff = ByteBuffer.allocate(4); // allocation of ByteBuffer for the size 
            byteBuff.putInt(fileSizeInt); // put the size in


            byte [] fileSizeByte = byteBuff.array(); 
            byte [] Type_file ={0x02};
            byte [] BytefileName = fileName.getBytes();  

            Integer fileNameSize = BytefileName.length;

            byte [] Header = new byte[6+fileNameSize];  
            byte byteFileNameSize = fileNameSize.byteValue();

            /* Header = Type of file[1] + Size of File[4] + SizeOfFileName[1] + file Name[SizeOfFileName[] 
               Theoretical Maximum size of the file 4.294.967.295 that can be passed  */
            

            
            Header[0]= Type_file[0];        // Type of data 
            Header[5] = byteFileNameSize;   // Size of File name 

            /* Construction of the Header */
            System.arraycopy(fileSizeByte,0,Header,1,fileSizeByte.length);  
            System.arraycopy(BytefileName,0,Header,6,fileNameSize);

            /* Construction of the final byte Array */
            byte[] Packet = new byte[Header.length + FILE.length];
            System.arraycopy(Header,0,Packet,0,Header.length);
            System.arraycopy(FILE, 0,Packet, Header.length, FILE.length);

            
            int nb_frag = Math.round(Packet.length/1024);        // nb of fragmentes of 1024 bytes transmits
            int sizeLastFrag = Packet.length - (1024*nb_frag) ;
           
            /* fragmenting in 1024 packet bytes */
            for ( int i = 0 ; i < nb_frag ; i++){
                
                /* Sending */
                byte[] frag = new byte[1024]; 
                System.arraycopy(Packet, i*1024, frag, 0, 1024);
                out.flush();
                out.write(frag); 
                out.flush(); 
                Thread.sleep(2000); // slow data transmission ; if not impossible to tranfer files > 30 kB due to buffers losses
 
                
            }
           byte[] frag = new byte[sizeLastFrag]; 
           System.arraycopy(Packet, nb_frag*1024, frag, 0, sizeLastFrag);
            
            /* Sending last fragment */ 
             out.flush();
             out.write(frag); 
             out.flush(); 
             
            /* Console display */
            display disp = new display(fileName);
            disp.FileSucess();
            
            
           
            
         
            
    
    }
   
   
}
