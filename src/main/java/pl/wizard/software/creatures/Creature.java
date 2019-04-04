package pl.wizard.software.creatures;

class Creature {

    private final String name;
    private final int maxHp;
    private final boolean isShooting;
    private int currentHp;
    private final int attack;
    private final int defence;
    private boolean counterAttacked;

    Creature(String aName, int aMaxHp, int aAttack, int aDefence) {
        this(aName, aMaxHp,aAttack,aDefence, false);
    }

    public Creature(String aShooting, int aI, int aI1, int aI2, boolean aB) {
        name = aShooting;
        maxHp = aI;
        currentHp = maxHp;
        attack = aI1;
        defence = aI2;
        isShooting = aB;

    }

    void attack(Creature aDefender) {
        int damageToDeal = calculateDamageToDeal(this, aDefender);
        aDefender.currentHp -= damageToDeal;
        counterAttack(aDefender);
    }

    private void counterAttack(Creature aDefender) {
        if(!counterAttacked && !isShooting){
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

    int getMaxHp() {
        return maxHp;
    }
}
