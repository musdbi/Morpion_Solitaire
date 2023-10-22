package game;

import components.Grid;
import java.lang.System.Logger;
import java.util.Set;

public class GameManager {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
    
    private Scoreboard classement;

    private String currentVersion;
    
    private int score;

    private String currentPlayer;
    
    private Grid board;

    /**
     * Constructs a GameManager object with one player
     *
     * @param currentVersion will specify which version of the game we are playing in (5T or 5D)
     */
    public GameManager(Integer version){
        if (version != 1) {
        	LOGGER.info("Created a game in 5D version");
        	currentVersion = "5D";
        }
        else if (version ==0) {
        	LOGGER.info("Created a game in 5T version");
        	currentVersion = "5T";
        }
    }

    /**
     * Sets up the game
     */
    public void setupGame(){
        board = new Grid ();
        score = 0;
        classement = new Scoreboard();
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
        classement.addScore(currentPlayer, score);
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

    public String displayRanking(){
        classement.write();
    }
    
}
