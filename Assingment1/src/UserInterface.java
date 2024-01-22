import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

class UserInterface {
    private Blockchain blockchain;

    public UserInterface(Blockchain blockchain) {
        this.blockchain = blockchain;
    }

    public void start() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Transaction");
            System.out.println("2. Print Blockchain");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTransaction();
                    break;
                case 2:
                    blockchain.printBlockchain();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void addTransaction() throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter sender: ");
        String sender = scanner.nextLine();
        System.out.print("Enter receiver: ");
        String receiver = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        String signature = "dummy_signature";

        Blockchain.Transaction transaction = new Blockchain.Transaction(sender, receiver, amount, signature);
        blockchain.addTransaction(transaction, signature);
        System.out.println("Transaction added successfully!");
    }
}
