package com.varun.tree;

import java.util.Map;
import java.util.PriorityQueue;

public final class TreeBuilder {

    public static HuffTree buildTree(Map<Character, Long> frequencyMap) {
        PriorityQueue<HuffTree> priorityQueue = buildPriorityQueue(frequencyMap);
        HuffTree nodeOne = null;
        HuffTree nodeTwo = null;
        HuffTree nodeThree = null;
        while (priorityQueue.size() > 1) {
            nodeOne = priorityQueue.remove();
            nodeTwo = priorityQueue.remove();
            nodeThree = new HuffTree(nodeOne.root(), nodeTwo.root(), nodeOne.weight() + nodeTwo.weight());
            priorityQueue.add(nodeThree);
        }
        return nodeThree;
    }

    private static PriorityQueue<HuffTree> buildPriorityQueue(Map<Character, Long> frequencyMap) {
        PriorityQueue<HuffTree> priorityQueue = new PriorityQueue<>();
        for (Map.Entry<Character, Long> entry : frequencyMap.entrySet()) {
            priorityQueue.add(new HuffTree(entry.getKey(), entry.getValue()));
        }
        return priorityQueue;
    }
}
