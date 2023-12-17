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
	
		private final static String path = "./data/classement.txt";
	
		@FXML
		private Stage stage;
	    
	    @FXML
	    private ListView<String> scoreListView;
	    
	    public void initialize() {
	        loadScores();
	    }
	    
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
	    
		public void switchToMenu (ActionEvent event) {
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    stage.setScene(MenuApp.menuScene);
		    stage.show();
		}
	}
