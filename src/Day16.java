import aocutil.AOCProblem;
import aocutil.InputReader;

import java.util.ArrayList;
import java.util.List;

public class Day16 implements AOCProblem {

    private static final Sue REFERENCE = new Sue(3, 7, 2, 3, 0, 0, 5, 3, 2, 1);
    private final List<Sue> aunts = new ArrayList<>();
    private final String[] properties = new String[]{"children", "cats", "samoyeds",
            "pomeranians", "akitas", "vizslas", "goldfish", "trees", "cars", "perfumes"};

    public Day16(String s) {
        for (String line : InputReader.multipleLines(s)) {
            Integer[] prop = new Integer[10];
            for (int i = 0; i < properties.length; i++) {
                int idx = line.indexOf(properties[i]);
                if (idx != -1) {
                    int idxStop = line.indexOf(',', idx);
                    idxStop = idxStop == -1 ? line.length() : idxStop;
                    prop[i] = Integer.parseInt(line.substring(idx, idxStop).replaceAll("\\D", "").trim());
                }
            }
            aunts.add(new Sue(prop[0], prop[1], prop[2], prop[3], prop[4], prop[5], prop[6], prop[7], prop[8], prop[9]));
        }
    }

    @Override
    public int solvePart1() {
        int x = -1;
        for (int i = 0; i < aunts.size(); i++) {
            Sue aunt = aunts.get(i);
            if (REFERENCE.equals(aunt)) {
                System.out.println(aunt);
                x = i;
            }
        }
        return x+1;
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private record Sue(Integer children, Integer cats, Integer samoyeds, Integer pomeranians, Integer akitas,
                       Integer vizslas, Integer goldfish, Integer trees, Integer cars, Integer perfumes) {

        public boolean equals(Sue o) {
            return (o.children == null || children.equals(o.children)) &&
                    (o.cats == null || cats.equals(o.cats)) &&
                    (o.samoyeds == null || samoyeds.equals(o.samoyeds)) &&
                    (o.pomeranians == null || pomeranians.equals(o.pomeranians)) &&
                    (o.akitas == null || akitas.equals(o.akitas)) &&
                    (o.vizslas == null || vizslas.equals(o.vizslas)) &&
                    (o.goldfish == null || goldfish.equals(o.goldfish)) &&
                    (o.trees == null || trees.equals(o.trees)) &&
                    (o.cars == null || cars.equals(o.cars)) &&
                    (o.perfumes == null || perfumes.equals(o.perfumes));
        }
    }
}
