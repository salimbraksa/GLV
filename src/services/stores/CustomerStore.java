package services.stores;

import helpers.extensions.DateExtensionKt;
import helpers.interfaces.Filterable;
import models.Customer;
import models.User;
import models.Vehicule;
import services.factories.CustomerFactory;
import services.factories.VehiculeFactory;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;

/**
 * Created by Salim on 5/9/16.
 */
public class CustomerStore implements StoreType<Customer>, Filterable<Customer> {

    // Singleton Implementation

    static private CustomerStore singleton = new CustomerStore();

    static public CustomerStore sharedInstance() {
        return singleton;
    }

    private CustomerStore() { }

    // Database manager

    Mysql mysql = Mysql.sharedInstance();

    // Store Methods

    @Override
    public void create(Customer object) {

        String type = object.getClass().getSimpleName();
        String birthday = DateExtensionKt.getTimestamp(object.getBirthday());
        String request = "INSERT INTO User (first_name, last_name, sexe, email, phone, diligence, cin, birthday, type) " +
                "VALUES ('"+ object.getFirstName() +"','"+ object.getLastName() +"','"+ object.getSexe().rawValue()
                +"','"+ object.getEmail() +"','"+ object.getPhone() + "','" +
                object.getDiligence().rawValue() + "','" + object.getCin() + "','" + birthday + "','"
                + type + "');";
        mysql.executeUpdate(request);

    }

    @Override
    public void delete(int id) {
        UserStore.sharedInstance().delete(id);
    }

    @Override
    public void update(int id, Customer object) {

        String birthday = DateExtensionKt.getTimestamp(object.getBirthday());
        String request = "UPDATE User " +
                "SET first_name='"+object.getFirstName()+"', last_name='"+object.getLastName()+"', sexe='"+object.getSexe()
                    +"', phone='"+object.getPhone()+"', email='"+object.getEmail()+"', diligence='"
                    +object.getDiligence().rawValue()+"', cin='"+object.getCin()+"', birthday='"+birthday+
                "' WHERE id="+id+";";
        System.out.println(request);
        mysql.executeUpdate(request);

    }

    @Override
    public Customer find(int id) {

        ResultSet result = mysql.executeQuery("SELECT * FROM user WHERE id="+id+";");

        Customer customer = null;

        //Retrieve data from database by column name

        try {

            //if there is a valid row match
            if (result.next()) {
                customer = new CustomerFactory(result).getTransformedValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return customer;
    }

    @Override
    public ArrayList<Customer> findAll() {
        ArrayList<Customer> customers = new ArrayList<>();
        ResultSet result = mysql.executeQuery("SELECT * FROM User WHERE type='Customer';");
        try {
             customers = new CustomerFactory(result).getTransformedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return customers;
    }

    @Override
    public ArrayList<Customer> filterBy(String column, String value) {
        ArrayList<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customer WHERE "+column+"="+value+";";
        ResultSet result = mysql.executeQuery(query);

        try {
            customers = new CustomerFactory(result).getTransformedValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return customers;
    }


}
