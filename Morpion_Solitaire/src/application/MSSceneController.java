package application;

import components.Grid;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MSSceneController {
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	
//	public void switchToName (ActionEvent event) throws IOException {
//		Parent root = FXMLLoader.load(getClass().getResource("NameScene.fxml"));
//		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//		scene = new Scene (root);
//		stage.setScene(scene);
//		stage.show();
//	}
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MSMenuApp.menuScene);
	    stage.show();
	}
	
	public void switchToGame (ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("GridScene.fxml"));
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		scene = new Scene (root);
		stage.setScene(scene);
		stage.show();
	}
}
