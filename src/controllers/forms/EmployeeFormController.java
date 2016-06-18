package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Admin;
import models.Employee;
import models.Manager;
import models.User;
import services.stores.EmployeeStore;

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

    // User Interaction

    @FXML
    void doneAction() {

        // Gather data from text fields
        Employee employee = buildEmployee();

        // Add this employee to the database
        EmployeeStore.sharedInstance().create(employee);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    @Override
    public void editAction() {

        Employee employee = buildEmployee();
        EmployeeStore.sharedInstance().update(employee.getId(), employee);
        delegate.didUpdateDatabase();
        cancelAction();

    }

    // Helpers

    private Employee buildEmployee() {

        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        User.Sexe gender = this.gender.getValue();
        String password = this.password.getText();
        Employee.Role role = this.role.getValue();
        Employee employee = null;

        // Instantiate the employee
        int id = -1;
        if ( model instanceof Employee ) {
            id = ((Employee) model).getId();
        }
        if (role == Employee.Role.Manager) {
            employee = new Manager(id, firstName, lastName, gender, email, phone, password);
        } else if (role == Employee.Role.Admin) {
            employee = new Admin(id, firstName, lastName, gender, email, phone, password);
        }
        return employee;

    }

}
