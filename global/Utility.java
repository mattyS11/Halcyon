package global;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
* A class for the useful tools and methods.
* @version 1.0
*/
public final class Utility
{
    private Utility(){}
    //Methods
    /**
    * Brings in an image from the file system
    * @param  fileName      The name of the image to load.
    * @return an image loaded from the file system. Quits the game if it cannot
    */
    public static BufferedImage loadImage(String fileName)
    {
        try
        {
            return  ImageIO.read(new FileInputStream(fileName));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
