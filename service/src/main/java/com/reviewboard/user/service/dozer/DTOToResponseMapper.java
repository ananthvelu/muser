package com.reviewboard.user.service.dozer;

import com.reviewboard.user.common.model.User;
import com.reviewboard.user.common.model.UserCredential;
import com.reviewboard.user.domain.dto.CredentialDAO;
import com.reviewboard.user.domain.dto.UserDAO;
import org.dozer.DozerBeanMapper;
import org.dozer.loader.DozerBuilder;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOToResponseMapper {
    /**
     * Defines a dtoToResponseMapper spring bean
     *
     * @return a dozer mapper
     */
    @Bean(name = "userDAOToUserMapper")
    public DozerBeanMapper userDAOToUserMapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.addMapping(credentialDTOToUserCredentialMapper());
        beanMapper.addMapping(userDTOToMapping());
        return beanMapper;
    }

    @Bean(name = "userToDAOMapper")
    public DozerBeanMapper userToDAOMapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        // beanMapper.addMapping(credentialToDTOMapper());
        beanMapper.addMapping(userToUserDTOMapping());
        return beanMapper;
    }

    @Bean(name = "credentialRequestMapper")
    public DozerBeanMapper credentialRequestMapper() {
        DozerBeanMapper beanMapper = new DozerBeanMapper();
        beanMapper.addMapping(credentialToDTOMapper());
        return beanMapper;
    }

    private BeanMappingBuilder userDTOToMapping() {
        return new ExtendedBeanMappingBuilder() {
            @Override
            protected void configure(DozerBuilder dozerBuilder) {
                mapping(UserDAO.class, User.class);
            }
        };
    }

    private BeanMappingBuilder credentialDTOToUserCredentialMapper() {
        return new ExtendedBeanMappingBuilder() {
            @Override
            protected void configure(DozerBuilder dozerBuilder) {
                mapping(CredentialDAO.class, UserCredential.class);
            }
        };
    }

    private BeanMappingBuilder userToUserDTOMapping() {
        return new ExtendedBeanMappingBuilder() {
            @Override
            protected void configure(DozerBuilder dozerBuilder) {
                mapping(UserCredential.class, CredentialDAO.class);
                mapping(User.class, UserDAO.class)
                        .exclude("id")
                        .fields("firstName", "firstName")
                        .fields("lastName", "lastName")
                        .fields("userCredential", "credentialDAO");
            }
        };
    }

    private BeanMappingBuilder credentialToDTOMapper() {
        return new ExtendedBeanMappingBuilder() {
            @Override
            protected void configure(DozerBuilder dozerBuilder) {
                mapping(UserCredential.class, CredentialDAO.class);
            }
        };
    }
}
