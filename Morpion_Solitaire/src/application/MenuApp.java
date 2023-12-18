package application;

import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;
import java.util.Arrays;
import java.util.List;

import game.GameManagerFX;

public class MenuApp extends Application {
	
	private GameManagerFX gm;

	private static final int WIDTH = 1280;
    private static final int HEIGHT = 720;
    
    private Image icon = new Image(getClass().getResourceAsStream("res/logo_nobg.png"));
    protected static MediaPlayer hoverSound;
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
    
    /**
     * Creates the scene's main content for the main menu.
     * This method initializes the background, title, animation line and menu items.
     *
     * @param primaryStage The main stage of the application.
     * @return The parent (Pane) containing all the menu's user interface elements.
     */

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
    
    /**
     * Adds background to the main menu.
     * The background is a colored rectangle covering the entire scene.
     */

    private void addBackground() {
    	// Colors to blend
        Color color = Color.web("#283943");
        Rectangle background = new Rectangle(WIDTH, HEIGHT, color);
        root.getChildren().add(background);
//        background.setEffect(new DropShadow(30, Color.WHITE));
//        root.getChildren().add(background);
    }
    
    /**
     * Adds title to main menu.
     * The title is displayed with a drop shadow effect.
     */

    private void addTitle() {
        Title title = new Title("MORPION SOLITAIRE");
        title.setTranslateX(WIDTH / 2 - title.getTitleWidth() / 2);
        title.setTranslateY(HEIGHT / 3);
        title.setEffect(new DropShadow(30, Color.BLACK));

        root.getChildren().add(title);
    }
    
    /**
     * Adds an animated line as a separator in the menu.
     *
     * @param x X position of line start.
     * @param y Y position of line start.
     */

    private void addLine(double x, double y) {
        line = new Line(x, y, x, y + 190);
        line.setStrokeWidth(3);
        line.setStroke(Color.web("#CDBF96"));
        line.setEffect(new DropShadow(15, Color.web("#CDBF96")));
        line.setScaleY(0);

        root.getChildren().add(line);
    }
    
    /**
     * Starts animation of line and menu items.
     * Line expands and menu items slide into view.
     */

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
    
    /**
     * Adds menu items to the scene.
     * Each item is an instance of MenuItem with an associated action.
     *
     * @param x X position for menu placement.
     * @param y Y position for menu placement.
     * @param primaryStage The application's main stage for navigating between scenes.
     */

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
        } else if (data.getKey().equals("Research Algorithm")) {
        	item.setOnAction(() -> {
        		ResearchAlgorithmController.initialize();
                primaryStage.setScene(selectResearchAlgorithmScene());
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
	
	private GridPane findGridPaneById(Node node, String id) {
	    if (node instanceof GridPane && id.equals(node.getId())) {
	        return (GridPane) node;
	    }
	    if (node instanceof Parent) {
	        for (Node child : ((Parent) node).getChildrenUnmodifiable()) {
	            GridPane found = findGridPaneById(child, id);
	            if (found != null) {
	                return found;
	            }
	        }
	    }
	    return null;
	}
	
	private Scene selectResearchAlgorithmScene() {
    	Parent newRoot;
    	try {
    		newRoot = FXMLLoader.load(getClass().getResource("ResearchAlgorithm.fxml"));
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("Could not load the FXML");
    		return null;
    	}
    	Scene scene = new Scene (newRoot);
    	GridPane firstPane = findGridPaneById(scene.getRoot(), "firstAlgoGameGrid");
    	GridPane secondPane = findGridPaneById(scene.getRoot(), "secondAlgoGameGrid");
    	firstPane.setVisible(false);
    	secondPane.setVisible(false);
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
        
     // Set window size
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);
   
     // Prevent window resizing
        primaryStage.setResizable(false);

     // Load audio file for hover sound
        String hoverSoundFile = getClass().getResource("res/hover.mp3").toExternalForm();
        Media hoverMedia = new Media(hoverSoundFile);
        hoverSound = new MediaPlayer(hoverMedia);
        hoverSound.setVolume(0.015);
        
     // Load audio file for background music
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
        
        primaryStage.setTitle("Morpion Solitaire");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        menuScene = primaryStage.getScene();
    }
}