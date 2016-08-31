package model.map;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * Represents a height map.
 * 
 * @author Youcef Bennour | ybennour@uw.edu
 * @author Mike Nickels | mnickels@uw.edu
 *
 */
public class HeightMap implements Map {
	
	
	private final float[][] noise;
	
	private final int width;
	
	private final int height;
	/**
	 * 
	 * @param simplexNoise
	 */
	public HeightMap(float[][] simplexNoise) {
		
		width = simplexNoise.length;
		height = simplexNoise[0].length;
		
		//Making a deep copy of the simplexNoise.
		noise = new float[height][width];
		
		//Copying the y values.
		for(int y = 0; y < height; y++){
			//Copying the x values. 
			for(int x = 0; x < width; x++){
				noise[y][x] = simplexNoise[y][x];
			}
		}
	}

	@Override
	public Image drawMap() {
		WritableImage imageMap = new WritableImage(width, height);
		
		//Draws the pixel in the image.
		PixelWriter px = imageMap.getPixelWriter();
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				px.setColor(x, y, getColor(noise[y][x]));
			}
		}
		return imageMap;
	}

	private Color getColor(float height){
		int seaLevel = 0;
		if(height <= -0.2)
			return Color.BLUE;
		if (height <= seaLevel)
			return Color.CYAN;
		if(height <= -0.1)
			return Color.YELLOW;
		if (height <= -0.3)
			return Color.LIGHTGREEN;
		if (height <= -0.5)
			return Color.DARKGREEN;
		if (height <= -0.8)
			return Color.GRAY;
		return Color.WHITE;
	}
	
	public float getHeight(int x, int y){
		return noise[y][x];
	}
}
