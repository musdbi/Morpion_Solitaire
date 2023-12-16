package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameOverController {
	
	@FXML
	private Stage stage;
	
	private String score;
	
	@FXML
	private Text scoreText;
	
	private Scene gridScene;
	
	public void setScore (String score) {
		this.score = score;
		scoreText.setText(score);
	}
	
	public void setGridScene(Scene gridScene) {
	    this.gridScene = gridScene;
	}
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
	
	public void switchToGame (ActionEvent event) {
		if (gridScene != null) {
	        stage.setScene(gridScene);
	        stage.show();
	    }
	}
}
