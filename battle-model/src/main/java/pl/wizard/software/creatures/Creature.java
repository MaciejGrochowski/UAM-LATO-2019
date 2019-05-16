package pl.wizard.software.creatures;

import com.google.common.collect.Range;
import pl.wizard.software.battlefield.BattleEngine;

public class Creature {

    private final String name;
    private final int maxHp;
    private int currentHp;
    private final Range<Integer> attack;
    private final int defence;
    private boolean counterAttacked;
    private int startAmount;
    private int currentAmount;
    private final CalculateDamageStrategyIf dealDamageStrategy;
    private int speed;

    Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence) {
        this(aName, aMaxHp, aAttack, aDefence, new LowerDamageStragegy());
    }

    Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, CalculateDamageStrategyIf aDamageStrategy) {
        name = aName;
        maxHp = aMaxHp;
        currentHp = maxHp;
        attack = aAttack;
        defence = aDefence;
        startAmount = 1;
        currentAmount = startAmount;
        dealDamageStrategy = aDamageStrategy;
        speed = 1;
    }

    public Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, int aSpeed) {
        this(aName, aMaxHp, aAttack, aDefence, new LowerDamageStragegy());
        speed = aSpeed;
    }

    public void attack(Creature aDefender) {
        int damageToDeal = dealDamageStrategy.calculateDamageToDeal(this, aDefender);
        aDefender.dealDamageToMe(damageToDeal);
        aDefender.counterAttack(this);
    }

    private void dealDamageToMe(int aDamageToDeal) {
        int bufor = aDamageToDeal;
        while (bufor >= currentHp) {
            currentAmount -= 1;
            bufor -= maxHp;
        }
        currentHp -= bufor;
    }

    private void counterAttack(Creature aAttacker) {
        if (!counterAttacked) {
            int damageToDeal = dealDamageStrategy.calculateDamageToDeal(this, aAttacker);
            aAttacker.dealDamageToMe(damageToDeal);
            counterAttacked = true;
        }
    }

    public void subscribeMe(BattleEngine aEngine) {
        aEngine.addPropertyChangeListener(BattleEngine.END_OF_TURN, (e -> {
            counterAttacked = false;
        }));
    }

//    protected int calculateDamageToDeal(Creature aAtacker, Creature aDefender) {
//        int damageToDeal = aAtacker.attack.lowerEndpoint() - aDefender.defence;
//        if (damageToDeal > 0) {
//            return aAtacker.getCurrentAmount() * damageToDeal;
//        } else {
//            return aAtacker.getCurrentAmount() * 1;
//        }
//    }

    public int getCurrentHp() {
        return currentHp;
    }

    public int getMaxHp() {
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

    public int getCurrentAmount() {
        return currentAmount;
    }

    public boolean isAlive() {
        return currentAmount > 0;
    }

    Range<Integer> getAttack() {
        return attack;
    }

    int getDefence() {
        return defence;
    }

    CalculateDamageStrategyIf getDealDamageStrategy() {
        return dealDamageStrategy;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return name;
    }
}
