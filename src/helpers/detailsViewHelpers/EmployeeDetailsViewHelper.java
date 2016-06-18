package helpers.detailsViewHelpers;

import controllers.forms.FormController;
import helpers.AppModels;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;
import services.stores.EmployeeStore;

/**
 * Created by Salim on 6/18/16.
 */
public class EmployeeDetailsViewHelper extends DetailsViewHelper {

    @Override
    public String[] getTableColumnNames() {
        String[] array = {"id", "firstName", "lastName", "sexe", "email", "phone", "role"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryFromColumnName(String columnName) {
        return new PropertyValueFactory<Employee, String>(columnName);
    }

    @Override
    public void delete(Object object) {
        Employee employee = castedItem(object);
        if (employee != null) {
            EmployeeStore.sharedInstance().delete(employee.getId());
        }
    }

    @Override
    public FormController<?> formControllerForOperation(FormController.FormType operation) {
        return AppModels.Employee.getFormController(operation);
    }

    private Employee castedItem(Object item) {
        return (Employee) item;
    }

}
