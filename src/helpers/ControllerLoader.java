package helpers;

import controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import sun.applet.Main;

import java.io.IOException;

/**
 * Created by Salim on 5/18/16.
 */
public class ControllerLoader {

    private String path;
    private Scene scene;
    private Controller controller;

    public String getPath() {
        return path;
    }

    public Scene getScene() {
        return scene;
    }

    public Controller getController() {
        return controller;
    }

    public ControllerLoader(String path) throws IOException {
        this.path = path;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        Parent root = (Parent)loader.load();
        controller = (Controller) loader.getController();
        scene = new Scene(root, 600, 400);
    }

}
