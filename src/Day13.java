import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 implements AOCProblem {

    private final List<String> puzzle;
    private final Map<String, Guest> guests = new HashMap<>();
    ;
    private String[] tableOrder;
    private int maximumHappiness = Integer.MIN_VALUE;

    public Day13(String s) {
        puzzle = InputReader.multipleLines(s);
        for (String line : puzzle) {
            String[] tokens = line.split(" ");
            String name = tokens[0];
            String neighbor = tokens[tokens.length - 1].replace(".", "");
            int value = Integer.parseInt(tokens[3]);
            value = tokens[2].equals("lose") ? -value : value;
            guests.putIfAbsent(name, new Guest(name));
            guests.get(name).happiness.put(neighbor, value);
        }
        tableOrder = guests.keySet().toArray(new String[0]);
    }

    private static <T> void swap(T[] elements, int a, int b) {
        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private <T> void permutations(int n, T[] arr) {
        if (n == 1) calcHappiness((String[]) arr);
        else {
            for (int i = 0; i < n - 1; i++) {
                permutations(n - 1, arr);
                if (n % 2 == 0) swap(arr, i, n - 1);
                else swap(arr, 0, n - 1);
            }
            permutations(n - 1, arr);
        }
    }

    private void calcHappiness(String[] order) {
        int value = 0;
        for (int i = 0; i < order.length; i++) {
            String left = i == 0 ? order[order.length - 1] : order[i - 1];
            String right = i == order.length - 1 ? order[0] : order[i + 1];
            value += guests.get(order[i]).happiness.get(left);
            value += guests.get(order[i]).happiness.get(right);
        }
        if (value > maximumHappiness) maximumHappiness = value;
    }

    @Override
    public int solvePart1() {
        permutations(tableOrder.length, tableOrder);
        return maximumHappiness;
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private class Guest {

        final String name;
        final Map<String, Integer> happiness;

        Guest(String name) {
            this.name = name;
            this.happiness = new HashMap<>();
        }
    }

}

