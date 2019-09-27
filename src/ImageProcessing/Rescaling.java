
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

/* Rescaling file acording to the needs of the user (height,width) */
/* Very effective to reduce file size for file > 50 kb */

package ImageProcessing;

import static View.ScalingOption.height;
import static View.ScalingOption.width;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Rescaling {
   private final String fileName;

  
    public Rescaling(String fileName){
        this.fileName=fileName;
            
    }
    
    public File RescaleProcess(File selectedFile) throws IOException, InterruptedException{
        

        BufferedImage image = ImageIO.read(selectedFile);
        int intHeight = Integer.parseInt(height);
        int intWidth = Integer.parseInt(width);
                        
        /* Processing */
        BufferedImage Image = new BufferedImage( intHeight, intWidth, BufferedImage.TYPE_INT_RGB );
        Graphics2D tGraphics2D = Image.createGraphics(); //create a graphics object to paint to
        tGraphics2D.fillRect( 0, 0, intHeight, intWidth );
        tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        tGraphics2D.drawImage( image, 0, 0, intHeight, intWidth, null ); //draw the image scaled
                        
        
        /* Regular expression to have the name of the file without the .extension */
        String fileNameWithoutExt = fileName.replaceFirst("[.][^.]+$", "");
                           
        /* Creation of the new file to send */                
        File file = new File("./Files/Rescaled",fileNameWithoutExt.concat(height).concat("x").concat(width).concat(".jpeg"));
        ImageIO.write( Image, "JPG", file ); //write the image to a file            
        return file ; 
         
    }
    
}
