import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = Blockchain.getInstance();
        AsymmetricEncryption ae = new AsymmetricEncryption();

        String publicKey = ae.getPublicKey();
        String encryptData = ae.encrypt("Hello", publicKey);

    }
}
