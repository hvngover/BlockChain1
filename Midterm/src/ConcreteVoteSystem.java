import java.security.NoSuchAlgorithmException;
import java.util.*;

public class ConcreteVoteSystem implements VoteSystem {
    private static VoteSystem instance;
    private Blockchain blockchain;
    private Set<Vote> votes;
    private Set<Candidate> candidates;


    private ConcreteVoteSystem(List<Candidate> candidates) {
        blockchain = Blockchain.getInstance();
        votes = new HashSet<>();

        for (Candidate candidate : candidates) {
            this.candidates.add(candidate);
        }
    }


    public static VoteSystem getInstance(List<Candidate> candidates) {
        if (instance == null) {
            return new ConcreteVoteSystem(candidates);
        }
        return instance;
    }

    @Override
    public void castVote(Vote sender) throws NoSuchAlgorithmException {
        Candidate[] candidatesTemp = candidates.toArray(new Candidate[candidates.size()]);
        candidatesTemp[sender.getCandidate()];
        votes.add(sender);
    }

    @Override
    public int getVotesForCandidate() {
        return 0;
    }

    @Override
    public void getCandidates() {
        blockchain.printBlockchain();
    }

    @Override
    public void getCandidateVoters(String candidate) {

    }
}
