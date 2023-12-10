package application;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import game.GameManagerFX;
import game.Mode;
import helpers.DefaultCoordinates4;
import helpers.DefaultCoordinates5;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MSGridController {
	
	@FXML
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	@FXML
    private GridPane gameGrid;
	@FXML
	private Text nomJoueur;
	@FXML
	private Text mode;
	@FXML
	private Text score;
	@FXML
	private Pane principal;
	
	private GameManagerFX gameManager;
	
	public void initGameManager() {
        this.gameManager = GameManagerFX.getInstance();
        gameManager.setupGame();
        if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) {
        	updateGridUI4DT();
        }
	    if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) {
	    	updateGridUI4DT();
	    }
	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) {
	    	updateGridUI5DT();
	    }
	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("D")) {
	    	updateGridUI5DT();
	    }
        updateLabels();
    }
	
	private void drawLines() {
	    Grid grid = gameManager.getGrid();
	    javafx.geometry.Point2D paneCoords = principal.localToScene(0, 0);

	    for (Line line : grid.getLines()) {
	        Set<Point> ends = line.getEndsOfLine();
	        Point[] endPoints = ends.toArray(new Point[0]);
	        Point startPoint = endPoints[0];
	        Point endPoint = endPoints[1];

	        Button startButton = findButtonInGrid(gameGrid, startPoint.getX(), startPoint.getY());
	        Button endButton = findButtonInGrid(gameGrid, endPoint.getX(), endPoint.getY());

	        Platform.runLater(() -> {
	            javafx.geometry.Point2D startButtonSceneCoords = startButton.localToScene(0, 0);
	            javafx.geometry.Point2D endButtonSceneCoords = endButton.localToScene(0, 0);

	            double startX = startButtonSceneCoords.getX() - paneCoords.getX() + startButton.getWidth() / 2; 
	            double startY = startButtonSceneCoords.getY() - paneCoords.getY() + startButton.getHeight() / 2;
	            double endX = endButtonSceneCoords.getX() - paneCoords.getX() + endButton.getWidth() / 2; 
	            double endY = endButtonSceneCoords.getY() - paneCoords.getY() + endButton.getHeight() / 2; 

	            javafx.scene.shape.Line guiLine = new javafx.scene.shape.Line(startX, startY, endX, endY);
	            guiLine.setStroke(javafx.scene.paint.Color.RED);

	            principal.getChildren().add(guiLine);
	        });
	    }
	}



	
	private Button findButtonInGrid(GridPane gridPane, int x, int y) {
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y && node instanceof Button) {
	            return (Button) node;
	        }
	    }
	    return null;
	}
	
	private void updateGridUI5DT() {
        Grid grid = gameManager.getGrid();
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates5.getValues();
        gameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
            	
            	final int finalX = x;
                final int finalY = y;
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setText("*");
                button.setFont(new Font(12));
               
                if (grid.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) grid.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: red");
                }
                else if (grid.getPoint(x, y).isPlayed()) {
                	button.setText("X");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold;");
                }
                else {
                	button.setText("*");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold;");
                }
                
                button.setOnAction(event -> handleGridButtonAction(finalX, finalY));
                gameGrid.add(button, x, y);
            }
        }
        grid.updatePlayablePoints();
        drawLines();
    }
	
	private void updateGridUI4DT() {
        Grid grid = gameManager.getGrid();
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates4.getValues();
        gameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
            	
            	final int finalX = x;
                final int finalY = y;
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setText("*");
                button.setFont(new Font(12));
               
                if (grid.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) grid.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: red");
                }
                else if (grid.getPoint(x, y).isPlayed()) {
                	button.setText("X");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold;");
                }
                else {
                	button.setText("*");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold;");
                }
                
                button.setOnAction(event -> handleGridButtonAction(finalX, finalY));
                gameGrid.add(button, x, y);
            }
        }
        grid.updatePlayablePoints();
        drawLines();
    }
	
	private void handleGridButtonAction(int x, int y) {
		if (!gameManager.getGrid().getPoint(x, y).isPlayed()) {
			gameManager.playAt(x, y); 
			if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) {
	        	updateGridUI4DT();
	        }
		    if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) {
		    	updateGridUI4DT();
		    }
		    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) {
		    	updateGridUI5DT();
		    }
		    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("D")) {
		    	updateGridUI5DT();
		    }
		    updateLabels();
		}
	}
	
	public void updateLabels() {
		if (gameManager != null) {
            nomJoueur.setText(gameManager.getPlayerName());
            if (mode.getText().isEmpty()) {
                if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) mode.setText("4T");
        	    if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) mode.setText("4D");
        	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) mode.setText("5T");
        	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("D")) mode.setText("5D");
            }
    	    score.setText(gameManager.getScore());
        }
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
}