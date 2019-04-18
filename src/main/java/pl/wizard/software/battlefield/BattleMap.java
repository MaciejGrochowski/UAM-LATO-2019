package pl.wizard.software.battlefield;

import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class BattleMap {

    private final Map<Creature, Point> map = new HashMap<>();

    void put(Creature aCreature, Point aPoint) {
        if (map.containsValue(aPoint)){
            throw new IllegalArgumentException("Field is not empty");
        }
        map.put(aCreature, aPoint);
    }

    void move(Creature aCreature, Point aPoint) {
        if (map.containsValue(aPoint)){
            throw new IllegalArgumentException("Field is not empty");
        }
        map.replace(aCreature, aPoint);
    }

    Point getCreaturePosition(Creature aCreature) {
        return map.get(aCreature);
    }
}
