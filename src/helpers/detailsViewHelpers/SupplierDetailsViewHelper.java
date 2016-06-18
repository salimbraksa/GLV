package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import helpers.AppModels;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Supplier;
import services.stores.SupplierStore;

/**
 * Created by Salim on 6/18/16.
 */
public class SupplierDetailsViewHelper extends DetailsViewHelper {

    @Override
    public String[] getTableColumnNames() {
        String[] array = {"id", "name", "email", "phone", "address"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName) {
        return new PropertyValueFactory<Supplier, String>(columnName);
    }

    @Override
    public void delete(Object object) {
        Supplier supplier = castedItem(object);
        if (supplier != null) {
            SupplierStore.sharedInstance().delete(supplier.getId());
        }
    }

    @Override
    public FormController<?> formControllerForOperation(FormController.FormType operation) {
        return AppModels.Supplier.getFormController(operation);
    }

    private Supplier castedItem(Object item) {
        return (Supplier) item;
    }

}
