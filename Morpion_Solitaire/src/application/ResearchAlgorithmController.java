package application;

import algorithms.DataManager;
import algorithms.nmcs.NMCS;
import algorithms.random.RandomAlgorithm;
import com.opencsv.exceptions.CsvValidationException;
import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import game.Mode;
import helpers.DefaultCoordinates4;
import helpers.DefaultCoordinates5;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
	private Parent root;
	
	@FXML
    private GridPane firstAlgoGameGrid;
	@FXML
    private GridPane secondAlgoGameGrid;
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
	
	private int depth = 2;
	
	private Grid gridFirst;
	
	private RandomAlgorithm firstRandomAlgo;
	private NMCS secondRandomAlgo;
	private static Map<String, Double> score0;
	private static Map<String, Double> score1;
	private static Map<String, Double> score2;
	private static Map<String, Double> score3;
	
	/**
     * Initializes score statistics for all search algorithms.
     * This method must be called before using the class to set initial scores.
     */
	
	public static void initialize() {
	    for (int i = 0; i < 4; i++) {
	        try {
	            setScore(i);
	        } catch (CsvValidationException | NumberFormatException e) {
	            throw new IllegalStateException(e);
	        }
	    }
	}
	
	/**
     * Defines statistics for a specific algorithm.
     *
     * @param index Corresponds to the algorithm whose statistics are to be determined: 0 = RANDOM / 1 = NMCS 1 / 2 = NMCS 2 / 3 = NMCS 3.
     * @throws CsvValidationException If an error occurs during CSV validation.
     * @throws NumberFormatException If a number formatting error occurs.
     */

	private static void setScore(int index) throws CsvValidationException, NumberFormatException {
	    switch (index) {
	        case 0:
	            ResearchAlgorithmController.setScore0(DataManager.calculateStatistics(0, Mode.toStringStatic()));
	            break;
	        case 1:
	            ResearchAlgorithmController.setScore1(DataManager.calculateStatistics(1, Mode.toStringStatic()));
	            break;
	        case 2:
	            ResearchAlgorithmController.setScore2(DataManager.calculateStatistics(2, Mode.toStringStatic()));
	            break;
	        case 3:
	            ResearchAlgorithmController.setScore3(DataManager.calculateStatistics(3, Mode.toStringStatic()));
	            break;
	        default:
	            throw new IllegalArgumentException("Index out of range: " + index);
	    }
	}
	
	/**
     * Starts the random algorithm and updates the user interface with the results.
     *
     * @param event The event that triggered this method.
     */

	public void startFirstAlgorithm (ActionEvent event) {
		long startTime = System.currentTimeMillis();
        DataManager.setCurrRunningAlgo(0);
        firstRandomAlgo = new RandomAlgorithm();
		this.gridFirst = firstRandomAlgo.algorithm();
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
		String formattedNumber = String.format("%.2f", elapsedTime);
		firstAlgoTime.setText(formattedNumber + " S");
		firstAlgoScore.setText(String.valueOf(this.gridFirst.getLines().size()));
		if (Mode.toStringStatic().equals("4D") || Mode.toStringStatic().equals("4T")) displayGridfirstAlgo4DT();
		if (Mode.toStringStatic().equals("5D") || Mode.toStringStatic().equals("5T")) displayGridfirstAlgo5DT();
        firstAlgoGameGrid.setVisible(true);
	}
	
	/**
     * Starts NMCS and updates user interface with results.
     *
     * @param event The event that triggered this method.
     */
	
	public void startSecondAlgorithm (ActionEvent event) {
		long startTime = System.currentTimeMillis();
		secondRandomAlgo = new NMCS(depth);
		DataManager.setCurrRunningAlgo(depth);
		Grid nmc = secondRandomAlgo.algorithm();
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
		String formattedNumber = String.format("%.2f", elapsedTime);
		secondAlgoTime.setText(formattedNumber + " S");
		secondAlgoScore.setText(String.valueOf(nmc.getLines().size()));
		if (Mode.toStringStatic().equals("4D") || Mode.toStringStatic().equals("4T")) displayGridsecondAlgo4DT(nmc);
		if (Mode.toStringStatic().equals("5D") || Mode.toStringStatic().equals("5T")) displayGridsecondAlgo5DT(nmc);
        secondAlgoGameGrid.setVisible(true);
	}
	
	private void displayGridfirstAlgo5DT() {
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates5.getValues();
        firstAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
                
                if (gridFirst.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) gridFirst.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (gridFirst.getPoint(x, y).isPlayed()) {
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

	private void displayGridsecondAlgo5DT(Grid gridSecond) {
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates5.getValues();
        secondAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
                
                if (gridSecond.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) gridSecond.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (gridSecond.getPoint(x, y).isPlayed()) {
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
        drawLinesSecond(gridSecond);
    }
	
	private void displayGridfirstAlgo4DT() {
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates4.getValues();
        firstAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
                
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
               
                if (gridFirst.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) gridFirst.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (gridFirst.getPoint(x, y).isPlayed()) {
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
	
	private void displayGridsecondAlgo4DT(Grid gridSecond) {
        HashSet<Integer> defaultPointsHashes = DefaultCoordinates4.getValues();
        secondAlgoGameGrid.getChildren().clear();
        for (int y = 0; y < Grid.getSize(); y++) {
            for (int x = 0; x < Grid.getSize(); x++) {
              
            	Button button = new Button();
                button.setMinSize(30,30);
                button.setFont(new Font(12));
               
                if (gridSecond.getPoint(x, y).isPlayed() && !defaultPointsHashes.contains(Objects.hash(x, y))) {
                	PlayedPoint playedPoint = (PlayedPoint) gridSecond.getPoint(x, y);
                    button.setText(String.valueOf(playedPoint.getId()));
                    button.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: #EEE8AA");
                }
                else if (gridSecond.getPoint(x, y).isPlayed()) {
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
        drawLinesSecond(gridSecond);
    }
	
	private void drawLinesFirst() {
	    javafx.geometry.Point2D paneCoords = principal.localToScene(0, 0);

	    for (Line line : gridFirst.getLines()) {
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
	            guiLine.setStroke(javafx.scene.paint.Color.web("#FF5757"));

	            principal.getChildren().add(guiLine);
	        });
	    }
	}
	
	private void drawLinesSecond(Grid gridSecond) {
	    javafx.geometry.Point2D paneCoords = principal.localToScene(0, 0);

	    for (Line line : gridSecond.getLines()) {
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
	            guiLine.setStroke(javafx.scene.paint.Color.web("#FF5757"));

	            principal.getChildren().add(guiLine);
	        });
	    }
	}
	
	/**
     * Finds a button in a specified grid based on its coordinates.
     *
     * @param gridPane The grid to search in.
     * @param x The X position in the grid.
     * @param y The Y position in the grid.
     * @return The button found, or null if no button is found.
     */
	
	private Button findButtonInGrid(GridPane gridPane, int x, int y) {
	    for (Node node : gridPane.getChildren()) {
	        if (GridPane.getColumnIndex(node) == x && GridPane.getRowIndex(node) == y && node instanceof Button) {
	            return (Button) node;
	        }
	    }
	    return null;
	}
	
	public void setDepthOne (ActionEvent event) {
		depth = 1;
	}
	
	public void setDepthTwo (ActionEvent event) {
		depth = 2;
	}
	
	public void setDepthThree (ActionEvent event) {
		depth = 3;
	}
	
	/**
     * Changes the current scene to display distribution curves.
     *
     * @param event The event that triggered this method.
     */
	
	public void switchToGraphics (ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GraphicScene.fxml"));
	   	try {
			root = loader.load();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}

	   	GraphicController graphicsController = loader.getController();
	   	graphicsController.initialize();	
	    stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    Scene scene = new Scene (root);
	    stage.setScene(scene);
	    stage.show();
	}
	
	public static Map<String, Double> getScore0() {
		return score0;
	}
	
	public static Map<String, Double> getScore1() {
		return score1;
	}
	
	public static Map<String, Double> getScore2() {
		return score2;
	}
	
	public static Map<String, Double> getScore3() {
		return score3;
	}
	
	public static void setScore0(Map<String, Double> score) {
		score0 = score;
	}
	
	public static void setScore1(Map<String, Double> score) {
		score1 = score;
	}
	
	public static void setScore2(Map<String, Double> score) {
		score2 = score;
	}
	
	public static void setScore3(Map<String, Double> score) {
		score3 = score;
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