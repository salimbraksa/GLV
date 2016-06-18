package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Customer;
import models.User;
import services.stores.CustomerStore;

import java.util.ArrayList;

/**
 * Created by Salim on 6/15/16.
 */
public class CustomerDetailsViewDataSource implements DetailsViewDataSource<Customer> {

    // Attributes

    private ArrayList<Customer> items;

    // Getters

    @Override
    public ArrayList<Customer> getItems() {
        return items;
    }


    // Constructors

    public CustomerDetailsViewDataSource() {
        reloadItems();
    }

    // Interface Methods

    @Override
    public String valueOfSummaryAtIndex(int index) {
        long count;
        switch (index) {
            case 0: count = items.stream().filter(p -> p.getSexe() == User.Sexe.Male).count(); break;
            case 1: count = items.stream().filter(p -> p.getSexe() == User.Sexe.Female).count(); break;
            default: return null;
        }
        return "" + count;
    }

    @Override
    public String nameOfSummaryAtIndex(int index) {
        switch (index) {
            case 0: return "Male";
            case 1: return "Female";
            default: return null;
        }
    }

    @Override
    public void reloadItems() {
        items = CustomerStore.sharedInstance().findAll();
    }

}
