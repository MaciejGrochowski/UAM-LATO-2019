package pl.wizard.software.gui.tiles;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.gui.tiles.CreatureMapTileDecoratorFactory;
import pl.wizard.software.gui.tiles.DefaultMapTileFactory;
import pl.wizard.software.gui.tiles.MapTile;

public class CurrentCreatureMapTileDecoratorFactory extends DefaultMapTileFactory {


    private final DefaultMapTileFactory decorated;

    public CurrentCreatureMapTileDecoratorFactory(DefaultMapTileFactory aTileFactory) {
        super();
        decorated = aTileFactory;
    }

    @Override
    public MapTile preapreTile(int aX, int aY) {
        MapTile mapTile =  decorated.preapreTile(aX, aY);
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureNameLabel().setFont(Font.font("Arial", 18));
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureNameLabel().setTextFill(Color.BLUE);
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureAmountLabel().setFont(Font.font("Arial", 14));
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureAmountLabel().setTextFill(Color.BLUE);
        return mapTile;
    }
}
