package services.stores;

import helpers.extensions.DateExtensionKt;
import helpers.interfaces.Filterable;
import models.Lease;
import models.Order;
import services.factories.OrderFactory;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 14/05/16.
 */
public class OrderStore implements StoreType<Order> {

    // Singleton implementation
    static private OrderStore singleton = new OrderStore();

    static public OrderStore sharedInstance(){ return singleton; }

    private OrderStore() { }

    Mysql mysql = Mysql.sharedInstance();

    @Override
    public void create(Order object) {
        String type = object.getClass().getSimpleName();
        String start_date = DateExtensionKt.getTimestamp(object.getDate());
        if (type.equals("Order")) {
            String query = "INSERT INTO orders (cost, supplier_id, vehicule_id, date, type) " +
                    "VALUES ('" + object.getCost() + "','" + object.getSupplierId() + "','" + object.getVehiculeId()
                    + "','" + start_date + "','" + type + "');";
            System.out.println(query);
            mysql.executeUpdate(query);
        }
        else{
            Lease lease = (Lease) object;
            String end_date = DateExtensionKt.getTimestamp(lease.getEndDate());
            String query = "INSERT INTO orders (cost, supplier_id, vehicule_id, date, end_date, type) " +
                    "VALUES ('" + object.getCost() + "','" + object.getSupplierId() + "','" + object.getVehiculeId()
                    + "','" + start_date +  "','" + end_date + "','" + type + "');";
            System.out.println(query);
            mysql.executeUpdate(query);
        }

    }

    @Override
    public void delete(int id) {
        mysql.executeUpdate("DELETE FROM orders WHERE id="+id+";");
    }

    @Override
    public void update(int id, Order object) {

        String type = object.getClass().getSimpleName();
        String start_date = DateExtensionKt.getTimestamp(object.getDate());

        if (type.equals("Order")){

            String update = "UPDATE orders SET "+
                    "cost="+object.getCost()+", supplier_id="+object.getSupplierId()+", vehicule_id="+object.getVehiculeId()
                    +", date='"+start_date+"', type='"+object.getType()+"' WHERE id="+id+";";
            mysql.executeUpdate(update);
        }
        else {

            Lease lease = (Lease) object;
            String end_date = DateExtensionKt.getTimestamp(lease.getEndDate());

            String update = "UPDATE orders SET "+
                    "cost="+object.getCost()+", supplier_id="+object.getSupplierId()+", vehicule_id="+
                    object.getVehiculeId() +", date='"+start_date+"', end_date='"+end_date+
                    "', type='"+object.getType()+"' WHERE id="+id+";";

            mysql.executeUpdate(update);

        }

    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public ArrayList<Order> findAll() {
        ArrayList<Order> orders = new ArrayList<>();
        ResultSet result = mysql.executeQuery("SELECT * FROM orders;");
        try {
            orders = new OrderFactory(result).getTansformatedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return orders;
    }

}
