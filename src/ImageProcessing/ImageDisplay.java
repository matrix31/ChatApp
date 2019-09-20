
/* UnderWater Chat App | Franck Bourzat | IMDEA Network */

/* Display images on a Jframe when it is received */

package ImageProcessing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageDisplay {
 
    public ImageDisplay (){
    }
    
    public void displayImage(String path, String fileName) throws IOException {
        
                                    
        BufferedImage bimg = ImageIO.read(new File("./ChatApp/Files/Received",fileName));
   
        int width = bimg.getWidth(); 
        int height = bimg.getHeight();                                
         
        /* Construction of the image */
        JFrame imageFrame = new JFrame();
        imageFrame.setTitle(fileName);
        imageFrame.setSize(width, height);
        imageFrame.setLocationRelativeTo(null);
        imageFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         
        /* Display */
        JLabel label = new JLabel(new ImageIcon(bimg));
        imageFrame.add(label);
        imageFrame.setVisible(true);
        
    }
    
}