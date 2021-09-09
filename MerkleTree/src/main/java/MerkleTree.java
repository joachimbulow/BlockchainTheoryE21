import org.apache.commons.lang3.ArrayUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MerkleTree {
    MessageDigest md;
    byte[] encodedHash;

    MerkleTree leftTree;
    MerkleTree rightTree;
    Leaf leftLeaf;
    Leaf rightLeaf;


    public MerkleTree(MessageDigest md) {
        this.md = md;
        //Default random hash value for newly created trees
        this.encodedHash = md.digest("Hej".getBytes());
    }

    public void add(MerkleTree leftTree, MerkleTree rightTree){

    }

    public void add(Leaf leftLeaf, Leaf rightLeaf){
        this.leftLeaf = leftLeaf;
        this.rightLeaf = rightLeaf;
    }

    public MerkleTree getLeftTree(){
        return this.leftTree;
    }

    public MerkleTree getRightTree(){
        return this.rightTree;
    }

    public Leaf getLeftLeaf() {
        return this.leftLeaf;
    }

    public Leaf getRightLeaf() {
        return this.rightLeaf;
    }

    public byte[] getEncodedHash() {return this.encodedHash; }

    public byte[] digest(){
        byte[] hash1 = leftLeaf == null ? this.leftTree.getEncodedHash() : this.leftLeaf.getEncodedhash();
        byte[] hash2 = rightLeaf == null ? this.rightTree.getEncodedHash() : this.rightLeaf.getEncodedhash();

        byte[] hash = md.digest(ArrayUtils.addAll(hash1, hash2));
        this.encodedHash = hash;
        return hash;
    }

    public void printTree(){
        System.out.println(this);
    }





}
