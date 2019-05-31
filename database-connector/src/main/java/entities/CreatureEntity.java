package entities;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CreatureEntity {

    public enum DamageStrategy {
        LOWEST_DAMAGE, HIGHEST_DAMAGE
    }

    @Id
    @GeneratedValue
    private long id;
    @ManyToMany(mappedBy = "creatures")
    private Set<FractionEntity> fractions;
    private String name;
    private int maxHp;
    private int minAttack;
    private int maxAttack;
    private int defence;
    private DamageStrategy dealDamageStrategy;
    private int speed;
    private int level;
    private int goldCost;

    @Version
    private int version;

    public CreatureEntity(){

    }

    public CreatureEntity(String aName, int aLevel){
        name = aName;
        level = aLevel;
    }

    public long getId() {
        return id;
    }

    public Set<FractionEntity> getFractions() {
        return fractions;
    }

    public void setFractions(Set<FractionEntity> aFractions) {
        fractions = aFractions;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        name = aName;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int aMaxHp) {
        maxHp = aMaxHp;
    }

    public int getMinAttack() {
        return minAttack;
    }

    public void setMinAttack(int aMinAttack) {
        minAttack = aMinAttack;
    }

    public int getMaxAttack() {
        return maxAttack;
    }

    public void setMaxAttack(int aMaxAttack) {
        maxAttack = aMaxAttack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int aDefence) {
        defence = aDefence;
    }

    public DamageStrategy getDealDamageStrategy() {
        return dealDamageStrategy;
    }

    public void setDealDamageStrategy(DamageStrategy aDealDamageStrategy) {
        dealDamageStrategy = aDealDamageStrategy;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int aSpeed) {
        speed = aSpeed;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int aLevel) {
        level = aLevel;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int aGoldCost) {
        goldCost = aGoldCost;
    }

}
