import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class DigitalSignature {
    public String sign(String data, String privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        PrivateKey key = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
        signature.initSign(key);
        signature.update(data.getBytes());
        byte[] signatureBytes = signature.sign();
        return Base64.getEncoder().encodeToString(signatureBytes);
    }

    public boolean verify(String data, String signature, String publicKey) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        PublicKey key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
        sig.initVerify(key);
        sig.update(data.getBytes());
        return sig.verify(Base64.getDecoder().decode(signature));
    }
}