/*
 * 
 */
package SPIHT;




import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;


import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.imageio.ImageIO;
import jwave.Transform;
import jwave.TransformBuilder;
import jwave.transforms.wavelets.Wavelet;

/**
 *
 * @author franck
 */
public class main {
    public static void main(String [] args) throws IOException, InterruptedException{
        
        int[] ARGB = new int[32*32*4];
        BufferedImage img = ImageIO.read(new File("/Users/franck/Desktop/stop.png"));
        Pixels pix = new Pixels();
        pix.PixelToARGB(img);
        
        
        
        /*
        Pixels pixels = new Pixels();
        BufferedImage img = ImageIO.read(new File("/Users/franck/Desktop/stop.png"));
        
        int height = img.getHeight();
        int width = img.getWidth();
        
        byte[ ] imageByteArray = pixels.PixelToByte1D(height,width);
        int[] ImageIntArray  = new int [(imageByteArray.length)/4];
        ImageIntArray = pixels.ByteToInt(imageByteArray, height, width);
        
        
        double[ ] Double = new double[(imageByteArray.length)/4];
        
        for ( int i = 0 ; i < (imageByteArray.length)/4 ; i++){
            double D = pixels.intToDouble(ImageIntArray[i]);
            Double[i] = D;
            
    
        }
        System.out.println("Double : "+Arrays.toString(Double));
        
        
        
            /* final part */
            Transform transform = TransformBuilder.create( "Fast Wavelet Transform", "Haar");
            Wavelet wavelet = transform.getWavelet( );
           // double[ ] DWTcoeff = transform.forward( Double);
            DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/Users/franck/Desktop/newi.png")));
            //new SPIHT(DWTcoeff,4,32,dos );
    }
}

        
        
        
        
        
        
        
        
        
         
        
        
        
      
        

        
        
      
    

