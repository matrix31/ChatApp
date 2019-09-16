
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */


package Network;

import Config.csv_read;
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
            System.out.println(" ------- Welcome to ChatApp ------- ");
            System.out.print("\n");
            socket = new Socket(adr,port);     // socket creation
            System.out.println(" ChatApp > Socket created to " + read.getAdr());
            System.out.println(" ChatApp > Select a remote Address\n");
        }
        catch (IOException e){
        }
    }
    
    
    
   public static BufferedImage resize(BufferedImage img, int height, int width) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
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
            
            int cpt = 0 ; 
            while ( cpt != Message.length){
                if (Message[cpt] == plusByte){
                    System.out.println(Message[i]);
                    
                    if (Message[cpt+1] == plusByte){
                         System.out.println(Message[i+1]);
                        if (Message[cpt+2] == plusByte){
                            System.out.println(Message[i+2]);
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

            System.out.println("");
            System.out.println(" ChatApp > You are sending a file please wait...");
      
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            File fileTest = new File("./ChatApp/Files/toSend/new.jpeg");
            byte[] FILE = Files.readAllBytes(fileTest.toPath());

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
                Thread.sleep(2000); // slow data transmission ; if not impossible to tranfer files > 30 kB due to losses
 
                
            }
           byte[] frag = new byte[sizeLastFrag]; 
           System.arraycopy(Packet, nb_frag*1024, frag, 0, sizeLastFrag);
            
            /* Sending last fragment */ 
             out.flush();
             out.write(frag); 
             out.flush(); 
             
            csv_read read = new csv_read();
            System.out.println(" ChatApp > File "+fileName+" sucessfully sent to "+read.getAdr());
            
            
     


            /* 
            System.out.println("Taille tableau "+fileSize);
            System.out.println(Arrays.toString(Packet));
            System.out.println("\n");
            System.out.println("Taille du fichier " +FILE.length);
            System.out.println("Header "+Arrays.toString(Header));
            */
    
    }
   
   
}
 


     
 
    
      
/********************************************************************************
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      byte[] header ={0x02};
      byte[] FILE = Files.readAllBytes(file.toPath());

      // use of Deflater compression
      Deflater compressor = new Deflater();
      compressor.setLevel(Deflater.BEST_COMPRESSION);
      compressor.setInput(FILE);
      compressor.finish();
      
      ByteArrayOutputStream bos = new ByteArrayOutputStream(FILE.length);
      byte[] buf = new byte[2048];
      while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
      }
      try {
        bos.close();
      }
      catch (IOException e) {}
      byte[] compressedData = bos.toByteArray();
      byte[] Packet = new byte[header.length + compressedData.length];
      
      System.arraycopy(header,0,Packet,0,header.length);
      System.arraycopy(compressedData, 0,Packet, header.length, compressedData.length);
      
      out.flush();
      out.write(Packet);
      out.flush(); 
      
      System.out.println(FILE.length);
      System.out.println(compressedData.length); 
      */
      

  

//BufferedImage originalImage = ImageIO.read(file);
       //float quality = 0.7f;
   

  // ByteArrayOutputStream os = new ByteArrayOutputStream(FILE.length);
    /* Compresion */
    
    
    //Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
    //if (!writers.hasNext())
    // throw new IllegalStateException("No writers found");
    // ImageWriter writer = (ImageWriter) writers.next();
    //ImageOutputStream ios = ImageIO.createImageOutputStream(os);
    //writer.setOutput(ios);
    //ImageWriteParam param = writer.getDefaultWriteParam();
    // param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    //param.setCompressionQuality(quality);
    
    /*           */ 
    
    
    //ImageIO.write(originalImage,"jpeg",os);         // utiliser tableau de byte File au lieu de  la taille de FileToByte
    //os.flush();                                   // 2192 vs 2883 byte 
    
    //byte [] FileToByte  = os.toByteArray(); // Array Byte of File 
 