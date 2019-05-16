package pl.wizard.software.gui.tiles;


import javafx.scene.paint.Color;

public class DiedCreatureDecoratorFactory extends DefaultMapTileFactory {
    private final DefaultMapTileFactory decorated;

    public DiedCreatureDecoratorFactory(DefaultMapTileFactory aTileFactory) {
        super();
        decorated = aTileFactory;
    }

    @Override
    public MapTile prepareTile(int aX, int aY) {
        MapTile mapTile = decorated.prepareTile(aX, aY);
        mapTile.getRect().setFill(Color.YELLOW);
        return mapTile;
    }
}
