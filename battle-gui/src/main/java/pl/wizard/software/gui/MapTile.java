package pl.wizard.software.gui;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import pl.wizard.software.creatures.Creature;

import java.awt.Point;
import java.util.Optional;

class MapTile extends StackPane {

    private Label creatureName;
    Point position;
    Optional<Creature> creature;

    MapTile(Point aPosition, Optional<Creature> aCreature) {
        position = aPosition;
        creature = aCreature;

        javafx.scene.shape.Rectangle rect = new Rectangle(60, 60);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.RED);
        getChildren().add(rect);


        creature.ifPresent(c -> {
                    creatureName = new Label(c.toString());
                    getChildren().add(creatureName);
                }
        );


        addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
            System.out.println("Hello i'm " + "x: " + position.getX() + "y: " + position.getY());
        });
    }

    protected void setActive() {
        creatureName.setTextFill(Color.BLUE);
    }
}
