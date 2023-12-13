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
import game.GameManager;
import game.GameManagerFX;
import game.Mode;

public class MenuApp extends Application {
	
	private GameManagerFX gm;

	private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    
    private Image icon = new Image(getClass().getResourceAsStream("res/logo_nobg.png"));
    protected static MediaPlayer hoverSound;
    protected static MediaPlayer clickSound;
    protected static MediaPlayer bgSound;
    
    private List<Pair<String, Runnable>> menuData = Arrays.asList(
            new Pair<String, Runnable>("Play", () -> {}),
            new Pair<String, Runnable>("Game Options", () -> {}),
            new Pair<String, Runnable>("Research Algorithm", () -> {}),
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

//    private void addBackground() {
//        ImageView imageView = new ImageView(new Image(getClass().getResource("res/Selenium.jpg").toExternalForm()));
//        imageView.setFitWidth(WIDTH);
//        imageView.setFitHeight(HEIGHT);
//
//        root.getChildren().add(imageView);
//    }
    
    private void addBackground() {
        int red = 40;   // Decimal equivalent of hexadecimal 28
        int green = 57; // Decimal equivalent of hexadecimal 39
        int blue = 67;  // Decimal equivalent of hexadecimal 43
//        Color color = Color.rgb(red, green, blue);
//        Color color = Color.web("#294328");
        Color color = Color.web("#284337");
        Rectangle background = new Rectangle(WIDTH, HEIGHT, color);
//        background.setEffect(new DropShadow(30, Color.WHITE));
        root.getChildren().add(background);
    }

    private void addTitle() {
        Title title = new Title("MORPION SOLITAIRE");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);
        title.setEffect(new DropShadow(30, Color.BLACK));

        root.getChildren().add(title);
    }

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 190);
        line.setStrokeWidth(3);
        line.setStroke(Color.web("#CDBF96"));
        line.setEffect(new DropShadow(15, Color.web("#CDBF96")));
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
        MenuItem item = new MenuItem(data.getKey());
        if (data.getKey().equals("Play")) {
            item.setOnAction(() -> {
                primaryStage.setScene(selectNameScene());
            });
        } else if (data.getKey().equals("Game Options")) {
        	item.setOnAction(() -> {
                primaryStage.setScene(selectOptionScene());
            });
        } else if (data.getKey().equals("Scoreboard")) {
        	item.setOnAction(() -> {
                primaryStage.setScene(selectScoreboardScene());
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
	
	private Scene selectScoreboardScene() {
    	Parent newRoot;
    	try {
    		newRoot = FXMLLoader.load(getClass().getResource("ScoreboardScene.fxml"));
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Could not load the FXML");
            GameManagerFX.getSB().show();
    		return null;
    	}
    	Scene scene = new Scene (newRoot);
    	return scene;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    	
    	gm = GameManagerFX.getInstance();
    	
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
        hoverSound.setVolume(0.015);
        
     // Charger le fichier audio pour la musique de fond
        String bgSoundFile = getClass().getResource("res/bg_music.mp3").toExternalForm();
        Media bgMedia = new Media(bgSoundFile);
        bgSound = new MediaPlayer(bgMedia);
        bgSound.setVolume(0.1);
        bgSound.play();
        bgSound.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                bgSound.seek(Duration.ZERO);
                bgSound.play();
            }
        });
        
     // Charger le fichier audio pour le son de clique
        String clickSoundFile = getClass().getResource("res/clickb.mp3").toExternalForm();
        Media clickMedia = new Media(clickSoundFile);
        clickSound = new MediaPlayer(clickMedia);
        
        primaryStage.setTitle("Morpion Solitaire");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        menuScene = primaryStage.getScene();
    }
}