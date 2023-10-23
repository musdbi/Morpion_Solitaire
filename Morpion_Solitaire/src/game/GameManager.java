package game;

import components.Grid;
import components.PlayedPoint;
import components.Point;

import java.lang.System.Logger;
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
    
    public  void play() {
        System.out.print("Enter x and y (e.g., 1, 2 or 3   4): ");
        String userInput = scanner.nextLine();
        String[] coordinates = userInput.split("[,\\s]+");
    	board.updateGrid(new PlayedPoint(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1])));
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
    	game.board.drawGrid();
    	System.out.println("Direction du point joué: " + ((PlayedPoint) game.board.getGrid().get(Objects.hash(14, 14))).getInvolvedDirections());
    	game.board.updatePlayablePoints();
    	game.play();
    	game.board.drawGrid();
    	
    	game.board.updatePlayablePoints();
    	game.play();
    	game.board.drawGrid();

    	game.board.updatePlayablePoints();
    	game.play();
    	game.board.drawGrid();

    }
}
