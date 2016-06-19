package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Vehicule;
import models.User;
import services.stores.VehiculeStore;

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

    public VehicleDetailsViewDataSource() {
        reloadItems();
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
    @Override
    public void reloadItems() {
        items = VehiculeStore.sharedInstance().findAll();
    }

}
