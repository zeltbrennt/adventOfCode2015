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
        List<Integer> packages = new ArrayList<>(List.of(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));
        Collections.reverse(packages);
        for (int i = 1; i < packages.size(); i++) {
            List<int[]> combos = Combinations.getCombination(packages, packages.size(), i);
            for (int[] combo : combos) {
                List<Integer> remaining = new ArrayList<>(packages);
                for (int p : combo) {
                    remaining.remove((Integer) p);
                }
                System.out.println(Arrays.toString(combo) + " " + remaining);
            }
        }
        return 0;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
