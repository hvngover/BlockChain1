import java.util.HashSet;
import java.util.Set;

public class Candidate {
    private String fullName;
    private Set<Vote> voters;
    private int candidateIndex;
    private int votersCount;

    public Candidate(String fullName, int candidateIndex) {
        this.fullName = fullName;
        this.candidateIndex = candidateIndex;
        voters = new HashSet<>();
        votersCount = 0;
    }

    public void addVote(Vote vote) {
        voters.add(vote);
        votersCount += 1;
    }

    public int getCandidateIndex() {
        return candidateIndex;
    }

    public Set<Vote> getVoters() {
        return voters;
    }

    public String getFullName() {
        return fullName;
    }

    public int getVotersCount() {
        return votersCount;
    }
}
