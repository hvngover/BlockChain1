import java.util.*;

public class ConcreteVoteSystem implements VoteSystem {
    private static VoteSystem instance;
    private Blockchain blockchain;
    private Set<Vote> votes;
    private List<Candidate> candidates;
    private String reason;


    private ConcreteVoteSystem(List<Candidate> candidates, String reason) {
        blockchain = Blockchain.getInstance();
        votes = new HashSet<>();
        this.reason = reason;

        this.candidates = new ArrayList<>();
        for (Candidate candidate : candidates) {
            this.candidates.add(candidate);
        }
    }



    public static VoteSystem getInstance(List<Candidate> candidates, String reason) {
        if (instance == null) {
            instance = new ConcreteVoteSystem(candidates, reason);
        }
        return instance;
    }

    @Override
    public Vote castVote(Vote sender) {
        if (!votes.contains(sender)) {
            candidates.remove(sender.getCandidate());
            candidates.get(sender.getCandidate().getCandidateIndex()).addVote(sender);
            votes.add(sender);
            sender.setVoted(true);
        }
        return sender;
    }

    @Override
    public int getVotesForCandidate(String candidate) {
        for (Candidate c : candidates) {
            if (c.getFullName() == candidate) {
                return c.getVotersCount();
            }
        }
        return 0;
    }

    @Override
    public List<Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public void getCandidateVoters(String candidate) {

    }
}
