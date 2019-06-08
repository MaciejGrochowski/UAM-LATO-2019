package pl.wizard.software.player;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.creatures.DefaultDamageStrategy;
import static pl.wizard.software.player.SpecialAbility.*;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    static final int NOT_IMPORTANT = 0;
    static final int NOT_IMPROVED = 0;


    @Test
    void shouldThrowExceptionWhenYouAreTryingToAddMoreThanFiveCreature() {
        Hero hero = new Hero();
        hero.addCreature(new Creature("Creature_1", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT));
        hero.addCreature(new Creature("Creature_2", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT));
        hero.addCreature(new Creature("Creature_3", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT));
        hero.addCreature(new Creature("Creature_4", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT));
        hero.addCreature(new Creature("Creature_5", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT));

        assertThrows(IllegalStateException.class, (() -> hero.addCreature(new Creature("Creature_1", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT))));
    }

    @Test
    void creatureShouldKillOtherBecauseOfHeroImprovedAttackerAttack(){
        Hero hero = new Hero(2,NOT_IMPORTANT,NOT_IMPORTANT,NOT_IMPORTANT);
        Creature creature1 = new Creature(null, 10, Range.closed(1,2), NOT_IMPROVED, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);
        Creature creature2 = new Creature(null, 2, Range.closed(NOT_IMPORTANT,NOT_IMPORTANT), NOT_IMPROVED, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);
        creature1.setHero(hero);
        creature1.attack(creature2);
        assertFalse(creature2.isAlive());
    }

    @Test
    void creatureShouldntKillOtherBecauseHeroImprovedDefenderDefence(){

        Creature creature1 = new Creature(null, 10, Range.closed(NOT_IMPORTANT,NOT_IMPORTANT), 0, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);
        Creature creature2 = new Creature(null, 2, Range.closed(1,2), 0, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);
        creature1.setHero(new Hero (NOT_IMPORTANT, 4, NOT_IMPORTANT, NOT_IMPORTANT));
        creature2.attack(creature1);
        assertTrue(creature1.isAlive());
    }

    @Test
    void creatureShouldDieAfterMoreAttacksAndDoesntDieAfterOneAttack(){
        Creature creature = new Creature(null, 10, Range.closed(4,4), NOT_IMPORTANT, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);
        Creature creature2 = new Creature(null, 3, Range.closed(NOT_IMPORTANT,NOT_IMPORTANT), NOT_IMPROVED, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);

        creature2.setHero(new Hero(NOT_IMPORTANT, 2, NOT_IMPORTANT, NOT_IMPORTANT));

        creature.attack(creature2);
        assertTrue(creature2.isAlive());
        creature.attack(creature2);
        assertFalse(creature2.isAlive());
    }

    @Test
    void creatureShouldKillOtherBecauseOfSpecialAbilityBless(){
        Hero hero = new Hero(NOT_IMPROVED,NOT_IMPROVED,NOT_IMPROVED,NOT_IMPROVED);
        hero.setSpec(BLESS);
        Creature creature = new Creature(null, 2, Range.closed(1,4), NOT_IMPORTANT, new DefaultDamageStrategy(), NOT_IMPORTANT, hero, 1);
        Creature creature2 = new Creature(null, 3, Range.closed(NOT_IMPORTANT,NOT_IMPORTANT), NOT_IMPORTANT, new DefaultDamageStrategy(), NOT_IMPORTANT, null, 1);
        hero.getSpec().setOnSpecialAbility(hero);
        hero.getCreatures().get(0).attack(creature2);
        assertFalse(creature2.isAlive());

    }




}