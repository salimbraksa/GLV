package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Employee;
import services.stores.EmployeeStore;

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

    public EmployeeDetailsViewDataSource() {
        reloadItems();
    }

    // DetailsViewDataSource Interface Methods

    @Override
    public void reloadItems() {
        items = EmployeeStore.sharedInstance().findAll();
    }

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

}
