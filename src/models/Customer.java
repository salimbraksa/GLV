package models;

import services.validation.Validatable;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * Created by chaymaebz on 28/04/16.
 */
public class Customer extends User{



    public enum Diligence {

        High, Medium, Low;

        public String rawValue() {
            switch (this) {
                case High: return "high";
                case Medium: return "medium";
                case Low: return "low";
            }
            return null;
        }

        public String hexColor() {
            switch (this) {
                case High: return "00C853";
                case Medium: return "FF7F00";
                case Low: return "F44336";
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

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        return null;
    }
}
