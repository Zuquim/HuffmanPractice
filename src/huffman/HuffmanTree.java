package huffman;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class HuffmanTree {
    private int size = 0;
    HuffmanNode root = new HuffmanNode();
    private PriorityQueue<HuffmanNode> huffmanQueue = new PriorityQueue();
    private ArrayList<String> pathTable = new ArrayList();
    private ArrayList<Character> valueTable = new ArrayList();
    HuffmanTree(int[] freq, char[] code) {
        this.size = freq.length;

        if (freq.length != code.length) {
            throw new UnsupportedOperationException("Error: Character and code length mismatch.");
        }

        for (int i = 0; i < this.size; i++) {
            huffmanQueue.offer(new HuffmanNode(code[i], freq[i], null, null, null));
        }

        createTree();
        createTable(this.root, "");
    }

    private void createTree() {
        // while elements remain in huffmanQueue, add to tree
        while (huffmanQueue.size() > 1) {
            // pop off two minimum elements in huffmanQueue
            HuffmanNode tempL = huffmanQueue.poll();
            HuffmanNode tempR = huffmanQueue.poll();

            // create root for two minimum elements and build tree
            HuffmanNode parent = new HuffmanNode(0, tempL.weight + tempR.weight, tempL, tempR, null);
            tempL.parent = parent;
            tempR.parent = parent;

            // add new tree back in huffmanQueue
            huffmanQueue.offer(parent);
            this.size++;
        }

        // set HuffmanTree root to remaining element in huffmanQueue
        this.root = huffmanQueue.peek();
    }

    private void createTable(HuffmanNode curr, String str) {
        // if iterator is null, return
        if (curr == null) return;

        // else if leaf, display path and value
        if (curr.leftT == null && curr.rightT == null) {
            char tempChar;
            if (curr.value == 32)
                tempChar = ' ';

            if (curr.value == 10)
                tempChar = 'n';

            else
                tempChar = (char) curr.value;
            // add value and path to code tables
            this.valueTable.add(tempChar);
            this.pathTable.add(str);
        }

        // add 0 if before moving to left child
        str += "0";
        // recursively call in pre-order
        createTable(curr.leftT, str);

        // adjust path and add 1 before moving to right child
        str = str.substring(0, str.length() - 1);
        str += "1";
        createTable(curr.rightT, str);
    }

    // global variable used for representing 'levels' of tree
    private String tacks = "";

    void getTree(HuffmanNode curr) {
        // if iterator is null, return
        if (curr == null) return;

        // else if leaf, display level, weight, and value
        if (curr.leftT == null && curr.rightT == null) {
            // case statements to handle displaying space and newline
            switch (curr.value) {
                case 32:
                    System.out.println(tacks + curr.weight + ": sp");
                    break;
                case 10:
                    System.out.println(tacks + curr.weight + ": nl");
                    break;
                default:
                    System.out.println(tacks + curr.weight + ": " + (char) curr.value);
                    break;
            }
        }

        // else display level and weight
        else
            System.out.println(tacks + curr.weight);

        // increment level marker
        tacks += "- ";
        // recursively call in pre-order
        getTree(curr.leftT);
        getTree(curr.rightT);
        // decrement level marker
        tacks = tacks.substring(0, tacks.length() - 2);
    }

    public int getSize() {
        return this.size;
    }

    /**
     * returns encoded bits for a given string
     */
    String encode(String input) {
        // create empty string to hold code
        String str = "";

        // iterate through given string
        for (int x = 0; x < input.length(); x++) {
            // iterate through code tables
            for (int i = 0; i < valueTable.size(); i++) {
                // if char in string matches code in table, add path to string
                if (valueTable.get(i) == input.charAt(x))
                    str += pathTable.get(i);
            }
        }
        return str;
    }

    /**
     * returns decoded string for a given set of bits
     */
    String decode(String bits) {
        // create empty string to hold decoded message
        String decodedStr = "";

        // iterate through bits
        for (int i = 0; i < bits.length(); i++) {
            if (!getChar(bits.substring(0, i + 1)).equals("")) {
                decodedStr += getChar(bits.substring(0, i + 1));
                bits = bits.substring(i + 1);
                i = 0;
            }
        }
        return decodedStr;
    }

    /**
     * returns character for a given set of bits
     */
    private String getChar(String bits) {
        // create string to hold potential character
        String character = "";
        // traverse code table to seek match
        for (int i = 0; i < pathTable.size(); i++) {
            // add to string if match is found
            if (pathTable.get(i).equals(bits))
                character = valueTable.get(i).toString();
        }
        return character;
    }
}
