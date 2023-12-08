package application;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.List;
import components.*;
import game.GameManagerFX;
import game.Mode;

public class MSMenuApp extends Application {
	
	private GameManagerFX GM;
	
    private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    
    private Image icon = new Image(getClass().getResourceAsStream("res/logo2.png"));
    protected static MediaPlayer hoverSound;
    protected static MediaPlayer clickSound;
    
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {}),
            new Pair<String, Runnable>("Game Options", () -> {}),
            new Pair<String, Runnable>("Additional Content", () -> {}),
            new Pair<String, Runnable>("Scoreboard", () -> {}),
            new Pair<String, Runnable>("Exit to Desktop", Platform::exit)
    );
    
    public static Scene menuScene;

    private Pane root = new Pane();
    private VBox menuBox = new VBox(-5);
    private Line line;

    private Parent createContent(Stage primaryStage) {
        addBackground();
        addTitle();

        double lineX = WIDTH / 2 - 100;
        double lineY = HEIGHT / 3 + 50;

        addLine(lineX, lineY);
        addMenu(lineX + 5, lineY + 5, primaryStage);

        startAnimation();

        return root;
    }

    private void addBackground() {
        ImageView imageView = new ImageView(new Image(getClass().getResource("res/fond.png").toExternalForm()));
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);

        root.getChildren().add(imageView);
    }

    private void addTitle() {
        MSTitle title = new MSTitle("MORPION SOLITAIRE");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 190);
        line.setStrokeWidth(3);
        line.setStroke(Color.color(1, 1, 1, 0.75));
        line.setEffect(new DropShadow(5, Color.BLACK));
        line.setScaleY(0);

        root.getChildren().add(line);
    }

    private void startAnimation() {
        ScaleTransition st = new ScaleTransition(Duration.seconds(1), line);
        st.setToY(1);
        st.setOnFinished(e -> {

            for (int i = 0; i < menuBox.getChildren().size(); i++) {
                Node n = menuBox.getChildren().get(i);

                TranslateTransition tt = new TranslateTransition(Duration.seconds(1 + i * 0.15), n);
                tt.setToX(0);
                tt.setOnFinished(e2 -> n.setClip(null));
                tt.play();
            }
        });
        st.play();
    }

    private void addMenu(double x, double y, Stage primaryStage) {
        menuBox.setTranslateX(x);
        menuBox.setTranslateY(y);
        menuData.forEach(data -> {
        MSMenuItem item = new MSMenuItem(data.getKey());
        if (data.getKey().equals("Play")) {
            item.setOnAction(() -> {
                menuScene = primaryStage.getScene();
                primaryStage.setScene(selectNameScene());
            });
        } else if (data.getKey().equals("Game Options")) {
        	item.setOnAction(() -> {
                menuScene = primaryStage.getScene();
                primaryStage.setScene(selectOptionScene());
            });
        } else if (data.getKey().equals("Scoreboard")) {
        	item.setOnAction(() -> {
                GM.getSB().show();
            });
        } else {
            item.setOnAction(data.getValue());
        }
        item.setTranslateX(-300);

        Rectangle clip = new Rectangle(300, 30);
        clip.translateXProperty().bind(item.translateXProperty().negate());

        item.setClip(clip);

        menuBox.getChildren().addAll(item);
        });
        root.getChildren().add(menuBox);
    }
  
	private Scene selectNameScene() {
    	Parent newRoot;
    	try {
    		newRoot = FXMLLoader.load(getClass().getResource("NameScene.fxml"));
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Could not load the FXML");
    		return null;
    	}
    	Scene scene = new Scene (newRoot);
    	return scene;
    }

	private Scene selectOptionScene() {
    	Parent newRoot;
    	try {
    		newRoot = FXMLLoader.load(getClass().getResource("OptionScene.fxml"));
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Could not load the FXML");
    		return null;
    	}
    	Scene scene = new Scene (newRoot);
    	return scene;
    }
	
	public void set4D (ActionEvent event) {
		Mode.setNumber(4);
		Mode.setType("D");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}
	
	public void set4T (ActionEvent event) {
		Mode.setNumber(4);
		Mode.setType("T");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}
	
	public void set5D (ActionEvent event) {
		Mode.setNumber(5);
		Mode.setType("D");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}
	
	public void set5T (ActionEvent event) {
		Mode.setNumber(5);
		Mode.setType("T");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
     // Initialisation du GameManger
    	GM = new GameManagerFX ();
    	
    	primaryStage.getIcons().add(icon);
        Scene scene = new Scene(createContent(primaryStage));
        
     // Fixer la taille de la fenêtre
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
   
     // Empêcher le redimensionnement de la fenêtre
        primaryStage.setResizable(false);

     // Charger le fichier audio pour le son de survol
        String hoverSoundFile = getClass().getResource("res/hover.mp3").toExternalForm();
        Media hoverMedia = new Media(hoverSoundFile);
        hoverSound = new MediaPlayer(hoverMedia);
        hoverSound.setVolume(0.1);

     // Charger le fichier audio pour le son de clique
        String clickSoundFile = getClass().getResource("res/clickb.mp3").toExternalForm();
        Media clickMedia = new Media(clickSoundFile);
        clickSound = new MediaPlayer(clickMedia);

        primaryStage.setTitle("Morpion Solitaire");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}