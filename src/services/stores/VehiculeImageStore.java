package services.stores;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Vehicule;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * Created by chaymaebz on 13/06/16.
 */
public class VehiculeImageStore {

    // Singleton implementation

    private static VehiculeImageStore singleton = new VehiculeImageStore();
    public static VehiculeImageStore sharedInstance(){ return singleton; }
    private VehiculeImageStore() { }

    // Methodes

    public Image setVehiculeImageToStore(Vehicule vehicule, Stage stage) {

        // Set up file chooser
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG);
        File sourceFile = fileChooser.showOpenDialog(stage);

        if(sourceFile!=null){
            // copy the Image to /images directory

            String newPath = System.getProperty("user.dir")+"/ressources/images/"+vehicule.getId()+"."+FilenameUtils.getExtension(sourceFile.getPath());
            File destFile = new File(newPath);

            // Copy from source file to destination file
            try {
                FileUtils.copyFile(sourceFile, destFile);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        // Get the image out of the file chooser
        try {
            BufferedImage bufferedImage = ImageIO.read(sourceFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            return image;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    public void removeVehicleImage(Vehicule vehicle) {

        // Get path
        String newPath = System.getProperty("user.dir") + "/ressources/images/"+vehicle.getId()+".jpg";
        File file = new File(newPath);

        // Remove it
        try {
            FileUtils.forceDelete(file);
        } catch ( IOException e ) {
            e.printStackTrace();
        }

    }

}
