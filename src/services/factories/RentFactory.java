package services.factories;

import models.Location;
import models.Rent;

import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by chaymaebz on 14/05/16.
 */
public class RentFactory {

    //Attributes

    ResultSet result;

    // Constructor

    public RentFactory(ResultSet result){     this.result = result;  }

    // Methods

    public Rent getTransformedValue() throws SQLException {
        if (result.next()){
            int id = result.getInt("id");
            int vehicule_id = result.getInt("vehicule_id");
            int customer_id = result.getInt("customer_id");
            Date start_date = Timestamp.valueOf(result.getString("start_date"));
            Date end_date = Timestamp.valueOf(result.getString("end_date"));
            Location pickup_location = new Location(result.getDouble("pickup_location_latitude"),
                    result.getDouble("pickup_location_longitude"));
            Location drop_location = new Location(result.getDouble("drop_location_latitude"),
                    result.getDouble("drop_location_longitude"));

            return new Rent(id,vehicule_id,customer_id,start_date,end_date,pickup_location,drop_location);
        }
        else {
            return null;
        }
    }

    public ArrayList<Rent> getTransformatedValues() throws SQLException {
        ArrayList<Rent> rents = new ArrayList<>();
        while (result.next()){
            Rent rent = getTransformedValue();
            rents.add(rent);
        }
        return rents;
    }
}
