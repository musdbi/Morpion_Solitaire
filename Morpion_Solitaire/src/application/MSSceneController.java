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

public class MSSceneController {
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	@FXML
	private TextField nomJoueur;
	
	private static GameManagerFX gameManager;
	
	public void initGameManager() {
		gameManager = GameManagerFX.getInstance();
    }
	
	public void switchToName (ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("NameScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene (root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}
	
	public void switchToGame (ActionEvent event) throws IOException {
	    if (nomJoueur.getText().isEmpty()) {
	    	Alert alert = new Alert(Alert.AlertType.ERROR);
	        alert.setTitle("Erreur");
	        alert.setHeaderText("Nom manquant");
	        alert.setContentText("Veuillez entrer un nom !");
	        alert.showAndWait();
	    } else {
	    	
	    	initGameManager ();
	    	gameManager.setPlayerName(nomJoueur.getText());
		    gameManager.setupGame();
		   	FXMLLoader loader = new FXMLLoader(getClass().getResource("GridScene.fxml"));
		   	root = loader.load();

		   	MSGridController gridController = loader.getController();
		    gridController.initGameManager();
		    gridController.updateLabels();
		         
		    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    scene = new Scene (root);
		    stage.setScene(scene);
		    stage.show();
				
	    }
	}
	
	public void checkBGSound (ActionEvent event) {
		if (MSMenuApp.bgSound.getStatus() == MediaPlayer.Status.PLAYING) MSMenuApp.bgSound.pause();
		else MSMenuApp.bgSound.play();
	}
}
