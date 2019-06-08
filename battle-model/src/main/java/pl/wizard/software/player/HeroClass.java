package pl.wizard.software.player;

import pl.wizard.software.creatures.Creature;

public interface HeroClass {

    void improve(Hero aHero);
    void worse(Hero aHero);
    void castSpell(Hero aHero, Creature aTarget);
}
