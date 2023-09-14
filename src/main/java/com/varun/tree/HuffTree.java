package com.varun.tree;

public class HuffTree implements Comparable<HuffTree> {

    private final HuffBaseNode root;

    public HuffTree(char element, long weight) {
        root = new HuffLeafNode(element, weight);
    }

    public HuffTree(HuffBaseNode left, HuffBaseNode right, long weight) {
        root = new HuffInternalNode(weight, left, right);
    }

    public HuffBaseNode root() {
        return root;
    }

    public long weight() {
        return root.weight();
    }

    @Override
    public int compareTo(HuffTree o) {
        return Long.compare(root.weight(), o.weight());
    }

    public String findHuffmanCode(char target) {
        return findHuffmanCode(this.root, target);
    }

    private String findHuffmanCode(HuffBaseNode root, char target) {
        if (root == null) {
            return null;
        }
        if (root.isLeaf()) {
            return ((HuffLeafNode) root).element() == target ? "" : null;
        }
        String left = findHuffmanCode(((HuffInternalNode) root).left(), target);
        if (left != null) {
            return "0" + left;
        }
        String right = findHuffmanCode(((HuffInternalNode) root).right(), target);
        if (right != null) {
            return "1" + right;
        }
        return null;
    }
}
