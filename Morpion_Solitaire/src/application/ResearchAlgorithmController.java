package application;

import algorithms.DataManager;
import algorithms.ResearchAlgorithm;
import algorithms.nmcs.NMCS;
import algorithms.random.RandomAlgorithm;
import com.opencsv.exceptions.CsvValidationException;
import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import game.GameManagerFX;
import game.Mode;
import helpers.DefaultCoordinates4;
import helpers.DefaultCoordinates5;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
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
	
	private RandomAlgorithm firstRandomAlgo;
	private NMCS secondRandomAlgo;
	
	public void startFirstAlgorithm (ActionEvent event) {
		long startTime = System.currentTimeMillis();
        DataManager.setCurrRunningAlgo(0);
        firstRandomAlgo = new RandomAlgorithm();
		firstRandomAlgo.trainAlgorithm(1);
		long endTime = System.currentTimeMillis();
		double elapsedTime = (endTime - startTime) * 0.001;
		String formattedNumber = String.format("%.2f", elapsedTime);
		firstAlgoTime.setText(formattedNumber + " S");
		firstAlgoScore.setText(String.valueOf(firstRandomAlgo.getGrid().getLines().size()));
		if (Mode.toStringStatic().equals("4D") || Mode.toStringStatic().equals("4T")) displayGridfirstAlgo4DT();
		if (Mode.toStringStatic().equals("5D") || Mode.toStringStatic().equals("5T")) displayGridfirstAlgo5DT();
        firstAlgoGameGrid.setVisible(true);
        try {
			GraphicController.setScore0(DataManager.calculateStatistics(0, Mode.toStringStatic()));
		} catch (CsvValidationException e) {
			throw new IllegalStateException(e);
		} catch (NumberFormatException e) {
			throw new IllegalStateException(e);
		}
	}
	
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
        if (depth == 1) {
        	try {
    			GraphicController.setScore1(DataManager.calculateStatistics(1, Mode.toStringStatic()));
    		} catch (CsvValidationException e) {
    			throw new IllegalStateException(e);
    		} catch (NumberFormatException e) {
    			throw new IllegalStateException(e);
    		}
        }
        else if (depth == 2){
        	try {
    			GraphicController.setScore2(DataManager.calculateStatistics(2, Mode.toStringStatic()));
    		} catch (CsvValidationException e) {
    			throw new IllegalStateException(e);
    		} catch (NumberFormatException e) {
    			throw new IllegalStateException(e);
    		}
        }
        else if (depth == 3) {
        	try {
    			GraphicController.setScore3(DataManager.calculateStatistics(3, Mode.toStringStatic()));
    		} catch (CsvValidationException e) {
    			throw new IllegalStateException(e);
    		} catch (NumberFormatException e) {
    			throw new IllegalStateException(e);
    		}
        }
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

	private void displayGridsecondAlgo5DT(Grid grid) {
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
        drawLinesSecond(grid);
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
	
	private void displayGridsecondAlgo4DT(Grid grid) {
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
        drawLinesSecond(grid);
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
	
	private void drawLinesSecond(Grid grid) {
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
	
	public void setDepthOne (ActionEvent event) {
		depth = 1;
	}
	
	public void setDepthTwo (ActionEvent event) {
		depth = 2;
	}
	
	public void setDepthThree (ActionEvent event) {
		depth = 3;
	}
	
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
	
	public void switchToMenu (ActionEvent event) {
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(MenuApp.menuScene);
	    stage.show();
	}
}