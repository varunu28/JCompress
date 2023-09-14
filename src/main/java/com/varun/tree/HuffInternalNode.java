package com.varun.tree;

public record HuffInternalNode(long weight, HuffBaseNode left, HuffBaseNode right) implements HuffBaseNode {
}
