package controllers.forms;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import models.Supplier;
import services.stores.SupplierStore;


/**
 * Created by Salim on 6/15/16.
 */
public class SupplierFormController extends FormController<Supplier> {

    // View

    @FXML private TextField name;
    @FXML private TextField email;
    @FXML private TextField phone;
    @FXML private TextField address;

    // Overriding Super Methods

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Supplier supplier = (Supplier) model;

        name.setText(supplier.getName());
        email.setText(supplier.getEmail());
        phone.setText(supplier.getPhone());
        address.setText(supplier.getAddress());

    }
    
    // User Interaction

    @FXML
    public void doneAction() {

        // Gather data from text fields
        Supplier supplier = buildSupplier();

        // Add this employee to the database
        SupplierStore.sharedInstance().create(supplier);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }

    @FXML
    public void editAction() {

        // Gather data from text fields
        Supplier supplier = buildSupplier();

        // Add this employee to the database
        SupplierStore.sharedInstance().update(supplier.getId(), supplier);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        cancelAction();

    }
    
    // Helpers

    private Supplier buildSupplier() {

        String name = this.name.getText();
        String email = this.email.getText();
        String phone = this.phone.getText();
        String address = this.address.getText();
        Supplier supplier;

        int id = -1;
        if ( model instanceof Supplier ) {
            id = ((Supplier) model).getId();
        }
        supplier = new Supplier(id, name, phone, email, address);

        return supplier;

    }

}
