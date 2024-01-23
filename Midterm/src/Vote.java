public class Vote {
    private String sender;
    private String signature;
    private Candidate candidate;
    private boolean voteUsed;

    public Vote(String sender, String signature, Candidate candidate) {
        this.sender = sender;
        this.signature = signature;
        this.candidate = candidate;
        this.voteUsed = false;
    }

    public String getSender() {
        return sender;
    }

    public String getSignature() {
        return signature;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public boolean getVoted() {
        return voteUsed;
    }

    public void setVoted(boolean voteUsed) {
        if (this.voteUsed) {
            return;
        }
        this.voteUsed = voteUsed;
    }
}
