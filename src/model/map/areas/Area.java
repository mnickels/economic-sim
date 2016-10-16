package model.map.areas;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores data of a specific area on the map.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public abstract class Area {
	
	/** The total number of each Resource contained in this Area. */
	private final int[] resources;
	/** The rate at which Resources are obtained from this Area. */
	private final double[] yieldRate;
	/** The Improvements that have been built in this Area. */
	private final List<Improvement> improvements;

	/**
	 * Creates a new Area with a fixed amount of total resources and an initial rate at which those resources are obtained.
	 * @param areaResources the amount of resources that this Area contains.
	 * @param yieldTurns the number of turns that areaResources should last without improvements.
	 */
	public Area(int[] areaResources, int[] yieldTurns) {
		this.resources = areaResources.clone();
		yieldRate = new double[resources.length];
		for (int i = 0; i < yieldRate.length; i++) {
			yieldRate[i] = (resources[i] / yieldTurns[i]) / resources[i];
		}
		improvements = new ArrayList<>();
	}
	
	/**
	 * Gets the resources that this Area will yield this turn.
	 * @return An array of resources yielded to the Player who owns this Area.
	 */
	public int[] yield() {
		int[] yield = new int[resources.length];
		for (int i = 0; i < yield.length; i++) {
			yield[i] = (int) (resources[i] * yieldRate[i]);
		}
		for (Improvement i : improvements) {
			int[] improvementYield = i.getYield(resources);
			for (int j = 0; j < yield.length; j++) {
				yield[j] += improvementYield[j];
			}
		}
		// check to make sure this Area has enough resources
		for (int i = 0; i < yield.length; i++) {
			if (yield[i] > resources[i]) {
				yield[i] = resources[i];
			}
			resources[i] -= yield[i];
		}
		return yield;
	}
	
	/**
	 * Adds the Improvement to this Area's list of Improvements.
	 * @param i the Improvement being built.
	 */
	public void build(Improvement i) {
		improvements.add(i);
	}
	
	/**
	 * Gets the cost of this Area.
	 * @return The cost for a Player to purchase this Area.
	 */
	public int getCost() {
		/*
		 * TODO: getCost() method
		 * either:
		 * 1.	base it on the resources in the Area at start
		 * 2.	make it a multiple of the turn yield
		 * 3.	if this should be a flat rate based on land area, pass the cost in to the constructor.
		 */
		return 0;
	}

}
