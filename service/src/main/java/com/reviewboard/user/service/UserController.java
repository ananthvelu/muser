package com.reviewboard.user.service;

import com.reviewboard.user.common.exception.NotFoundException;
import com.reviewboard.user.common.model.User;
import com.reviewboard.user.domain.bl.UserBL;
import com.reviewboard.user.domain.dto.UserDAO;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

/**
 * User service API implementation.
 */
@RestController
public class UserController {

    @Inject
    private Mapper userDAOToUserMapper;

    @Inject
    private Mapper userToDAOMapper;

    @Inject
    private Mapper credentialRequestMapper;

    @Inject
    private UserBL userBL;

    /**
     * Get User details based on full name
     *
     * @param firstName firstname to lookup
     * @param lastName  firstname to lookup
     * @return User object
     */
    @RequestMapping(value = "/user", name = "/getbyname", method = RequestMethod.GET, params = {"firstname", "lastname"})
    public User getUserByFullName(@RequestParam(value = "firstname") String firstName,
                                  @RequestParam(value = "lastname") String lastName) {
        UserDAO userDAO = userBL.getUserByFullName(firstName, lastName);
        if (userDAO != null) {
            return userDAOToUserMapper.map(userDAO, User.class);
        } else {
            throw new NotFoundException(firstName + lastName);
        }
    }

//    /**
//     * Get User details based on Username
//     * @param userName user name to lookup
//     * @return User object
//     */
//    @RequestMapping(value = "/user", name = "/getbyusername", method = RequestMethod.GET, params = "username")
//    public User getUser(@RequestParam(value="username") String userName) {
//        UserDAO userDAO = userBL.getUserByUsername(userName);
//        if (userDAO != null) {
//            return dozerResponse.map(userDAO, User.class);
//        } else {
//            throw new NotFoundException(userName);
//        }
//    }

//

    /**
     * Create a new User if not already exists.
     *
     * @param user user details
     * @return created user details
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public User createUser(@RequestBody @Valid final User user) {
        UserDAO userDAO;
        userDAO = userToDAOMapper.map(user, UserDAO.class);

        UserDAO createdUser = userBL.createUser(userDAO);
        return userDAOToUserMapper.map(createdUser, User.class);
    }
//
//    /**
//     * Check login credentials and return user details if match found.
//     * @param userCredential USer credential details
//     * @return Found user details
//     */
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
//    public User login(@RequestBody @Valid final UserCredential userCredential) {
//        CredentialDAO credentialDAO = credentialToDAOMap.map(userCredential, CredentialDAO.class);
//
//        UserDAO userFound = userBL.loginCheck(credentialDAO);
//        return dozerResponse.map(userFound, User.class);
//    }
//
//    @RequestMapping(value = "/user/passwordreset", method = RequestMethod.POST)
//    public void resetPassword(@RequestBody @Valid final UserCredential userCredential) {
//        CredentialDAO credentialDAO = credentialToDAOMap.map(userCredential, CredentialDAO.class);
//        userBL.createNewPassword(credentialDAO);
//    }
//
//
//    @RequestMapping(value = "/user/changepassword", method = RequestMethod.POST)
//    public void changeCurrentPassword(@RequestParam(value = "username") String userName,
//                                      @RequestParam(value = "old_password") String oldPassword,
//                                      @RequestParam(value = "new_password") String newPassword) {
//        userBL.changeExistingPassword(userName, oldPassword, newPassword);
//    }

//    private static Mapper getDTOMapper() {
//        if (restDtoMapper == null) {
//            restDtoMapper = new DozerBeanMapper();
//            restDtoMapper.setMappingFiles(mapperFileList);
//        }
//        return restDtoMapper;
//    }

    @Inject
    @Qualifier("userToDAOMapper")
    private void setUserToDAOMap(Mapper userToDAOMapper) {
        this.userToDAOMapper = userToDAOMapper;
    }

    @Inject
    @Qualifier("userDAOToUserMapper")
    private void setDozerResponse(Mapper userDAOToUserMapper) {
        this.userDAOToUserMapper = userDAOToUserMapper;
    }

    @Inject
    @Qualifier("credentialRequestMapper")
    private void setCredentialToDAOMap(Mapper credentialRequestMapper) {
        this.credentialRequestMapper = credentialRequestMapper;
    }

}
