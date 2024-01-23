import java.util.List;

public interface VoteSystem {
    Vote castVote(Vote sender);
    int getVotesForCandidate(String candidate);
    List<Candidate> getCandidates();
    void getCandidateVoters(String candidate);
}
