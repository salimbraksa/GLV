package services.stores;

import helpers.extensions.DateExtensionKt;
import models.Customer;
import services.mysql.Mysql;

import java.util.ArrayList;

/**
 * Created by Salim on 5/9/16.
 */
public class CustomerStore implements StoreType<Customer> {

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

    }

    @Override
    public Customer find(int id) {
        return null;
    }

    @Override
    public ArrayList<Customer> findAll() {
        return null;
    }

}
