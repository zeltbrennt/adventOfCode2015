import aocutil.AOCProblem;

enum spellName {
    MAGIC_MISSILE, DRAIN, SHIELD, POISON, RECHARGE
}

record Spell(spellName name, int MPCost) {
}

public class Day22 implements AOCProblem {
    static final int PLAYER_HP = 50;
    //static final int PLAYER_HP = 10;
    static final int PLAYER_MP = 500;
    //static final int PLAYER_MP = 250;
    final Spell[] spells = new Spell[]{
            new Spell(spellName.POISON, 173),
            new Spell(spellName.MAGIC_MISSILE, 53),
            new Spell(spellName.DRAIN, 73),
            new Spell(spellName.SHIELD, 113),
            new Spell(spellName.RECHARGE, 229)};
    int minMana = Integer.MAX_VALUE;
    int bossHP;
    int bossDMG;
    int playerHP;
    int playerMP;
    boolean difficult;

    public Day22(int bossHP, int bossDMG) {
        this.bossHP = bossHP;
        this.bossDMG = bossDMG;
        this.playerHP = PLAYER_HP;
        this.playerMP = PLAYER_MP;
    }

    int takeTurn(int turn, Spell spell, int bossHP, int playerHP, int playerMP, int manaSpend, int poisonEffect, int rechargeEffect, int shieldEffect, String memo) {
        //player effect
        if (difficult) playerHP--;
        if (playerHP <= 0) return Integer.MAX_VALUE;
        memo += "-- Player turn " + turn + " --\n- Player has " + playerHP + " HP, " + (shieldEffect > 0 ? 7 : 0) + " armor, " + playerMP + " MP\n- Boss has " + bossHP + " HP\n";
        if (shieldEffect > 0) {
            shieldEffect--;
            memo += "Shields timer is now " + shieldEffect + "\n";
        }
        if (poisonEffect > 0) {
            bossHP -= 3;
            poisonEffect--;
            memo += "Poison deals 3 damage, timer is now " + poisonEffect + "\n";
        }
        if (rechargeEffect > 0) {
            playerMP += 101;
            rechargeEffect--;
            memo += "Recharge provides 101 mana, timer is now " + rechargeEffect + "\n";
        }
        memo += "Player casts " + spell.name() + (spell.name() == spellName.MAGIC_MISSILE ? " deals 4 damage\n\n" : "\n\n");
        //cast spell
        playerMP -= spell.MPCost();
        manaSpend += spell.MPCost();
        switch (spell.name()) {
            case MAGIC_MISSILE -> bossHP -= 4;
            case DRAIN -> {
                playerHP += 2;
                bossHP -= 2;
            }
            case SHIELD -> shieldEffect = 6;
            case POISON -> poisonEffect = 6;
            case RECHARGE -> rechargeEffect = 5;
        }
        if (bossHP <= 0) {
       //     System.out.println(memo + "boss dead \n===============================> " + manaSpend + " <===============================");
            return manaSpend;
        }
        //boss effect and damage
        turn++;
        memo += "-- Boss turn " + turn + " --\n- Player has " + playerHP + " HP, " + (shieldEffect > 0 ? 7 : 0) + " armor, " + playerMP + " MP\n- Boss has " + bossHP + " HP\n";
        if (shieldEffect > 0) {
            shieldEffect--;
            memo += "Shields timer is now " + shieldEffect + "\n";
        }
        if (poisonEffect > 0) {
            bossHP -= 3;
            poisonEffect--;
            memo += "Poison deals 3 damage, timer is now " + poisonEffect + "\n";
        }
        if (rechargeEffect > 0) {
            playerMP += 101;
            rechargeEffect--;
            memo += "Recharge provides 101 mana, timer is now " + rechargeEffect + "\n";
        }
        if (bossHP <= 0) {
        //    System.out.println(memo + "boss dead \n===============================> " + manaSpend + " <===============================");
            return manaSpend;
        }
        memo += "Boss deals 8 " + (shieldEffect > 0 ? "- 7 = 1 " : "") + "damage\n\n";
        playerHP -= shieldEffect > 0 ? Math.min(1, bossDMG - 7) : bossDMG;
        if (playerHP <= 0) return Integer.MAX_VALUE;
        for (Spell nextSpell : spells) {
            // spells can be cast on the turn they'll run out...
            if (nextSpell.name() == spellName.RECHARGE && rechargeEffect > 1) continue;
            if (nextSpell.name() == spellName.POISON && poisonEffect > 1) continue;
            if (nextSpell.name() == spellName.SHIELD && shieldEffect > 1) continue;
            if (nextSpell.MPCost() > playerMP) continue;
            if (nextSpell.MPCost() + manaSpend >= minMana) continue;
            minMana = Math.min(minMana, takeTurn(turn + 1, nextSpell, bossHP, playerHP, playerMP, manaSpend, poisonEffect, rechargeEffect, shieldEffect, memo));
        }
        return minMana;
    }

    @Override
    public int solvePart1() {
        for (Spell spell : spells) {
            minMana = Math.min(minMana, takeTurn(0, spell, bossHP, playerHP, playerMP, 0, 0, 0, 0, ""));
        }
        return minMana;
    }

    @Override
    public int solvePart2() {
        minMana = Integer.MAX_VALUE;
        difficult = true;
        return solvePart1();
    }
}
