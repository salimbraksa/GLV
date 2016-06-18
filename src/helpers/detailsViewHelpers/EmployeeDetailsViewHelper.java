package helpers.detailsViewHelpers;

import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;

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

    public Employee castedItem(Object item) {
        return (Employee) item;
    }

}
