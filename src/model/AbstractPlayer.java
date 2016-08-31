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
	
	@Override
	public boolean canAfford(int[] cost) {
		for (int i = 0; i < cost.length; i++)
			if (resources[i] < cost[i]) return false;
		return true;
	}
	
	@Override
	public int resourceCount(Resource r) {
		return resources[r.ordinal()];
	}
	
	@Override
	public void collectResource(Resource r, int amt) {
		resources[r.ordinal()] += amt;
	}
	
	@Override
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
