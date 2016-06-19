package controllers.forms;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.Vehicule;
import services.FetchImage.ImageFetch;
import services.stores.VehiculeImageStore;
import services.stores.VehiculeStore;

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
    @FXML private ImageView imageView;
    private boolean imageWasInitiallyNull = true;

    // Initializer

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        state.getItems().addAll(Vehicule.State.Good, Vehicule.State.Bad);
    }

    // Overriding Super Methods

    @Override
    public void setModel(Object model) {
        super.setModel(model);

        Vehicule vehicle = (Vehicule) model;

        type.setText(vehicle.getType());
        price.setText("" + vehicle.getPrice());
        state.setValue(vehicle.getState());
        currentStock.setText(""+vehicle.getCurrentStock());
        totalStock.setText(""+vehicle.getTotalStock());

        Image image = ImageFetch.sharedInstance().getImage(vehicle);
        imageWasInitiallyNull = image == null;
        imageView.setImage(image);

    }
    
    // User Interaction

    @FXML
    public void doneAction() {

        // Gather data from text fields
        Vehicule vehicle = buildVehicule();

        // Add this employee to the database
        VehiculeStore.sharedInstance().create(vehicle);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        window.close();

    }

    @Override
    public void cancelAction() {

        // Hack
        if (this.type.isDisabled()) {
        } else {
            if (imageWasInitiallyNull) {
                VehiculeImageStore.sharedInstance().removeVehicleImage((Vehicule) model);
            }
        }

        super.cancelAction();
    }

    @FXML
    public void editAction() {

        // Gather data from text fields
        Vehicule vehicle = buildVehicule();

        // Add this employee to the database
        VehiculeStore.sharedInstance().update(vehicle.getId(), vehicle);

        // Let the delegate handles the rest
        delegate.didUpdateDatabase();

        // Then close this window
        window.close();

    }

    @FXML
    private void chooseImage() {

        Vehicule vehicle = (Vehicule) model;
        if (vehicle == null) { return; }
        Image image = VehiculeImageStore.sharedInstance().setVehiculeImageToStore(vehicle, window);
        if (image != null) {
            imageView.setImage(image);
        }

    }

    // Helpers

    private Vehicule buildVehicule() {

        double price = Double.parseDouble(this.price.getText());
        String type = this.type.getText();
        int currentStock =  Integer.parseInt(this.currentStock.getText());
        int totalStock = Integer.parseInt(this.totalStock.getText());
        Vehicule.State state = this.state.getValue();

        Vehicule vehicle;

        int id = -1;
        if ( model instanceof Vehicule ) {
            id = ((Vehicule) model).getId();
        }
        vehicle = new Vehicule(id, type, price, state, currentStock, totalStock);

        return vehicle;

    }

}
