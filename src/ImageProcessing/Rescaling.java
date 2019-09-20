
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

/* Rescaling file acording to the need of the uder (height,width) */

package ImageProcessing;

import static View.ScalingOption.height;
import static View.ScalingOption.width;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Rescaling {
   public String fileName;
   public int i = 0 ; 
    
    
    public Rescaling(String fileName){
        this.fileName=fileName;
            
    }
    
    public File RescaleProcess(File selectedFile) throws IOException, InterruptedException{
        

        BufferedImage image = ImageIO.read(selectedFile);
        int intHeight = Integer.parseInt(height);
        int intWidth = Integer.parseInt(width);
                        
        /*check items....*/
        BufferedImage Image = new BufferedImage( intHeight, intWidth, BufferedImage.TYPE_INT_RGB );
        Graphics2D tGraphics2D = Image.createGraphics(); //create a graphics object to paint to
        tGraphics2D.setBackground( Color.WHITE );
        tGraphics2D.setPaint( Color.WHITE );
        tGraphics2D.fillRect( 0, 0, intHeight, intWidth );
        tGraphics2D.setRenderingHint( RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR );
        tGraphics2D.drawImage( image, 0, 0, intHeight, intWidth, null ); //draw the image scaled
                        
        String ext = "";
        int point = fileName.lastIndexOf('.');
        if (i > 0) {
            ext = fileName.substring(0,point+1);
        }
        
        /* regular expression to have the name of the file witout the .extension */
        String fileNameWithoutExt = fileName.replaceFirst("[.][^.]+$", "");
                           
                        
        File file = new File("./ChatApp/Files/Rescaled",fileNameWithoutExt.concat(height).concat("x").concat(width).concat(".jpeg"));
        ImageIO.write( Image, "JPG", file ); //write the image to a file            
        return file ; 
         
    }
    
}
