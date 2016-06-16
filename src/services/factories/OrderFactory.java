package services.factories;

import models.Lease;
import models.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by chaymaebz on 14/05/16.
 */
public class OrderFactory {

    // Attributes

    ResultSet result;

    // Constructor

    public OrderFactory(ResultSet result){ this.result = result; }

    // Methods

    public Order getTransformatedValue() throws SQLException {

        int id = result.getInt("id");
        Double coast = result.getDouble("cost");
        int supplier_id = result.getInt("supplier_id");
        int vehicule_id = result.getInt("vehicule_id");
        String type = result.getString("type");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = null;
        Date endDate = null;
        try {
            date = format.parse(result.getString("date"));
            String end_date = result.getString("end_date");
            if (end_date != null) {
                endDate = format.parse(result.getString("end_date"));
            }
        } catch (Exception e ) {
            e.printStackTrace();
        }

        if (type.equals("order")) {
            return new Order(id, coast, date, supplier_id, vehicule_id);
        } else if (type.equals("lease")) {
            return new Lease(id, coast, date, supplier_id, vehicule_id, endDate);
        }
        return null;

    }

    public ArrayList<Order> getTansformatedValues() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();

        while (result.next()){
            Order order = getTransformatedValue();
            orders.add(order);
        }
        return orders;

    }
}
