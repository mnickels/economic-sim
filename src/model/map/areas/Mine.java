package model.map.areas;

import model.resources.Resource;

/**
 * A Mine improvement.
 * Increases yield of ores.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class Mine implements Improvement {

	@Override
	public int[] getCost() {
		int[] cost = new int[Resource.values().length];
		cost[Resource.MONEY.ordinal()] = 500;		// 5000 gold cost
		cost[Resource.WOOD.ordinal()] = 60;		// 600 wood cost
		cost[Resource.FOOD.ordinal()] = 50;		// 500 food cost
		return cost;
	}

	@Override
	public int[] getYield(int[] resources) {
		int[] yield = new int[Resource.values().length];
		yield[Resource.WOOD.ordinal()] = (int) (resources[Resource.WOOD.ordinal()] * 0.05);		// 5% increase in wood yield
		return yield;
	}

}
