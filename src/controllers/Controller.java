package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Salim on 5/18/16.
 */
public class Controller implements Initializable {

    // Views

    @FXML
    private Parent root;
    private Scene scene;

    public Scene getScene() {
        return scene;
    }

    //

    public String fxmlPath = "";

    //

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    // Builders

    public void loadScene() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        if ( root != null ) {
            scene = new Scene(root, 600, 400);
        }
    }

}
