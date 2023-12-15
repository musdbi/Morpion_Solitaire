package application;

import algorithms.DataManager;
import algorithms.ResearchAlgorithm;
import algorithms.random.RandomAlgorithm;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ResearchAlgorithmController {
	
	@FXML
	private Stage stage;
	
	@FXML
    private GridPane firstAlgoGameGrid;
	@FXML
    private GridPane secondAlgoGameGrid;
	@FXML
	private Text mode;
	@FXML
	private Text firstAlgoScore;
	@FXML
	private Text secondAlgoScore;
	@FXML
	private Text firstAlgoTime;
	@FXML
	private Text secondAlgoTime;
	@FXML
	private Pane principal;
	
	private RandomAlgorithm firstRandomAlgo;
	private RandomAlgorithm secondRandomAlgo;
	
	public void startFirstAlgorithm (ActionEvent event) {
		mode.setText(Mode.toStringStatic());
		long startTime = System.currentTimeMillis();
        DataManager.setCurrRunningAlgo(0);
        firstRandomAlgo = new RandomAlgorithm();
		int it = 50;
		firstRandomAlgo.trainAlgorithm(it);
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
		String formattedNumber = String.format("%.2f", elapsedTime);
		firstAlgoTime.setText(formattedNumber + " S");
		firstAlgoScore.setText(String.valueOf(firstRandomAlgo.getGrid().getLines().size()));
        System.out.println("Time taken: " + elapsedTime + " seconds");
        if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) {
        	displayGridfirstAlgo4DT();
        }
	    if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) {
	    	displayGridfirstAlgo4DT();
	    }
	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) {
	    	displayGridfirstAlgo5DT();
	    }
	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("D")) {
	    	displayGridfirstAlgo5DT();
	    }
        firstAlgoGameGrid.setVisible(true);
	}
	
	public void startSecondAlgorithm (ActionEvent event) {
		mode.setText(Mode.toStringStatic());
		long startTime = System.currentTimeMillis();
        DataManager.setCurrRunningAlgo(0);
        secondRandomAlgo = new RandomAlgorithm();
		int it = 50;
		secondRandomAlgo.trainAlgorithm(it);
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
		String formattedNumber = String.format("%.2f", elapsedTime);
		secondAlgoTime.setText(formattedNumber + " S");
		secondAlgoScore.setText(String.valueOf(secondRandomAlgo.getGrid().getLines().size()));
        System.out.println("Time taken: " + elapsedTime + " seconds");
        if (Mode.getNumber() == 4 && Mode.getType().toString().equals("T")) {
        	displayGridsecondAlgo4DT();
        }
	    if (Mode.getNumber() == 4 && Mode.getType().toString().equals("D")) {
	    	displayGridsecondAlgo4DT();
	    }
	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("T")) {
	    	displayGridsecondAlgo5DT();
	    }
	    if (Mode.getNumber() == 5 && Mode.getType().toString().equals("D")) {
	    	displayGridsecondAlgo5DT();
	    }
        secondAlgoGameGrid.setVisible(true);
	}
	
	private void displayGridfirstAlgo5DT() {
        Grid grid = firstRandomAlgo.getGrid();
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates5.getValues();
        firstAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
                
                if (grid.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) grid.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (grid.getPoint(x, y).isPlayed()) {
                	button.setText("X");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else {
                	button.setText("•");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                firstAlgoGameGrid.add(button, x, y);
            }
        }
        drawLinesFirst();
    }

	private void displayGridsecondAlgo5DT() {
        Grid grid = secondRandomAlgo.getGrid();
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates5.getValues();
        secondAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
                
                if (grid.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) grid.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (grid.getPoint(x, y).isPlayed()) {
                	button.setText("X");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else {
                	button.setText("•");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                secondAlgoGameGrid.add(button, x, y);
            }
        }
        drawLinesSecond();
    }
	
	private void displayGridfirstAlgo4DT() {
        Grid grid = firstRandomAlgo.getGrid();
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates4.getValues();
        firstAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
               
                if (grid.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) grid.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (grid.getPoint(x, y).isPlayed()) {
                	button.setText("X");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else {
                	button.setText("•");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                firstAlgoGameGrid.add(button, x, y);
            }
        }
        drawLinesFirst();
    }
	
	private void displayGridsecondAlgo4DT() {
        Grid grid = secondRandomAlgo.getGrid();
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates4.getValues();
        secondAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
              
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
               
                if (grid.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) grid.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (grid.getPoint(x, y).isPlayed()) {
                	button.setText("X");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else {
                	button.setText("•");
                	button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                secondAlgoGameGrid.add(button, x, y);
            }
        }
        drawLinesSecond();
    }
	
	private void drawLinesFirst() {
	    Grid grid = firstRandomAlgo.getGrid();
	    javafx.geometry.Point2D paneCoords = principal.localToScene(0, 0);

	    for (Line line : grid.getLines()) {
	        Set<Point> ends = line.getEndsOfLine();
	        Point[] endPoints = ends.toArray(new Point[0]);
	        Point startPoint = endPoints[0];
	        Point endPoint = endPoints[1];

	        Button startButton = findButtonInGrid(firstAlgoGameGrid, startPoint.getX(), startPoint.getY());
	        Button endButton = findButtonInGrid(firstAlgoGameGrid, endPoint.getX(), endPoint.getY());

	        Platform.runLater(() -> {
	            javafx.geometry.Point2D startButtonSceneCoords = startButton.localToScene(0, 0);
	            javafx.geometry.Point2D endButtonSceneCoords = endButton.localToScene(0, 0);

	            double startX = startButtonSceneCoords.getX() - paneCoords.getX() + startButton.getWidth() / 2; 
	            double startY = startButtonSceneCoords.getY() - paneCoords.getY() + startButton.getHeight() / 2;
	            double endX = endButtonSceneCoords.getX() - paneCoords.getX() + endButton.getWidth() / 2; 
	            double endY = endButtonSceneCoords.getY() - paneCoords.getY() + endButton.getHeight() / 2; 

	            javafx.scene.shape.Line guiLine = new javafx.scene.shape.Line(startX, startY, endX, endY);
	            guiLine.setStroke(javafx.scene.paint.Color.BLACK);

	            principal.getChildren().add(guiLine);
	        });
	    }
	}
	
	private void drawLinesSecond() {
	    Grid grid = secondRandomAlgo.getGrid();
	    javafx.geometry.Point2D paneCoords = principal.localToScene(0, 0);

	    for (Line line : grid.getLines()) {
	        Set<Point> ends = line.getEndsOfLine();
	        Point[] endPoints = ends.toArray(new Point[0]);
	        Point startPoint = endPoints[0];
	        Point endPoint = endPoints[1];

	        Button startButton = findButtonInGrid(secondAlgoGameGrid, startPoint.getX(), startPoint.getY());
	        Button endButton = findButtonInGrid(secondAlgoGameGrid, endPoint.getX(), endPoint.getY());

	        Platform.runLater(() -> {
	            javafx.geometry.Point2D startButtonSceneCoords = startButton.localToScene(0, 0);
	            javafx.geometry.Point2D endButtonSceneCoords = endButton.localToScene(0, 0);

	            double startX = startButtonSceneCoords.getX() - paneCoords.getX() + startButton.getWidth() / 2; 
	            double startY = startButtonSceneCoords.getY() - paneCoords.getY() + startButton.getHeight() / 2;
	            double endX = endButtonSceneCoords.getX() - paneCoords.getX() + endButton.getWidth() / 2; 
	            double endY = endButtonSceneCoords.getY() - paneCoords.getY() + endButton.getHeight() / 2; 

	            javafx.scene.shape.Line guiLine = new javafx.scene.shape.Line(startX, startY, endX, endY);
	            guiLine.setStroke(javafx.scene.paint.Color.BLACK);

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
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
}