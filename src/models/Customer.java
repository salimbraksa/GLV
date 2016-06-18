package models;

import java.util.Date;

/**
 * Created by chaymaebz on 28/04/16.
 */
public class Customer extends User{

    public enum Diligence {

        High, Medium, Low;

        public String rawValue() {
            switch (this) {
                case High: return "High";
                case Medium: return "Medium";
                case Low: return "Low";
            }
            return null;
        }

        public String hexColor() {
            switch (this) {
                case High: return "F44336";
                case Medium: return "FF7F00";
                case Low: return "00C853";
            }
            return null;
        }

    }

    // Attributes

    private Diligence diligence;
    private String cin;
    private Date birthday;

    // Constructor

    public Customer(int id, String firstName, String lastName, Sexe sexe, String email,
                    String phone, Diligence diligence, String cin, Date birthday){
        super(id, firstName, lastName, sexe, email, phone);

        this.diligence = diligence;
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

    public Diligence getDiligence() {
        return diligence;
    }

    @Override
    public String toString() {
        return getId() + "-" + getFirstName() + " " + getLastName();
    }
}
