import aocutil.AOCProblem;

public class Day21 implements AOCProblem {
    private final int bossHP;
    private final int bossDMG;
    private final int bossARM;
    private final int playerHP;
    private int minGoldSpend = Integer.MAX_VALUE;

    private final Item[] items = new Item[]{
            new Item("Dagger", ItemType.WEAPON, 8, 4, 0),
            new Item("Shortsword", ItemType.WEAPON, 10, 5, 0),
            new Item("Warhammer", ItemType.WEAPON, 25, 6, 0),
            new Item("Longsword", ItemType.WEAPON, 40, 7, 0),
            new Item("Greataxe", ItemType.WEAPON, 74, 8, 0),
            new Item("Leather", ItemType.ARMOR, 13, 0, 1),
            new Item("Chainmail", ItemType.ARMOR, 31, 0, 2),
            new Item("Splintmail", ItemType.ARMOR, 53, 0, 3),
            new Item("Bandedmail", ItemType.ARMOR, 75, 0, 4),
            new Item("Platemail", ItemType.ARMOR, 102, 0, 5),
            new Item("Damage +1", ItemType.RING, 25, 1, 0),
            new Item("Damage +2", ItemType.RING, 50, 2, 0),
            new Item("Damage +3", ItemType.RING, 100, 3, 0),
            new Item("Defense +1", ItemType.RING, 20, 0, 1),
            new Item("Defense +2", ItemType.RING, 40, 0, 2),
            new Item("Defense +3", ItemType.RING, 80, 0, 3)
    };


    public Day21(int hp, int dmg, int arm) {
        this.playerHP = 100;
        this.bossHP = hp;
        this.bossDMG = dmg;
        this.bossARM = arm;
    }

    private boolean playerWins(int playerDMG, int playerARM) {
        return bossHP / Math.max(playerDMG - bossARM, 1) <= playerHP / Math.max(bossDMG - playerARM, 1);
    }

    @Override
    public int solvePart1() {
        goShopping(0, 0, 0, null, null, null, null);
        return minGoldSpend;
    }

    private void goShopping(int playerDMG, int playerARM, int goldSpend, Item weapon, Item armor, Item ring1, Item ring2) {
        if (playerWins(playerDMG, playerARM)) {
            minGoldSpend = Math.min(goldSpend,  minGoldSpend);
            if (goldSpend == minGoldSpend) {
                // Debu
                System.out.printf("dmg = %d, arm = %d, gld = %d\n", playerDMG, playerARM, minGoldSpend);
                System.out.printf("WPN: %s, ARM: %s, RNG: %s and %s\n", weapon.name, armor == null ? "empty" : armor.name, ring1 == null ? "empty" : ring1.name, ring2 == null ? "empty" : ring2.name);
            }
            //Debug
            return;
        }
        if (goldSpend > minGoldSpend) {
            //System.out.println("aborting...");
            return;
        }
        for (Item item : items) {
            boolean canBuyWeapon = item.type == ItemType.WEAPON && weapon == null;
            boolean canBuyArmor = item.type == ItemType.ARMOR && armor == null;
            boolean canBuyRings = item.type == ItemType.RING && (ring1 == null || (ring2 == null && !ring1.name.equals(item.name)));
            if (!(canBuyWeapon || canBuyArmor || canBuyRings)) continue;
            goShopping(playerDMG + item.dmg, playerARM + item.arm, goldSpend + item.gld,
                    item.type == ItemType.WEAPON ? item : weapon,
                    item.type == ItemType.ARMOR ? item : armor,
                    item.type == ItemType.RING && ring1 == null ? item : ring1,
                    item.type == ItemType.RING && ring1 != null ? item : ring2);
        }
    }

    @Override
    public int solvePart2() {
        return 0;
    }

    private enum ItemType { WEAPON, ARMOR, RING }

    private record Item(String name, ItemType type, int gld, int dmg, int arm) {
    }
}
