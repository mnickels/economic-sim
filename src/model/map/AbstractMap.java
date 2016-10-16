package model.map;

import javafx.scene.image.Image;

public abstract class AbstractMap implements Map {

	private final int width;
	private final int height;
	
	protected AbstractMap(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	@Override
	public abstract Image drawMap();
	
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

}
