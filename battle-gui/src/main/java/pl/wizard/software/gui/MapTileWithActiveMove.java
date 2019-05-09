package pl.wizard.software.gui;

import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.Optional;

class MapTileWithActiveMove extends MapTile {

    private final BattleEngine engine;

    MapTileWithActiveMove(Point aPosition, Optional<Creature> aCreature, BattleEngine aEngine) {
        super(aPosition, aCreature);
        setMovePossible();
        engine = aEngine;

        addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            System.out.println("Hello i'm " + "x: " + position.getX() + "y: " + position.getY());
            engine.move(new Point((int) position.getX(), (int) position.getY()));
        });
    }


}
