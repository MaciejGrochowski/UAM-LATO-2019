package pl.wizard.software.gui;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pl.wizard.software.creatures.Creature;

import java.awt.Point;
import java.util.Optional;

class MapTileWithActiveCreature extends MapTile {
    public MapTileWithActiveCreature(Point aPoint, Optional<Creature> aCreature) {
        super(aPoint, aCreature);
        getCreatureIcon().setFont(Font.font("Arial", 18));
        getCreatureIcon().setTextFill(Color.GREEN);
    }
}
