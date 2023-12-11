package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import game.GameManagerFX;
import game.Scoreboard;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class MSScoreboardController {
	
		private final static String path = "./classement.txt";
	
		@FXML
		private Stage stage;
	    
	    @FXML
	    private ListView<String> scoreListView;
	    
	    public void initialize() {
	        loadScores();
	    }
	    
	    private void loadScores() {
	        try {
	            List<String> scores = Files.readAllLines(Paths.get(path));
	            scoreListView.getItems().addAll(scores);
	        } catch (Exception e) {
	        	GameManagerFX.getSB().show();
	            e.printStackTrace();
	        }
	    }
	    
		public void switchToMenu (ActionEvent event) {
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		    stage.setScene(MSMenuApp.menuScene);
		    stage.show();
		}
	}
