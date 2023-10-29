package game;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import helpers.IllegalPlayedPointException;
import helpers.OutOfGridException;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

public class GameManager {

//    @SuppressWarnings("unused")
//    private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
    
    private Scoreboard ranking;

    private String currentVersion;
    
    private int score;

    private String currentPlayer;
    
    private Grid board;
    
	private Scanner scanner;


    /**
     * Constructs a GameManager object with one player
     *
     * @param currentVersion will specify which version of the game we are playing in (5T or 5D)
     */
    public GameManager(int version, String player){
    	if (player .isEmpty()) throw new IllegalArgumentException("Please type a valid name.");
        if (version != 1) {
        	System.out.println("Created a game in 5D version");
        	currentVersion = "5D";
        }
        else if (version ==0) {
        	System.out.println("Created a game in 5T version");
        	currentVersion = "5T";
        }
        this.currentPlayer = player;
        this.scanner= new Scanner(System.in);
    }

    /**
     * Sets up the game
     */
    public void setupGame(){
        board = new Grid();
        board.initGrid();
        score = 0;
        ranking = new Scoreboard();
        if (currentVersion == "5D") {
        	startGameD();
        }
        else {
        	startGameT();
        }
    }
    
    public void play() {
    	int x, y = 0;
    	while (true) {
    		System.out.println("Enter x and y (e.g : 1, 3 or 1 3) : ");
            String userInput = scanner.nextLine();
            String[] coordinates = userInput.split("[,\\s]+");
            try {
            	x = Integer.parseInt(coordinates[0]);
                y = Integer.parseInt(coordinates[1]);
                if (coordinates.length != 2) throw new ArrayIndexOutOfBoundsException("Please type 2 coordinates.");
                if (x < 0 || y < 0) throw new OutOfGridException("Coordinates cannot be negative.");
                if (x >= board.getSize() || (y >= board.getSize())) throw new OutOfGridException("The point is outside the grid.");
                if (board.getPlayablePoints().get(new Point (x,y))== null) throw new IllegalPlayedPointException ("This point is not playable");
                break;
            } catch (NumberFormatException e1) {
                System.out.println("Please enter valid integer coordinates.");
            } catch (OutOfGridException e2) {
            	System.out.println(e2.getMessage());
            } catch (ArrayIndexOutOfBoundsException e3) {
            	System.out.println(e3.getMessage());
            } catch (IllegalPlayedPointException e4) {
            	System.out.println(e4.getMessage());
            }
    	}
    	PlayedPoint playedPoint = new PlayedPoint(x,y);
    	if (x == 12 && y == 11) {
    		System.out.println("Ligne pour 12 11: " + this.board.getPlayablePoints().get(playedPoint));
    	}
    	List<Line> playableLines = new ArrayList<>(this.board.getPlayablePoints().get(playedPoint));
    	board.updateGrid(playedPoint, playableLines.get(0));
    }

    /**
     * Starts the game in 5T version
     */
    public void startGameT(){
        
    }
    
    /**
     * Starts the game in 5D version
     */
    public void startGameD(){
        
    }
    
    public void endParty(int score){
        this.score = score;
        ranking.addScore(currentPlayer, score);
    }
    
    public int getScore() {
		return this.score;
	}
	
	public String getPlayerName() {
		return this.currentPlayer;
	}
	
	public Grid getGrid() {
		return this.board;
	}
	
	public String getVersion() {
		return this.currentVersion;
	}

    public void displayRanking(){
        ranking.write();
    }
    
    public static void main(String[] args) {
    	GameManager game = new GameManager(1, "Pierre");
    	game.board = new Grid();
    	game.board.initGrid();
    	game.board.updatePlayablePoints();
    	game.board.drawGrid();
    	game.play();
    	
    	while(!game.board.getPlayablePoints().isEmpty()) {
    		game.board.updatePlayablePoints();
        	System.out.println(game.board.getPlayablePoints());
    		game.board.drawGrid();
        	game.play();
        	
    	}
    }
}
