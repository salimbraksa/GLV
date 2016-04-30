package models;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Admin extends User {

    // Constructor
    public Admin(int id,String first_name, String last_name, String sexe, String email, String phone){
        super(id, first_name, last_name, sexe, email, phone);
    }
}
