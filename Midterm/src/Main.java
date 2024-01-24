import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("The purpose of the elections?");
        System.out.println("For example: Choosing New President");
        String reason = new Scanner(System.in).nextLine();

        List<Candidate> candidates = new ArrayList<>();

        System.out.println("How many Candidates?");
        int candidatesCount = new Scanner(System.in).nextInt();

        String []candidateNames = new String[candidatesCount];
        System.out.println("Their Full names?");

        for (int i = 1; i <= candidatesCount; i++) {
            System.out.println("Candidate #" + i);
            candidateNames[i-1] = new Scanner(System.in).nextLine();
            candidates.add(i-1, new Candidate(candidateNames[i-1], i));
        }
        
        VoteSystem voteSystem = ConcreteVoteSystem.getInstance(candidates, reason);

        boolean loop = true;
        while (loop) {
            System.out.println("1 - Cast Vote for Candidate\n" +
                    "2 - Print Information about Concrete Candidate\n" +
                    "3 - Print info about all Candidates\n" +
                    "4 - Stop Election");
            switch (new Scanner(System.in).nextInt()) {
                case 1:
                    System.out.println("Candidate Number?");
                    Candidate thisCandidate = candidates.get(new Scanner(System.in).nextInt());

                    System.out.println("Your FullName?");
                    String voter = new Scanner(System.in).nextLine();

                    System.out.println("Your Signature please");
                    String signature = new Scanner(System.in).nextLine();

                    Vote vote = new Vote(voter, signature, thisCandidate);
                    Vote castedVote = voteSystem.castVote(vote);

                    break;
                case 2:
                    System.out.println("Which Candidate");
                    try {
                        Candidate concreteCandidate = candidates.get(new Scanner(System.in).nextInt());
                        System.out.println("Candidate #" + concreteCandidate.getCandidateIndex());
                        System.out.println("Full Name: " + concreteCandidate.getFullName());
                        System.out.println("Votes Count " + concreteCandidate.getVotersCount());
                    } catch (NullPointerException e) {
                        System.out.println("There is no Such Candidate Number");
                    }
                    break;
                case 3:
                    for (Candidate candidate : voteSystem.getCandidates()) {
                        System.out.println("Candidate #" + candidate.getCandidateIndex());
                        System.out.println("Full Name: " + candidate.getFullName());
                        System.out.println("Votes Count " + candidate.getVotersCount());
                    }
                    break;
                case 4:
                    loop = false;
                    break;
                default:
                    System.out.println("There is no such choice");
            }
        }
        //System.out.println("Result of Election:");
        //List<Candidate> = new ArrayList<>();
        //for (Candidate candidate : candidates) {
          //  if ()
        //}
    }
}