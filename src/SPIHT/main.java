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
        
        
        BufferedImage img = ImageIO.read(new File("/Users/franck/Desktop/stop.png"));
        int h = img.getHeight();
        int w = img.getWidth();
        int[] ARGB = new int[h*w*4]; // passer height*width
        
        Pixels pix = new Pixels();
        ARGB = pix.PixelToARGB(img);
        
        double [] Red = new double[h*w];
        double [] Green = new double[h*w];
        double [] Blue = new double[h*w];
        double [] Alpha = new double[h*w];
        
        Red = pix.redPixels(ARGB,255,h,w);
        Green = pix.greenPixels(ARGB,255,h,w);
        Blue = pix.bluePixels(ARGB,255,h,w);
        Alpha =pix.alpha(ARGB,255,h,w);
                
        
         Transform transform = TransformBuilder.create( "Fast Wavelet Transform", "Haar");
         double[ ] dwtRedcoeff = transform.forward(Red);
         double[ ] dwtGreencoeff = transform.forward(Green);
         double[ ] dwtBluecoeff = transform.forward(Blue);
         double[ ] dwtAlphacoeff = transform.forward(Alpha);
         System.out.println(Arrays.toString(dwtRedcoeff));
        System.out.println(Arrays.toString(ARGB));
        System.out.println(Arrays.toString(dwtGreencoeff));
        System.out.println(Arrays.toString(dwtBluecoeff));
        System.out.println(Arrays.toString(dwtAlphacoeff));
        
        double[] dwTCoeff = new double[h*w*4];
        System.arraycopy(dwtRedcoeff, 0,dwTCoeff ,0 , h*w);
        System.arraycopy(dwtGreencoeff, 0,dwTCoeff ,h*w , h*w);
        System.arraycopy(dwtBluecoeff, 0,dwTCoeff ,h*w*2 , h*w);
        System.arraycopy(dwtAlphacoeff, 0,dwTCoeff ,h*w*3 , h*w);
        
        
        
         
         DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/Users/franck/Desktop/newi.jpg")));
         new SPIHT(dwTCoeff,8,32,dos );
         
         
        
        
        
        
        
      //  --> Construitre Red[], Green[], blue[], alpha[]
      // divide each number by max value
      // pondre un double float
      // prendre chaque tableau "normalisé" et insérer dans DWT 
        
        
        
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
           
            //DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("/Users/franck/Desktop/newi")));
            //new SPIHT(DWTcoeff,4,32,dos );
    }
}

        
        
        
        
        
        
        
        
        
         
        
        
        
      
        

        
        
      
    

