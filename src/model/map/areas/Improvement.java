package model.map.areas;

/**
 * An Improvement to an Area that allows for better resource acquisition.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public interface Improvement {
	
	/**
	 * Gets the cost to build this improvement.
	 * @return An int[] of resources that it will cost to build this Improvement.
	 */
	public int[] getCost();

	/**
	 * Gets the amount of yield offered by this Improvement.
	 * @param resources the total resources available in an Area.
	 * @return An int[] of resources yielded by this Improvement.
	 */
	public int[] getYield(int[] resources);

}
