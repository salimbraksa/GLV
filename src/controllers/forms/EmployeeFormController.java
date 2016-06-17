package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Employee;
import models.User;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Salim on 6/15/16.
 */
public class EmployeeFormController extends FormController<Employee> implements Initializable {

    // View

    @FXML public TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private ComboBox<User.Sexe> gender;
    @FXML private ComboBox<Employee.Role> role;
    @FXML private PasswordField password;
    @FXML private PasswordField passwordConfirmation;

    // Initializers

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        gender.getItems().addAll(User.Sexe.Male, User.Sexe.Female);
        role.getItems().addAll(Employee.Role.Admin, Employee.Role.Manager);

    }


    // Overriding Super Methods

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Employee employee = (Employee) model;

        firstName.setText(employee.getFirstName());
        lastName.setText(employee.getLastName());
        email.setText(employee.getEmail());
        phone.setText(employee.getPhone());
        gender.setValue(employee.getSexe());
        role.setValue(employee.getRole());

    }

}
