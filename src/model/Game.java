package model;

import model.players.ComputerPlayer;
import model.players.HumanPlayer;
import model.players.Player;

/**
 * Contains all necessary game model data of a single game.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class Game {
	
	private static final Game instance = new Game();
	
	private Player[] players;
	private int turn;

	/**
	 * Creates a new Game model.
	 */
	private Game() {
		players = new Player[7];
		players[0] = new HumanPlayer("Greg");
		for (int i = 1; i < 7; i++) {
			players[i] = new ComputerPlayer("COM " + i);
		}
		turn = 0;
	}
	
	/**
	 * Executes a single turn, or "tick," of the simulation.
	 * 
	 */
	public void turn() {
		System.out.println("Turn " + turn++);
		
		for (Player p : players) {
			p.turn();
		}
	}
	
	public static Game getInstance() {
		return instance;
	}

}
