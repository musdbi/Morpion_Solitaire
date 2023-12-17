package application;

import javafx.beans.binding.Bindings;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuItem extends Pane {
    private Text text;

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
         bg.setStroke(Color.web("#CDBF96"));
         bg.fillProperty().bind(
                 Bindings.when(hoverProperty())
                     .then(Color.color(0, 0, 0, 0.50))
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
            MenuApp.hoverSound.play();
//            bg.getPoints().set(2, 215.0); // Resetting the point at index 2 to its original X-axis value
//            bg.getPoints().set(4, 230.0); // Resetting the point at index 3 to its original X-axis value
//            bg.getPoints().set(6, 215.0); // Adjusting the point at index 3 (200, 30) to 230 on X-axis
         });
         
         bg.setOnMouseExited(e -> {
             bg.getPoints().setAll(originalPoints);
         });
     }

    public void setOnAction(Runnable action) {
        setOnMouseClicked(e -> action.run());
    }
}