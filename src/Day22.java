import aocutil.AOCProblem;

enum spellName {
    MAGIC_MISSILE, DRAIN, SHIELD, POISON, RECHARGE
}

record Spell(spellName name, int MPCost) {
}

public class Day22 implements AOCProblem {
    static final int PLAYER_HP = 100;
    static final int PLAYER_MP = 500;
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
    int shieldEffect;
    int rechargeEffect;
    int poisonEffect;

    public Day22(int bossHP, int bossDMG) {
        this.bossHP = bossHP;
        this.bossDMG = bossDMG;
        this.playerHP = PLAYER_HP;
        this.playerMP = PLAYER_MP;
        this.poisonEffect = 0;
        this.shieldEffect = 0;
        this.rechargeEffect = 0;
    }

    int takeTurn(int turn, Spell spell, int bossHP, int playerHP, int playerMP, int manaSpend, int poisonEffect, int rechargeEffect, int shieldEffect) {
        if (playerMP < 0 || playerHP <= 0 || manaSpend > minMana) return minMana;
        if (spell.name() == spellName.RECHARGE && rechargeEffect > 0) return minMana;
        if (spell.name() == spellName.POISON && poisonEffect > 0) return minMana;
        if (spell.name() == spellName.SHIELD && shieldEffect > 0) return minMana;
        //player effect
        shieldEffect = Math.max(0, shieldEffect - 1);
        if (poisonEffect > 0) {
            bossHP -= 3;
            poisonEffect--;
        }
        if (rechargeEffect > 0) {
            playerMP += 101;
            rechargeEffect--;
        }
        if (bossHP <= 0) return manaSpend;
        if (turn % 2 == 0) {
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
        } else {
            //boss effect and damage
            playerHP -= shieldEffect > 0 ? Math.min(1, bossDMG - 7) : bossDMG;
        }
        for (Spell s : spells) {
            minMana = Math.min(minMana, takeTurn(turn + 1, s, bossHP, playerHP, playerMP, manaSpend, poisonEffect, rechargeEffect, shieldEffect));
        }
        return minMana;
    }

    @Override
    public int solvePart1() {
        for (Spell spell : spells) {
            minMana = takeTurn(0, spell, bossHP, playerHP, playerMP, 0, 0, 0, 0);
        }
        return minMana;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
