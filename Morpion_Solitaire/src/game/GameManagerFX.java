package game;

import components.Grid;
import components.Line;
import components.PlayedPoint;
import components.Point;
import exceptions.IllegalPlayedPointException;
import exceptions.NotALineException;
import exceptions.OutOfGridException;
import game.Mode;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameManagerFX {

//    @SuppressWarnings("unused")
//    private static final Logger LOGGER = LoggerFactory.getLogger(GameManager.class);
    
    private static Scoreboard ranking;

    private String currentVersion;
    
    private int score;

    private String currentPlayer;
    
    private Grid board;
    
	private Scanner scanner;
	
	private Mode mode;
	
    private static GameManagerFX instance;


    /**
     * Constructs a GameManager object with one player
     *
     * @param currentVersion will specify which version of the game we are playing in (5T or 5D)
     */
    public GameManagerFX(){
    	mode = new Mode();
    	board = new Grid();
    	ranking = new Scoreboard();
    	currentPlayer = "player";
    }
    
    public static GameManagerFX getInstance() {
        if (instance == null) {
            instance = new GameManagerFX();
        }
        return instance;
    }

    /**
     * Sets up the game
     */
    public void setupGame(){
    	score = 0;
        board.initGrid();
        this.board.updatePlayablePoints();
        //this.board.drawGrid();
    }
    
    /**
     * This method launch the game with the selected mode.
     * 
     * @param mode
     */
    public void launchGame(){
    	while (!this.board.getPlayablePoints().isEmpty()) {
    		this.play();
        	this.board.updatePlayablePoints();
        	System.out.println("Point jouables et leurs lignes: " + this.board.getPlayablePoints());
        	System.out.println("Coups possible: " + this.board.getPossibleMoves());

        	this.board.drawGrid();
    	}
    }
    
    public void endGame(){
        this.score = this.board.getLines().size();
        System.out.println("Partie terminée, score: " + this.score);
        ranking.addScore(currentPlayer, score);
    }
    
    public void play() {
    	PlayedPoint playedPoint = playPoint();
    	Line playedLine = chooseLine(playedPoint);
    	board.updateGrid(playedPoint, playedLine);
    }
    
    public void chooseMode() {
    	int number;
    	String type="";
    	while (true) {
    		System.out.println("Choose the mode among these: 4 D, 4 T, 5 D, 5 T:");
            String userInput = scanner.nextLine();
            String[] coordinates = userInput.replaceAll(" ", "").split("");
            try {
            	if (coordinates.length != 2) throw new ArrayIndexOutOfBoundsException("You have to enter one number and one letter");
            	number = Integer.parseInt(coordinates[0]);
            	Mode.setNumber(number);
            	type = coordinates[1];
            	Mode.setType(type);
            	break;
            } catch (NumberFormatException e1) {
                System.out.println("The number of the mode can not be a character");
            } catch (ArrayIndexOutOfBoundsException e2) {
            	System.out.println(e2.getMessage());
            } catch (IllegalArgumentException e3) {
                System.out.println(e3.getMessage());
            }
    	}
    }
    
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
    
    public Line chooseLine(PlayedPoint playedPoint) {
    	int line = 0;
    	List<Line> playableLines = new ArrayList<>(this.board.getPlayablePoints().get(playedPoint));
    	if (playableLines.size() > 1){
    		System.out.println("Which line do you want to play for " + playedPoint.toString() + " ?\n" + "Give the number of the line\n");
        	for (int i =0; i < playableLines.size(); i++) {
        		System.out.println("Line "+ (i + 1) +": " +playableLines.get(i));
        	}
    	}
    	while (playableLines.size() > 1) {
            String userInput = scanner.nextLine();
            try {
            	line = Integer.parseInt(userInput) - 1;
            	if (!(0 <= line && line <= playableLines.size() - 1)) throw new NotALineException();
                break;
            } catch (NumberFormatException e1) {
                System.out.println("Please enter a number of the list above.");
            } catch (NotALineException e2) {
                System.out.println(e2.getMessage());
            }
    	}
    	return playableLines.get(line);
    }
    
    public void playAt(int x, int y) {
        // Vérifiez si le point est jouable
        Point point = new Point(x, y);
        if (!board.getPlayablePoints().containsKey(point)) {
            // Gérez le cas où le point n'est pas jouable, par exemple, afficher une alerte
            return;
        }

        // Jouez le point
        PlayedPoint playedPoint = new PlayedPoint(point, board.getLines().size() + 1);
        Line playedLine = chooseLine(playedPoint);
        board.updateGrid(playedPoint, playedLine);
        score = board.getLines().size(); // Mettez à jour le score
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
	
	public void setPlayerName(String name) {
		currentPlayer = name;
	}

    public void displayRanking(){
        ranking.write();
    }

    public static void main(String[] args) {
    	GameManagerFX game = new GameManagerFX();
    	game.setupGame();
    	game.launchGame();
    	game.endGame();
    }
}