package pl.wizard.software.gui;

import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.Optional;

class MapTileWithActiveCreature extends MapTile {
    public MapTileWithActiveCreature(Point aPoint, Optional<Creature> aCreature) {
        super(aPoint, aCreature);
        setActive();
    }
}
