package services.stores;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Vehicule;

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

    public void setVehiculeImageToStore(Vehicule vehicule, Stage stage) {
        FileChooser fileChooser = new FileChooser();

        File sourceFile = fileChooser.showOpenDialog(stage);

        if(sourceFile!=null){


            // copy the Image to /images directory

            String newPath = System.getProperty("user.dir")+"/ressources/images_tmp/"+vehicule.getId();

            File destFile = new File(newPath);

            try {
                copyDirectory(sourceFile,destFile);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void confirmVehiculeImage(Vehicule vehicule){

        String srcUrl = System.getProperty("user.dir")+"/ressources/images_tmp/"+vehicule.getId();
        String destUrl = System.getProperty("user.dir")+"/ressources/images/"+vehicule.getId();

        File srcFile = new File(srcUrl);
        File destFile = new File(destUrl);

        try {
            copyDirectory(srcFile,destFile);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void copyDirectory(File sourceLocation , File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++) {
                copyDirectory(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }
        } else {

            InputStream in = new FileInputStream(sourceLocation);
            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }
    }



}
