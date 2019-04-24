package pl.wizard.software.battlefield;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GameEngineTest {

    private static final int NOT_IMPORTANT = 0;

    @Test
    void shouldPrepareCorrectCreatureQueue(){
        Creature H1_S1 = new Creature("H1_S1", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, 1);
        Creature H1_S3 = new Creature("H1_S3", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, 3);
        Creature H1_S4 = new Creature("H1_S4", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, 4);
        Hero hero1 = new Hero();
        hero1.addCreature(H1_S1);
        hero1.addCreature(H1_S3);
        hero1.addCreature(H1_S4);
        Creature H2_S2 = new Creature("H2_S2", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, 2);
        Creature H2_S5 = new Creature("H2_S5", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, 5);
        Hero hero2 = new Hero();
        hero2.addCreature(H2_S2);
        hero2.addCreature(H2_S5);
        BattleEngine engine = new BattleEngine(hero1, hero2);

        assertEquals(H2_S5,engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S4,engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S3,engine.getCurrentCreature());
        engine.pass();
        assertEquals(H2_S2,engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S1,engine.getCurrentCreature());
        engine.pass();
        assertEquals(H2_S5,engine.getCurrentCreature());
    }
}