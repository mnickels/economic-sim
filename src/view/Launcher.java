package view;

import javafx.application.Application;

/**
 * The launcher for the program.
 * 
 * @author Mike Nickels | mnickels@uw.edu
 */
public final class Launcher {
	
	/**
	 * Private constructor to prevent unwanted instantiation.
	 */
	private Launcher() {
		// do nothing
	}

	/**
	 * Main method, starting point of the program.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		Application.launch(Game.class, args);
	}

}
