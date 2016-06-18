package models;


import helpers.SBError;

import java.util.ArrayList;
import java.util.Map;

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

    @Override
    public ArrayList<Error> validate(Map<String, Object> additionalInfos) {
        ArrayList<Error> listErrors = new ArrayList<>();
        if (this.getFirstName() == null){
            SBError error = new SBError("Empty Field","The attribute 'first name' is required");
            listErrors.add(error);
        }
        if (this.getLastName() == null){
            SBError error = new SBError("Empty Field","The attribute 'last name' is required");
            listErrors.add(error);
        }
        if (this.getPhone() == null){
            SBError error = new SBError("Empty Field","The attribute 'phone' is required");
            listErrors.add(error);
        }
        if (this.getEmail() == null){
            SBError error = new SBError("Empty Field","The attribute 'email' is required");
            listErrors.add(error);
        }
        if (this.getSexe() == null){
            SBError error = new SBError("Empty Field","The attribute 'sexe' is required");
            listErrors.add(error);
        }
        if (this.getRole() == null){
            SBError error = new SBError("Empty Field","The attribute 'role' is required");
            listErrors.add(error);
        }
        if (this.getPassword() == null){
            SBError error = new SBError("Empty Field","The attribute 'password' is required");
            listErrors.add(error);
        }
        else {
            String confirmationPassword = "confirmationPassword";
            if (additionalInfos.containsKey(confirmationPassword)){
                if (additionalInfos.get(confirmationPassword)!=this.getPassword()){
                    SBError error = new SBError("invalid Password","The two passwords don't match");
                    listErrors.add(error);
                }
            }
        }

        return listErrors;
    }

}
