package pl.wizard.software.player;

import com.google.common.collect.Range;
import pl.wizard.software.creatures.Creature;

import java.util.ArrayList;
import java.util.List;

public class Hero {

    private final List<Creature> creatures;

    private int attack;
    private int defence;
    private int intelligence;
    private int charisma;
    private int mana;
    public double criticalchance;
    private SpecialAbility spec;


    public Hero(){
        this(0,0,0,0);
    }

    Hero(int aAttack, int aDefence, int aInteligence, int aCharisma) {
        this(aAttack, aDefence, aInteligence, aCharisma, "no");
    }

    public Hero(int aAttack, int aDefence, int aIntelligence, int aCharisma, String aSpecialAbility){
        attack = aAttack;
        defence = aDefence;
        intelligence = aIntelligence;
        charisma = aCharisma;
        creatures = new ArrayList<>();
        mana = 10*intelligence;
        criticalchance = 0.05 * charisma;
        spec = new SpecialAbility(aSpecialAbility);
    }

    public void addCreature(Creature aCreature) {
        if(creatures.size()>=5){
            throw new IllegalStateException("Hero doesn't have empty slot for next creature");
        }
        creatures.add(aCreature);

    }

    public List<Creature> getCreatures() {
        return creatures;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public SpecialAbility getSpec() {
        return spec;
    }

    public void setSpec(String aSpec) {
        spec = new SpecialAbility(aSpec);
    }
}
