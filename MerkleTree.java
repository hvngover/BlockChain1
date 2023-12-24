import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree {
    private List<String> leaves;
    private List<String> merkleTree;

    public MerkleTree(List<String> leaves) {
        this.leaves = leaves;
        this.merkleTree = buildMerkleTree();
    }

    private List<String> buildMerkleTree() {
        List<String> tree = new ArrayList<>(leaves);

        while (tree.size() > 1) {
            List<String> newTree = new ArrayList<>();
            for (int i = 0; i < tree.size() - 1; i += 2) {
                String hash = hash(tree.get(i) + tree.get(i + 1));
                newTree.add(hash);
            }
            if (tree.size() % 2 != 0) {
                String hash = hash(tree.get(tree.size() - 1) + tree.get(tree.size() - 1));
                newTree.add(hash);
            }
            tree = newTree;
        }

        return tree;
    }

    private String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getMerkleRoot() {
        if (merkleTree.isEmpty()) {
            return null;
        }
        return merkleTree.get(0);
    }
}