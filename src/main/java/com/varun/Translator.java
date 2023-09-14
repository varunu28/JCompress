package com.varun;

import com.varun.tree.HuffBaseNode;
import com.varun.tree.HuffInternalNode;
import com.varun.tree.HuffLeafNode;
import com.varun.tree.HuffTree;

public final class Translator {

    public static String encode(String data, HuffTree root) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : data.toCharArray()) {
            String encodedValue = root.findHuffmanCode(c);
            if (encodedValue == null) {
                System.out.println("NULL");
            }
            stringBuilder.append(encodedValue);
        }
        return stringBuilder.toString();
    }

    public static String decode(String encodedData, HuffTree root) {
        HuffBaseNode curr = root.root();
        StringBuilder sb = new StringBuilder();
        for (char bit : encodedData.toCharArray()) {
            if (bit == '0') {
                curr = ((HuffInternalNode) curr).left();
            } else {
                curr = ((HuffInternalNode) curr).right();
            }
            if (curr.isLeaf()) {
                sb.append(((HuffLeafNode) curr).element());
                curr = root.root();
            }
        }
        return sb.toString();
    }
}
