package spellbook;

import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

public class SpellBook {

    public void cast(Hero aHero, int multiplier, Creature aTarget){
        aTarget.dealDamageToMe(multiplier * aHero.getIntelligence());
        aHero.loseMana(5);
    }

}
