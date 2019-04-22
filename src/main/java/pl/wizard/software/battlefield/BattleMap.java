package pl.wizard.software.battlefield;

import com.google.common.collect.Range;
import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class BattleMap {

    static final int MAX_WIDTH = 15;
    static final int MAX_HEIGHT = 10;
    private final Map<Creature, Point> map;
    private final Range<Integer> width;
    private final Range<Integer> height;

    BattleMap(){
        map = new HashMap<>();
        width = Range.closed(0,MAX_WIDTH);
        height = Range.closed(0,MAX_HEIGHT);
    }

    void put(Creature aCreature, Point aPoint) {
        if (map.containsValue(aPoint)) {
            throw new IllegalArgumentException("Field is not empty");
        }
        if(!width.contains((int)aPoint.getX()) || !height.contains((int)aPoint.getY())){
            throw new IllegalArgumentException("You are trying to add creature outside the map!");
        }
        map.put(aCreature, aPoint);
    }

    void move(Creature aCreature, Point aNewPoint) {
        if (map.containsValue(aNewPoint)) {
            throw new IllegalArgumentException("Field is not empty");
        }
        int moveRange = aCreature.getSpeed();
        Point oldPoint = map.get(aCreature);

        if (aNewPoint.distance(oldPoint) > moveRange) {
            throw new IllegalArgumentException("Creature has only " + moveRange + " speed");
        }

        map.replace(aCreature, aNewPoint);
    }

    Point getCreaturePosition(Creature aCreature) {
        return map.get(aCreature);
    }
}
