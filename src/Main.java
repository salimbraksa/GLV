import controllers.Controller;
import helpers.ControllerLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Employee;
import models.Order;
import models.Vehicule;
import services.factories.OrderFactory;
import services.stores.EmployeeStore;
import services.stores.OrderStore;
import services.stores.VehiculeStore;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ControllerLoader loader = new ControllerLoader("/views/login/login.fxml");
        primaryStage.setTitle("Login");
        primaryStage.setScene(loader.getScene());
        primaryStage.show();
        primaryStage.setResizable(false);

    }

    public static void main(String[] args) {
        launch(args);
    }

}
