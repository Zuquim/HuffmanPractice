package huffman;

public class HuffmanNode implements Comparable<HuffmanNode> {
    public int value;
    public int weight;
    public HuffmanNode leftT;
    public HuffmanNode rightT;
    public HuffmanNode parent;

    public HuffmanNode() {
        parent = null;
    }

    public HuffmanNode(int v, int w, HuffmanNode lTree, HuffmanNode rTree, HuffmanNode par) {
        value = v;
        weight = w;
        leftT = lTree;
        rightT = rTree;
        parent = par;
    }

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

/*    @java.lang.Override
    public java.lang.String toString() {
        return "HuffmanNode{" +
                "value=" + value +
                ", weight=" + weight +
                ", leftT=" + leftT +
                ", rightT=" + rightT +
                ", parent=" + parent +
                '}';
    }*/
}
