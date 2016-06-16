package services.factories;

import models.Location;
import models.Rent;

import java.text.SimpleDateFormat;
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

    public RentFactory(ResultSet result){ this.result = result;  }

    // Methods

    public Rent getTransformedValue() throws SQLException {

        int id = result.getInt("id");
        int vehicule_id = result.getInt("vehicule_id");
        int customer_id = result.getInt("customer_id");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date start_date = null;
        Date end_date = null;
        try {
            start_date = format.parse(result.getString("start_date"));
            end_date = format.parse(result.getString("end_date"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String pickupLocation = result.getString("pickup_location");
        String dropLocation = result.getString("drop_location");

        return new Rent(id,vehicule_id,customer_id,start_date,end_date,pickupLocation,dropLocation);

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
