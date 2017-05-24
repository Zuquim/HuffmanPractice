package huffman;

public class HuffmanNode implements Comparable<HuffmanNode> {
    // fields
    int value;
    int weight;
    HuffmanNode leftTree;
    HuffmanNode rightTree;
    HuffmanNode parent;

    // constructors
    HuffmanNode() {
        parent = null;
    }

    HuffmanNode(int v, int w, HuffmanNode lTree, HuffmanNode rTree, HuffmanNode par) {
        value = v;
        weight = w;
        leftTree = lTree;
        rightTree = rTree;
        parent = par;
    }

    // setters/getters

    @Override
    public int compareTo(HuffmanNode rhs) {
        return weight - rhs.weight;
    }

    @Override
    public String toString() {
        String str = "";
        str += this.value;
        return str;
    }
}
