import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface VoteSystem {
    void castVote(Vote sender) throws NoSuchAlgorithmException;
    int getVotesForCandidate();
    void getCandidates();
    void getCandidateVoters(String candidate);
}
