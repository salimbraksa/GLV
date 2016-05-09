package services.stores;

import models.Employee;
import org.jasypt.util.password.StrongPasswordEncryptor;
import services.mysql.Mysql;

import java.util.ArrayList;

/**
 * Created by Salim on 5/9/16.
 */
public class EmployeeStore implements StoreType<Employee> {

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

        StrongPasswordEncryptor strongPasswordEncryptor = new StrongPasswordEncryptor();
        String encryptedPassword = strongPasswordEncryptor.encryptPassword(object.getPassword());
        String request = "INSERT INTO User (first_name, last_name, sexe, email, phone, type, password) " +
                         "VALUES ('"+ object.getFirstName() +"','"+ object.getLastName() +"','"+ object.getSexe().rawValue()
                         +"','"+ object.getEmail() +"','"+ object.getPhone() +"','"+ object.getModelName() +"','"+ encryptedPassword +"');";
        mysql.executeUpdate(request);
        
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public void update(int id, Employee object) {

    }

    @Override
    public Employee find(int id) {
        return null;
    }

    @Override
    public ArrayList<Employee> findAll() {
        return null;
    }

}
