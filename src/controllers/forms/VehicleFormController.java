package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import models.Vehicule;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * Created by Salim on 6/15/16.
 */
public class VehicleFormController extends FormController<Vehicule> implements Initializable {

    // View

    @FXML private TextField type;
    @FXML private TextField price;
    @FXML private ComboBox<Vehicule.State> state;
    @FXML private TextField currentStock;
    @FXML private TextField totalStock;

    // Initializer

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        state.getItems().addAll(Vehicule.State.Good, Vehicule.State.Bad);
    }


    // Overriding Super Methods

    @Override
    public void setEditable(boolean editable) {
        super.setEditable(editable);
    }

    @Override
    public void setModel(Object model) {
        super.setModel(model);
        Vehicule vehicle = (Vehicule) model;

        type.setText(vehicle.getType());
        price.setText("" + vehicle.getPrice());
        state.setValue(vehicle.getState());
        currentStock.setText(""+vehicle.getCurrentStock());
        totalStock.setText(""+vehicle.getTotalStock());

    }

}
