import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.stores.EmployeeStore;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("controllers/login/login.fxml"));
        primaryStage.setTitle("New World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setResizable(false);
        EmployeeStore.sharedInstance().filterBy(EmployeeStore.FilterOption.Id, "1");

    }

    public static void main(String[] args) {
        launch(args);
    }

}
