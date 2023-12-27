public class DigitalSignature {
    public String sign(String data, String privateKey) {
        String hashedData = Hashing.hashData(data);
        return hashedData + privateKey;
    }

    public boolean verify(String data, String signature, String publicKey) {
        String hashedData = Hashing.hashData(data);
        String expectedSignature = hashedData + publicKey;
        return expectedSignature.equals(signature);
    }
}