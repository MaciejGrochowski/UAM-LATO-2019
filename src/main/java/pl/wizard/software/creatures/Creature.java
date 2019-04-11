package pl.wizard.software.creatures;

class Creature {

    private final String name;
    private final int maxHp;
    private int currentHp;
    private final int attack;
    private final int defence;
    private boolean counterAttacked;
    private int startAmount;
    private int currentAmount;

    Creature(String aName, int aMaxHp, int aAttack, int aDefence) {
        name = aName;
        maxHp = aMaxHp;
        currentHp = maxHp;
        attack = aAttack;
        defence = aDefence;
        startAmount = 1;
        currentAmount = startAmount;
    }


    void attack(Creature aDefender) {
        int damageToDeal = calculateDamageToDeal(this, aDefender);
        aDefender.dealDamageToMe(damageToDeal);
        aDefender.counterAttack(this);
    }

    private void dealDamageToMe(int aDamageToDeal) {
        currentHp -= aDamageToDeal;
    }

    private void counterAttack(Creature aAttacker) {
        if (!counterAttacked) {
            int damageToDeal = calculateDamageToDeal(this, aAttacker);
            aAttacker.dealDamageToMe(damageToDeal);
            counterAttacked = true;
        }
    }

    protected int calculateDamageToDeal(Creature aAtacker, Creature aDefender) {
        int damageToDeal = aAtacker.attack - aDefender.defence;
        if (damageToDeal > 0) {
            return aAtacker.getCurrentAmount() * damageToDeal;
        } else {
            return aAtacker.getCurrentAmount() * 1;
        }
    }

    int getCurrentHp() {
        return currentHp;
    }

    int getMaxHp() {
        return maxHp;
    }

    protected void setCurrentHp(int aCurrentHp) {
        currentHp = aCurrentHp;
    }

    void addCreaturesToStack(int aAmount) {
        startAmount += aAmount;
        currentAmount = startAmount;
    }

    int getStartAmount() {
        return startAmount;
    }

    int getCurrentAmount() {
        return currentAmount;
    }

    boolean isAlive() {
        return currentAmount > 0;
    }
}
