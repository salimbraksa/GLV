package services.stores;

import helpers.interfaces.FilterOptionType;
import helpers.interfaces.Filterable;
import models.Employee;
import org.jasypt.util.password.StrongPasswordEncryptor;

import services.factories.EmployeeFactory;
import services.mysql.Mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Salim on 5/9/16.
 */
public class EmployeeStore implements StoreType<Employee>, Filterable<Employee> {

    // Enums

    public enum FilterOption implements FilterOptionType {

        // Cases
        Id("id"), FirstName("first_name"), LastName("last_name"), Email("email");

        // Constructor
        String option;
        FilterOption(String value) {
            option = value;
        }

        @Override
        public String getRawValue() {
            return option;
        }
    }

    // Singleton Implementation

    static private EmployeeStore singleton = new EmployeeStore();

    static public EmployeeStore sharedInstance() {
        return singleton;
    }

    private EmployeeStore() { }

    // Database manager

    Mysql mysql = Mysql.sharedInstance();

    // Store Methods

    @Override
    public void create(Employee object) {

        String type = object.getClass().getSimpleName();
        StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = strongPasswordEncryptor.encryptPassword(object.getPassword());
        String request = "INSERT INTO User (first_name, last_name, sexe, email, phone, type, password) " +
                         "VALUES ('"+ object.getFirstName() +"','"+ object.getLastName() +"','"+ object.getSexe().rawValue()
                         +"','"+ object.getEmail() +"','"+ object.getPhone() +"','"+ type +"','"+ encryptedPassword +"');";
        mysql.executeUpdate(request);

    }

    @Override
    public void delete(int id) {
        UserStore.sharedInstance().delete(id);
    }

    @Override
    public void update(int id, Employee object) {

        String type = object.getClass().getSimpleName();
        mysql.executeUpdate("UPDATE User" +
                "SET first_name="+object.getFirstName()+", last_name="+object.getLastName()+", sexe="+object.getSexe()
                +", phone="+object.getPhone()+", email="+object.getEmail()+", type="+type+
                "WHERE id="+id+";");

    }

    @Override
    public Employee find(int id) {

        String query = "Select * From User WHERE id="+id+";";
        ResultSet result = mysql.executeQuery(query);
        Employee employee = null;

        try {
            if (result.next()){
                employee = new EmployeeFactory(result).getTransformedValue();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return employee;

    }

    @Override
    public ArrayList<Employee> findAll() {

        ArrayList<Employee> employees = new ArrayList<>();
        ResultSet result = mysql.executeQuery("SELECT * FROM User WHERE type='manager' OR type='admin';");

        try {
            employees = new EmployeeFactory(result).getTransformerValues();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        mysql.disconnect();
        return employees;
    }

    // Filterable Interface

    @Override
    public ArrayList<Employee> filterBy(FilterOptionType option, String value) {

        // Execute Query
        String query = "SELECT * FROM User WHERE " + option.getRawValue() + "='" + value + "';";
        ResultSet result = mysql.executeQuery(query);
        ArrayList<Employee> employees = new ArrayList<>();

        // Try Accessing the result
        try {
            employees = new EmployeeFactory(result).getTransformerValues();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mysql.disconnect();
        return employees;

    }

}
