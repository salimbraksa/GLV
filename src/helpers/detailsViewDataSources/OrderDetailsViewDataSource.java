package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Order;

import java.util.ArrayList;

/**
 * Created by Salim on 6/15/16.
 */
public class OrderDetailsViewDataSource implements DetailsViewDataSource<Order> {

    // Attributes

    private ArrayList<Order> items;

    // Getters

    @Override
    public ArrayList<Order> getItems() {
        return items;
    }


    // Constructors

    public OrderDetailsViewDataSource(ArrayList<Order> items) {
        this.items = items;
    }

    // Interface Methods

    @Override
    public String valueOfSummaryAtIndex(int index) {
        long count;
        switch (index) {
            case 0: count = items.stream().filter(p -> p.getType() == Order.Type.Order).count(); break;
            case 1: count = items.stream().filter(p -> p.getType() == Order.Type.Lease).count(); break;
            default: return null;
        }
        return "" + count;
    }

    @Override
    public String nameOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "Orders";
            case 1: return "Leases";
            default: return null;
        }
    }

    @Override
    public String[] getTableViewColumns() {
        String[] array = {"id", "cost", "supplierId", "vehiculeId", "date", "endDate"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryForColumn(String colName) {
        return new PropertyValueFactory<Order, String>(colName);
    }

}
