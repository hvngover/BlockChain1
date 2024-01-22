import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("How many Candidates?");
        int candidatesCount = new Scanner(System.in).nextInt();
        String []candidates = new String[candidatesCount];
        for (int i = 0; i > candidatesCount; i++) {
            candidates[i] = new Scanner(System.in).nextLine();
        }
        VoteSystem voteSystem = ConcreteVoteSystem.getInstance(candidates);

        voteSystem.getCandidates();
    }
}