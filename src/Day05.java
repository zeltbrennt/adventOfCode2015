import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 implements AOCProblem {

    private final List<String> puzzle;
    public Day05(String inputFile) {
        this.puzzle = InputReader.multipleLines(inputFile);
    }

    private boolean threeRules(String str) {
        Pattern pVowels = Pattern.compile("[aeiou]");
        Pattern pDouble = Pattern.compile("(\\w)\\1+");
        Pattern pForbidden = Pattern.compile("ab|cd|pq|xy");
        Matcher mVowels = pVowels.matcher(str);
        Matcher mDouble = pDouble.matcher(str);
        Matcher mForbidden = pForbidden.matcher(str);
        return mVowels.results().count() >= 3 && mDouble.find() && !mForbidden.find();
    }

    private boolean twoRules(String str) {
        boolean r1 = false, r2 = false;
        for (int i = 0, j = 2; j <= str.length(); i++, j++) {
            Pattern pRepeat = Pattern.compile(str.substring(i, j));
            Pattern pBetween = Pattern.compile(str.substring(i, j-1) + "." + str.substring(i, j-1));
            Matcher mRepeat = pRepeat.matcher(str.substring(j));
            Matcher mBetween = pBetween.matcher(str);
            if (mRepeat.find()) r1 = true;
            if (mBetween.find()) r2 = true;
            if (r1 && r2) break;
        }
        return r1 && r2;
    }
    @Override
    public int solvePart1() {
        return puzzle.stream().mapToInt(str -> threeRules(str) ? 1 : 0).sum();
    }

    @Override
    public int solvePart2() {
        return puzzle.stream().mapToInt(str -> twoRules(str) ? 1 : 0).sum();
    }
}
