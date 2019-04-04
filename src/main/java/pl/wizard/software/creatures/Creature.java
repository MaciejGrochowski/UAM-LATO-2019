package pl.wizard.software.creatures;

class Creature {

    private final String name;
    private final int maxHp;
    private int currentHp;
    private final int attack;
    private final int defence;

    Creature(String aName, int aMaxHp, int aAttack, int aDefence) {
        name = aName;
        maxHp = aMaxHp;
        currentHp = maxHp;
        attack = aAttack;
        defence = aDefence;
    }

    void attack(Creature aDefender) {
        int damageToDeal = this.attack - aDefender.defence;
        if (damageToDeal > 0) {
            aDefender.currentHp -= damageToDeal;
        } else {
            aDefender.currentHp -= 1;
        }

        counterAttack(aDefender);
    }

    private void counterAttack(Creature aDefender) {
        Creature attacker = this;
        int damageToDeal = aDefender.attack - attacker.defence;
        if (damageToDeal > 0) {
            attacker.currentHp -= damageToDeal;
        } else {
            attacker.currentHp -= 1;
        }
    }

    int getCurrentHp() {
        return currentHp;
    }
}
