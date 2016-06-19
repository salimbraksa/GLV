package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import models.*;
import models.Rent;
import services.stores.CustomerStore;
import services.stores.RentStore;
import services.stores.VehiculeStore;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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

    // User Interaction

    @FXML
    private void doneAction() {

        // Gather data from text fields
        Rent rent = buildRent();

        // Add this employee to the database
        RentStore.sharedInstance().create(rent);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    @FXML
    public void editAction() {

        // Gather data from text fields
        Rent rent = buildRent();

        // Add this employee to the database
        RentStore.sharedInstance().update(rent.getId(), rent);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    private Rent buildRent() {

        String pickup = this.pickup.getText();
        String drop = this.drop.getText();
        Customer customer = this.customerBox.getValue();
        Vehicule vehicle = this.vehicleBox.getValue();

        LocalDate localStartDate = this.startDate.getValue();
        LocalDate localEndDate = this.endDate.getValue();
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Rent rent;
        // Instantiate the employee

        int id = -1;
        if ( model instanceof Rent ) {
            id = ((Rent) model).getId();
        }
        rent = new Rent(id, vehicle.getId(), customer.getId(), startDate, endDate, pickup, drop);

        return rent;

    }

}
