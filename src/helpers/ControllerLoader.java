package helpers;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.jetbrains.annotations.Nullable;
import sun.applet.Main;

import java.io.IOException;

/**
 * Created by Salim on 5/18/16.
 */
public class ControllerLoader {

    private String path;
    private Scene scene;
    private Controller controller;

    @Nullable public String getPath() {
        return path;
    }

    @Nullable public Scene getScene() {
        return scene;
    }

    @Nullable public Controller getController() {
        return controller;
    }

    public ControllerLoader(String path)  {
        this.path = path;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root;
        try {
            root = loader.load();
            if ( root != null ) {
                controller = loader.getController();
                scene = new Scene(root, 600, 400);
                if (controller != null) {
                    controller.view = scene.getRoot();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
