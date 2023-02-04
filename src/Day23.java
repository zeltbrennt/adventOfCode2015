import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 implements AOCProblem {
    private final List<String> instructions;
    private final Map<String, Integer> register = new HashMap<>(Map.of("a", 0, "b", 0));

    public Day23(String s) {
        this.instructions = InputReader.multipleLines(s);
    }

    @Override
    public int solvePart1() {
/*        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            System.out.println(i + " " + instruction);
        }*/
        int idx = 0;
        while (idx < instructions.size()) {
            String[] tokens = instructions.get(idx).split(",* ");
            System.out.printf("%02d: %-12s", idx, instructions.get(idx));
            switch (tokens[0]) {
                case "hlf" -> register.put(tokens[1], register.get(tokens[1]) / 2);
                case "tpl" -> register.put(tokens[1], register.get(tokens[1]) * 3);
                case "inc" -> register.put(tokens[1], register.get(tokens[1]) + 1);
                case "jmp" -> idx += Integer.parseInt(tokens[1]) - 1;
                case "jio" -> idx += register.get(tokens[1]) == 1 ? Integer.parseInt(tokens[2]) - 1 : 0;
                case "jie" -> idx += register.get(tokens[1]) % 2 == 0 ? Integer.parseInt(tokens[2]) - 1 : 0;
            }
            System.out.printf("a = %d, b = %d\n", register.get("a"), register.get("b"));
            idx++;
        }
        return register.get("b");
    }

    @Override
    public int solvePart2() {
        register.put("a", 1);
        register.put("b", 0);
        return 0;
    }
}
