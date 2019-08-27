
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */


package Network;


import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.zip.Deflater;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;



public class TCPclient implements Serializable {
    public Socket socket ;
    
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
            byte[] header ={0x01};
            byte[] Message = message.getBytes();
            byte[] Packet = new byte[header.length + Message.length];
            
            System.arraycopy(header,0,Packet,0,header.length);
            System.arraycopy(Message, 0,Packet, header.length, Message.length);
            
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
      byte[] buf = new byte[1024];
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
      float quality = 0.5f;
      
      byte[] FILE = Files.readAllBytes(file.toPath());
      ByteArrayOutputStream os = new ByteArrayOutputStream(FILE.length);
      
      Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
      
      if (!writers.hasNext())
        throw new IllegalStateException("No writers found");
      
      
    ImageWriter writer = (ImageWriter) writers.next();
    ImageOutputStream ios = ImageIO.createImageOutputStream(os);
    writer.setOutput(ios);
    
    ImageWriteParam param = writer.getDefaultWriteParam();
    param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
    param.setCompressionQuality(quality);
    writer.write(null, new IIOImage(originalImage, null, null), param);
    os.flush();
    
   byte [] compress = os.toByteArray();
      
      

   
    
    /* Sending */
    
    byte[] header ={0x02};
   
    byte[] Packet = new byte[header.length + compress.length];
    System.arraycopy(header,0,Packet,0,header.length);
    System.arraycopy(compress, 0,Packet, header.length, compress.length);
      
      out.flush();
      out.write(Packet);
      out.flush(); 
      
    System.out.println(Arrays.toString(FILE));
    System.out.println("\n"+"\n"+"\n");
    System.out.println(Arrays.toString(compress));
    
    
    System.out.println(FILE.length);
    System.out.println("\n"+"\n"+"\n");
    System.out.println(compress.length);
    
     
  }
  public void Close() throws IOException{
      socket.close();
      
  }
}
    
 

     
    
    
      
      

  

   
 