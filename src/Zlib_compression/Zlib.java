
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

package Zlib_compression ;

import java.io.ByteArrayOutputStream;  
import java.io.IOException;  
import java.util.zip.DataFormatException;  
import java.util.zip.Deflater;  
import java.util.zip.Inflater;  


/* methods in charge of compression and decompression */



public class Zlib {
 

  public static byte[] compress(byte[] data) throws IOException {  

   Deflater deflater = new Deflater(); 
   deflater.setInput(data);  
   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);   
   deflater.finish();  
   byte[] buffer = new byte[1024];   
   while (!deflater.finished()) {  
       
    int count = deflater.deflate(buffer);   
    outputStream.flush();
    outputStream.write(buffer, 0, count); 
    outputStream.flush();
    
   }  
   outputStream.close(); 
   byte[] output = outputStream.toByteArray(); 
   
   return output;  

  }  

  public static byte[] decompress(byte[] data) throws IOException, DataFormatException {  
      
   Inflater inflater = new Inflater();   
   inflater.setInput(data);  
   ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);  
   byte[] buffer = new byte[1024];  
   while (!inflater.finished()) {  
       
    int count = inflater.inflate(buffer);  
    
   }  
   outputStream.close();  
   byte[] output = outputStream.toByteArray();  
   return output;  

  }  

 }

