package models;

import services.validation.Validatable;

/**
 * Created by Salim on 5/9/16.
 */
public abstract class Employee extends User{

    public enum Role {
        Admin, Manager;
        public String rawValue() {
            switch (this) {
                case Admin: return "Admin";
                case Manager: return "Manager";
                default: return null;
            }
        }
    }

    // Attributes

    private String password;

    // Constructor

    public Employee(int id,String firstName, String last_name, Sexe sexe, String email, String phone, String password) {
        super(id, firstName, last_name, sexe, email, phone);
        this.password = password;
    }

    // Getters

    public String getPassword() {
        return password;
    }

    abstract public Role getRole();

}
