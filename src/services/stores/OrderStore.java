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
        String type = object.getClass().getSimpleName();

        if (type=="Order") {
            String query = "INSERT INTO Order (coast, supplier_id, vehicule_id, date, type) " +
                    "VALUES ('" + object.getCost() + "','" + object.getSupplierId() + "','" + object.getVehiculeId()
                    + "','" + object.getDate() + "','" + type + "');";
            mysql.executeUpdate(query);
        }
        else{
            String query = "INSERT INTO Order (coast, supplier_id, vehicule_id, date, end_date, type) " +
                    "VALUES ('" + object.getCost() + "','" + object.getSupplierId() + "','" + object.getVehiculeId()
                    + "','" + object.getDate() +  "','" + ((Lease) object).getEndDate() + "','" + type + "');";
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

        if (type=="Order"){
            String update = "UPDATE order SET "+
                    "coast="+object.getCost()+", supplier_id="+object.getSupplierId()+", vehicule_id="+object.getVehiculeId()
                    +", date="+object.getDate()+", type="+object.getType()+" WHERE id="+id+";";
            mysql.executeUpdate(update);
        }
        else {
            String update = "UPDATE order SET "+
                    "coast="+object.getCost()+", supplier_id="+object.getSupplierId()+", vehicule_id="+
                    object.getVehiculeId() +", date="+object.getDate()+", end_date="+((Lease) object).getEndDate()+
                    ", type="+object.getType()+" WHERE id="+id+";";
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
