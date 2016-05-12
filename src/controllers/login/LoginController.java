package controllers.login;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.User;
import services.authentication.Authentication;
import services.authentication.AuthenticationDelegate;

/**
 * Created by Salim on 4/26/16.
 */
public class LoginController implements AuthenticationDelegate {

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
        System.out.println(user.getEmail());
    }

    @Override
    public void authenticationDidFailWithError(Error error) {

        String headerTitle = "Authentication Error";
        String contentTitle = "";

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(null);
        alert.setHeaderText(headerTitle);
        alert.setContentText(contentTitle);
        alert.showAndWait();

    }

}
