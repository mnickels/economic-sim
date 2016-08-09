package model;

/**
 * The abstract model for a player of the simulation game.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public interface Player {
	
	/**
	 * Called to execute a single turn for a player.
	 */
	public void turn();

}
