package pl.wizard.software.gui.tiles;

import javafx.scene.paint.Color;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.creatures.Creature;

public class AttackPossibleMapTileDecoratorFactory extends DefaultMapTileFactory {

    private final BattleEngine engine;
    private final Creature creature;
    private final DefaultMapTileFactory decorated;

    public AttackPossibleMapTileDecoratorFactory(DefaultMapTileFactory aTileFactory, BattleEngine aEngine, Creature aCreature) {
        super();
        decorated = aTileFactory;
        engine = aEngine;
        creature = aCreature;
    }

    @Override
    public MapTile prepareTile(int aX, int aY) {
        MapTile mapTile = decorated.prepareTile(aX, aY);

        mapTile.getRect().setFill(Color.RED);
        mapTile.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            engine.attack(creature);
        });

        return mapTile;
    }
}
