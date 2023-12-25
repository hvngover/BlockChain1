import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String hashData(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(data.getBytes());

        StringBuilder hashStringBuilder = new StringBuilder();
        for (byte hashByte : hashBytes) {
            hashStringBuilder.append(String.format("%02x", hashByte));
        }

        return hashStringBuilder.toString();
    }
}
