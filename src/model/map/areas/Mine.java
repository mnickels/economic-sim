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
		cost[Resource.MONEY.ordinal()] = 500;		// 500 gold cost
		cost[Resource.WOOD.ordinal()] = 60;		// 60 wood cost
		cost[Resource.FOOD.ordinal()] = 50;		// 50 food cost
		return cost;
	}

	@Override
	public int[] getYield(int[] resources) {
		int[] yield = new int[Resource.values().length];
		yield[Resource.ORES.ordinal()] = (int) (resources[Resource.ORES.ordinal()] * 0.05);		// 5% increase in ores yield
		return yield;
	}

}
