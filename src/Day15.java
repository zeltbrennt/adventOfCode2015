import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Day15 implements AOCProblem {
    
    private List<Ingredient> ingredientList = new ArrayList<>();
    private int scoreMax = 0;

    public Day15(String s) {
        for (String line : InputReader.multipleLines(s)) {
            String name = line.substring(0, line.indexOf(':'));
            int[] values =  Arrays.stream(line.replaceAll("[^0-9-]", " ").trim().split(" +")).mapToInt(Integer::parseInt).toArray();
            ingredientList.add(new Ingredient(name, values[0], values[1], values[2], values[3], values[4]));
        }
    }

    @Override
    public int solvePart1() {
        for (int i = 0; i <= 100; i++) {
            for (int j = 0; j <= 100 - i; j++) {
                for (int k = 0; k <= 100 - i - j; k++) {
                    int l = 100 - i - j - k;
                    int score = get_score(i, j, k, l);
                    scoreMax = Math.max(score, scoreMax);
                    if (score == scoreMax && scoreMax > 0) System.out.printf("%3d Sugar, %3d Sprinkles, %3d Candy, %3d Chocolate: Score = %d\n", i,j,k,l, score);
                }

            }
        }
        return scoreMax;
    }

    private int get_score(int i0, int i1, int i2, int i3) {
        int cap, dur, flav, tex;
        cap = dur = flav = tex = 0;
        int[] prop = new int[] {i0, i1, i2, i3};
        for (int i = 0; i < 4; i++) {
            cap += ingredientList.get(i).cap * prop[i];
            dur += ingredientList.get(i).dur * prop[i];
            flav += ingredientList.get(i).flav * prop[i];
            tex += ingredientList.get(i).tex * prop[i];
        }
        return Math.max(0, cap) * Math.max(0, dur) * Math.max(0, flav) * Math.max(0, tex);
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private record Ingredient(String name, int cap, int dur, int flav, int tex, int cal) {
    }
}
