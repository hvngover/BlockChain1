import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Blockchain {
    private class Block {
        private final int index = blockCount + 1;
        private List<Transaction> transactions;
        private int transactionCount = 0;
        private final String previousHash;
        private final long timestamp;
        private final String hash;

        public Block(String previousHash, String hash) {
            this.transactions = new ArrayList<>();
            this.previousHash = previousHash;
            this.timestamp = System.currentTimeMillis();
            this.hash = hash;
        }

        public List<Transaction> getTransactions() {
            return transactions;
        }

        public int getTransactionCount() {
            return transactionCount;
        }
        public void addTransaction(Transaction transaction) {
            transactions.add(transaction);
            transactionCount++;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public String getHash() {
            return hash;
        }
    }

    public class Transaction {
        private String sender;
        private String receiver;
        private double amount;
        private String signature;

        public Transaction(String sender, String receiver, double amount, String signature) {
            this.sender = sender;
            this.receiver = receiver;
            this.amount = amount;
            this.signature = signature;
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

    public int blockCount = 0;
    public Hashtable<String ,Block> Chain;
    private List<String> HashList;

    private Blockchain() {
        this.Chain = new Hashtable<>();
    }

    public void addBlock(String user) throws NoSuchAlgorithmException {
        final String hash = user + blockCount;
        if (Chain.get(blockCount) == null) {
            Chain.put(hash, new Block("null", HashGenerator.createHash(hash)));
        } else {
            Chain.put(hash, new Block(Chain.get(blockCount).getHash(), HashGenerator.createHash(hash)));
        }
        HashList.add(blockCount++, hash);
        this.blockCount++;
    }
    public void addTransaction(int blockIndex, Transaction transaction) {
        Chain.get(HashList.get(blockIndex)).addTransaction(transaction);
    }
}
