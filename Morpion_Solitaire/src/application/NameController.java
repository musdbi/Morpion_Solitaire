package application;

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
import javafx.stage.Stage;

public class NameController {
	
	@FXML
	private Stage stage;
	private Parent root;
	@FXML
	private TextField nomJoueur;
	
	private static GameManagerFX gameManager;
	
	/**
     * Initializes the game manager by retrieving a single instance (singleton) of GameManagerFX.
     */
	
	public void initGameManager() {
		gameManager = GameManagerFX.getInstance();
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
	
	/**
     * Changes the current scene to the game scene.
     * Checks that the player name is not empty, displays an alert in the event of an error, 
     * and initializes the game manager before switching scenes.
     *
     * @param event The event that triggered this method.
     * @throws IOException If GridScene.fxml fails to load.
     */
	
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
