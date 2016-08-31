package model;

import java.util.ArrayList;
import java.util.List;

import model.map.areas.Area;
import model.resources.Resource;

/**
 * Abstract implementation of a Player.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public abstract class AbstractPlayer implements Player {
	
	private final String name;
	private final int[] resources;
	private final List<Area> ownedLand;

	/**
	 * Initialize an AbstractPlayer with a given identifier name and with zero resources to start with.
	 * @param name the String to identify this Player by.
	 */
	public AbstractPlayer(String name) {
		this.name = name;
		resources = new int[Resource.values().length];
		ownedLand = new ArrayList<>();
	}
	
	@Override
	public void acquireLand(Area a) {
		ownedLand.add(a);
	}
	
	/**
	 * Get the amount of a Resource that there is.
	 * @param r the Resource to check the quantity of.
	 * @return The number of the specified Resource that this Player has.
	 */
	public int resourceCount(Resource r) {
		return resources[r.ordinal()];
	}
	
	/**
	 * Gather a certain amount of a Resource.
	 * @param m the Resource to collect.
	 * @param amt the amount of the Resource being collected.
	 */
	public void collectResource(Resource r, int amt) {
		resources[r.ordinal()] += amt;
	}
	
	/**
	 * Consume an amount of a Material that the Player owns, if enough are available.
	 * @param r the Material to spend.
	 * @param amt the number of the Material that is being used up.
	 * @return False if there are not enough of that type of resources to spend; true otherwise.
	 */
	public boolean spendMaterial(Resource r, int amt) {
		if (resourceCount(r) < amt) return false;
		resources[r.ordinal()] -= amt;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(String.format("%s '%s'", getClass().getSimpleName(), name));
		for (Resource r : Resource.values()) {
			s.append(String.format("\n\t%s: %d", r, resourceCount(r)));
		}
		return s.toString();
	}

}
