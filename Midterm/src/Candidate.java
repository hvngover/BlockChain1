import java.util.HashSet;
import java.util.Set;

public class Candidate {
    private String fullName;
    private Set<Vote> voters;
    private int votersCount;

    public Candidate(String fullName) {
        this.fullName = fullName;
        voters = new HashSet<>();
    }

    public void addVote(Vote vote) {
        voters.add(vote);
        votersCount++;
    }

    public Set<Vote> getVoters() {
        return voters;
    }

    public String getFullName() {
        return fullName;
    }
}
