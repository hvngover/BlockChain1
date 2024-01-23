import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("The purpose of the elections?");
        System.out.println("For example: Choosing New President");
        String reason = new String(new Scanner(System.in).nextLine());

        List<Candidate> candidates = new ArrayList<>();

        System.out.println("How many Candidates?");
        int candidatesCount = new Scanner(System.in).nextInt();

        String []candidateNames = new String[candidatesCount];
        System.out.println("Their Full names?");

        for (int i = 1; i <= candidatesCount; i++) {
            System.out.println("Candidate #" + i);
            candidateNames[i] = new Scanner(System.in).nextLine();
            candidates.add(i, new Candidate(candidateNames[i], i));
        }
        
        VoteSystem voteSystem = ConcreteVoteSystem.getInstance(candidates, reason);

        boolean loop = true;
        while (loop) {
            System.out.println("1 - Cast Vote for Candidate\n2 - Print Information about Concrete Candidate");
            switch (new Scanner(System.in).nextInt()) {
                case 1:

                case 2:

                case 3:
                    for (Candidate candidate : voteSystem.getCandidates()) {
                        System.out.println("Candidate #" + candidate.getCandidateIndex());
                        System.out.println("Full Name: " + candidate.getFullName());
                        System.out.println("");
                    }
                case 4:

                default:
                    System.out.println("There is no such choice");
            }
        }
    }
}