import aocutil.AOCProblem;

public class Day10 implements AOCProblem {

    private final String puzzle;
    private String sequence;

    public Day10(String s) {
        puzzle = s;
        sequence = s;
    }

    private void lookAndSay() {
        char c = sequence.charAt(0);
        int counter = 0;
        StringBuilder newSequence = new StringBuilder();
        for (int i = 0; i < sequence.length(); i++) {
            if (sequence.charAt(i) == c) counter++;
            else {
                newSequence.append(counter);
                newSequence.append(c);
                counter = 1;
                c = sequence.charAt(i);
            }
        }
        newSequence.append(counter);
        newSequence.append(c);
        sequence = newSequence.toString();
    }

    @Override
    public int solvePart1() {
        for (int i = 0; i < 40; i++) {
            lookAndSay();
            //System.out.println(sequence);
        }
        return sequence.length();
    }

    @Override
    public int solvePart2() {
        if (sequence.length() <= 10) solvePart1();
        for (int i = 0; i < 10; i++) {
            lookAndSay();
        }
        return sequence.length();
    }
}
