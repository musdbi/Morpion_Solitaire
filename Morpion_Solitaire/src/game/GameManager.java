package game;

import components.Grid;
import java.lang.System.Logger;

import java.util.Set;

public class GameManager {

//    @SuppressWarnings("unused")
//    private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
    
    private Scoreboard ranking;

    private String currentVersion;
    
    private int score;

    private String currentPlayer;
    
    private Grid board;

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
}
