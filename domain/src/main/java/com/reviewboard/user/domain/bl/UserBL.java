package com.reviewboard.user.domain.bl;

import com.reviewboard.user.domain.dto.CredentialDAO;
import com.reviewboard.user.domain.dto.UserDAO;

/**
 * User domain Business Logic goes into this class.
 */

public interface UserBL {

    /**
     * Look up and get the user details by  userName.
     * @param username login user name of the user to look up
     * @return User details if found any match.
     */
    UserDAO getUserByUsername(String username);

    /**
     * Create a new User in the data base.
     * @param userDAO User details
     * @return created User DAO
     */
    UserDAO createUser(UserDAO userDAO);

    /**
     * Look up and get the user details by full name.
     * @param firstName first name of the user to look up
     * @param lastName last name of the user to look up
     * @return User details if found any match.
     */
    UserDAO getUserByFullName(String firstName, String lastName);

    /**
     * Check if user credentials are correct.
     * If yes, return User details.
     * @param credentialDAO     customer entered credential details
     * @return UserDAO details.
     */
    UserDAO loginCheck(CredentialDAO credentialDAO);

    /**
     * Create a new password (password reset)
     * @param credentialDAO customer login credentials
     */
    void createNewPassword(CredentialDAO credentialDAO);

    /**
     * Change existing password.
     * @param username - user name
     * @param oldPassword - existing password.
     * @param newPassword - new password.
     */
    void changeExistingPassword(String username, String oldPassword, String newPassword);
}