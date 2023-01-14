import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day15 implements AOCProblem {

    private List<Ingredient> ingredientList = new ArrayList<>();
    private int scoreMax = 0;

    public Day15(String s) {
        for (String line : InputReader.multipleLines(s)) {
            String name = line.substring(0, line.indexOf(':'));
            int[] values = Arrays.stream(line.replaceAll("[^0-9-]", " ").trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            ingredientList.add(new Ingredient(name, values[0], values[1], values[2], values[3], values[4]));
        }
    }

    @Override
    public int solvePart1() {
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100 - i; j++) {
                for (int k = 0; k <= 100 - i - j; k++) {
                    int l = 100 - i - j - k;
                    int score = get_score(new int[]{i, j, k, l});
                    scoreMax = Math.max(score, scoreMax);
                    //if (score == scoreMax && scoreMax > 0) System.out.printf("%3d Sugar, %3d Sprinkles, %3d Candy, %3d Chocolate: Score = %d\n", i,j,k,l, score);
                }

            }
        }
        return scoreMax;
    }

    private int get_score(int[] prop) {
        int cap = IntStream.range(0, prop.length).map(m -> ingredientList.get(m).cap * prop[m]).sum();
        int dur = IntStream.range(0, prop.length).map(m -> ingredientList.get(m).dur * prop[m]).sum();
        int flav = IntStream.range(0, prop.length).map(m -> ingredientList.get(m).flav * prop[m]).sum();
        int tex = IntStream.range(0, prop.length).map(m -> ingredientList.get(m).tex * prop[m]).sum();
        return Math.max(0, cap) * Math.max(0, dur) * Math.max(0, flav) * Math.max(0, tex);
    }

    @Override
    public int solvePart2() {
        if (scoreMax != 0) scoreMax = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100 - i; j++) {
                for (int k = 0; k <= 100 - i - j; k++) {
                    int l = 100 - i - j - k;
                    int score = get_score(new int[]{i, j, k, l});
                    int cal = get_cal(new int[]{i, j, k, l});
                    scoreMax = score > scoreMax && cal == 500 ? score : scoreMax;
                }
            }
        }
        return scoreMax;
    }

    private int get_cal(int[] prop) {
        return IntStream.range(0, prop.length).map(m -> ingredientList.get(m).cal * prop[m]).sum();
    }

    private record Ingredient(String name, int cap, int dur, int flav, int tex, int cal) {
    }
}
