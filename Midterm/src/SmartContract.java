import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface SmartContract {
    void castVote(Vote sender, int candidate) throws NoSuchAlgorithmException;
    int getVotesForCandidate();
    void getCandidates();
}
