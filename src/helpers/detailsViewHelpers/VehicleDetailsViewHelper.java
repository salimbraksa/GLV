package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import helpers.AppModels;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Vehicule;
import services.stores.VehiculeStore;

/**
 * Created by Salim on 6/18/16.
 */
public class VehicleDetailsViewHelper extends DetailsViewHelper {

    @Override
    public String[] getTableColumnNames() {
        String[] array = {"id", "type", "price", "state", "currentStock", "totalStock"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName) {
        return new PropertyValueFactory<Vehicule, String>(columnName);
    }

    @Override
    public void delete(Object object) {
        Vehicule vehicle = castedItem(object);
        if (vehicle != null) {
            VehiculeStore.sharedInstance().delete(vehicle.getId());
        }
    }

    @Override
    public FormController<?> formControllerForOperation(FormController.FormType operation) {
        return AppModels.Vehicle.getFormController(operation);
    }

    private Vehicule castedItem(Object item) {
        return (Vehicule) item;
    }

}
