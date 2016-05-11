package services.stores;

import models.Admin;
import models.Employee;
import models.Manager;
import models.User;
import org.jasypt.util.password.StrongPasswordEncryptor;
import services.mysql.Mysql;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        //maybe we need to change the type like from manager to admin !!
        ResultSet result = mysql.executeQuery("UPDATE User" +
                "SET first_name="+object.getFirstName()+", last_name="+object.getLastName()+", sexe="+object.getSexe()
                +", phone="+object.getPhone()+", email="+object.getEmail()+", type="+type+
                "WHERE id="+id+";");

    }

    @Override
    public Employee find(int id) {

        ResultSet result = mysql.executeQuery("SELECT * FROM user WHERE id="+id+";");

        //Retrieve data from database by column name

        try {
            //if there is a valid row match
            if (result.next()){
                int employeeId = result.getInt("id");
                String employeeFirstName = result.getString("first_name");
                String employeeLastName = result.getString("last_name");
                User.Sexe employeeSexe = User.Sexe.valueOf(result.getString("sexe"));
                String employeePhone = result.getString("phone");
                String employeeEmail = result.getString("email");
                String employeePassword = result.getString("password");
                String employeeType = result.getString("type");

              if (employeeType=="manager") {
                  return new Manager(employeeId, employeeFirstName, employeeLastName,
                          employeeSexe, employeePhone, employeeEmail, employeePassword);
              }
              else if(employeeType=="admin") {
                  return new Admin(employeeId, employeeFirstName, employeeLastName,
                          employeeSexe, employeePhone, employeeEmail, employeePassword);
              }
                else{
                  return null;
              }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Employee> findAll() {
        ArrayList<Employee> listEmployee = new ArrayList<>();

        //Get list of Id of admins and managers
        ResultSet result = mysql.executeQuery("SELECT id FROM User WHERE type='manager' OR type='admin';");
        try {
            //for each row match
            while (result.next()){
                int employeeId = result.getInt("id");

                //instance of the admin or manager handled by the methode find
                Employee employee = find(employeeId);

                //add the employee to the list
                listEmployee.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listEmployee;
    }

}
