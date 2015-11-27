package com.reviewboard.user.domain.dao;

/**
 * Crypto Interface to generate hash and hmacs
 */
public interface CryptoDAO {
    /**
     * Generate hash out of the password
     * @param password user password.
     * @return Hash String
     */
    String generateHashFromPassword(String password, byte[] salt);

    /**
     * Generate Password Salt.
     * @return password seed.
     */
    byte[] generatePasswordSalt();

    /**
     * Compare two hash and return true if they are same.
     * @param password customer entered password
     * @param salt salt from customer's existing information
     * @param destinationHash hash to be compared with
     * @return boolean true if both are same.
     */
    boolean comparePasswordHash(String password, String salt, String destinationHash);
}
