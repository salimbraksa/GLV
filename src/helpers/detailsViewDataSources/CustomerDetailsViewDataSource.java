package helpers.detailsViewDataSources;

import helpers.interfaces.DetailsViewDataSource;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Customer;
import models.User;

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

    public CustomerDetailsViewDataSource(ArrayList<Customer> items) {
        this.items = items;
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
    public String[] getTableViewColumns() {
        String[] array = {"id", "first name", "last name", "sexe", "email", "phone", "diligence", "cin", "birthday"};
        return array;
    }

    @Override
    public PropertyValueFactory getPropertyValueFactoryForColumn(String colName) {
        switch (colName) {
            case "id": return new PropertyValueFactory<Customer, String>("id");
            case "first name": return new PropertyValueFactory<Customer, String>("firstName");
            case "last name": return new PropertyValueFactory<Customer, String>("lastName");
            case "sexe": return new PropertyValueFactory<Customer, String>("sexe");
            case "email": return new PropertyValueFactory<Customer, String>("email");
            case "phone": return new PropertyValueFactory<Customer, String>("phone");
            case "diligence": return new PropertyValueFactory<Customer, String>("diligence");
            case "cin": return new PropertyValueFactory<Customer, String>("cin");
            case "birthday": return new PropertyValueFactory<Customer, String>("birthday");
            default: return null;
        }
    }

}
