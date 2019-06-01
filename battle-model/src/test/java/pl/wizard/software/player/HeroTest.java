package pl.wizard.software.player;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.creatures.DefaultDamageStrategy;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    public static final int NOT_IMPORTANT = 0;
    private Hero hero1;
    private Hero hero2;
    private Creature creature1;
    private Creature creature2;
    private Creature creature3;
    private Hero hero3;

    @BeforeEach
    void prepareHeroes(){

        hero1 = new Hero(2,2,2,2);
        hero2 = new Hero(0,0,0,0);
        hero3 = new Hero(0, 5, 0, 0);
        creature1 = new Creature("1", 10, Range.closed(1,2), 0, new DefaultDamageStrategy(), NOT_IMPORTANT, hero1, 1);
        creature2 = new Creature("2", 2, Range.closed(1,2), 0, new DefaultDamageStrategy(), NOT_IMPORTANT, hero2, 1);
        creature3 = new Creature("3", 20, Range.closed(2,2), 0, new DefaultDamageStrategy(), NOT_IMPORTANT, hero3, 1);
    }



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
    void creatureShouldDieBecauseOfHerosStats(){
        creature1.attack(creature2);
        assertFalse(creature2.isAlive());
    }

    @Test
    void creatureShouldntDieBecauseOfHerosStats(){
        creature1.setHero(hero2);
        creature2.setHero(hero1);
        creature1.attack(creature2);
        assertTrue(creature2.isAlive());
    }

    @Test
    void creatureShouldDieAfter20Attacks(){
        for(int i=0;i<19;i++){
            creature1.attack(creature3);
        }
        assertTrue(creature3.isAlive());
        creature1.attack(creature3);
        assertFalse(creature3.isAlive());
    }

}