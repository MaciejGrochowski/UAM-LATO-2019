package pl.wizard.software.battlefield;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BattleEngineTest {

    private static final int NOT_IMPORTANT = 0;

    private Hero heroWithImp;
    private Hero heroWithCentaur;
    private Creature imp;
    private Creature centaur;

    @BeforeEach
    void init() {
        imp = new Creature("Imp", 4, Range.closed(2, 3), 3, 14);
        heroWithImp = new Hero();
        heroWithImp.addCreature(imp);

        centaur = new Creature("Centaur", 10, Range.closed(4, 7), 3, 15);
        heroWithCentaur = new Hero();
        heroWithCentaur.addCreature(centaur);
    }

    @Test
    void shouldPrepareCorrectCreatureQueue() {
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

        assertEquals(H2_S5, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S4, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S3, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H2_S2, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S1, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H2_S5, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S4, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S3, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H2_S2, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H1_S1, engine.getCurrentCreature());
        engine.pass();
        assertEquals(H2_S5, engine.getCurrentCreature());
        engine.pass();
    }

    @Test
    void creatureShouldResetCounterAttackAfterEndOfTurn() {
        BattleEngine engine = new BattleEngine(heroWithImp, heroWithCentaur);

        engine.attack(imp);
        engine.pass();
        engine.attack(imp);

        assertEquals(8, centaur.getCurrentHp());
    }

    @Test
    void afterAttackEngineShouldChangeCurrentCreature() {
        BattleEngine engine = new BattleEngine(heroWithImp, heroWithCentaur);

        assertEquals(centaur, engine.getCurrentCreature());
        engine.attack(imp);
        assertEquals(imp, engine.getCurrentCreature());
    }

    @Test
    void creaturesShouldBePutInFirstAndLastRow(){
        BattleEngine engine = new BattleEngine(heroWithImp, heroWithCentaur);

        Point impPosition = engine.getCreaturePosition(imp);
        Point centaurPosition = engine.getCreaturePosition(centaur);

        assertEquals(new Point(0,1), impPosition);
        assertEquals(new Point(15,1), centaurPosition);
    }

    @Test
    void shouldPutAllCreatures() {
        Creature c1 = new Creature("C1", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature c2 = new Creature("C2", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature c3 = new Creature("C3", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature c4 = new Creature("C4", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature c5 = new Creature("C5", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Hero hero1 = new Hero();
        hero1.addCreature(c1);
        hero1.addCreature(c2);
        hero1.addCreature(c3);
        hero1.addCreature(c4);
        hero1.addCreature(c5);
        Creature h2c1 = new Creature("H2_C1", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature h2c2 = new Creature("H2_C2", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature h2c3 = new Creature("H2_C3", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature h2c4 = new Creature("H2_C4", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Creature h2c5 = new Creature("H2_C5", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT, NOT_IMPORTANT), NOT_IMPORTANT, NOT_IMPORTANT);
        Hero hero2 = new Hero();
        hero2.addCreature(h2c1);
        hero2.addCreature(h2c2);
        hero2.addCreature(h2c3);
        hero2.addCreature(h2c4);
        hero2.addCreature(h2c5);
        BattleEngine engine = new BattleEngine(hero1, hero2);

        assertEquals(new Point(0,1), engine.getCreaturePosition(c1));
        assertEquals(new Point(0,3), engine.getCreaturePosition(c2));
        assertEquals(new Point(0,5), engine.getCreaturePosition(c3));
        assertEquals(new Point(0,7), engine.getCreaturePosition(c4));
        assertEquals(new Point(0,9), engine.getCreaturePosition(c5));
        assertEquals(new Point(BattleMap.MAX_WIDTH,1), engine.getCreaturePosition(h2c1));
        assertEquals(new Point(BattleMap.MAX_WIDTH,3), engine.getCreaturePosition(h2c2));
        assertEquals(new Point(BattleMap.MAX_WIDTH,5), engine.getCreaturePosition(h2c3));
        assertEquals(new Point(BattleMap.MAX_WIDTH,7), engine.getCreaturePosition(h2c4));
        assertEquals(new Point(BattleMap.MAX_WIDTH,9), engine.getCreaturePosition(h2c5));
    }

    @Test
    void shouldReturnTrueIfCreatureCanMoveToIndicatedPoint(){
        BattleEngine engine = new BattleEngine(heroWithImp, heroWithCentaur);

        assertTrue(engine.isMovePossible(new Point(14,14)));
    }

    @Test
    void shouldReturnFalseIfCreatureCannnotMoveToIndicatedPoint(){
        BattleEngine engine = new BattleEngine(heroWithImp, heroWithCentaur);

        assertFalse(engine.isMovePossible(new Point(0,15)));
    }
}