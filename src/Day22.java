import aocutil.AOCProblem;

enum spellName {
    MAGIC_MISSLE, DRAIN, SHIELD, POISON, RECHARGE
}

record Spell(spellName name, int MPCost) {
}

public class Day22 implements AOCProblem {
    static final int PLAYER_HP = 100;
    static final int PLAYER_MP = 500;

    int bossHP;
    int bossDMG;
    int playerHP;
    int playerMP;
    int shieldEffect;
    int rechargeEffect;
    int poisonEffect;
    private Spell[] spells = new Spell[]{
            new Spell(spellName.MAGIC_MISSLE, 53),
            new Spell(spellName.DRAIN, 73),
            new Spell(spellName.SHIELD, 113),
            new Spell(spellName.POISON, 173),
            new Spell(spellName.RECHARGE, 229)};

    public Day22(int bossHP, int bossDMG) {
        this.bossHP = bossHP;
        this.bossDMG = bossDMG;
        this.playerHP = PLAYER_HP;
        this.playerMP = PLAYER_MP;
        this.poisonEffect = 0;
        this.shieldEffect = 0;
        this.rechargeEffect = 0;
    }
    void castSpell(Spell spell) {
        playerMP -= spell.MPCost();
        switch (spell.name()) {
            case MAGIC_MISSLE -> bossHP -= 4;
            case DRAIN -> {
                playerHP += 2;
                bossHP -= 2;
            }
            case SHIELD -> shieldEffect = 6;
            case POISON -> poisonEffect = 6;
            case RECHARGE -> rechargeEffect = 5;
        }
    }

    void playerTurn() {
        shieldEffect = Math.max(0, shieldEffect - 1);
        if (rechargeEffect > 0) {
            playerMP += 101;
            rechargeEffect--;
        }
        // cast spell ??
    }

    void bossTurn() {
        if (poisonEffect > 0) {
            bossHP -= 3;
        }
        playerHP -= shieldEffect > 0 ? Math.min(1, bossDMG - 7) : bossDMG;
    }


    @Override
    public int solvePart1() {
        return 0;
    }

    @Override
    public int solvePart2() {
        return 0;
    }
}
