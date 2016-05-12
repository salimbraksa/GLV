package services.factories;

import models.Admin;
import models.Employee;
import models.Manager;
import models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Salim on 5/12/16.
 */
public class EmployeeFactory {

    // Attributes

    ResultSet result;

    // Constructor

    public EmployeeFactory(ResultSet result) {
        this.result = result;
    }

    // Methods

    public Employee getTransformedValue() throws SQLException {

        int id = result.getInt("id");
        String firstName = result.getString("first_name");
        String lastName = result.getString("last_name");
        User.Sexe sex = User.Sexe.valueOf(result.getString("sexe"));
        String phone = result.getString("phone");
        String email = result.getString("email");
        String password = result.getString("password");
        String type = result.getString("type");

        if (type.equals("admin")) {
            return new Admin(id, firstName, lastName, sex, email, phone, password);
        } else if (type.equals("manager")) {
            return new Manager(id, firstName, lastName, sex, email, phone, password);
        }

        return null;

    }

    public ArrayList<Employee> getTransformerValues() throws SQLException {

        ArrayList<Employee> employees = new ArrayList<>();

        while (result.next()) {
            Employee employee = getTransformedValue();
            employees.add(employee);
        }
        
        return employees;

    }

}
