package services.factories;

import models.Lease;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by chaymaebz on 14/05/16.
 */
public class LeaseFactory {

    // Attributes

    ResultSet result;

    // Constructor

    public LeaseFactory(ResultSet result){ this.result = result; }

    // Methods

    public Lease getTransformatedValue() throws SQLException {

        if (result.next()){
            int id = result.getInt("id");
            Double coast = result.getDouble("coast");
            int supplier_id = result.getInt("supplier_id");
            int vehicule_id = result.getInt("vehicule_id");
            Date date = Timestamp.valueOf(result.getString("date"));
            Date end_date = Timestamp.valueOf(result.getString("end_date"));

            return new Lease(id,coast,date,supplier_id,vehicule_id,end_date);
        }
        else {
            return null;
        }
    }

    public ArrayList<Lease> getTansformatedValues() throws SQLException {
        ArrayList<Lease> leases = new ArrayList<>();

        while (result.next()){
            Lease lease = getTransformatedValue();
            leases.add(lease);
        }
        return leases;
    }
}
