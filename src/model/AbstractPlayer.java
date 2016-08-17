package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.map.areas.Area;
import model.resources.Resource;

/**
 * Abstract implementation of a Player.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public abstract class AbstractPlayer implements Player {
	
	private final String name;
	private final Map<Resource, Integer> resources;
	private final List<Area> ownedLand;

	/**
	 * Initialize an AbstractPlayer with a given identifier name and with zero resources to start with.
	 * @param name the String to identify this Player by.
	 */
	public AbstractPlayer(String name) {
		this.name = name;
		resources = new HashMap<>();
		// set up all material types to begin with 0 units
		for (Resource m : Resource.values()) {
			resources.put(m, 0);
		}
		ownedLand = new ArrayList<>();
	}
	
	@Override
	public void acquireLand(Area a) {
		ownedLand.add(a);
	}
	
	/**
	 * Get the amount of a resource that there is.
	 * @param m the Material to check the quantity of.
	 * @return The number of the specified Material that this Player has.
	 */
	public int materialCount(Resource m) {
		return resources.get(m);
	}
	
	/**
	 * Gather a certain amount of a Material.
	 * @param m the Material to collect.
	 * @param amt the amount of the Material being collected.
	 */
	public void collectMaterial(Resource m, int amt) {
		resources.put(m, materialCount(m) + amt);
	}
	
	/**
	 * Consume an amount of a Material that the Player owns, if enough are available.
	 * @param m the Material to spend.
	 * @param amt the number of the Material that is being used up.
	 * @return False if there are not enough of that type of resources to spend; true otherwise.
	 */
	public boolean spendMaterial(Resource m, int amt) {
		if (materialCount(m) < amt) return false;
		resources.put(m, materialCount(m) - amt);
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder(String.format("%s '%s'", getClass().getSimpleName(), name));
		for (Resource m : Resource.values()) {
			s.append(String.format("\n\t%s: %d", m, materialCount(m)));
		}
		return s.toString();
	}

}
