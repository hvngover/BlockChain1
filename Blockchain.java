import java.security.NoSuchAlgorithmException;
import java.util.*;

class Blockchain {
    private class Block {
        private List<Transaction> transactions;
        private Transaction lastTransaction;
        private final String previousHash;
        private final int maxTransactions = 5;
        private final String merkleRoot;
        private final long timestamp;
        private final String hash;

        public Block(String previousHash, List<String> transactionData, Transaction transaction) throws NoSuchAlgorithmException {
            this.lastTransaction = transaction;
            this.transactions = new ArrayList<>();
            transactions.add(lastTransaction);
            this.merkleRoot = calculateMerkleRoot(transactionData);
            this.previousHash = previousHash;
            this.timestamp = System.currentTimeMillis();
            this.hash = calculateBlockHash();
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getHash() {
            return hash;
        }

        public int getMaxTransactions() {
            return maxTransactions;
        }

        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
        }

        public String getPreviousHash() {
            return previousHash;
        }

        public String getMerkleRoot() {
            return merkleRoot;
        }

        private String calculateBlockHash() throws NoSuchAlgorithmException {
            String blockData = previousHash + lastTransaction.getAmount() + timestamp + merkleRoot;
            return Hashing.hashData(blockData);
        }

        private String calculateMerkleRoot(List<String> transactionData) throws NoSuchAlgorithmException {
            if (transactionData == null || transactionData.isEmpty()) {
                return "";
            }
            MerkleTree merkleTree = new MerkleTree(transactionData);
            return Hashing.hashData(merkleTree.getMerkleRoot());
        }
    }

    public static class Transaction {
        private final String sender;
        private final String receiver;
        private final double amount;
        private final String signature;

        public Transaction(String sender, String receiver, double amount, String signature) {
            this.sender = sender;
            this.receiver = receiver;
            this.amount = amount;
            this.signature = signature;
        }

        private String encryptTransactionData(String publicKey) throws Exception {
            AsymmetricEncryption encryption = new AsymmetricEncryption();
            return encryption.encrypt(sender + receiver + amount + signature);
        }

        private void decryptTransactionData(String encryptedData, String privateKey) throws Exception {
            AsymmetricEncryption encryption = new AsymmetricEncryption();
            String decryptedData = encryption.decrypt(encryptedData);
        }

        public String getSender() {
            return sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public double getAmount() {
            return amount;
        }

        public String getSignature() {
            return signature;
        }
    }

    private static Blockchain instance;
    private int blockCount = 0;
    private Hashtable<String, Block> Chain;
    private List<String> HashList;

    private Blockchain() {
        this.Chain = new Hashtable<>();
        this.HashList = new ArrayList<>();
    }

    public static Blockchain getInstance() {
        if (instance == null) {
            instance = new Blockchain();
        }
        return instance;
    }

    private void addBlock(Transaction transaction, List<String> transactionData) throws NoSuchAlgorithmException {
        Block block;
        if (HashList.get(blockCount) == null) {
            block = new Block("null", transactionData, transaction);
        } else {
            block = new Block(Chain.get(HashList.get(blockCount)).getHash(), transactionData, transaction);
        }
        Chain.put(block.getHash(), block);
        HashList.add(block.getHash());
        this.blockCount++;
    }

    public void addTransaction(Transaction transaction, String signature) throws NoSuchAlgorithmException {
        if (!transaction.getSignature().equals(signature)) {
            System.out.println("Signature is incorrect");
            return;
        }
        if (HashList.isEmpty()) {
            HashList.add(0, "");
            List<String> list = new ArrayList<>();
            list.add("");
            Chain.put("",new Block("", list, new Transaction("", "", 0, "")));
        }
        if (Chain.get(HashList.get(blockCount)).getTransactions().size() == Chain.get(HashList.get(blockCount)).getMaxTransactions()) {
            List<String> transactionData = new ArrayList<>();
            for (Transaction tx : Chain.get(HashList.get(blockCount)).getTransactions()) {
                transactionData.add(tx.getSender() + tx.getReceiver() + tx.getAmount() + tx.getSignature());
            }

            transactionData.add(transaction.getSender() + transaction.getReceiver() + transaction.getAmount() + transaction.getSignature());
            addBlock(transaction, transactionData);
        } else {
            Chain.get(HashList.get(blockCount)).addTransaction(transaction);
        }
    }

    public void printBlockchain() {
        for (String hash : HashList) {
            Block block = Chain.get(hash);
            System.out.println("Block Hash: " + block.getHash());
            System.out.println("Previous Hash: " + block.getPreviousHash());
            System.out.println("Timestamp: " + block.getTimestamp());
            System.out.println("Merkle Root: " + block.getMerkleRoot());
            System.out.println("Transactions:");

            for (Transaction transaction : block.getTransactions()) {
                System.out.println("---------------------------");
                System.out.println("|  Sender: " + transaction.getSender() + "  |");
                System.out.println("|  Receiver: " + transaction.getReceiver() + "  |");
                System.out.println("|  Amount: " + transaction.getAmount() + "  |");
                System.out.println("|  Signature: " + transaction.getSignature() + "  |");
                System.out.println("---------------------------");
                for (int c = 0; c < 3; c++) {
                    System.out.print("            {}\n");
                }
            }
            System.out.println("----------------------------");
        }
    }
}
