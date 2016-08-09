package controller;

import model.Game;

/**
 * Controls the flow of the simulation.
 * Handles player turns and other features that need to be updated in a time-sensitive manner.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class GameController implements Runnable {
	
	private final Game game;
	
	private boolean running;
	private int targetUDS = 10;

	/**
	 * Create a new GameController.
	 */
	public GameController() {
		game = new Game();
	}
	
	/**
	 * Executes a single turn, or "tick," of the simulation.
	 */
	public void turn() {
		game.turn();
	}

	@Override
	public void run() {
		running = true;
		
		long lastTime = System.nanoTime();
		double nsPerFrame = 1000000000 / (double) targetUDS;
		
		int turns = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerFrame;
			lastTime = now;
			
			while (delta >= 1) {
				turns++;
				turn();
				delta -= 1;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(turns + " turns");
				turns = 0;
			}
		}
	}

}
