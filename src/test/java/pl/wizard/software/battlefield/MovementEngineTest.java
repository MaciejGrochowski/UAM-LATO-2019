package pl.wizard.software.battlefield;

import com.google.common.collect.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MovementEngineTest {

    private Creature creature1;
    private Creature creature2;
    private BattleMap map;

    @BeforeEach
    void init() {
        creature1 = new Creature("Imp", 4, Range.closed(2, 3), 3, 1);
        creature2 = new Creature("Imp", 4, Range.closed(2, 3), 3, 2);
        map = new BattleMap();
    }

    @Test
    void creatureShouldMoveOneTileInRight() {
        map.put(creature1, new Point(1, 1));

        map.move(creature1, new Point(1, 2));

        assertEquals(new Point(1, 2), map.getCreaturePosition(creature1));
    }

    @Test
    void creaturesCannotStayInTheSameTile() {
        map.put(creature1, new Point(1, 1));
        map.put(creature2, new Point(2, 2));

        assertThrows(IllegalArgumentException.class,
                () -> map.move(creature2, new Point(1, 1)));
    }

    @Test
    void cannotPutCreatureToNotEmptyTile() {
        map.put(creature1, new Point(1, 1));

        assertThrows(IllegalArgumentException.class,
                () -> map.put(creature2, new Point(1, 1)));
    }

    @Test
    void finalMovingTest() {
        map.put(creature1, new Point(1, 1));
        map.put(creature2, new Point(2, 2));
        map.move(creature2, new Point(3, 3));

        assertDoesNotThrow(() -> map.move(creature1, new Point(2, 2)));
    }

    @Test
    void creatureShouldNotCanMoveMoreThanHerSpeed() {
        map.put(creature1, new Point(1, 1));

        assertThrows(IllegalArgumentException.class,
                () -> map.move(creature1, new Point(2, 2)));
    }

    @Test
    void shouldThrowExceptionWhileTryToPutCreatureOutisdeTheMap() {
        assertThrows(IllegalArgumentException.class,
                () -> map.put(creature1, new Point(BattleMap.MAX_WIDTH + 1, BattleMap.MAX_HEIGHT)));
        assertThrows(IllegalArgumentException.class,
                () -> map.put(creature1, new Point(BattleMap.MAX_WIDTH + 1, BattleMap.MAX_HEIGHT + 1)));
        assertThrows(IllegalArgumentException.class,
                () -> map.put(creature1, new Point(BattleMap.MAX_WIDTH, BattleMap.MAX_HEIGHT + 1)));
    }

    @Test
    void shouldThrowExceptionWhileTryToMoveCreatureOutsideMap(){
        map.put(creature1, new Point(BattleMap.MAX_WIDTH, BattleMap.MAX_HEIGHT));

        assertThrows(IllegalArgumentException.class,
                () -> map.move(creature1, new Point(BattleMap.MAX_WIDTH + 1, BattleMap.MAX_HEIGHT)));
        assertThrows(IllegalArgumentException.class,
                () -> map.move(creature1, new Point(BattleMap.MAX_WIDTH + 1, BattleMap.MAX_HEIGHT + 1)));
        assertThrows(IllegalArgumentException.class,
                () -> map.move(creature1, new Point(BattleMap.MAX_WIDTH, BattleMap.MAX_HEIGHT + 1)));
    }
}
