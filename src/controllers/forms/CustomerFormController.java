package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Circle;
import models.Customer;
import models.Employee;
import models.User;
import services.stores.CustomerStore;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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

    // User Interaction

    @FXML
    public void doneAction() {

        // Gather data from text fields
        Customer customer = buildCustomer();

        // Add this employee to the database
        CustomerStore.sharedInstance().create(customer);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    // Helpers

    private Customer buildCustomer() {

        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        User.Sexe gender = this.gender.getValue();
        String cin = this.cin.getText();
        LocalDate localBirthday = this.birthday.getValue();
        Date birthday = Date.from(localBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Customer.Diligence diligence;
        if ( this.diligenceBox != null) {
            diligence = this.diligenceBox.getValue();
        } else {
            diligence = Customer.Diligence.High;
        }

        Customer customer;
        // Instantiate the employee
        int id = -1;
        if ( model instanceof Customer ) {
            id = ((Customer) model).getId();
        }
        customer = new Customer(id, firstName, lastName, gender, email, phone, diligence, cin, birthday);

        return customer;

    }

}
