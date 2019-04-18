package pl.wizard.software.battlefield;

import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class BattleMap {

    Map<Creature, Point> map = new HashMap<>();

    void put(Creature aCreature, Point aPoint) {
        map.put(aCreature, aPoint);
    }

    void move(Creature aCreature, Point aPoint) {
        map.replace(aCreature, aPoint);
    }

    Point getCreaturePosition(Creature aCreature) {
        return map.get(aCreature);
    }
}
