package com.reviewboard.user.domain.datamodel.impl;

import com.reviewboard.user.domain.dto.UserDAO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends MongoRepository<UserDAO, String>, UserRepositoryCustom {
    //UserDAO findByCredentialUsername(CredentialDAO credential);
    UserDAO findByFirstNameAndLastName(String firstName, String lastName);
    //UserDAO create(UserDAO userDAO);
}