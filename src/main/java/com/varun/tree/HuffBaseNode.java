package com.varun.tree;

public interface HuffBaseNode {

    default boolean isLeaf() {
        return false;
    }

    long weight();
}
