public class Vote {
    private String sender;
    private String signature;
    private int candidate;

    public Vote(String sender, String signature) {
        this.sender = sender;
        this.signature = signature;
        this.candidate =
    }

    public String getSender() {
        return sender;
    }

    public String getSignature() {
        return signature;
    }

    public int getCandidate() {
        return candidate;
    }
}
