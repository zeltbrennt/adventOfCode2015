import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.*;

public class Day09 implements AOCProblem {

    private final Map<String, List<String>> adjecencyMap;
    private final Map<String, Node> nodes;

    public Day09(String input) {
        adjecencyMap = new HashMap<>();
        nodes = new HashMap<>();
        List<String[]> puzzle = InputReader.multipleLines(input).stream().map(x -> x.split("\\sto\\s|\\s=\\s")).toList();
        for (String[] strings : puzzle) {
            String nodeA = strings[0];
            String nodeB = strings[1];
            int distance = Integer.parseInt(strings[2]);
            if (adjecencyMap.containsKey(nodeA)) adjecencyMap.get(nodeA).add(nodeB);
            else {
                adjecencyMap.put(nodeA, new ArrayList<>(Collections.singletonList(nodeB)));
                nodes.put(nodeA, new Node(nodeA));
            }
            nodes.get(nodeA).con.put(nodeB,distance);
            if (adjecencyMap.containsKey(nodeB)) adjecencyMap.get(nodeB).add(nodeA);
            else {
                adjecencyMap.put(nodeB, new ArrayList<>(Collections.singletonList(nodeA)));
                nodes.put(nodeB, new Node(nodeB));
            }
            nodes.get(nodeB).con.put(nodeA, distance);
        }
    }


    @Override
    public int solvePart1() {
        return 0;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}

class Node {

    final String name;
    final Map<String, Integer> con;

    Node(String name) {
        this.name = name;
        this.con = new HashMap<>();
    }
}