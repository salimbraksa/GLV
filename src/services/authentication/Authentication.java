package services.authentication;

import models.Employee;
import models.User;
import org.jasypt.util.password.StrongPasswordEncryptor;
import services.filterOptions.UserFilter;
import services.stores.EmployeeStore;
import helpers.extensions.ArrayListExtensionKt;

import java.util.ArrayList;

public class Authentication {

    // Properties

    public AuthenticationDelegate delegate;

    // Methods

    public void authenticateWithCredentials(String username, String password) {

        ArrayList<Employee> employees = EmployeeStore.sharedInstance().filterBy(UserFilter.email, username);
        Employee employee = ArrayListExtensionKt.getFirst(employees);
        if (employee != null) {

            // Compare Password
            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
            if (encryptor.checkPassword(password, employee.getPassword())) {
                delegate.authenticationDidSucceedWithUser(employee);
            } else {
                delegate.authenticationDidFailWithError(new Error());
            }

        } else {
            delegate.authenticationDidFailWithError(new Error());
        }

    }

}
