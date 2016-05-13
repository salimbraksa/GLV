package services.factories;

import models.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 12/05/16.
 */
public class SupplierFactory {
    // Attributes

    ResultSet result;

    // Constructor

    public SupplierFactory(ResultSet result){
        this.result = result;
    }

    // Methods

    public Supplier getTransformedValue() throws SQLException {

        if (result.next()) {

            int id = result.getInt("id");
            String name = result.getString("name");
            String phone = result.getString("phone");
            String email = result.getString("email");
            String adress = result.getString("adress");

            return new Supplier(id, name, phone, email, adress);
        } else {
            return null;
        }
    }

    public ArrayList<Supplier> getTransformedValues() throws SQLException {
        ArrayList<Supplier> suppliers = new ArrayList<>();

        while (result.next()){
            Supplier supplier = getTransformedValue();
            suppliers.add(supplier);
        }

        return suppliers;
    }

}
