package services.factories;

import models.Vehicule;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by chaymaebz on 12/05/16.
 */
public class VehiculeFactory {

    // Attributes

    ResultSet result;

    // Constructor

    public VehiculeFactory(ResultSet result){
        this.result = result;
    }

     // Methods

    public Vehicule getTransformedValue() throws SQLException {
        return null;
    }

}
