package models;

/**
 * Created by chaymaebz on 29/04/16.
 */
public class Supplier {

    // Attributes

    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;

    // Constructor

    public Supplier(int id, String name, String phone, String email, String address){
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    // Getters


    public String getAdress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}
