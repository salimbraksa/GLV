package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import helpers.AppModels;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Order;
import services.stores.OrderStore;

/**
 * Created by Salim on 6/18/16.
 */
public class OrderDetailsViewHelper extends DetailsViewHelper {

    @Override
    public String[] getTableColumnNames() {
        String[] array = {"id", "cost", "supplierId", "vehiculeId", "date", "endDate"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName) {
        return new PropertyValueFactory<Order, String>(columnName);
    }

    @Override
    public void delete(Object object) {
        Order order = castedItem(object);
        if (order != null) {
            OrderStore.sharedInstance().delete(order.getId());
        }
    }

    public FormController<?> formControllerForOperation(FormController.FormType operation) {
        return AppModels.Order.getFormController(operation);
    }

    private Order castedItem(Object item) {
        return (Order) item;
    }

}
