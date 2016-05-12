package services.factories;

import models.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

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
        return null;
    }
}
