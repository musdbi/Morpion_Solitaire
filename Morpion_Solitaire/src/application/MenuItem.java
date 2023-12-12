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

public class MenuItem extends Pane {
    private Text text;
    private Polygon bg;

    public MenuItem(String name) {
        Polygon bg = new Polygon(
                0, 0,
                200, 0,
                215, 15,
                200, 30,
                0, 30
        );
        Double[] originalPoints = new Double[]{
                0.0, 0.0,
                200.0, 0.0,
                215.0, 15.0,
                200.0, 30.0,
                0.0, 30.0
        };
        bg.setStroke(Color.color(1, 1, 1, 0.75));
        bg.fillProperty().bind(
                Bindings.when(hoverProperty())
                    .then(Color.color(0, 0, 0, 0.5))
                    .otherwise(Color.color(0, 0, 0, 0.25))
            );
        text = new Text(name);
        text.setTranslateX(5);
        text.setTranslateY(20);
        text.setFont(Font.loadFont(MenuApp.class.getResource("res/Penumbra-HalfSerif-Std_35114.ttf").toExternalForm(), 14));
        text.setFill(Color.web("#EEE8AA"));

        getChildren().addAll(bg, text);
        
        setOnMouseEntered(e -> {
        	MenuApp.hoverSound.stop();
        	MenuApp.clickSound.stop();
            MenuApp.hoverSound.play();
            bg.getPoints().set(2, 215.0); // Resetting the point at index 2 to its original X-axis value
            bg.getPoints().set(4, 230.0); // Resetting the point at index 3 to its original X-axis value
            bg.getPoints().set(6, 215.0); // Adjusting the point at index 3 (200, 30) to 230 on X-axis
        });
        
        bg.setOnMouseExited(e -> {
            // Réinitialisation des points du polygone à leur forme d'origine
            bg.getPoints().setAll(originalPoints);
        });
        
        setOnMouseClicked(e -> {
        	MenuApp.hoverSound.stop();
            MenuApp.clickSound.stop();
            MenuApp.clickSound.play();
            bg.getPoints().set(2, 215.0); // Resetting the point at index 2 to its original X-axis value
            bg.getPoints().set(3, 230.0); // Resetting the point at index 3 to its original X-axis value
            bg.getPoints().set(4, 215.0); // Adjusting the point at index 3 (200, 30) to 230 on X-axis

        });
    }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}