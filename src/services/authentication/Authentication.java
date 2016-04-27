package services.authentication;

import models.User;

/**
 * Created by Salim on 4/27/16.
 */

interface AuthenticationDelegate {
    void authenticationDidSucceedWithUser(User user);
    void authenticationDidFailWithError(Error error);
}

public class Authentication {

    // Properties

    public AuthenticationDelegate delegate;

    // Methods

    void authenticateWithCredentials(String username, String password) {

    }

}
