package pl.wizard.software.gui;

import javafx.scene.paint.Color;
import pl.wizard.software.battlefield.BattleEngine;
import pl.wizard.software.gui.tiles.DefaultMapTileFactory;
import pl.wizard.software.gui.tiles.MapTile;

import java.awt.*;

class MovePossibleTileDecoratorFactory extends DefaultMapTileFactory {
    private final DefaultMapTileFactory decorated;
    private final BattleEngine engine;

    public MovePossibleTileDecoratorFactory(DefaultMapTileFactory aTileFactory, BattleEngine aEngine) {
        super();
        decorated = aTileFactory;
        engine = aEngine;
    }

    @Override
    public MapTile preapreTile(int aX, int aY) {
        MapTile mapTile = decorated.preapreTile(aX, aY);

        mapTile.getRect().setFill(Color.GREY);
        mapTile.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED, (e) -> {
            engine.move(new Point(aX, aY));
        });
        return mapTile;
    }
}
