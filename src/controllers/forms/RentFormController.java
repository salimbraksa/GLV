package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.Customer;
import models.Rent;
import models.User;
import models.Vehicule;
import services.stores.CustomerStore;
import services.stores.VehiculeStore;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static helpers.AppModels.Vehicle;

/**
 * Created by Salim on 6/15/16.
 */
public class RentFormController extends FormController<Rent> implements Initializable {

    // View

    @FXML private ComboBox<Vehicule> vehicleBox;
    @FXML private ComboBox<Customer> customerBox;
    @FXML private TextField pickup;
    @FXML private TextField drop;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;

    // Initializers

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        List<Vehicule> vehicles = VehiculeStore.sharedInstance().findAll()
                .stream().filter(p -> p.getCurrentStock() != 0).collect(Collectors.toList());
        for (Vehicule vehicule : vehicles) {
            vehicleBox.getItems().add(vehicule);
        }

        ArrayList<Customer> customers = CustomerStore.sharedInstance().findAll();
        for (Customer customer : customers) {
            customerBox.getItems().add(customer);
        }

    }

    // Overriding Super Methods

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Rent rent = (Rent) model;

        vehicleBox.setValue(VehiculeStore.sharedInstance().find(rent.getVehiculeId()));
        customerBox.setValue(CustomerStore.sharedInstance().find(rent.getCustomerId()));
        pickup.setText(rent.getPickupLocation());
        drop.setText(rent.getDropLocation());

        LocalDate startLocalDate = rent.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        startDate.setValue(startLocalDate);

        LocalDate endLocalDate = rent.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        endDate.setValue(endLocalDate);

    }

}
