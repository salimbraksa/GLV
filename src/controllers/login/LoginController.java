package controllers.login;

import controllers.Controller;
import helpers.ControllerLoader;
import helpers.SBError;
import helpers.shared.Shared;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;
import services.authentication.Authentication;
import services.authentication.AuthenticationDelegate;

/**
 * Created by Salim on 4/26/16.
 */
public class LoginController extends Controller implements AuthenticationDelegate {

    // Views

    public PasswordField passwordField;
    public TextField usernameField;

    // User Interactions

    public void cancelAction() {
        Platform.exit();
    }

    public void signinAction() {

        Authentication authenticator = new Authentication();
        authenticator.delegate = this;
        authenticator.authenticateWithCredentials(usernameField.getText(), passwordField.getText());

    }

    // Authentication Delegate

    @Override
    public void authenticationDidSucceedWithUser(User user) {

        // Set this user
        Shared.currentUser = user;

        // Open dashboard
        ControllerLoader loader = new ControllerLoader("/views/home/home.fxml");
        Stage homeStage = new Stage();
        Scene homeScene = loader.getScene();
        homeStage.setTitle("Yo");
        homeStage.setScene(loader.getScene());
        homeStage.show();

        // Close current window
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.close();


    }

    @Override
    public void authenticationDidFailWithError(Error error) {

        String title = ((SBError) error).getTitle();
        String message = error.getMessage();

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

}
