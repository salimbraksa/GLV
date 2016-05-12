package services.stores;

import models.Vehicule;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 11/05/16.
 */
public class VehiculeStore implements StoreType<Vehicule> {

    //singleton implementation

    static private VehiculeStore singleton = new VehiculeStore();

    static public VehiculeStore sharedInstance() {
        return singleton;
    }

    private VehiculeStore() {
    }

    //database manager

    Mysql mysql = Mysql.sharedInstance();


    @Override
    public void create(Vehicule object) {

        String request = "INSERT INTO vehicule (type, price, state) VALUES ('" + object.getType() + "', '" + object.getPrice() +
                "', '" + object.getState().rawValue() + "');";
        mysql.executeQuery(request);
        mysql.disconnect();
    }

    @Override
    public void delete(int id) {
        UserStore.sharedInstance().delete(id);
    }

    @Override
    public void update(int id, Vehicule object) {

        mysql.executeQuery("UPDATE vehicule" +
                "SET type=" + object.getType() + ", price=" + object.getPrice() + ", state" + object.getState().rawValue() +
                "WHERE id=" + id + ";");
        mysql.disconnect();

    }

    @Override
    public Vehicule find(int id) {
        String query = "SELECT * FROM vehicule WHERE id=" + id + ";";
        ResultSet result = mysql.executeQuery(query);

        //retrieve data by column name

        try {
            //if there is a row match
            if (result.next()) {
                String type = result.getString("type");
                Double price = result.getDouble("price");
                Vehicule.State state = Vehicule.State.valueOf(result.getString("state"));
                mysql.disconnect();
                //return instance of the vehicule
                return new Vehicule(id, type, price, state);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        //if no row match then return null
        return null;
    }

    @Override
    public ArrayList<Vehicule> findAll() {

        ArrayList<Vehicule> listVehicule = new ArrayList<>();

        //get list of id of all vehicules
        ResultSet result = mysql.executeQuery("SELECT id FROM vehicule");

        try {
            //for every row match
            while (result.next()) {
                int id = result.getInt("id");

                //instance of that vehicule
                Vehicule vehicule = find(id);

                //add the vehicule to the Arraylist
                listVehicule.add(vehicule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return listVehicule;
    }

}
