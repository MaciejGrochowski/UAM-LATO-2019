package pl.wizard.software.creatures;

class Creature {

    private final String name;
    private final int maxHp;
    private int currentHp;
    private final int attack;
    private final int defence;
    private boolean counterAttacked;

    Creature(String aName, int aMaxHp, int aAttack, int aDefence) {
        name = aName;
        maxHp = aMaxHp;
        currentHp = maxHp;
        attack = aAttack;
        defence = aDefence;
    }

    void attack(Creature aDefender) {
        int damageToDeal = calculateDamageToDeal(this, aDefender);
        aDefender.currentHp -= damageToDeal;
        counterAttack(aDefender);
    }

    private void counterAttack(Creature aDefender) {
        if(!counterAttacked){
            int damageToDeal = aDefender.calculateDamageToDeal(aDefender, this);
            this.currentHp -= damageToDeal;
            counterAttacked = true;
        }
    }

    private int calculateDamageToDeal(Creature aAtacker, Creature aDefender) {
        int damageToDeal = aAtacker.attack - aDefender.defence;
        if (damageToDeal > 0) {
            return damageToDeal;
        } else {
            return 1;
        }
    }

    int getCurrentHp() {
        return currentHp;
    }
}
