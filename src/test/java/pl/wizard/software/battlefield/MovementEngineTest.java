package pl.wizard.software.battlefield;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovementEngineTest {

    @Test
    void creatureShouldMoveOneTileInRight(){
        Creature creature = new Creature("Imp", 4, Range.closed(2, 3), 3);

        BattleMap map = new BattleMap();
        map.put(creature, new Point(1,1));
        map.move(creature, new Point(3,2) );

        assertEquals(new Point(3,2), map.getCreaturePosition(creature));
    }
}
