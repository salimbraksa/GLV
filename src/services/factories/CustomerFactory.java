package services.factories;

import models.Customer;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chaymaebz on 12/05/16.
 */
public class CustomerFactory {

    // Attributes

    ResultSet result;

    // Constructor

    public CustomerFactory(ResultSet result){
        this.result = result;
    }

    // Methods

    public Customer getTransformedValue() throws SQLException {

        String type = result.getString("type");

        if (type.equals("customer")) {

            //user information
            int id = result.getInt("id");
            String first_name = result.getString("first_name");
            String last_name = result.getString("last_name");
            User.Sexe sexe = User.Sexe.valueOf(result.getString("sexe"));
            String phone = result.getString("phone");
            String email = result.getString("email");

            // Additional information about the customer
            System.out.println("Diligence: " + result.getString("diligence"));
            Customer.Diligence diligence = Customer.Diligence.valueOf(result.getString("diligence"));
            String cin = result.getString("cin");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date birthday = null;
            try {
                birthday = format.parse(result.getString("birthday"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            return new Customer(id, first_name, last_name, sexe, email, phone, diligence, cin, birthday);
        }
        else {
            return null;
        }
    }

    public ArrayList<Customer> getTransformedValues() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        while (result.next()){
            Customer customer = getTransformedValue();
            customers.add(customer);
        }

        return customers;
    }


}
