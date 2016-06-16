package services.FetchImage;

import javafx.scene.image.Image;
import models.Vehicule;

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

    public Image getImage(Vehicule vehicule){
        Image image;
        try{
            image = new Image(System.getProperty("user.dir")+"/ressources/images/"+vehicule.getId());
        }
        catch (Exception e){
            return null;
        }
        return image;
    }

}
