package graphics;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

class MergedIcons {

    public BufferedImage merge(Image img1, Image img2) throws Exception {
        BufferedImage imgBG = this.toBufferedImage(img1);
        BufferedImage imgFG = this.toBufferedImage(img2);
        // For simplicity we will presume the images are of identical size
        BufferedImage combinedImage = new BufferedImage( 
                imgBG.getWidth(), 
                imgBG.getHeight(), 
                BufferedImage.TYPE_INT_ARGB );
        Graphics2D g = combinedImage.createGraphics();
        g.drawImage(imgBG,0,0,null);
        g.drawImage(imgFG,0,0,null);
        g.dispose();
       return combinedImage;
    }
    
    public MergedIcons(){};
    
    public BufferedImage toBufferedImage(Image img)
    {
        if (img instanceof BufferedImage)
        {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }
}
