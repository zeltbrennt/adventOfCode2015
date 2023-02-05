import aocutil.AOCProblem;
import aocutil.Combinations;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day24 implements AOCProblem {
    private final List<String> packages;

    public Day24(String s) {
        this.packages = InputReader.multipleLines(s);
    }

    private boolean isBalanced(List<Integer> arr, int target) {
        for (int i = 1; i < arr.size() / 2; i++) {
            System.out.println(i);
            List<int[]> combos = Combinations.getCombination(arr, arr.size(), i);
            for (int[] combo : combos) {
            int weight = 0;
                List<Integer> remaining = new ArrayList<>(arr);
                for (int p : combo) {
                    weight += p;
                    remaining.remove((Integer) p);
                }
                if (weight != target) continue;
                if (remaining.stream().mapToDouble(x -> x).sum() == weight) return true;
            }
        }
        return false;
    }

    @Override
    public int solvePart1() {
        //List<Integer> packages = new ArrayList<>(List.of(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));
        List<Integer> packages = new ArrayList<>();
        this.packages.forEach(x -> packages.add(Integer.parseInt(x)));
        Collections.reverse(packages);
        boolean found = false;
        for (int i = 1; i < packages.size() / 2; i++) {
            System.out.println(i);
            List<int[]> combos = Combinations.getCombination(packages, packages.size(), i);
            for (int[] combo : combos) {
            int weight = 0;
                List<Integer> remaining = new ArrayList<>(packages);
                for (int p : combo) {
                    remaining.remove((Integer) p);
                    weight += p;
                }
                if (isBalanced(remaining, weight)) {
                    System.out.println(Arrays.toString(combo) + " " + remaining);
                    found = true;
                }
            }
            if (found) break;
        }
        return 0;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
