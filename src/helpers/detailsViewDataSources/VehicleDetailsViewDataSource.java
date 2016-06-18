package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Vehicule;
import models.User;

import java.util.ArrayList;

/**
 * Created by Salim on 6/15/16.
 */
public class VehicleDetailsViewDataSource implements DetailsViewDataSource<Vehicule> {

    // Attributes

    private ArrayList<Vehicule> items;

    // Getters

    @Override
    public ArrayList<Vehicule> getItems() {
        return items;
    }


    // Constructors

    public VehicleDetailsViewDataSource(ArrayList<Vehicule> items) {
        this.items = items;
    }

    // Interface Methods

    @Override
    public String valueOfSummaryAtIndex(int index) {
        long count;
        switch (index) {
            case 0: count = items.size(); break;
            case 1: count = items.stream().filter(p -> p.getState() == Vehicule.State.Bad).count(); break;
            default: return null;
        }
        return "" + count;
    }

    @Override
    public String nameOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "Vehicles";
            case 1: return "In bad shape";
            default: return null;
        }
    }

    public String[] getTableViewColumns() {
        String[] array = {"id", "type", "price", "state", "currentStock", "totalStock"};
        return array;
    }

    public PropertyValueFactory getPropertyValueFactoryForColumn(String colName) {
        switch (colName) {
            case "id": return new PropertyValueFactory<Vehicule, String>("id");
            case "type": return new PropertyValueFactory<Vehicule, String>("type");
            case "price": return new PropertyValueFactory<Vehicule, String>("price");
            case "state": return new PropertyValueFactory<Vehicule, String>("state");
            case "currentStock": return new PropertyValueFactory<Vehicule, String>("currentStock");
            case "totalStock": return new PropertyValueFactory<Vehicule, String>("totalStock");
            default: return null;
        }
    }

    @Override
    public void reloadItems() {

    }
}
