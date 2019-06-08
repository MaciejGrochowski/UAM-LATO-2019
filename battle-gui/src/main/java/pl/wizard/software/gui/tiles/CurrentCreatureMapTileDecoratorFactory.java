package pl.wizard.software.gui.tiles;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CurrentCreatureMapTileDecoratorFactory extends DefaultMapTileFactory {


    private final DefaultMapTileFactory decorated;

    public CurrentCreatureMapTileDecoratorFactory(DefaultMapTileFactory aTileFactory) {
        super();
        decorated = aTileFactory;
    }

    @Override
    public MapTile prepareTile(int aX, int aY) {
        MapTile mapTile =  decorated.prepareTile(aX, aY);
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureNameLabel().setFont(Font.font("Arial", 12));
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureNameLabel().setTextFill(Color.BLUE);
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureAmountLabel().setFont(Font.font("Arial", 10));
        ((CreatureMapTileDecoratorFactory)decorated).getCreatureAmountLabel().setTextFill(Color.BLUE);
        return mapTile;
    }
}
