package models;

/**
 * Created by Salim on 4/27/16.
 */
public class User {

    public enum Sexe {

        // Cases

        Male,
        Female;

        // Methods

        public String rawValue() {
            switch (this) {
                case Male: return "male";
                case Female: return "female";
            }
            return null;
        }


    }

    // User attributes

    private int id;
    private String firstName;
    private String lastName;
    private Sexe sexe;
    private String email;
    private String phone;

    // Constructor

    public User(int id, String firstName, String lastName, Sexe sexe, String email, String phone){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sexe = sexe;
        this.email = email;
        this.phone = phone;
    }

    // Getters

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public Sexe getSexe() {
        return sexe;
    }
}
