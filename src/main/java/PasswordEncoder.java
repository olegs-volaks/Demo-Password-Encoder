import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

public class PasswordEncoder {

    public String encode(String password, int level) {
        byte[] salt = getSalt();
        byte[] passwordByte = password.getBytes(StandardCharsets.UTF_8);
        try {
            return hash(salt, passwordByte, level);
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    public String encode(String password) {
        return encode(password, 8);
    }

    public boolean matches(String encodedPassword, String password) {
        if (encodedPassword.length() < 160) {
            throw new IncorrectPasswordFormException();
        }
        String[] data = encodedPassword.split("[$]");
        if (data.length < 3) {
            throw new IncorrectPasswordFormException();
        }
        BigInteger bigIntegerSalt = new BigInteger(data[1], 16);
        byte[] salt = bigIntegerSalt.toByteArray();

//      When converting numbers, we can get an extra (empty) start byte
        if (salt.length > 16) {
            salt = Arrays.copyOfRange(salt, 1, salt.length);
        }

        byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
        int level = Integer.parseInt(data[0]);
        String result;
        try {
            result = hash(salt, passwordBytes, level);
        } catch (NoSuchAlgorithmException ex) {
            return false;
        }
        return result.equals(encodedPassword);
    }

    private byte[] getSalt() {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[16];
            secureRandom.nextBytes(salt);
            return salt;
        } catch (NoSuchAlgorithmException ex) {
            return null;
        }
    }

    private String hash(byte[] salt, byte[] password, int level) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        messageDigest.update(salt);
        messageDigest.update(password);
        byte[] hashedPasswordBytes = messageDigest.digest();
        for (int i = 1; i < level; i++) {
            messageDigest.reset();
            messageDigest.update(hashedPasswordBytes);
            hashedPasswordBytes = messageDigest.digest();
        }
        BigInteger hashedPasswordBigInteger = new BigInteger(1, hashedPasswordBytes);
        BigInteger saltBigInteger = new BigInteger(1, salt);
        return level + "$" + saltBigInteger.toString(16) + "$" + hashedPasswordBigInteger.toString(16);
    }
}
