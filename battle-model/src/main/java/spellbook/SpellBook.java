package spellbook;

import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

public class SpellBook {

    public void cast(Hero aHero, int multiplier, Creature aTarget) {

        if(aHero.getActualMana() >= 5) {
            aTarget.dealDamageToMe(multiplier * aHero.getIntelligence());
            aHero.loseMana(5);
        }
        else{
            throw new IllegalArgumentException("No mana");
        }
    }

}
