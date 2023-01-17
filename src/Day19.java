import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 implements AOCProblem {

    private final List<String> replVal = new ArrayList<>();
    private final List<String> replWith = new ArrayList<>();
    private final String molecule;

    public Day19(String s) {
        String molecule = "";
        for (String line : InputReader.multipleLines(s)) {
            if (line.equals("")) continue;
            if (!line.contains("=>")) {
                molecule = line;
                break;
            }
            String[] values = line.split(" => ");
            replVal.add(values[0]);
            replWith.add(values[1]);
        }
        this.molecule = molecule;
    }

    @Override
    public int solvePart1() {
        Set<String> distinct = new HashSet<>();
        for (int i = 0; i < replVal.size(); i++) {
            String repl = replWith.get(i);
            String mol = replVal.get(i);
            int start = 0;
            int idx;
            while (true) {
                idx = this.molecule.indexOf(mol, start);
                if (idx == -1) break;
                distinct.add(this.molecule.substring(0, idx) + repl + this.molecule.substring(idx + mol.length()));
                start = idx + mol.length();
            }
        }
        return distinct.size();
    }

    private int replace(String str, int n) {
        if (str.equals("e")) return n;
        int result = -1;
        for (int i = 0; i < replVal.size(); i++) {
            int start = 0, idx;
            String pattern = replWith.get(i);
            String repl = replVal.get(i);
            while (true) {
                idx = str.indexOf(pattern, start);
                if (idx == -1) break;
                String replaced = str.substring(0, idx) + repl + str.substring(idx + pattern.length());
                start = idx + 1;
                result = replace(replaced, n + 1);
                if (result != -1) return result;
            }
        }
        return result;
    }

    @Override
    public int solvePart2() {
        return replace(molecule, 0);
    }
}
