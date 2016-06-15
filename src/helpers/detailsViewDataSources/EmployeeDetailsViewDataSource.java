package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
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
        switch (index) {
            case 0: return "3";
            case 1: return "10";
            default: return null;
        }
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
