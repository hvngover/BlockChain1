public class AsymmetricEncryption {
    private static final String publicKey = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String privateKey = "ZYXWVUTSRQPONMLKJIHGFEDCBAzyxwvutsrqponmlkjihgfedcba9876543210";

    public boolean isPublicKey(String publicKey) {
        if (publicKey == this.publicKey) {
            return true;
        }
        return false;
    }

    public boolean isPrivateKey(String privateKey) {
        if (privateKey == this.privateKey) {
            return true;
        }
        return false;
    }

    public String encrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();

        for (char c : plaintext.toCharArray()) {
            int index = publicKey.indexOf(c);
            if (index != -1) {
                ciphertext.append(privateKey.charAt(index));
            } else {
                ciphertext.append(c);
            }
        }

        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) {
        StringBuilder plaintext = new StringBuilder();

        for (char c : ciphertext.toCharArray()) {
            int index = privateKey.indexOf(c);
            if (index != -1) {
                plaintext.append(publicKey.charAt(index));
            } else {
                plaintext.append(c);
            }
        }

        return plaintext.toString();
    }
}
