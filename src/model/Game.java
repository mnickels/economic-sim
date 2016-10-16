package model;

import model.map.BiomeMap;
import model.map.HeightMap;
import model.map.Map;
import model.map.MapType;
import model.map.SimplexNoise;
import model.players.ComputerPlayer;
import model.players.HumanPlayer;
import model.players.Player;

/**
 * Contains all necessary game model data of a single game.
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public class Game {
	
	private static final int WIDTH = 4000;
	private static final int HEIGHT = 3000;
	private static final int OCTAVES = 7;
	private static final float ROUGHNESS = 0.4f;
	private static final float FREQUENCY = 0.001f;
	
	private static final Game instance = new Game();
	
	private Player[] players;
	private int turn;
	private Map[] mapTypes;

	/**
	 * Creates a new Game model.
	 */
	private Game() {
		players = new Player[7];
		players[0] = new HumanPlayer("Greg");
		for (int i = 1; i < players.length; i++) {
			players[i] = new ComputerPlayer("COM " + i);
		}
		
		generateMaps();
		
		turn = 0;
	}
	
	private void generateMaps() {
		mapTypes = new Map[MapType.values().length];
		
		SimplexNoise noiseGen = new SimplexNoise();
		float[][] noise = noiseGen.generateOctavedSimplexNoise(WIDTH, HEIGHT, OCTAVES, ROUGHNESS, FREQUENCY);
		
		mapTypes[MapType.AREA_MAP.ordinal()] = new HeightMap(noise);
		mapTypes[MapType.HEIGHT_MAP.ordinal()] = new HeightMap(noise);
		mapTypes[MapType.BIOME_MAP.ordinal()] = new BiomeMap(noise);
	}
	
	/**
	 * Executes a single turn, or "tick," of the simulation.
	 * 
	 */
	public void turn() {
		System.out.println("Turn " + turn++);
		
		for (Player p : players) {
			p.turn();
		}
	}
	
	public Player getLocalPlayer() {
		return players[0];
	}
	
	public Map getMap(MapType type) {
		return mapTypes[type.ordinal()];
	}
	
	public static Game getInstance() {
		return instance;
	}

}
