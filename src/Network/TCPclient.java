
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */


package Network;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Integer.toHexString;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Deflater;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;



public class TCPclient implements Serializable {
    
    public Socket socket ;
    static String fileSizeString;
    public int fileSizeInt; 
    
    public TCPclient(String adr, int port) throws ClassNotFoundException{
        try {
            System.out.println(" ChatApp > Welcome");
            socket = new Socket(adr,port);     // socket creation
            System.out.println(" ChatApp > Socket created to " + socket.getInetAddress());
            System.out.println(" ChatApp > Select a remote Address\n");
        }
        catch (IOException e){
        }
    }
    
   public void SendMessage(String message){
       try{
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            byte[] Type_file ={0x01};
            byte[] Message = message.getBytes();
         
          
         
          
          
          
          
            byte[] Packet = new byte[Type_file.length + Message.length];
            
            System.arraycopy(Type_file,0,Packet,0,Type_file.length);
            System.arraycopy(Message, 0,Packet, Type_file.length, Message.length);
            
            out.flush();
            out.write(Packet);
            out.flush();
           /* Temporary store data to send in a buffer and clear it so data can be sent in one go : 
            improve performances */
        }
       catch (IOException o){
       }  
   }
   
  public void SendFile(File file) throws IOException{
     /*
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
      
      
      DataOutputStream out = new DataOutputStream(socket.getOutputStream());
      BufferedImage originalImage = ImageIO.read(file);
       //float quality = 0.7f;
      
      
      byte[] FILE = Files.readAllBytes(file.toPath());
      ByteArrayOutputStream os = new ByteArrayOutputStream(FILE.length);
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
    
    
    ImageIO.write(originalImage,"jpeg",os);         // utiliser tableau de byte File au lieu de  la taille de FileToByte
    //os.flush();                                   // 2192 vs 2883 byte 
    
    byte [] FileToByte  = os.toByteArray(); // Array Byte of File 
    System.out.println("Taille tableau "+FileToByte.length);
    
    /* Header Construction */
    
    Integer fileSize =  FILE.length;  // size of the file in int
    fileSizeInt = Integer.parseInt(Integer.toHexString(fileSize),16); 
    
    /*
       Integer.toHexString(fileSize) Size of the File -->  String Hexadecimal
       Integer.parseInt(String) String Hexa --> Int 
    */
   
    
    ByteBuffer byteBuff = ByteBuffer.allocate(4); // allocation of ByteBuffer for the size 
    byteBuff.putInt(fileSizeInt); // put the size in 
    
    byte [] fileSizeByte = byteBuff.array(); 
    byte[] Type_file ={0x02}; 
    byte [] Header = new byte[5];  // Header = Type of file[1] + Size of File[4] ; Maximum size of 4.294.967.295
    
    Header[0]= Type_file[0];
    System.arraycopy(fileSizeByte,0,Header,1,fileSizeByte.length);
    
    
    /* Sending */
    
    byte[] Packet = new byte[Header.length + FileToByte.length];
    System.arraycopy(Header,0,Packet,0,Header.length);
    System.arraycopy(FileToByte, 0,Packet, Header.length, FileToByte.length);
      
      out.flush();
      out.write(Packet); 
      out.flush(); 
      
    
    System.out.println(Arrays.toString(Packet));
    System.out.println("\n");
    System.out.println("Taille du fichier " +FILE.length);
    System.out.println("Header "+Arrays.toString(Header));
    

  }
  public void Close() throws IOException{ 
            socket.close();
           
  }
}
 


     
    
    
      
      

  

   
 