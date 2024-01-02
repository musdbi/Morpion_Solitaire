package application;

import game.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class ModeController {
	
	@FXML
	private Stage stage;
	
	public void set4D (ActionEvent event) {
		Mode.setNumber(4);
		Mode.setType("D");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
	
	public void set4T (ActionEvent event) {
		Mode.setNumber(4);
		Mode.setType("T");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
	
	public void set5D (ActionEvent event) {
		Mode.setNumber(5);
		Mode.setType("D");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
	
	public void set5T (ActionEvent event) {
		Mode.setNumber(5);
		Mode.setType("T");
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
	
	/**
	 * Allows you to parameterize the music, i.e. trigger it if it is paused, or pause it if it is not.
	 * @param event The event that triggered this method.
	 */
	
	public void checkBGSound (ActionEvent event) {
		if (MenuApp.bgSound.getStatus() == MediaPlayer.Status.PLAYING) MenuApp.bgSound.pause();
		else MenuApp.bgSound.play();
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
