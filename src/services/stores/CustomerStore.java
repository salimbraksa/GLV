package services.stores;

import helpers.extensions.DateExtensionKt;
import models.Customer;
import models.User;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import java.util.Date;

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

        String birthday = DateExtensionKt.getTimestamp(object.getBirthday());
        String request = "UPDATE User " +
                "SET first_name="+object.getFirstName()+", last_name="+object.getLastName()+", sexe="+object.getSexe()
                    +", phone="+object.getPhone()+", email="+object.getEmail()+", diligence="
                    +object.getDiligence().rawValue()+", cin="+object.getCin()+", birthday="+birthday+
                " WHERE id="+id+";";
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

                String first_name = result.getString("first_name");
                String last_name = result.getString("last_name");
                User.Sexe sexe = User.Sexe.valueOf(result.getString("sexe"));
                String phone = result.getString("phone");
                String email = result.getString("email");
                Customer.Diligence diligence = Customer.Diligence.valueOf(result.getString("diligence"));
                String cin = result.getString("cin");
                Date birthday = Timestamp.valueOf(result.getString("birthday"));

                customer = new Customer(id, first_name, last_name, sexe, email, phone, diligence, cin, birthday);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return customer;
    }

    @Override
    public ArrayList<Customer> findAll() {

         ArrayList<Customer> listCustomer = new ArrayList<>();

        //Get list of Id of cutomers
        ResultSet result = mysql.executeQuery("SELECT id FROM User WHERE type='customer';");

        try {
            //for each row match
            while (result.next()){
                int customerId = result.getInt("id");

                //instance of the customer handled by the methode find
                Customer customer = find(customerId);

                //add the employee to the list
                listCustomer.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return listCustomer;
    }

}
