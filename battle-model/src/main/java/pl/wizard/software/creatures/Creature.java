package pl.wizard.software.creatures;

import com.google.common.collect.Range;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.player.Hero;

import java.util.Optional;


public class Creature {

    private final String name;
    private final int maxHp;
    private final int defaultSpeed;
    private int currentHp;
    private final Range<Integer> attack;
    private final int defence;
    private boolean counterAttacked;
    private int startAmount;
    private int currentAmount;
    private CalculateDamageStrategyIf dealDamageStrategy;
    private int speed;
    private Optional<Hero> hero;


    public Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, CalculateDamageStrategyIf aDamageStrategy, int aSpeed, Hero aHero, int aStartAmount){
        name = aName;
        maxHp = aMaxHp;
        currentHp = maxHp;
        attack = aAttack;
        defence = aDefence;
        startAmount = aStartAmount;
        currentAmount = startAmount;
        dealDamageStrategy = aDamageStrategy;
        defaultSpeed = aSpeed;
        speed = defaultSpeed;
        setHero(aHero);

    }

    public Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, int aSpeed, Hero aHero, int aStartAmount){
        this(aName, aMaxHp, aAttack, aDefence, new LowerDamageStragegy(), aSpeed, aHero, aStartAmount);
    }

    Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence) {
        this(aName, aMaxHp, aAttack, aDefence, new LowerDamageStragegy(), 1, null, 1);
    }

    Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, CalculateDamageStrategyIf aDamageStrategy) {
        this(aName, aMaxHp, aAttack, aDefence, aDamageStrategy, 1, null, 1);
    }

    public Creature(String aName, int aMaxHp, Range<Integer> aAttack, int aDefence, int aSpeed) {
        this(aName, aMaxHp, aAttack, aDefence, new LowerDamageStragegy(), aSpeed, null, 1);


    }


    public int attack(Creature aDefender) {

        if (this.getHero().isPresent() && aDefender.getHero().isPresent() && this.getHero().equals(aDefender.getHero())){
            return -1;
        }

        int damageToDeal = dealDamageStrategy.calculateDamageToDeal(this, aDefender);
        aDefender.dealDamageToMe(damageToDeal);
        aDefender.counterAttack(this);
        return 0;
    }

    public void dealDamageToMe(int aDamageToDeal) {


        int bufor = aDamageToDeal;
        while (bufor >= currentHp) {
            currentAmount -= 1;
            bufor -= maxHp;
        }
        currentHp -= bufor;
        currentAmount = Math.max(0, currentAmount);
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
            speed = defaultSpeed;
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

    public Optional<Hero> getHero() {
        return hero;
    }

    public void setHero(Hero aHero) {
        hero = Optional.ofNullable(aHero);
        hero.ifPresent(e -> {
            e.addCreature(this);
            e.getSpec().addPropertyChangeListener(e.getSpec().SPEED, (f -> {
                speed += 1;

            }));
            e.getSpec().addPropertyChangeListener(e.getSpec().MORE_COUNTER_ATTACKS, (f -> {
                counterAttacked = false;
            }));
            e.getSpec().addPropertyChangeListener(e.getSpec().BLESS, (f -> {dealDamageStrategy = new UpperDamageStrategy();}));
        });
    }

}
