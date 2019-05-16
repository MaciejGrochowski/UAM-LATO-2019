package pl.wizard.software.gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import pl.wizard.software.creatures.Creature;

import java.awt.Point;
import java.util.Optional;

class MapTile extends StackPane {

    private final Rectangle rect;
    private Label creatureNameLabel;
    private Label creatureAmountLabel;
    private final Point position;
    private Optional<Creature> creature;

    MapTile(Point aPosition, Optional<Creature> aCreature) {
        BorderPane creaturePane = new BorderPane();
        position = aPosition;
        creature = aCreature;

        rect = new Rectangle(60, 60);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.RED);
        getChildren().add(rect);

        creature.ifPresent(c -> {
                    creatureNameLabel = new Label(c.toString());
                    creatureNameLabel.setFont(Font.font("Arial", 14));
                    creatureAmountLabel = new Label(String.valueOf(c.getCurrentAmount()));
                    creatureAmountLabel.setFont(Font.font("Arial", 12));
                    creaturePane.setCenter(creatureNameLabel);
                    creaturePane.setBottom(creatureAmountLabel);
                    creaturePane.setAlignment(creatureAmountLabel, Pos.CENTER);
                    getChildren().add(creaturePane);
                }
        );
    }

    protected Optional<Creature> getCreature() {
        return creature;
    }

    protected Rectangle getBackgroundRectangle() {
        return rect;
    }

    protected Label getCreatureIcon() {
        return creatureNameLabel;
    }

    protected Point getPosition() {
        return position;
    }
}
