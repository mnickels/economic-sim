package model;

/**
 * Contains all necessary game model data of a single game.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class Game {
	
	private static final Game instance = new Game();
	
	private Player[] players;

	/**
	 * Creates a new Game model.
	 */
	private Game() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Executes a single turn, or "tick," of the simulation.
	 * 
	 */
	public void turn() {
		// TODO call all other "turn()" methods that need to be called in a single simulation tick
	}
	
	public static Game getInstance() {
		return instance;
	}

}
