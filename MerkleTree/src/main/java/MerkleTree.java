import java.security.MessageDigest;

public class MerkleTree {
    MessageDigest md;
    MerkleTree leftTree;
    MerkleTree rightTree;
    Leaf leftLeaf;
    Leaf rightLeaf;


    public MerkleTree(MessageDigest md){
        this.md = md;
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

    public byte[] digest(){
        return null;
    }

    public void printTree(){
        System.out.println(this);
    }





}
