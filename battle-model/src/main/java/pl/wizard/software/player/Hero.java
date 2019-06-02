package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;
import spellbook.SpellBook;

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
    private Equipment eq;
    private HeroClass heroClass;
    private SpellBook spellBook;


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
        eq = new Equipment();
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

    void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefence() {
        return defence;
    }

    void setDefence(int defence) {
        this.defence = defence;
    }

    public int getIntelligence() {
        return intelligence;
    }

    void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
        this.mana = 10*intelligence;
    }

    int getCharisma() {
        return charisma;
    }

    void setCharisma(int aCharisma) {
        this.charisma = aCharisma;
        this.criticalchance = 0.05*charisma;
    }

    public SpecialAbility getSpec() {
        return spec;
    }

    void setSpec(String aSpec) {
        spec = new SpecialAbility(aSpec);
    }

    public void setEq(Equipment aEq){
        eq.turnOffArtefacts(this);
        eq = aEq;
        eq.turnOnArtefacts(this);
    }

    void improve(int aAttack, int aDefence, int aIntelligence, int aCharisma){
        attack+= aAttack;
        defence+= aDefence;
        intelligence += aIntelligence;
        charisma += aCharisma;
        mana = 10*intelligence;
        criticalchance = 0.05 * charisma;

    }

    void worsen(int aAttack, int aDefence, int aIntelligence, int aCharisma){
        attack -= aAttack;
        defence -= aDefence;
        intelligence -= aIntelligence;
        charisma -= aCharisma;
        mana = 10*intelligence;
        criticalchance = 0.05 * charisma;
    }

    Equipment getEq() {
        return eq;
    }

    double getCriticalChance() {
        return criticalchance;
    }

    public int getActualMana() {
        return mana;
    }

    SpellBook getSpellBook() {
        return spellBook;
    }

    public void loseMana(int aMana) {
        mana -= aMana;
    }

    void setHeroClass(HeroClass aHeroClass) {
        if (heroClass != null){
            heroClass.worse(this);
        }
        heroClass = aHeroClass;
        heroClass.improve(this);
    }

    HeroClass getHeroClass(){
        return heroClass;
    }

    void setSpellBook(SpellBook aSpellBook) {
        spellBook = aSpellBook;
    }
}
