package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import helpers.AppModels;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Rent;
import services.stores.RentStore;

/**
 * Created by Salim on 6/18/16.
 */
public class RentDetailsViewHelper extends DetailsViewHelper {

    @Override
    public String[] getTableColumnNames() {
        String[] array = {"id", "vehiculeId", "customerId", "startDate", "endDate", "pickupLocation", "dropLocation"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName) {
        return new PropertyValueFactory<Rent, String>(columnName);
    }

    @Override
    public void delete(Object object) {
        Rent rent = castedItem(object);
        if (rent != null) {
            RentStore.sharedInstance().delete(rent.getId());
        }
    }

    @Override
    public FormController<?> formControllerForOperation(FormController.FormType operation) {
        return AppModels.Rent.getFormController(operation);
    }

    private Rent castedItem(Object item) {
        return (Rent) item;
    }

}
