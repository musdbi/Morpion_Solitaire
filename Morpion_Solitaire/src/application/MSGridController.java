package application;

import game.GameManagerFX;
import game.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MSGridController {
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	private GameManagerFX gameManager;
	
	@FXML
	private Text nomJoueur;
	@FXML
	private Text mode;
	@FXML
	private Text score;
	
	public void initGameManager() {
        this.gameManager = GameManagerFX.getInstance();
    }
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}
	
	public void checkBGSound (ActionEvent event) {
		if (MSMenuApp.bgSound.getStatus() == MediaPlayer.Status.PLAYING) MSMenuApp.bgSound.pause();
		else MSMenuApp.bgSound.play();
	}

	public void updateLabels() {
		if (gameManager != null) {
            nomJoueur.setText(gameManager.getPlayerName());
            if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) mode.setText("4T");
    	    if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) mode.setText("4D");
    	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) mode.setText("5T");
    	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("D")) mode.setText("5D");
    	    score.setText(gameManager.getScore());
        }
	}
}