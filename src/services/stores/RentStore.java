package services.stores;

import com.sun.org.apache.regexp.internal.RE;
import helpers.extensions.DateExtensionKt;
import helpers.interfaces.Filterable;
import models.Rent;
import services.factories.RentFactory;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 13/05/16.
 */
public class RentStore implements StoreType<Rent>, Filterable<Rent>{

    // Singleton Implementation

    static private RentStore singleton = new RentStore();

    static public RentStore sharedInstance() {
        return singleton;
    }

    private RentStore() { }

    // Database manager

    Mysql mysql = Mysql.sharedInstance();

    // Store Methods


    @Override
    public void create(Rent object) {
        String start_date = DateExtensionKt.getTimestamp(object.getStartDate());
        String end_date = DateExtensionKt.getTimestamp(object.getEndDate());
        String query = "INSERT INTO rent (vehicule_id,customer_id,start_date,end_date,pickup_location," + ",drop_location) VALUES ('"+object.getVehiculeId()
                +"','"+object.getCustomerId()+"','"+start_date+"','"+end_date+"','"+object.getPickupLocation()
                +"','" +object.getDropLocation()+"');";
        mysql.executeUpdate(query);
    }

    @Override
    public void delete(int id) {
        mysql.executeUpdate("DELETE FROM rent WHERE id="+id+";");
    }

    @Override
    public void update(int id, Rent object) {
        String start_date = DateExtensionKt.getTimestamp(object.getStartDate());
        String end_date = DateExtensionKt.getTimestamp(object.getEndDate());
        String query = "";
        mysql.executeUpdate(query);
    }

    @Override
    public Rent find(int id) {
        String query = " SELECT * FROM rent WHERE id="+id+";";
        ResultSet result = mysql.executeQuery(query);
        Rent rent = null;
        try {
            rent = new RentFactory(result).getTransformedValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return rent;
    }

    @Override
    public ArrayList<Rent> findAll() {
        ArrayList<Rent> rents = new ArrayList<>();
        ResultSet result = mysql.executeQuery("SELECT * from rent");
        try {
            rents = new RentFactory(result).getTransformatedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return rents;
    }

    @Override
    public ArrayList<Rent> filterBy(String column, String value) {
        ArrayList<Rent> rents = new ArrayList<>();
        ResultSet result = mysql.executeQuery("SELECT * FROM Rent WHERE "+column+"="+value+";");

        try {
            rents = new RentFactory(result).getTransformatedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return rents;
    }
}
