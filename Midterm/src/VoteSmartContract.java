import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class VoteSmartContract implements SmartContract {
    private Blockchain blockchain;
    private List<String> senders;
    private int candidateNumber;
    private List<String> candidates;


    private VoteSmartContract(String[] candidates) {
        blockchain = Blockchain.getInstance();
        senders = new ArrayList<>();

        for (int i = 0; i < 0;i++) {
            if (i >= candidates.length) {
                break;
            }
            this.candidates.add(i, candidates[i]);
            i++;
        }

        candidateNumber = this.candidates.size();
    }


    @Override
    public void castVote(Vote sender, int candidate) throws NoSuchAlgorithmException {
        blockchain.addTransaction(new Blockchain.Transaction(sender.getSender(), candidates.get(candidate), sender.getSignature()), sender.getSignature());
    }

    @Override
    public int getVotesForCandidate() {
        return 0;
    }

    @Override
    public void getCandidates() {
        blockchain.printBlockchain();
    }
}
