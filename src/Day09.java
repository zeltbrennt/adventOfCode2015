import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

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
            if (!nodes.containsKey(nodeA)) nodes.put(nodeA, new Node(nodeA));
            nodes.get(nodeA).con.put(nodeB, distance);
            if (!nodes.containsKey(nodeB)) nodes.put(nodeB, new Node(nodeB));
            nodes.get(nodeB).con.put(nodeA, distance);
        }

    }

    private static <T> void swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private <T> void permutations(int n, T[] arr) {
        if (n == 1) calcDistance((String[]) arr);
        else {
            for (int i = 0; i < n - 1; i++) {
                permutations(n - 1, arr);
                if (n % 2 == 0) swap(arr, i, n - 1);
                else swap(arr, 0, n - 1);
            }
            permutations(n - 1, arr);
        }
    }

    private void calcDistance(String[] permutation) {
        int dist = IntStream.range(1, permutation.length).map(i -> nodes.get(permutation[i - 1]).con.get(permutation[i])).sum();
        if (dist < shortestDistance) {
            shortestDistance = dist;
            //System.out.printf("%s is minimum @ %d\n", Arrays.toString(permutation), shortestDistance);
        }
        if (dist > longestDistance) {
            longestDistance = dist;
            //System.out.printf("%s is MAXIMUM @ %d\n", Arrays.toString(permutation), longestDistance);
        }
    }


    @Override
    public int solvePart1() {
        String[] arr = nodes.keySet().toArray(new String[0]);
        permutations(arr.length, arr);
        return shortestDistance;
    }

    @Override
    public int solvePart2() {
        if (longestDistance == Integer.MIN_VALUE) solvePart1();
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