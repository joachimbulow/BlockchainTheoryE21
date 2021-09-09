import org.apache.commons.lang3.ArrayUtils;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        //Create simple merkle tree
        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");
            ArrayList<String> leaves = new ArrayList<>(Arrays.asList("kdsa", "123", "132", "432", "432321", "hdsof"));
            MerkleTree finalTree = buildTree(leaves, md);
            System.out.println(bytesToHex(finalTree.encodedHash));

/*
            Leaf leaf1 = new Leaf(md, "Hejsa :)");
            Leaf leaf2 = new Leaf(md, "Hejsa :))");
            Leaf leaf3 = new Leaf(md, "Heyyy :))");
            Leaf leaf4 = new Leaf(md, "Hellouw :)");

            MerkleTree leftTree = new MerkleTree(md);
            MerkleTree rightTree = new MerkleTree(md);

            leftTree.leftLeaf = leaf1;
            leftTree.rightLeaf = leaf2;
            rightTree.leftLeaf = leaf3;
            rightTree.rightLeaf = leaf4;

            leftTree.digest();
            rightTree.digest();

            MerkleTree root = new MerkleTree(md);
            root.leftTree = leftTree;
            root.rightTree = rightTree;
            root.digest();

            System.out.println(bytesToHex(root.encodedHash));
*/
        }
        catch (NoSuchAlgorithmException e){
            System.out.println("No such algorithm :))))");
        }
    }

    private static MerkleTree buildTree(ArrayList<String> leaves, MessageDigest md){
        ArrayList<MerkleTree> trees = new ArrayList<>();
        // IOOB exception maybe?
        for (int i = 0; i < leaves.size(); i = i + 2){
            MerkleTree newTree = new MerkleTree(md);
            newTree.leftLeaf = new Leaf(md, leaves.get(i));;
            newTree.rightLeaf = i == leaves.size() - 1 ? new Leaf(md, "0") : new Leaf(md, leaves.get(i + 1));
            newTree.digest();
            trees.add(newTree);
        }
        return buildTreeTree(trees, md);
    }

    private static MerkleTree buildTreeTree(ArrayList<MerkleTree> trees, MessageDigest md){
        //End condition
        if (trees.size() == 1){
            return trees.get(0);
        }

        ArrayList<MerkleTree> newTrees = new ArrayList<>();
        for (int i = 0; i < trees.size(); i = i + 2){
            MerkleTree newTree = new MerkleTree(md);
            newTree.leftTree = trees.get(i);
            newTree.rightTree = i == trees.size() - 1 ? new MerkleTree(md) : trees.get(i + 1);
            newTree.digest();
            newTrees.add(newTree);
        }
        return buildTreeTree(newTrees, md);
    }




    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
