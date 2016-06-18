package models;

import services.stores.CustomerStore;
import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Admin extends Employee {

    // Constructor

    public Admin(int id,String firstName, String last_name, Sexe sexe, String email, String phone, String password){
        super(id, firstName, last_name, sexe, email, phone, password);
    }

    private void createCustomer(int id, String firstName, String lastName, Sexe sexe, String email,
                                    String phone, Customer.Diligence diligence, String cin, Date birthday){

        Customer customer = new Customer(id, firstName, lastName, sexe, email, phone, diligence, cin, birthday);

        CustomerStore.sharedInstance().create(customer);
    }

    @Override
    public Role getRole() {
        return Role.Admin;
    }

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }
}
