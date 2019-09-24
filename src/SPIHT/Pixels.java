/*
 * 
 */
package SPIHT;



import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferDouble;
import java.awt.image.DataBufferInt;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class Pixels {
  
    
    public Pixels (){
    
    }
    
    public int[] PixelToARGB(BufferedImage image) throws IOException, InterruptedException{
         
         /* tableau de int */
        
        Raster r = image.getData();
        int[] pixel = r.getPixels(0,0,r.getWidth(), r.getHeight(), (int[])null);
        System.out.println(Arrays.toString(pixel));

        
         return pixel;
    }
    
    
    
    
}
      
      
         //   BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
       
      //  image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      
      /*
      
        BufferedImage in = ImageIO.read(file);
        byte[] pixels = new byte[height*width];
        pixels = ((DataBufferByte) in.getRaster().getDataBuffer()).getData() ;
        System.out.println("Pixel :"+height*width+" , "+ "Byte "+pixels.length);
        return pixels ;
      */
      
/*
      
      
    public int [] ByteToInt(byte[] Byte,int height, int width){
        
        int[] PixelToInt = new int [Byte.length];
       
        for (int i = 0 ; i< (height*width) / 4 ; i++){
        
          byte[] byteArray = new byte[4]; 
          
          System.arraycopy(Byte, 4*i, byteArray, 0, 4);
          
          ByteBuffer bytebuff = ByteBuffer.allocate(4);
          bytebuff = ByteBuffer.wrap(byteArray);
          int Int = bytebuff.getInt();
          
          PixelToInt[i] = Int;
          
        }
        System.out.println(Arrays.toString(PixelToInt));
        return PixelToInt; 
    }
    
    public double intToDouble(int Int){
        double value = (double) Int;
        return value;
    }
}


      */
      
      