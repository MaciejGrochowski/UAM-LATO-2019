package pl.wizard.software.gui;

import javafx.scene.paint.Color;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;

import java.awt.Point;
import java.util.Optional;

class MapTileWithActiveMove extends MapTile {

    private final BattleEngine engine;

    MapTileWithActiveMove(Point aPosition, Optional<Creature> aCreature, BattleEngine aEngine) {
        super(aPosition, aCreature);
        setMovePossible();
        engine = aEngine;

        addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            engine.move(new Point((int) getPosition().getX(), (int) getPosition().getY()));
        });
    }

    private void setMovePossible() {
        getBackgroundRectangle().setFill(Color.GRAY);
    }


}
