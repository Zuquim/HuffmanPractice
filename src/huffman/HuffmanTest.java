package huffman;

import java.util.Scanner;

public class HuffmanTest {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // fields
        int freq[] = {10, 15, 12, 3, 4, 13, 1};
        char code[] = {'a', 'e', 'i', 's', 't', ' ', '\n'};

        // build Huffman Tree using given codes/frequencies
        HuffmanTree hTree = new HuffmanTree(freq, code);

        // display contents of Huffman Tree in Pre-Order Traversal
        System.out.println("Display Tree:");
        HuffmanNode curr = hTree.root;
        hTree.getTree(curr);
        System.out.println("");

        // encode 'tea'
        System.out.println("Encode 'tea': " + hTree.encode("tea") + "\n");

        // decode 'tea' -- using the actual methods built in
        System.out.println("Decode '" + hTree.encode("tea") + "': " +
                hTree.decode(hTree.encode("tea")));
    }
}
