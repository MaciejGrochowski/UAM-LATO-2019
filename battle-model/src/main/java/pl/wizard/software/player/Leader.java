package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;

public class Leader implements HeroClass {

    public void improve(Hero aHero) {
        aHero.setCharisma(aHero.getCharisma()*2);
    }

    public void worse(Hero aHero) {
        aHero.setCharisma(aHero.getCharisma()/2);
    }

    public void castSpell(Hero aHero, Creature aTarget){
        aHero.getSpellBook().cast(aHero, 1, aTarget);
    }
}
