import controllers.Controller;
import helpers.ControllerLoader;
import helpers.shared.Shared;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Employee;
import models.Order;
import models.Supplier;
import models.Vehicule;
import services.CanCanCan.CanCanCan;
import services.factories.OrderFactory;
import services.stores.EmployeeStore;
import services.stores.OrderStore;
import services.stores.VehiculeStore;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ControllerLoader loader = new ControllerLoader("/views/login/login.fxml");
        primaryStage.setTitle("Login");
        primaryStage.setScene(loader.getScene());
        primaryStage.show();
        primaryStage.setResizable(false);

        Object supp = new Supplier(1, "yo", "yo", "yo", "y");
        for (Field field : supp.getClass().getDeclaredFields()) {
            field.setAccessible(true); // You might want to set modifier to public first.
            Object value = field.get(supp);
            if (value != null) {
                System.out.println(field.getName() + "=" + value);
            }
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

}
