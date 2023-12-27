import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing {
    public static String hashData(String data) {
        int hash = 0;

        for (char c : data.toCharArray()) {
            hash ^= (hash << 5) + c + (hash >> 2);
        }

        return Integer.toHexString(hash);
    }
}
