package pl.wizard.software.player;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;

import static org.junit.jupiter.api.Assertions.*;

class HeroTest {

    public static final int NOT_IMPORTANT = 0;

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
}