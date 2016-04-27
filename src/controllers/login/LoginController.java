package controllers.login;

import javafx.application.Platform;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.jasypt.util.password.StrongPasswordEncryptor;

/**
 * Created by Salim on 4/26/16.
 */
public class LoginController {

    // Views

    public PasswordField passwordField;
    public TextField usernameField;

    // User Interactions

    public void cancelAction() {
        Platform.exit();
    }

    public void signinAction() {

        // Encrypt password
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encryptedPassword = "PUCyqLD04H7KW4zCBQ92V0mvHa/ZCCYnyqcT6YJ+PKkxwy/DQlTbdGi7k3SmMfuY"; // encryptor.encryptPassword(passwordField.getText());

        if (encryptor.checkPassword(passwordField.getText(), encryptedPassword)) {
            System.out.println("Login succeeds");
        } else {
            System.out.println("Failure");
        }

    }

}
