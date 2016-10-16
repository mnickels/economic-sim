package model.map;

import java.util.Random;

import javafx.scene.image.Image;

public class BiomeMap extends AbstractMap {
	
	private final Biome[][] biomes;
	private float[][] height;
	private final Random rand;
	
	public BiomeMap(float[][] heights) {
		this(heights, System.nanoTime());
	}
	
	public BiomeMap(float[][] heights, long seed) {
		super(heights[0].length, heights.length);
		height = heights;
		rand = new Random(seed);
		biomes = new Biome[getHeight()][getWidth()];
	}
	
	
	@Override
	public Image drawMap() {
		return null;
	}
	
	
	public void biomes(){
		for(int y = 0; y < getHeight(); y++){
			for(int x = 0; x < getWidth(); x++){
				if(height[y][x] > .7)
					biomes[y][x] = Biome.MOUNTAINS;
				if(height[y][x] <= 0 )
					biomes[y][x] = Biome.OCEAN;
			}
		}
	}

}
