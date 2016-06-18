package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Created by Salim on 6/16/16.
 */
public class SupplierDetailsViewDataSource implements DetailsViewDataSource<Supplier> {

    // Attributes

    private ArrayList<Supplier> items;

    // Getters

    @Override
    public ArrayList<Supplier> getItems() {
        return items;
    }


    // Constructors

    public SupplierDetailsViewDataSource(ArrayList<Supplier> items) {
        this.items = items;
    }

    // Interface Methods

    @Override
    public String valueOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "" + items.size();
            case 1: return "";
            default: return null;
        }
    }

    @Override
    public String nameOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "Suppliers";
            case 1: return "";
            default: return null;
        }
    }

    @Override
    public void reloadItems() {

    }

    public String[] getTableViewColumns() {
        String[] array = {"id", "name", "email", "phone", "address"};
        return array;
    }

    public PropertyValueFactory getPropertyValueFactoryForColumn(String colName) {
        switch (colName) {
            case "id": return new PropertyValueFactory<Supplier, String>("id");
            case "name": return new PropertyValueFactory<Supplier, String>("name");
            case "email": return new PropertyValueFactory<Supplier, String>("email");
            case "phone": return new PropertyValueFactory<Supplier, String>("phone");
            case "address": return new PropertyValueFactory<Supplier, String>("address");
            default: return null;
        }
    }

}
