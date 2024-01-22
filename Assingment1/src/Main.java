public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = Blockchain.getInstance();
        UserInterface ui = new UserInterface(blockchain);

        ui.start();
    }
}
