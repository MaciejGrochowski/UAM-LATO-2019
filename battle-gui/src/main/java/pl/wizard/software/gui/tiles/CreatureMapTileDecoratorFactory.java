package pl.wizard.software.gui.tiles;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import pl.wizard.software.creatures.Creature;

public class CreatureMapTileDecoratorFactory extends DefaultMapTileFactory {

    private final DefaultMapTileFactory decorated;
    private final Creature creature;
    private final Label creatureNameLabel;
    private final Label creatureAmountLabel;

    public CreatureMapTileDecoratorFactory(DefaultMapTileFactory aTileFactory, Creature aCreature) {
        super();
        decorated = aTileFactory;
        creature = aCreature;
        creatureNameLabel = new Label(creature.toString());
        creatureAmountLabel = new Label(String.valueOf(creature.getCurrentAmount()));
    }

    @Override
    public MapTile prepareTile(int aX, int aY) {
        MapTile mapTile = decorated.prepareTile(aX, aY);

        BorderPane creaturePane = new BorderPane();
        creatureNameLabel.setFont(Font.font("Arial", 12));
        creatureAmountLabel.setFont(Font.font("Arial", 10));
        creaturePane.setCenter(creatureNameLabel);
        creaturePane.setBottom(creatureAmountLabel);
        creaturePane.setAlignment(creatureAmountLabel, Pos.CENTER);
        mapTile.getChildren().add(creaturePane);
        return mapTile;
    }

    Label getCreatureNameLabel() {
        return creatureNameLabel;
    }

    Label getCreatureAmountLabel() {
        return creatureAmountLabel;
    }
}
