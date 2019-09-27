/* UnderWater Chat App | Franck Bourzat | IMDEA Networks */

/* Image Processing on pixels for the SPIHTalgorithm */

package SPIHT;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;

public class Pixels {
  

    public Pixels (){
    
    }
    
    public int[] PixelToARGB(BufferedImage image) throws IOException, InterruptedException{
         
         
        /* return a array of int representing [A,R,G,B] */
        Raster r = image.getData();
        int[] pixel = r.getPixels(0,0,r.getWidth(), r.getHeight(), (int[])null);
        return pixel;
    }
    
        /* return an array of red pixels of the images divided by 255 */
        public double[] redPixels(int[] Pixels, double max, int height, int width){
            double[] normPixels = new double[height*width];
            int [] RedPix = new int[height*width];
            
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, i*4, RedPix, i, 1);
                normPixels[i] = RedPix[i]/ (double)max ;
                
            }
            return normPixels;
        }
        
           /* return an array of green pixels of the images divided by 255 */
        public double[] greenPixels(int[] Pixels, double max,int height, int width){
            double[] normPixels = new double[height*width];
            int [] GreenPix = new int[height*width];
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, (i*4)+1, GreenPix, i, 1);
                normPixels[i] = (int) (GreenPix[i]/ (double)max) ;
            }
            return normPixels;
        }
        
         /* return an array of blue pixels of the images divided by 255 */
        public double[] bluePixels(int[] Pixels, double max,int height, int width){
            int [] BluePix = new int[height*width];
            double[] normPixels = new double[height*width];
            
            for ( int i = 0 ; i < height*width ; i++){
                System.arraycopy(Pixels, (i*4)+2, BluePix, i, 1);
                normPixels[i] = BluePix[i]/ (double)max ;
            }
            
            return normPixels;
        }
        
         /* return an array of alpha of the images divided by 255 */
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
      
      