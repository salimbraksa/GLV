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

        int id = result.getInt("id");
        String type = result.getString("type");
        double price = result.getDouble("price");
        int currentStock = result.getInt("currentStock");
        int totalStock = result.getInt("totalStock");
        String image = result.getString("image");
        Vehicule.State state = Vehicule.State.valueOf(result.getString("state"));

        return new Vehicule(id,type,price,state, currentStock, totalStock, image);

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
