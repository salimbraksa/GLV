package services.stores;


import helpers.interfaces.Filterable;
import models.Vehicule;
import services.factories.VehiculeFactory;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 11/05/16.
 */
public class VehiculeStore implements StoreType<Vehicule>, Filterable<Vehicule> {

    //singleton implementation

    static private VehiculeStore singleton = new VehiculeStore();

    static public VehiculeStore sharedInstance(){ return singleton; }

    private VehiculeStore() { }

    //database manager

    Mysql mysql = Mysql.sharedInstance();


    @Override
    public void create(Vehicule object) {

        String request = "INSERT INTO vehicule (type, price, state) VALUES ('"+object.getType()+"', '"+object.getPrice()+
                "', '"+object.getState().rawValue()+"');";
        mysql.executeQuery(request);
    }

    @Override
    public void delete(int id) {
        mysql.executeUpdate("DELETE FROM vehicule WHERE id="+id+";");
    }

    @Override
    public void update(int id, Vehicule object) {

        mysql.executeQuery("UPDATE vehicule" +
                "SET type="+object.getType()+", price="+object.getPrice()+", state"+object.getState().rawValue()+
                "WHERE id="+id+";");

    }

    @Override
    public Vehicule find(int id) {

        String query = "SELECT * FROM vehicule where id="+id+";";
        ResultSet result = mysql.executeQuery(query);
        Vehicule vehicule = null;
        try {
            if (result.next()){
                vehicule = new VehiculeFactory(result).getTransformedValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return vehicule;
    }

    @Override
    public ArrayList<Vehicule> findAll() {
        String query = "SELECT * FROM vehicule";
        ResultSet result = mysql.executeQuery(query);
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        try {
            vehicules = new VehiculeFactory(result).getTransformedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return vehicules;
    }


    @Override
    public ArrayList<Vehicule> filterBy(String column, String value) {
        String query = "SELECT * FROM vehicule WHERE "+column+"="+value+";";
        ResultSet result = mysql.executeQuery(query);
        ArrayList<Vehicule> vehicules = new ArrayList<>();

        try {
            vehicules = new VehiculeFactory(result).getTransformedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return vehicules;

    }
}
