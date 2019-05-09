package pl.wizard.software.gui;

import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.Optional;

class MapTileWithActiveMove extends MapTile {

    MapTileWithActiveMove(Point aPosition, Optional<Creature> aCreature) {
        super(aPosition, aCreature);
        setMovePossible();

        addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            System.out.println("Hello i'm " + "x: " + position.getX() + "y: " + position.getY());
        });
    }


}
