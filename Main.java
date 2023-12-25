public class Main {
    public static void main(String[] args) throws Exception {
        Blockchain blockchain = Blockchain.getInstance();
        AsymmetricEncryption ae = new AsymmetricEncryption();
        UserInterface ui = new UserInterface(blockchain);

        ui.start();
    }
}
