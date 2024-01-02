package game;

import application.GridController;
import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import helpers.exceptions.IllegalPlayedPointException;
import helpers.exceptions.OutOfGridException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class GameManagerFX {

//    @SuppressWarnings("unused")
//    private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
    
    private static Scoreboard ranking;

    private String currentVersion;
    
    private int score;

    private String currentPlayer;
    
    private Grid board;
    
	private Scanner scanner;
	
    private static GameManagerFX instance;


    /**
     * Constructs a GameManagerFX object. Initializes the game with a default player and an empty grid.
     */
    
    public GameManagerFX(){
    	board = new Grid();
    	ranking = new Scoreboard();
    	currentPlayer = "player";
    }
    
    /**
     * Gets the single instance of GameManagerFX, following the Singleton design pattern.
     *
     * @return The single instance of GameManagerFX.
     */
    
    public static GameManagerFX getInstance() {
        if (instance == null) {
            instance = new GameManagerFX();
        }
        instance.resetGame();
        return instance;
    }

    /**
     * Sets up the game by initializing the grid and updating playable points.
     */
    
    public void setupGame(){
    	score = 0;
        board.initGrid();
        this.board.updatePlayablePoints();
    }
    
    /**
     * Launches the game in the selected mode and manages the game flow.
     */
    
    public void launchGame(){
    	while (!this.board.getPlayablePoints().isEmpty()) {
    		this.play();
        	this.board.updatePlayablePoints();
        	System.out.println("Point jouables et leurs lignes: " + this.board.getPlayablePoints());
        	System.out.println("Coups possible: " + this.board.getPossibleMoves());

        	System.out.println(this.board);;
    	}
    }
    
    /**
     * Ends the game, calculates the final score, and updates the leaderboard.
     */

    public void endGame(){
        this.score = this.board.getLines().size();
        ranking.addScore(currentPlayer, score);
        ranking.append();
        ranking.sort();
        ranking.clear();
    }
    
    /**
     * Resets the game grid and score to start a new game.
     */
    
    public void resetGame() {
        score = 0;
        board = new Grid();
        board.initGrid();
        board.updatePlayablePoints();
    }
    
    /**
     * Manages a game turn by prompting the player to play a point and choose a line.
     */

    public void play() {
    	PlayedPoint playedPoint = playPoint();
    	Line playedLine = chooseLine(playedPoint);
    	board.updateGrid(playedPoint, playedLine);
    }
    
    /**
     * Prompts the player to enter a point to play and validates the input.
     *
     * @return The played point.
     */
    
    public PlayedPoint playPoint() {
    	int x, y = 0;
    	while (true) {
    		System.out.println("Enter x and y (e.g : 1, 3 or 1 3) : ");
            String userInput = scanner.nextLine();
            String[] coordinates = userInput.split("[,\\s]+");
            try {
            	if (coordinates.length != 2) throw new ArrayIndexOutOfBoundsException("Please type 2 coordinates.");
            	x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
                if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
                if (x >= Grid.getSize() || (y >= Grid.getSize())) throw new OutOfGridException("The point is outside the grid.");
                if (board.getPlayablePoints().get(new Point (x,y))== null) throw new IllegalPlayedPointException ("This point is not playable");
                break;
            } catch (NumberFormatException e1) {
                System.out.println("Please enter valid integer number.");
            } catch (OutOfGridException e2) {
            	System.out.println(e2.getMessage());
            } catch (ArrayIndexOutOfBoundsException e3) {
            	System.out.println(e3.getMessage());
            } catch (IllegalPlayedPointException e4) {
            	System.out.println(e4.getMessage());
            }
    	}
    	return new PlayedPoint(this.board.getPoint(x, y), this.board.getLines().size() + 1); // Adding number of lines + 1 for played point id because lines of grid have not been updtated yet
    }
    
    /**
     * Allows the player to choose a line for the played point.
     *
     * @param playedPoint The played point.
     * @param grid The grid controller for UI update.
     * @return The chosen line for the played point.
     */
    
    public Line chooseLine(PlayedPoint playedPoint, GridController grid) {
        List<Line> playableLines = new ArrayList<>(this.board.getPlayablePoints().get(playedPoint));
        if (playableLines.size() <= 1) {
            return playableLines.isEmpty() ? null : playableLines.get(0);
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose a Line");
        alert.setHeaderText("Multiple lines available for point : " + playedPoint.toStringBis());
        alert.setContentText("Choose one of the following lines : ");
        alert.getButtonTypes().clear();
        
        String[] tableauDeCouleurs = {
                "#FF0000", "#42FF00", "#0017FF", "#FFF000", "#FF8F00", "#FF00D8", "#00F0FF", "#FF009E", "#686868", "#000000"
            };
        
        String[] tableauDeCouleursBis = {
                "RED", "GREEN", "BLUE", "YELLOW", "ORANGE",
                "MAGENTA", "CYAN", "PINK", "LIGHT_GRAY", "BLACK"
            };
        
        int j = 0;
        
        for (Line line : playableLines) {
        	
            ButtonType buttonType = new ButtonType(tableauDeCouleursBis [j]);
            alert.getButtonTypes().add(buttonType);
            
            for (Point point : line.getEndsOfLine()) {
            	if (!point.equals(playedPoint)) grid.findButtonInGrid(grid.getGameGrid(), point.getX(),point.getY()).setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-text-fill: " + tableauDeCouleurs[j]);
            }
            
            j+=1;
         }   
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            for (int i = 0; i < playableLines.size(); i++) {
                if (result.get() == alert.getButtonTypes().get(i)) {
                    return playableLines.get(i);
                }
            }
        }

        return null;
    }
    
    /**
     * Overloaded method of chooseLine without the grid controller.
     *
     * @param playedPoint The played point.
     * @return The chosen line for the played point.
     */
    
    public Line chooseLine(PlayedPoint playedPoint) {
        List<Line> playableLines = new ArrayList<>(this.board.getPlayablePoints().get(playedPoint));
        if (playableLines.size() <= 1) {
            return playableLines.isEmpty() ? null : playableLines.get(0);
        }
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Choose a Line");
        alert.setHeaderText("Multiple lines available for point : " + playedPoint.toStringBis());
        alert.setContentText("Choose one of the following lines : ");
        alert.getButtonTypes().clear();
        
        for (Line line : playableLines) {
            String lineDescription = "Line " + (playableLines.indexOf(line) + 1) + " : " + lineToString(line);
            ButtonType buttonType = new ButtonType(lineDescription);
            alert.getButtonTypes().add(buttonType);
        }
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            for (int i = 0; i < playableLines.size(); i++) {
                if (result.get() == alert.getButtonTypes().get(i)) {
                    return playableLines.get(i);
                }
            }
        }

        return null;
    }

    private String lineToString(Line line) {
        StringBuilder sb = new StringBuilder();
        for (Point point : line.getPoints()) {
            sb.append("(").append(point.getX()).append(",").append(point.getY()).append(") ");
        }
        return sb.toString();
    }
    
    /**
     * Plays a move at a specific position on the grid.
     *
     * @param x The X-coordinate of the played point.
     * @param y The Y-coordinate of the played point.
     * @param grid The grid controller for UI update.
     */
    
    public void playAt(int x, int y, GridController grid) {
        Point point = new Point(x, y);
        if (!board.getPlayablePoints().containsKey(point)) {
            return;
        }
        PlayedPoint playedPoint = new PlayedPoint(point, board.getLines().size() + 1);
        Line playedLine = chooseLine(playedPoint, grid);
        board.updateGrid(playedPoint, playedLine);
        score = board.getLines().size();
    }
    
    public String getScore() {
		return String.valueOf(this.score);
	}
	
	public String getPlayerName() {
		return this.currentPlayer;
	}
	
	public Grid getGrid() {
		return this.board;
	}
	
	public static Scoreboard getSB() {
		return ranking;
	}
	
	public String getVersion() {
		return this.currentVersion;
	}
	
	public void setPlayerName(String currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
    public void displayRanking(){
        ranking.write();
    }
}
