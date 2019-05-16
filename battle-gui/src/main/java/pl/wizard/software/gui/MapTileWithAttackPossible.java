package pl.wizard.software.gui;

import javafx.scene.paint.Color;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;

import java.awt.*;
import java.util.Optional;

class MapTileWithAttackPossible extends MapTile {
    private final BattleEngine engine;

    public MapTileWithAttackPossible(Point aPoint, Optional<Creature> aCreature, BattleEngine aEngine) {
        super(aPoint, aCreature);
        setAttackPossible();
        engine = aEngine;

        addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            getCreature().ifPresent(c -> engine.attack(c));
        });
    }

    private void setAttackPossible() {
        getBackgroundRectangle().setFill(Color.RED);
    }


}
