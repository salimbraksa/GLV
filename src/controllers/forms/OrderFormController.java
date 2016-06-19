package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import models.*;
import services.stores.OrderStore;
import services.stores.SupplierStore;
import services.stores.VehiculeStore;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * Created by Salim on 6/15/16.
 */
public class OrderFormController extends FormController<Order> implements Initializable {

    // View

    @FXML private ComboBox<Vehicule> vehicleBox;
    @FXML private ComboBox<Supplier> supplierBox;
    @FXML private TextField cost;
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

        ArrayList<Supplier> suppliers = SupplierStore.sharedInstance().findAll();
        for (Supplier supplier : suppliers) {
            supplierBox.getItems().add(supplier);
        }

    }

    // Overriding Super Methods

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Order order = (Order) model;

        vehicleBox.setValue(VehiculeStore.sharedInstance().find(order.getVehiculeId()));
        supplierBox.setValue(SupplierStore.sharedInstance().find(order.getSupplierId()));
        cost.setText(""+order.getCost());

        LocalDate startLocalDate = order.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        startDate.setValue(startLocalDate);

        if (order instanceof Lease ) {
            Lease lease = (Lease) order;
            LocalDate endLocalDate = lease.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            endDate.setValue(endLocalDate);
        }

    }

    // User Interaction

    @FXML
    private void doneAction() {

        // Gather data from text fields
        Order order = buildOrder();

        // Add this employee to the database
        OrderStore.sharedInstance().create(order);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    @FXML
    public void editAction() {

        // Gather data from text fields
        Order order = buildOrder();

        // Add this employee to the database
        OrderStore.sharedInstance().update(order.getId(), order);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    // Helpers

    private Order buildOrder() {

        double cost = Double.parseDouble(this.cost.getText());
        Supplier supplier = this.supplierBox.getValue();
        Vehicule vehicle = this.vehicleBox.getValue();

        LocalDate localStartDate = this.startDate.getValue();
        LocalDate localEndDate = this.endDate.getValue();
        Date startDate = Date.from(localStartDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = null;
        if (localEndDate != null ) {
            endDate = Date.from(localEndDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        }

        Order order;
        // Instantiate the employee
        int id = -1;
        if ( model instanceof Order ) {
            id = ((Order) model).getId();
        }

        if (localEndDate == null) {
            order = new Order(id, cost, startDate, supplier.getId(), vehicle.getId());
        } else {
            System.out.println(endDate);
            order = new Lease(id, cost, startDate, supplier.getId(), vehicle.getId(), endDate);
        }

        return order;

    }
    
}
