package Utils;

import java.security.SecureRandom;

public class RandomHelper {

    private static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    private static final String ALPHANUMERIC = ALPHA + NUMERIC;
    private static final SecureRandom random = new SecureRandom();

    /**
     * Generate a random alphabetic string.
     * @param length number of characters
     * @return random string
     */
    public static String randomString(int length) {
        return generateRandom(ALPHA, length);
    }

    /**
     * Generate a random numeric string.
     * @param length number of digits
     * @return random number as string
     */
    public static String randomNumber(int length) {
        return generateRandom(NUMERIC, length);
    }

    /**
     * Generate a random alphanumeric string.
     * @param length number of characters
     * @return random alphanumeric string
     */
    public static String randomAlphaNumeric(int length) {
        return generateRandom(ALPHANUMERIC, length);
    }

    /**
     * Generate a random email with given prefix and domain.
     * @param prefix prefix before random string
     * @param domain domain name (e.g., gmail.com)
     * @return random email address
     */
    public static String randomEmail(String prefix, String domain) {
        return prefix + randomAlphaNumeric(6) + "@" + domain;
    }

    // --- Private core method ---
    private static String generateRandom(String charset, int length) {
        StringBuilder result = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charset.length());
            result.append(charset.charAt(index));
        }
        return result.toString();
    }
}
