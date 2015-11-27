package com.reviewboard.user.domain.bl.impl;

import com.reviewboard.user.common.exception.CredentialsNotFoundException;
import com.reviewboard.user.common.exception.NotFoundException;
import com.reviewboard.user.common.exception.OldAndNewPaswordCannotBeSame;
import com.reviewboard.user.common.exception.UserExistsAlreadyException;
import com.reviewboard.user.domain.bl.UserBL;
import com.reviewboard.user.domain.dao.CryptoDAO;
import com.reviewboard.user.domain.datamodel.impl.UserRepository;
import com.reviewboard.user.domain.dto.CredentialDAO;
import com.reviewboard.user.domain.dto.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * User domain Business Logic goes into this class.
 */
@Service
public class UserBLImpl implements UserBL {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CryptoDAO cryptoDAO;

    @Override
    public UserDAO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDAO getUserByFullName(String firstName, String lastName) {
        return userRepository.findByFirstNameAndLastName(firstName, lastName);
    }
    /**
     * Check if user credentials are correct.
     * If yes, return User details.
     * @param credentialDAO customer entered credential details
     * @return UserDAO details.
     */
    @Override
    public UserDAO loginCheck(CredentialDAO credentialDAO) {
        UserDAO userDAOFromDB = getUserByUsername(credentialDAO.getUsername());
        if (userDAOFromDB == null) {
            throw new NotFoundException("user account not found");
        } else {
            if (checkPasswordMatch(credentialDAO.getPassword(), userDAOFromDB.getCredentialDAO())) {
                return userDAOFromDB;
            } else {
                throw new CredentialsNotFoundException();
            }
        }
    }

    /**
     * Change Existing password.
     * @param username - user name
     * @param oldPassword - existing password.
     * @param newPassword - new password.
     */
    public void changeExistingPassword(String username, String oldPassword, String newPassword) {
        if (oldPassword.equals(newPassword)) {
            throw new OldAndNewPaswordCannotBeSame();
        }
        UserDAO userDAO = getUserByUsername(username);
        if (checkPasswordMatch(oldPassword, userDAO.getCredentialDAO())) {
            userDAO.getCredentialDAO().setPasswordSalt(new String(cryptoDAO.generatePasswordSalt()));
            userDAO.getCredentialDAO().setPasswordHash(
                    cryptoDAO.generateHashFromPassword(newPassword, userDAO.getCredentialDAO().getPasswordSalt().getBytes()));
            userRepository.save(userDAO);
        } else {
            throw new CredentialsNotFoundException();
        }
    }
    /**
     * Create a new password (password reset)
     * @param credentialDAO customer login credentials
     */
    public void createNewPassword(CredentialDAO credentialDAO) {
        UserDAO existingUser = getUserByUsername(credentialDAO.getUsername());
        //if no existing username match found, create a new one.
        if (existingUser != null) {
            existingUser.getCredentialDAO().setPasswordSalt(new String(cryptoDAO.generatePasswordSalt()));
            existingUser.getCredentialDAO().setPasswordHash(cryptoDAO.generateHashFromPassword(credentialDAO.getPassword(),
                    existingUser.getCredentialDAO().getPasswordSalt().getBytes()));
            userRepository.save(existingUser);
        } else {
            throw new NotFoundException(credentialDAO.getUsername());
        }
    }

    /**
     * Create a new User in the data base.
     * @param userDAO User details
     * @return created User DAO
     */
    @Override
    public UserDAO createUser(UserDAO userDAO) {
        //load user and find out if its an existing user
        UserDAO existingUser = getUserByUsername(userDAO.getCredentialDAO().getUsername());
        if (existingUser != null) {
            throw new UserExistsAlreadyException(userDAO.getCredentialDAO().getUsername());
        }

        //if no existing username match found, create a new one.
        userDAO.getCredentialDAO().setPasswordSalt(new String(cryptoDAO.generatePasswordSalt()));
        userDAO.getCredentialDAO().setPasswordHash(cryptoDAO.generateHashFromPassword(
                userDAO.getCredentialDAO().getPassword(), userDAO.getCredentialDAO().getPasswordSalt().getBytes()));
        return userRepository.insert(userDAO);
    }

    /**
     * Check for password match with the one in DB.
     * @param password user entered password
     * @param credentialDAO Customer Credential from DB
     * @return boolean true if match found.
     */
    private boolean checkPasswordMatch(String password, CredentialDAO credentialDAO) {
        return cryptoDAO.comparePasswordHash(password,
                credentialDAO.getPasswordSalt(), credentialDAO.getPasswordHash());
    }

    /**
     * Create new password for the existing user
     * @param existingUser existing user details
     * @param password new password
     */
    private void createNewPassword(UserDAO existingUser, String password) {

    }

    /**
     * Setter method to inject cryptoDAO
     * @param cryptoDAO cryptoDAO client
     */
    public void setCryptoDAO(CryptoDAO cryptoDAO) {
        this.cryptoDAO = cryptoDAO;
    }

//    private static Mapper getDTOMapper() {
//        if (restDtoMapper == null) {
//            restDtoMapper = new DozerBeanMapper();
//            restDtoMapper.setMappingFiles(mapperFileList);
//        }
//        return restDtoMapper;
//    }
}
