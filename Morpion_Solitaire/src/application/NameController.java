package application;

import components.Grid;
import game.GameManagerFX;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class NameController {
	
	@FXML
	private Stage stage;
	private Parent root;
	@FXML
	private TextField nomJoueur;
	
	private static GameManagerFX gameManager;
	
	public void initGameManager() {
		gameManager = GameManagerFX.getInstance();
    }
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
	
	public void switchToGame (ActionEvent event) throws IOException {
	    if (nomJoueur.getText().isEmpty()) {
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Error");
	        alert.setHeaderText("Missing Name");
	        alert.setContentText("Please enter a name !");
	        alert.showAndWait();
	    } else {
	    	initGameManager ();
	    	gameManager.setPlayerName(nomJoueur.getText());
		    gameManager.setupGame();
		   	FXMLLoader loader = new FXMLLoader(getClass().getResource("GridScene.fxml"));
		   	root = loader.load();

		   	GridController gridController = loader.getController();
		    gridController.initGameManager();
		    gridController.updateLabels();
		         
		    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    Scene scene = new Scene (root);
		    stage.setScene(scene);
		    stage.show();
				
	    }
	}
}
