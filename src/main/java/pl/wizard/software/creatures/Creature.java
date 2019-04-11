package pl.wizard.software.creatures;

import com.google.common.collect.Range;

class Creature {

    private final String name;
    private final int maxHp;
    private int currentHp;
    private final Range<Integer> attack;
    private final int defence;
    private boolean counterAttacked;
    private int startAmount;
    private int currentAmount;
    private Geberish geberish;

    Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, Geberish aGeberish) {
        name = aName;
        maxHp = aMaxHp;
        currentHp = maxHp;
        attack = aAttack;
        defence = aDefence;
        startAmount = 1;
        currentAmount = startAmount;
        geberish = aGeberish;
    }


    void attack(Creature aDefender) {
        int damageToDeal = geberish.calculateDamageToDeal(this, aDefender);
        aDefender.dealDamageToMe(damageToDeal);
        aDefender.counterAttack(this);
    }

    private void dealDamageToMe(int aDamageToDeal) {
        int bufor = aDamageToDeal;
                while(bufor >= currentHp){
                    currentAmount -= 1;
                    bufor -= maxHp;
                }
        currentHp -= bufor;
    }

    private void counterAttack(Creature aAttacker) {
        if (!counterAttacked) {
            int damageToDeal = geberish.calculateDamageToDeal(this, aAttacker);
            aAttacker.dealDamageToMe(damageToDeal);
            counterAttacked = true;
        }
    }

//    protected int calculateDamageToDeal(Creature aAtacker, Creature aDefender) {
//        int damageToDeal = aAtacker.attack.lowerEndpoint() - aDefender.defence;
//        if (damageToDeal > 0) {
//            return aAtacker.getCurrentAmount() * damageToDeal;
//        } else {
//            return aAtacker.getCurrentAmount() * 1;
//        }
//    }

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

    Range<Integer> getAttack() {
        return attack;
    }

    int getDefence() {
        return defence;
    }

    protected Geberish getGeberish() {
        return geberish;
    }
}
