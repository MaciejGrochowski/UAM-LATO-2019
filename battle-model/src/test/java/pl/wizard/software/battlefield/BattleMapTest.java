package pl.wizard.software.battlefield;

import com.google.common.collect.Range;
import org.junit.jupiter.api.Test;
import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BattleMapTest {

    public static final int NOT_IMPORTANT = 1;

    @Test
    void shouldReturnCorrectCreatureByPositionAndEmptyOptionalWhileAskByEmptySlot(){
        BattleMap map = new BattleMap();
        Creature c1 = new Creature("a", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT,NOT_IMPORTANT),NOT_IMPORTANT,NOT_IMPORTANT);
        Creature c2 = new Creature("a", NOT_IMPORTANT, Range.closed(NOT_IMPORTANT,NOT_IMPORTANT),NOT_IMPORTANT,NOT_IMPORTANT);

        map.put(c1, new Point(1,1));
        map.put(c2, new Point(15,10));

        Optional<Creature> shouldBeC1 = map.getCreatureByPosition(new Point(1, 1));
        Optional<Creature> shouldBeC2 = map.getCreatureByPosition(new Point(15, 10));
        Optional<Creature> shouldBeEmpty = map.getCreatureByPosition(new Point(5, 5));

        assertEquals(c1, shouldBeC1.get());
        assertEquals(c2, shouldBeC2.get());
        assertEquals(Optional.empty(), shouldBeEmpty);
    }

}