package model.map;

import model.resources.Resource;

public enum Biome {
	DESERT(Resource.OIL),
	MOUNTAINS(Resource.ORES),
	OCEAN(Resource.MONEY);
	
	private final Resource[] resources;
	
	private Biome(Resource... res){
		resources = res;
	}
	
	public Resource[] yieldTypes(){
		return resources.clone();
	}
}
