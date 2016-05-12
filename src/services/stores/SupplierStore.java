package services.stores;

import models.Supplier;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by chaymaebz on 12/05/16.
 */
public class SupplierStore implements StoreType<Supplier> {


    //singleton implementation

    static private SupplierStore singleton = new SupplierStore();

    static public SupplierStore sharedInstance() {
        return singleton;
    }

    private SupplierStore() {
    }

    //database manager

    Mysql mysql = Mysql.sharedInstance();

    @Override
    public void create(Supplier object) {
        String query = "INSERT INTO supplier (name,phone,email,adress) VALUES ('" + object.getName() + "','" +
                object.getPhone() + "','" + object.getEmail() + "','" + object.getAdress() + "');";
        mysql.executeUpdate(query);
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM supplier WHERE id="+id+";";
        mysql.executeUpdate(query);
    }

    @Override
    public void update(int id, Supplier object) {
        String query = "UPDATE supplier "+
                "SET name="+object.getName()+", phone="+object.getPhone()+", email="+object.getEmail()+", adress="+
                object.getAdress()+
                " WHERE id="+id+";";
        mysql.executeUpdate(query);

    }

    @Override
    public Supplier find(int id) {
        Supplier supplier = null;

        ResultSet result = mysql.executeQuery("SELECT * FROM supplier WHERE id="+id+";");
        //retrieve data
        try {
            if (result.next()){

                String name = result.getString("name");
                String phone = result.getString("phone");
                String email = result.getString("email");
                String adress = result.getString("adress");

                supplier = new Supplier(id,name,phone,email,adress);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return supplier;
    }

    @Override
    public ArrayList<Supplier> findAll() {
        return null;
    }
}
