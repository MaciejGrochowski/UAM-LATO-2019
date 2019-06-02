package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;

public class Wizard implements HeroClass {


    public void improve(Hero aHero) {
        aHero.setIntelligence(aHero.getIntelligence()*2);

    }

    public void worse(Hero aHero) {
        aHero.setIntelligence(aHero.getIntelligence()/2);
    }

    public void castSpell(Hero aHero, Creature aTarget) {
        aHero.getSpellBook().cast(aHero, 2, aTarget);

    }
}
