import aocutil.AOCProblem;
import aocutil.Combinations;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day24 implements AOCProblem {
    private final List<String> packages;

    public Day24(String s) {
        this.packages = InputReader.multipleLines(s);
    }

    @Override
    public int solvePart1() {
        //List<Integer> packages = new ArrayList<>(List.of(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));
        List<Integer> packages = new ArrayList<>();
        this.packages.forEach(x -> packages.add(Integer.parseInt(x)));
        Collections.reverse(packages);
        long result = Long.MAX_VALUE;
        boolean found = false;
        for (int i = 1; i <= packages.size() / 2 && !found; i++) {
            List<int[]> combos = Combinations.getCombination(packages, packages.size(), i);
            for (int[] combo : combos) {
                List<Integer> remaining = new ArrayList<>(packages);
                for (int p : combo) {
                    remaining.remove((Integer) p);
                }
                double target = Arrays.stream(combo).sum();
                double test = remaining.stream().mapToDouble(Integer::doubleValue).sum();
                long prod = Arrays.stream(combo).mapToLong(Long::valueOf).reduce(1L, (a, b) -> a * b);
                if (test == 2 * target && prod < result) {
                    System.out.println(Arrays.toString(combo) + " " + prod);
                    result = prod;
                    found = true;
                }
            }
        }
        return (int) result;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
