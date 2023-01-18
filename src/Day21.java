import aocutil.AOCProblem;

public class Day21 implements AOCProblem {
    private final int bossHP;
    private final int bossDMG;
    private final int bossDEF;
    private final int playerHP;
    private final Item[] weapons = new Item[]{
            new Item("Dagger", ItemType.WEAPON, 8, 4, 0),
            new Item("Shortsword", ItemType.WEAPON, 10, 5, 0),
            new Item("Warhammer", ItemType.WEAPON, 25, 6, 0),
            new Item("Longsword", ItemType.WEAPON, 40, 7, 0),
            new Item("Greataxe", ItemType.WEAPON, 74, 8, 0)
    };
    private final Item[] armor = new Item[]{
            new Item("Leather", ItemType.ARMOR, 13, 0, 1),
            new Item("Chainmail", ItemType.ARMOR, 31, 0, 2),
            new Item("Splintmail", ItemType.ARMOR, 53, 0, 3),
            new Item("Bandedmail", ItemType.ARMOR, 75, 0, 4),
            new Item("Platemail", ItemType.ARMOR, 102, 0, 5),
            new Item("empty", ItemType.ARMOR, 0, 0, 0)
    };
    private final Item[] rings = new Item[]{
            new Item("Damage +1", ItemType.RING, 25, 1, 0),
            new Item("Damage +2", ItemType.RING, 50, 2, 0),
            new Item("Damage +3", ItemType.RING, 100, 3, 0),
            new Item("Defense +1", ItemType.RING, 20, 0, 1),
            new Item("Defense +2", ItemType.RING, 40, 0, 2),
            new Item("Defense +3", ItemType.RING, 80, 0, 3),
            new Item("empty", ItemType.RING, 0, 0, 0),
            new Item("empty", ItemType.RING, 0, 0, 0)
    };

    public Day21(int hp, int dmg, int def) {
        this.playerHP = 100;
        this.bossHP = hp;
        this.bossDMG = dmg;
        this.bossDEF = def;
    }

    private boolean playerWins(int playerDMG, int playerDEF) {
        int bossDamage = Math.max(bossDMG - playerDEF, 1);
        int playerDamage = Math.max(playerDMG - bossDEF, 1);
        int boss = bossHP;
        int player = playerHP;
        while (boss > 0 && player > 0) {
            boss -= playerDamage;
            player -= bossDamage;
        }
        return boss <= 0;
    }

    @Override
    public int solvePart1() {
        int minGoldSpend = Integer.MAX_VALUE;
        for (Item weapon : weapons) {
            for (Item armor : armor) {
                for (Item ring : rings) {
                    for (Item ring2 : rings) {
                        if (ring == ring2) continue;
                        int totalDamage = weapon.dmg + ring.dmg + ring2.dmg;
                        int totalDef = armor.def + ring.def + ring2.def;
                        int totalGold = weapon.gld + armor.gld + ring.gld + ring2.gld;
                        if (playerWins(totalDamage, totalDef)) {
                            if (totalGold < minGoldSpend) {
                                minGoldSpend = totalGold;
                                //System.out.printf("dmg = %d, arm = %d, gld = %d\n", totalDamage, totalDef, totalGold);
                                //System.out.printf("WPN: %s, ARM: %s, RNG: %s and %s\n", weapon.name, armor.name, ring.name, ring2.name);
                            }
                        }
                    }
                }
            }
        }
        return minGoldSpend;
    }

    @Override
    public int solvePart2() {
        int maxGoldSpend = 0;
        for (Item weapon : weapons) {
            for (Item armor : armor) {
                for (Item ring : rings) {
                    for (Item ring2 : rings) {
                        if (ring == ring2) continue;
                        int totalDamage = weapon.dmg + ring.dmg + ring2.dmg;
                        int totalDef = armor.def + ring.def + ring2.def;
                        int totalGold = weapon.gld + armor.gld + ring.gld + ring2.gld;
                        if (!playerWins(totalDamage, totalDef)) {
                            if (totalGold > maxGoldSpend) {
                                maxGoldSpend = totalGold;
                                //System.out.printf("dmg = %d, def = %d, gld = %d\n", totalDamage, totalDef, totalGold);
                                //System.out.printf("WPN: %s, ARM: %s, RNG: %s and %s\n", weapon.name, armor.name, ring.name, ring2.name);
                            }
                        }
                    }
                }
            }
        }
        return maxGoldSpend;
    }

    private enum ItemType {WEAPON, ARMOR, RING}

    private record Item(String name, ItemType type, int gld, int dmg, int def) {
    }
}
