package model.players;

import model.map.areas.Area;
import model.resources.Resource;

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
	
	/**
	 * Determines if the Player can afford a specified cost of resources.
	 * @param cost the number of resources to check for.
	 * @return True if the Player can afford the specified costs, false if they are unable to pay for all of the costs.
	 */
	public boolean canAfford(int[] cost);
	
	/**
	 * Get the amount of a Resource that there is.
	 * @param r the Resource to check the quantity of.
	 * @return The number of the specified Resource that this Player has.
	 */
	public int resourceCount(Resource r);
	
	/**
	 * Gather a certain amount of a Resource.
	 * @param r the Resource to collect.
	 * @param amt the amount of the Resource being collected.
	 */
	public void collectResource(Resource r, int amt);
	
	/**
	 * Consume an amount of a Resource that the Player owns, if enough are available.
	 * @param r the Resource to spend.
	 * @param amt the number of the Resource that is being used up.
	 * @return False if there are not enough of that type of resources to spend; true otherwise.
	 */
	public boolean spendResource(Resource r, int amt);

}
