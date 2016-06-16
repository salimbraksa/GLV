package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Rent;
import models.Rent;

import java.util.ArrayList;

/**
 * Created by Salim on 6/15/16.
 */
public class RentDetailsViewDataSource implements DetailsViewDataSource<Rent> {

    // Attributes

    private ArrayList<Rent> items;

    // Getters

    @Override
    public ArrayList<Rent> getItems() {
        return items;
    }


    // Constructors

    public RentDetailsViewDataSource(ArrayList<Rent> items) {
        this.items = items;
    }

    // Interface Methods

    @Override
    public String valueOfSummaryAtIndex(int index) {
        long count;
        switch (index) {
            case 0: count = items.size(); break;
            case 1: count = 0; break;
            default: return null;
        }
        return "" + count;
    }

    @Override
    public String nameOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "Rents";
            case 1: return "Overdue";
            default: return null;
        }
    }

    @Override
    public String[] getTableViewColumns() {
        String[] array = {"id", "vehiculeId", "customerId", "startDate", "endDate", "pickupLocation", "dropLocation"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryForColumn(String colName) {
        return new PropertyValueFactory<Rent, String>(colName);
    }

}