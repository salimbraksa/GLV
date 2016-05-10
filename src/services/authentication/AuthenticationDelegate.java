package services.authentication;

import models.User;

/**
 * Created by Salim on 4/27/16.
 */

public interface AuthenticationDelegate {

    void authenticationDidSucceedWithUser(User user);
    void authenticationDidFailWithError(Error error);
}
