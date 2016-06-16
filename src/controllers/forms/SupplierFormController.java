package controllers.forms;

import javafx.fxml.FXML;

import javafx.scene.control.TextField;
import models.Supplier;


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
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Supplier supplier = (Supplier) model;

        name.setText(supplier.getName());
        email.setText(supplier.getEmail());
        phone.setText(supplier.getPhone());
        address.setText(supplier.getAddress());

    }

}
