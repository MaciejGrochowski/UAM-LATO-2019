package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;

public class Warrior implements HeroClass {

    public void improve(Hero aHero) {
        aHero.setAttack(aHero.getAttack()*2);
        aHero.setDefence(aHero.getDefence()+2);
    }

    public void worse(Hero aHero) {
        aHero.setAttack(aHero.getAttack()/2);
        aHero.setDefence(aHero.getDefence()-2);
    }

    public void castSpell(Hero aHero, Creature aTarget) {
        throw new IllegalArgumentException("Warrior can't cast spells");
    }
}
