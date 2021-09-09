import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Leaf {

    public MessageDigest md;

    public byte[] encodedhash;

    public Leaf(MessageDigest md, String message) {
        this.md = md;
        this.encodedhash = md.digest(message.getBytes());
    }

    public byte[] getEncodedhash(){
        return this.encodedhash;
    }
}
