package model.players;

/**
 * Defines different AI proclivities.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public enum AIType {

	GREEDY,		// always takes the best for himself, regardless of expense
	FRUGAL,		// spends less money
	RECKLESS,	// makes risky decisions, depletes land quicker
	MODERATE	// moderate
	
}
