package services.authentication;

import helpers.SBError;
import models.Employee;
import org.jasypt.util.password.StrongPasswordEncryptor;
import helpers.filterOptions.UserFilter;
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
        SBError error = new SBError("Authentication Error", "The email address or password you entered is not valid");

        if (employee != null) {

            // Compare Password
            StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
            if (encryptor.checkPassword(password, employee.getPassword())) {
                delegate.authenticationDidSucceedWithUser(employee);
            } else {
                delegate.authenticationDidFailWithError(error);
            }

        } else {
            delegate.authenticationDidFailWithError(error);
        }

    }

}
