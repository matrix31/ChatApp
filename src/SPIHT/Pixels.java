

/* Image Processing on pixels */
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
         
         
        /* return a array of int representing [A,R,G,B] */
        Raster r = image.getData();
        int[] pixel = r.getPixels(0,0,r.getWidth(), r.getHeight(), (int[])null);
        return pixel;
    }
    
        public double[] redPixels(int[] Pixels, double max, int height, int width){
            double[] normPixels = new double[height*width];
            int [] RedPix = new int[height*width];
            
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, i*4, RedPix, i, 1);
                normPixels[i] = RedPix[i]/ (double)max ;
                
            }
            return normPixels;
        }
        public double[] greenPixels(int[] Pixels, double max,int height, int width){
            double[] normPixels = new double[height*width];
            int [] GreenPix = new int[height*width];
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, (i*4)+1, GreenPix, i, 1);
                normPixels[i] = (int) (GreenPix[i]/ (double)max) ;
            }
            return normPixels;
        }
        public double[] bluePixels(int[] Pixels, double max,int height, int width){
            int [] BluePix = new int[height*width];
            double[] normPixels = new double[height*width];
            
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, (i*4)+2, BluePix, i, 1);
                normPixels[i] = BluePix[i]/ (double)max ;
            }
            
            return normPixels;
        }
        public double[] alpha(int[] Pixels, double max,int height, int width){
            int [] Alpha = new int[height*width];
            double[] normPixels = new double[height*width];
            
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, (i*4)+3, Alpha, i, 1);
                normPixels[i] = Alpha[i]/ (double)max ;
            }
             
            return normPixels;
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
      
      