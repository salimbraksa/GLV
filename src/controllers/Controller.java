package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Salim on 5/18/16.
 */
public class Controller  {

    // Attributes

    public Parent view;
    public Stage window;

    // Methods

    public void show(double width, double height) {

        Stage stage = new Stage();
        window = stage;
        stage.setScene(view.getScene());
        stage.show();

    }

}
