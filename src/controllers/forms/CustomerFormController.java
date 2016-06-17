package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import models.Customer;
import models.Employee;
import models.User;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * Created by Salim on 6/15/16.
 */
public class CustomerFormController extends FormController<Customer> implements Initializable {

    // View

    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private ComboBox<User.Sexe> gender;
    @FXML private TextField cin;
    @FXML private DatePicker birthday;
    @FXML private Circle diligenceColor;
    @FXML private ComboBox<Customer.Diligence> diligenceBox;

    // Initializers

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        gender.getItems().addAll(User.Sexe.Male, User.Sexe.Female);
        if (diligenceBox != null) {
            diligenceBox.getItems().addAll(Customer.Diligence.High, Customer.Diligence.Medium, Customer.Diligence.Low);
        }
    }

    // Overriding Super Methods

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Customer customer = (Customer) model;

        firstName.setText(customer.getFirstName());
        lastName.setText(customer.getLastName());
        email.setText(customer.getEmail());
        phone.setText(customer.getPhone());
        gender.setValue(customer.getSexe());
        cin.setText(customer.getCin());
        LocalDate date = customer.getBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        birthday.setValue(date);

        if (diligenceBox != null) {
            diligenceBox.setValue(customer.getDiligence());
            String hex = customer.getDiligence().hexColor();
            diligenceColor.setStyle("-fx-fill: #" + hex + ";");
        }

    }

}
