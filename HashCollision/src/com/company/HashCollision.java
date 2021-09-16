package com.company;

import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;

public class HashCollision implements Runnable {

    List<String> input;
    private MessageDigest md;
    int bitLength;

    public HashCollision(List<String> input, int bitLength, MessageDigest md){
        this.input = input;
        this.md = md;
        this.bitLength = bitLength;
    }

    @Override
    public void run() {

        for (String inputString : input){
            long startTime = System.currentTimeMillis();
            System.out.println("Now checking for collision from string: " + inputString);
            String encodedHex = Main.bytesToHex(md.digest(inputString.getBytes()));
            boolean isDone = false;
            long l = System.currentTimeMillis();
            while (l - startTime < 10000) {
                UUID uuid = UUID.randomUUID();
                String randomHash = Main.bytesToHex(md.digest(uuid.toString().getBytes()));
                int counter1 = 0;
                for (int counter2 = bitLength; counter2 < encodedHex.length(); counter2++) {
                    if (encodedHex.substring(counter1, counter2).equals(randomHash.substring(counter1, counter2))) {
                        System.out.println("Found collision between: " + inputString + " and " + uuid);
                        isDone = true;
                        break;
                    }
                    counter1++;
                }
                if (isDone){
                    break;
                }

            }
            if (!isDone) System.out.println("No collision found :(");
            isDone = false;
        }
    }
}
