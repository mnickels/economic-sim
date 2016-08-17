package model;

import model.map.areas.Area;

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
	
	/**
	 * Add an Area to the list of Areas claimed by this Player.
	 * @param a the Area being acquired.
	 */
	public void acquireLand(Area a);
	
	public boolean canAfford(int[] cost);

}
