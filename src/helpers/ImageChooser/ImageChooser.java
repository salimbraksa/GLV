package helpers.ImageChooser;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by chaymaebz on 13/06/16.
 */
public class ImageChooser {

    // singleton implementation

    static private ImageChooser singleton = new ImageChooser();

    static public ImageChooser sharedInstance(){ return singleton;}

    private Image image;

    private ImageChooser(){ }

    //methods

    public Image getImage() {

        FileChooser fileChooser = new FileChooser();

        Image image = null;

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return image;

    }



}
