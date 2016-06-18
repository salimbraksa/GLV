package services.stores;

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
    }

    @Override
    public void delete(int id) {
        mysql.executeUpdate("DELETE FROM orders WHERE id="+id+";");
    }

    @Override
    public void update(int id, Order object) {
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
