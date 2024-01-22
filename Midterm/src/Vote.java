public class Vote {
    private String sender;
    private String signature;

    public Vote(String sender, String signature) {
        this.sender = sender;
        this.signature = signature;
    }

    public String getSender() {
        return sender;
    }

    public String getSignature() {
        return signature;
    }
}
