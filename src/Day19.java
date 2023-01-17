import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 implements AOCProblem {


    private List<String> replacements;
    private String molecule;

    public Day19(String s) {
        replacements = InputReader.multipleLines(s);
        molecule = replacements.get(replacements.size() - 1);
        replacements.remove(molecule);
        replacements.remove("");
    }

    @Override
    public int solvePart1() {
        Set<String> distinct = new HashSet<>();
        for (String line : replacements) {
            int start = 0;
            int idx;
            String[] temp = line.split(" => ");
            String mol = temp[0];
            String repl = temp[1];
            while (true) {
                idx = this.molecule.indexOf(mol, start);
                if (idx == -1) break;
                distinct.add(this.molecule.substring(0, idx) + repl + this.molecule.substring(idx + mol.length()));
                start = idx + 1;
            }
        }
        return distinct.size();
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
