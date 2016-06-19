package services.FetchImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import models.Vehicule;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by chaymaebz on 14/06/16.
 */
public class ImageFetch {

    // Singleton Implementation

    private static ImageFetch singleton = new ImageFetch();
    public static ImageFetch sharedInstance() {
        return singleton;
    }

    private ImageFetch() {
    }

    public Image getImage(String imageName){

        File sourceFile = new File(System.getProperty("user.dir")+"/ressources/images/"+imageName);
        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return image;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;

    }

}
