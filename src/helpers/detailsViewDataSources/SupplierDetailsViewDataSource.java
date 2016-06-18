package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Supplier;
import services.stores.SupplierStore;

import java.util.ArrayList;

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

    public SupplierDetailsViewDataSource() {
        reloadItems();
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
        items = SupplierStore.sharedInstance().findAll();
    }

}
