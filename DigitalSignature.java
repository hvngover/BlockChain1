public class DigitalSignature {
    public static String sign(String data, String privateKey) {
        String hashedData = Hashing.hashData(data);
        return hashedData + privateKey;
    }

    public static boolean verify(String data, String signature, String publicKey) {
        String hashedData = Hashing.hashData(data);
        String expectedSignature = hashedData + publicKey;
        return expectedSignature.equals(signature);
    }
}