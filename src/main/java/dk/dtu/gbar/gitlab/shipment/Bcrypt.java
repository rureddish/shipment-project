package dk.dtu.gbar.gitlab.shipment;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {


    /**
     *
     * @param password String to be hashed
     * @return String hash of the password
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     *
     * @param candidate Candidate non hashed password to be checked
     * @param hash password in hash from taken from database
     * @return True if password matches
     */
    public static boolean checkPassword(String candidate, String hash) {
        return (BCrypt.checkpw(candidate, hash));
    }
}
