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

        String request = "INSERT INTO vehicule (type, price, state) VALUES ('"+object.getType()+"', '"+object.getPrice()+
                "', '" + object.getState().rawValue() + "');";
        mysql.executeUpdate(request);
    }

    @Override
    public void delete(int id) {
        String request = "DELETE FROM vehicule WHERE id="+id+";";
        mysql.executeUpdate(request);
    }

    @Override
    public void update(int id, Vehicule object) {
        String query = "UPDATE vehicule " +
                "SET type="+object.getType()+", price="+object.getPrice()+", state"+object.getState().rawValue()+
                " WHERE id=" + id + ";";
        mysql.executeUpdate(query);

    }

    @Override
    public Vehicule find(int id) {
        String query = "SELECT * FROM vehicule WHERE id=" + id + ";";
        ResultSet result = mysql.executeQuery(query);

        Vehicule vehicule = null;
        //retrieve data by column name

        try {
            //if there is a row match
            if (result.next()) {
                String type = result.getString("type");
                Double price = result.getDouble("price");
                Vehicule.State state = Vehicule.State.valueOf(result.getString("state"));

                vehicule = new Vehicule(id, type, price, state);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        //if no row match then it will return null
        return vehicule;
    }

    @Override
    public ArrayList<Vehicule> findAll() {

        ArrayList<Vehicule> listVehicule = new ArrayList<>();

        //get list of id of all vehicules
        ResultSet result = mysql.executeQuery("SELECT id FROM vehicule;");

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
