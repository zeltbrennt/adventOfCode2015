import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.*;

public class Day09 implements AOCProblem {

    private final Map<String, Node> nodes;

    private int shortestDistance = Integer.MAX_VALUE;
    private int longestDistance = Integer.MIN_VALUE;

    public Day09(String input) {
        nodes = new HashMap<>();
        List<String[]> puzzle = InputReader.multipleLines(input).stream().map(x -> x.split("\\sto\\s|\\s=\\s")).toList();
        for (String[] strings : puzzle) {
            String nodeA = strings[0];
            String nodeB = strings[1];
            int distance = Integer.parseInt(strings[2]);
            if (!nodes.containsKey(nodeA))  nodes.put(nodeA, new Node(nodeA));
            nodes.get(nodeA).con.put(nodeB,distance);
            if (!nodes.containsKey(nodeB))  nodes.put(nodeB, new Node(nodeB));
            nodes.get(nodeB).con.put(nodeA, distance);
        }

    }

    private void permutations() {
        String[] arr = nodes.keySet().toArray(new String[0]);
        calcDistance(arr);
        int n = arr.length;
        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }
        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(arr, i % 2 == 0 ?  0: indexes[i], i);
                calcDistance(arr);
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 0;
                i++;
            }
        }
    }
    private  static <T> void swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private void calcDistance(String[] permutation) {
        int dist = 0;
        for (int i = 1;  i < permutation.length ; i++) {
            dist += nodes.get(permutation[i-1]).con.get(permutation[i]);
        }
        if (dist < shortestDistance) {
            shortestDistance = dist;
            System.out.printf("%s is minimum @ %d\n", Arrays.toString(permutation), shortestDistance);
        }
        if (dist > longestDistance) {
            longestDistance = dist;
            System.out.printf("%s is MAXIMUM @ %d\n", Arrays.toString(permutation), longestDistance);
        }
    }



    @Override
    public int solvePart1() {
        permutations();
        return shortestDistance;
    }

    @Override
    public int solvePart2() {
        return longestDistance;
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