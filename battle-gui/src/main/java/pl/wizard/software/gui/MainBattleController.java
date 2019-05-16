package pl.wizard.software.gui;

import com.google.common.collect.Range;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.gui.tiles.CreatureMapTileDecoratorFactory;
import pl.wizard.software.gui.tiles.CurrentCreatureMapTileDecoratorFactory;
import pl.wizard.software.gui.tiles.DefaultMapTileFactory;
import pl.wizard.software.gui.tiles.MapTile;
import pl.wizard.software.player.Hero;

import java.awt.*;
import java.util.Optional;

public class MainBattleController {

    private final BattleEngine engine;

    @FXML
    GridPane gridMap;
    @FXML
    Button passButton;

    public MainBattleController() {
        Hero p1 = new Hero();
//        p1.addCreature(new Creature("P1_C1", 1, Range.closed(1, 1), 1, 1));
//        p1.addCreature(new Creature("P1_C2", 1, Range.closed(2, 2), 2, 2));
//        p1.addCreature(new Creature("P1_C3", 1, Range.closed(3, 3), 3, 3));
//        p1.addCreature(new Creature("P1_C4", 1, Range.closed(4, 4), 4, 4));
        p1.addCreature(new Creature("P1_C5", 1, Range.closed(5, 5), 5, 7));
        Hero p2 = new Hero();
//        p2.addCreature(new Creature("P2_C1", 1, Range.closed(1, 1), 1, 1));
//        p2.addCreature(new Creature("P2_C2", 1, Range.closed(2, 2), 2, 2));
//        p2.addCreature(new Creature("P2_C3", 1, Range.closed(3, 3), 3, 3));
//        p2.addCreature(new Creature("P2_C4", 1, Range.closed(4, 4), 4, 4));
        p2.addCreature(new Creature("P2_C5", 1, Range.closed(5, 5), 5, 10));

        engine = new BattleEngine(p1, p2);

    }

    @FXML
    private void initialize() {
        refreshGui();
        passButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) ->{
            engine.pass();
        });
        engine.addPropertyChangeListener(BattleEngine.CURRENT_CREATURE_MOVED, (e -> {
            refreshGui();
        }));
        engine.addPropertyChangeListener(BattleEngine.NEXT_CREATURE, (e -> {
            refreshGui();
        }));
        engine.addPropertyChangeListener(BattleEngine.CREATURE_ATACKED, (e -> {
            refreshGui();
        }));
    }

    private void refreshGui() {
        for (int x = 0; x <= BattleEngine.MAP_MAX_WIDTH; x++) {
            for (int y = 0; y <= BattleEngine.MAP_MAX_HEIGHT; y++) {
                DefaultMapTileFactory tileFactory = new DefaultMapTileFactory();

                Optional<Creature> creature = engine.getCreatureByPosition(new Point(x, y));
                if(creature.isPresent()){
                    tileFactory = new CreatureMapTileDecoratorFactory(tileFactory, creature.get());
                    if(engine.getCurrentCreature().equals(creature.get())){
                        tileFactory = new CurrentCreatureMapTileDecoratorFactory(tileFactory);
                    }
                }

                if(engine.isMovePossible(new Point(x,y))){
                    tileFactory = new MovePossibleTileDecoratorFactory(tileFactory, engine);
                }


                MapTile mapTile = tileFactory.preapreTile(x, y);
                gridMap.add(mapTile, x, y);
            }
        }
    }
}
