package services.stores;

import helpers.interfaces.Filterable;
import models.Lease;
import services.factories.LeaseFactory;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 14/05/16.
 */
public class LeaseStore implements StoreType<Lease>, Filterable<Lease> {

    // Singleton implementation
    static private LeaseStore singleton = new LeaseStore();

    static public LeaseStore sharedInstance(){
        return singleton;
    }

    private LeaseStore() {  }

    Mysql mysql = Mysql.sharedInstance();

    @Override
    public void create(Lease object) {
        String query = "INSERT INTO Lease (coast,supplier_id,vehicule_id,date,end_date) VALUES ('"
                +object.getCost()+"','" +object.getSupplierId()+"','"+object.getVehiculeId()+"','"
                +object.getDate()+"','"+object.getEndDate()+"');";
        mysql.executeUpdate(query);
    }

    @Override
    public void delete(int id) {
        mysql.executeUpdate("DELETE FROM Lease WHERE id="+id+";");
    }

    @Override
    public void update(int id, Lease object) {
        String query = "UPDATE lease SET coast="+object.getCost()+", supplier_id="+object.getSupplierId()
                +", vehicule_id="+object.getVehiculeId()+", date="+object.getDate()+", end_date="+object.getEndDate()+";";
        mysql.executeUpdate(query);
    }

    @Override
    public Lease find(int id) {
        String query = "SELECT * FROM lease WHERE id="+id+";";
        ResultSet result = mysql.executeQuery(query);
        Lease lease = null;
        try {
            if (result.next()){

                lease = new LeaseFactory(result).getTransformatedValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return lease;
    }

    @Override
    public ArrayList<Lease> findAll() {
        ArrayList<Lease> leases = new ArrayList<>();
        ResultSet result = mysql.executeQuery("SELECT * FROM lease");
        try {
            leases =new LeaseFactory(result).getTansformatedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return leases;
    }

    @Override
    public ArrayList<Lease> filterBy(String column, String value) {
        ArrayList<Lease> leases = new ArrayList<>();
        String query = "SELECT * FROM lease WHERE "+column+"="+value+";";
        ResultSet result = mysql.executeQuery(query);

        try {
            leases = new LeaseFactory(result).getTansformatedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return leases;
    }
}
