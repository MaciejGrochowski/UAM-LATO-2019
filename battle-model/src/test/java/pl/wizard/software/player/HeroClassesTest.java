package pl.wizard.software.player;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;

import static org.junit.jupiter.api.Assertions.*;



public class HeroClassesTest {

    Hero hero1;
    HeroClass heroClass;
    private Creature creature1;


    @BeforeEach
    void prepareheroes(){
        hero1 = new Hero(1,1,1,1);
        heroClass = new Warrior();
        creature1 = new Creature(null, 10,null,0, 0);
    }


    @Test
    void heroShouldHaveBetterStatsBecauseHeIsWarrior(){
        hero1.setHeroClass(heroClass);

        assertEquals(2, hero1.getAttack() );
        assertEquals(3, hero1.getDefence());
    }

    @Test
    void warriorShouldntCastSpells(){
        hero1.setHeroClass(heroClass);
        assertThrows(Exception.class, () -> hero1.getHeroClass().castSpell(hero1, creature1));


    }


}
