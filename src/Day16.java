import aocutil.AOCProblem;

public class Day16 implements AOCProblem {

    private static final Sue REFERENCE = new Sue(3, 7, 2, 3, 0, 0, 5, 3, 2, 1);
    private final Sue[] aunts = new Sue[500];

    public Day16(String s) {
        Sue test = new Sue(null, null, null, null, 3, null, 0, null, 9, null);
        aunts[0] = test;
    }

    @Override
    public int solvePart1() {
        return REFERENCE.equals(aunts[0]) ? 1 : 0;
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
