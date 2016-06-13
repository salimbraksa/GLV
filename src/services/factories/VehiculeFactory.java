package services.factories;

import models.Vehicule;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

        if (result.next()){
            int id = result.getInt("id");
            String type = result.getString("type");
            Double price = result.getDouble("price");
            Vehicule.State state = Vehicule.State.valueOf(result.getString("state"));
            String image_name = result.getString("image_name");

            return new Vehicule(id,type,price,state,image_name);
        }
        else {
            return null;
        }
    }

     public ArrayList<Vehicule> getTransformedValues() throws SQLException {
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        while (result.next()){
            Vehicule vehicule = getTransformedValue();
            vehicules.add(vehicule);
        }

        return vehicules;
    }


}
