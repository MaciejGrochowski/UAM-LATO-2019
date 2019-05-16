package pl.wizard.software.gui.tiles;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import pl.wizard.software.creatures.Creature;
import pl.wizard.software.gui.tiles.DefaultMapTileFactory;
import pl.wizard.software.gui.tiles.MapTile;

public class CreatureMapTileDecoratorFactory extends DefaultMapTileFactory {

    private final DefaultMapTileFactory decorated;
    private final Creature creature;

    public CreatureMapTileDecoratorFactory(DefaultMapTileFactory aTileFactory, Creature aCreature) {
        super();
        decorated = aTileFactory;
        creature = aCreature;
    }

    @Override
    public MapTile preapreTile(int aX, int aY) {
        MapTile mapTile = decorated.preapreTile(aX, aY);
        BorderPane creaturePane = new BorderPane();
        Label creatureNameLabel = new Label(creature.toString());
        creatureNameLabel.setFont(Font.font("Arial", 14));
        Label creatureAmountLabel = new Label(String.valueOf(creature.getCurrentAmount()));
        creatureAmountLabel.setFont(Font.font("Arial", 12));
        creaturePane.setCenter(creatureNameLabel);
        creaturePane.setBottom(creatureAmountLabel);
        creaturePane.setAlignment(creatureAmountLabel, Pos.CENTER);
        mapTile.getChildren().add(creaturePane);
        return mapTile;
    }
}
