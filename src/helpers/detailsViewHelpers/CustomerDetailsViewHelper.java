package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import helpers.AppModels;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Customer;
import services.stores.CustomerStore;

/**
 * Created by Salim on 6/18/16.
 */
public class CustomerDetailsViewHelper extends DetailsViewHelper {

    @Override
    public String[] getTableColumnNames() {
        String[] array = {"id", "firstName", "lastName", "sexe", "email", "phone", "diligence", "cin", "birthday"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName) {
        return new PropertyValueFactory<Customer, String>(columnName);
    }

    @Override
    public void delete(Object object) {
        Customer customer = castedItem(object);
        if (customer != null) {
            CustomerStore.sharedInstance().delete(customer.getId());
        }
    }

    public FormController<?> formControllerForOperation(FormController.FormType operation) {
        return AppModels.Customer.getFormController(operation);
    }

    private Customer castedItem(Object item) {
        return (Customer) item;
    }

}
