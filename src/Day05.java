import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day05 implements AOCProblem {

    private final List<String> puzzle;
    public Day05(String inputFile) {
        this.puzzle = InputReader.multipleLines(inputFile);
        //this.puzzle = List.of("ugknbfddgicrmopn", "aaa", "jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb");
    }

    private boolean naughtyOrNice(String str) {
        Pattern pVowels = Pattern.compile("[aeiou]");
        Pattern pDouble = Pattern.compile("(\\w)\\1+");
        Pattern pForbidden = Pattern.compile("ab|cd|pq|xy");
        Matcher mVowels = pVowels.matcher(str);
        Matcher mDouble = pDouble.matcher(str);
        Matcher mForbidden = pForbidden.matcher(str);
        return mVowels.results().count() >= 3 && mDouble.find() && !mForbidden.find();
    }
    @Override
    public int solvePart1() {
        return puzzle.stream().mapToInt(str -> naughtyOrNice(str) ? 1 : 0).sum();
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
