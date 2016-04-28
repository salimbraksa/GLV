package models;

/**
 * Created by Salim on 4/27/16.
 */
public class User {

    // User attributes

    private int id;
    private String first_name;
    private String last_name;
    private String sexe;
    private String email;
    private String phone;

    // Constructor

    public User(int id,String first_name, String last_name, String sexe, String email, String phone){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.sexe = sexe;
        this.email = email;
        this.phone = phone;
    }

    // Getters

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getId() {
        return id;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public String getSexe() {
        return sexe;
    }
}
