package models;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Admin extends Employee {

    // Constructor

    public Admin(int id,String firstName, String last_name, Sexe sexe, String email, String phone, String password){
        super(id, firstName, last_name, sexe, email, phone, password);
    }

}
