package models;

import java.util.Date;

/**
 * Created by chaymaebz on 28/04/16.
 */
public class Customer extends User{

    // Attributes

    private String assiduity;
    private String cin;
    private Date birthday;

    // Constructor

    public Customer(int id, String firstName, String lastName, String sexe, String email,
                    String phone, String assiduity, String cin, Date birthday){

        super(id, firstName, lastName, sexe, email, phone);
        this.assiduity = assiduity;
        this.cin = cin;
        this.birthday = birthday;
    }

    // Getters

    public Date getBirthday() {
        return birthday;
    }

    public String getCin() {
        return cin;
    }

    public String getAssiduity() {
        return assiduity;
    }
}
