package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;

import java.util.ArrayList;

/**
 * Created by Salim on 6/15/16.
 */
public class EmployeeDetailsViewDataSource implements DetailsViewDataSource<Employee> {

    // Attributes

    private ArrayList<Employee> items;

    // Getters

    @Override
    public ArrayList<Employee> getItems() {
        return items;
    }


    // Constructors

    public EmployeeDetailsViewDataSource(ArrayList<Employee> items) {
        this.items = items;
    }

    // Interface Methods

    @Override
    public String valueOfSummaryAtIndex(int index) {
        long count;
        switch (index) {
            case 0: count = items.stream().filter(p -> p.getRole() == Employee.Role.Manager).count(); break;
            case 1: count = items.stream().filter(p -> p.getRole() == Employee.Role.Admin).count(); break;
            default: return null;
        }
        return "" + count;
    }

    @Override
    public String nameOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "Managers";
            case 1: return "Admins";
            default: return null;
        }
    }

    @Override
    public String[] getTableViewColumns() {
        String[] array = {"id", "first name", "last name", "sexe", "email", "phone", "role"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryForColumn(String colName) {
        switch (colName) {
            case "id": return new PropertyValueFactory<Employee, String>("id");
            case "first name": return new PropertyValueFactory<Employee, String>("firstName");
            case "last name": return new PropertyValueFactory<Employee, String>("lastName");
            case "sexe": return new PropertyValueFactory<Employee, String>("sexe");
            case "email": return new PropertyValueFactory<Employee, String>("email");
            case "phone": return new PropertyValueFactory<Employee, String>("phone");
            case "role": return new PropertyValueFactory<Employee, String>("role");
            default: return null;
        }
    }

}
