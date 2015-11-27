package com.reviewboard.user.domain.dao.impl;

import com.reviewboard.user.common.exception.InternalServerException;
import com.reviewboard.user.domain.dao.CryptoDAO;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;

/**
 * Crypto Interface to generate hash and hmacs
 */
@Service
public class CryptoDAOImpl implements CryptoDAO {

    private static final int HASH_BYTE_SIZE = 64; // 512 bits
    private static final int PBKDF2_ITERATIONS = 1000;


    /**
     * Generate Password Salt.
     * @return password seed.
     */
    @Override
    public byte[] generatePasswordSalt() {
        // generate random salt
        SecureRandom random = new SecureRandom();
        byte salt[] = new byte[19]; // use salt size at least as long as hash
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Generate hash out of the password
     * @param password user password.
     * @return Hash String
     */
    @Override
    public String generateHashFromPassword(String password, byte[] salt) {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512"); // we would like this to be "PBKDF2WithHmacSHA512" instead? What Provider implements it?
            return new String(skf.generateSecret(spec).getEncoded());
        } catch (Exception e) {
            throw new InternalServerException("Exception happened in Crypto function");
        }
    }

    /**
     * Compare two hash and return true if they are same.
     * @param password customer entered password
     * @param salt salt from customer's existing information
     * @param destinationHash hash to be compared with
     * @return boolean true if both are same.
     */
    @Override
    public boolean comparePasswordHash(String password, String salt, String destinationHash) {
        return generateHashFromPassword(password, salt.getBytes()).equals(destinationHash);
    }
}
