package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import game.GameManagerFX;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ScoreboardController {
	
		private final static String path = "./data/scoreboard.txt";
	
		@FXML
		private Stage stage;
	    
	    @FXML
	    private ListView<String> scoreListView;
	    
	    /**
	     * Initializes the controller and loads scores into the list.
	     * This method is automatically called after loading the FXML file.
	     */
	    
	    public void initialize() {
	        loadScores();
	    }
	    
	    /**
	     * Loads scores from a text file and displays them in the list.
	     * Scores are read from the file specified by path.
	     * In the event of an error, an exception is raised and processed.
	     */
	    
	    private void loadScores() {
	        try {
	            scoreListView.setStyle("-fx-control-inner-background: #283943;-fx-border-color: #EEE8AA; -fx-border-width: 1px; -fx-text-fill: #EEE8AA;"); 
	            List<String> scores = Files.readAllLines(Paths.get(path));
	            scoreListView.getItems().addAll(scores);
	        } catch (Exception e) {
	        	GameManagerFX.getSB().show();
	            e.printStackTrace();
	        }
	    }
	    
	    /**
	     * Changes the current scene to return to the main menu.
	     * This method is triggered by an action event, typically a button click.
	     *
	     * @param event The event that triggered this method.
	     */
	    
		public void switchToMenu (ActionEvent event) {
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    stage.setScene(MenuApp.menuScene);
		    stage.show();
		}
	}
