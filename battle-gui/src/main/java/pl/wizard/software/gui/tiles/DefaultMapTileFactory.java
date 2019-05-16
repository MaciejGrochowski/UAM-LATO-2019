package pl.wizard.software.gui.tiles;

import javafx.scene.paint.Color;

public class DefaultMapTileFactory {

    private MapTile mapTile;

    public MapTile preapreTile(int aX, int aY){
        mapTile = new MapTile(aX, aY);
        mapTile.getRect().setFill(Color.WHITE);
        mapTile.getRect().setStroke(Color.RED);
        return mapTile;
    }

}
