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
		cost[Resource.MONEY.ordinal()] = 5000;		// 5000 gold cost
		cost[Resource.WOOD.ordinal()] = 600;		// 600 wood cost
		cost[Resource.FOOD.ordinal()] = 500;		// 500 food cost
		return cost;
	}

	@Override
	public int[] getYield(int[] resources) {
		// TODO
		return null;
	}

}
