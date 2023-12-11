package application;

import javafx.beans.binding.Bindings;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MSMenuItem extends Pane {
    private Text text;
    private Polygon bg;

    public MSMenuItem(String name) {
        Polygon bg = new Polygon(
                0, 0,
                200, 0,
                215, 15,
                200, 30,
                0, 30
        );
        bg.setStroke(Color.color(1, 1, 1, 0.75));
        bg.fillProperty().bind(
                Bindings.when(hoverProperty())
                    .then(Color.color(0, 0, 0, 0.5))
                    .otherwise(Color.color(0, 0, 0, 0.25))
            );
        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont(MSMenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        text.setFill(Color.web("#EEE8AA"));

        getChildren().addAll(bg, text);
        
        setOnMouseEntered(e -> {
        	MSMenuApp.hoverSound.stop();
        	MSMenuApp.clickSound.stop();
            MSMenuApp.hoverSound.play();
        });
        
        setOnMouseClicked(e -> {
        	MSMenuApp.hoverSound.stop();
            MSMenuApp.clickSound.stop();
            MSMenuApp.clickSound.play();
        });
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}