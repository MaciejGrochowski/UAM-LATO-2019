package pl.wizard.software.gui;

import com.google.common.collect.Range;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.player.Hero;

import java.awt.Point;
import java.util.Optional;

public class MainBattleController {

    private final BattleEngine engine;

    @FXML
    GridPane gridMap;

    public MainBattleController() {
        Hero p1 = new Hero();
        p1.addCreature(new Creature("P1_C1", 1, Range.closed(1, 1), 1, 1));
        p1.addCreature(new Creature("P1_C2", 1, Range.closed(2, 2), 2, 2));
        p1.addCreature(new Creature("P1_C3", 1, Range.closed(3, 3), 3, 3));
        p1.addCreature(new Creature("P1_C4", 1, Range.closed(4, 4), 4, 4));
        p1.addCreature(new Creature("P1_C5", 1, Range.closed(5, 5), 5, 5));
        Hero p2 = new Hero();
        p2.addCreature(new Creature("P2_C1", 1, Range.closed(1, 1), 1, 1));
        p2.addCreature(new Creature("P2_C2", 1, Range.closed(2, 2), 2, 2));
        p2.addCreature(new Creature("P2_C3", 1, Range.closed(3, 3), 3, 3));
        p2.addCreature(new Creature("P2_C4", 1, Range.closed(4, 4), 4, 4));
        p2.addCreature(new Creature("P2_C5", 1, Range.closed(5, 5), 5, 5));

        engine = new BattleEngine(p1, p2);

    }

    @FXML
    private void initialize() {

        for (int x = 0; x <= BattleEngine.MAP_MAX_WIDTH; x++) {
            for (int y = 0; y <= BattleEngine.MAP_MAX_HEIGHT; y++) {

                Optional<Creature> creature = engine.getCreatureByPosition(new Point(x, y));
                Creature currenctCreature = engine.getCurrentCreature();
                MapTile mapTile;
                if (creature.isPresent() && currenctCreature.equals(creature.get())) {
                    mapTile = new MapTileWithActiveCreature(new Point(x, y), creature);
                } else if (engine.isMovePossible(new Point(x, y))) {
                    mapTile = new MapTileWithActiveMove(new Point(x, y), creature);
                } else {
                    mapTile = new MapTile(new Point(x, y), creature);
                }
                gridMap.add(mapTile, x, y);
            }
        }
    }
}
