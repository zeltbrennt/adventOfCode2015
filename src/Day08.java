import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.List;

public class Day08 implements AOCProblem {

    private final List<String> puzzle;


    public Day08(String input) {
        puzzle = InputReader.multipleLines(input);
    }
    @Override
    public int solvePart1() {
        int charsInCode = 0;
        int charsInString = 0;
        for (String s : puzzle) {
            charsInCode += s.length();
            String f = s.substring(1, s.length()-1)
                    .replaceAll("\\\\\"", "A")
                    .replaceAll("\\\\x[a-z0-9]{2}", "B")
                    .replaceAll("\\\\{2}", "C");
            System.out.println(f);
            System.out.printf("%d, %d\n", s.length(), f.length());
            charsInString += f.length();
        }
        return charsInCode - charsInString;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
