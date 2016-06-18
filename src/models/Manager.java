package models;

import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Manager extends Employee {

    // Constructor

    public Manager(int id,String firstName, String last_name, Sexe sexe, String email, String phone, String password){
        super(id, firstName, last_name, sexe, email, phone, password);
    }

    // Methods

    @Override
    public Role getRole() {
        return Role.Manager;
    }


}
