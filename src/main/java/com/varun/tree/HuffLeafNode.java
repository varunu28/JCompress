package com.varun.tree;

public record HuffLeafNode(char element, long weight) implements HuffBaseNode {
    @Override
    public boolean isLeaf() {
        return true;
    }
}
