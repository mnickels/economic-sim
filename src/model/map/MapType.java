/**
 * 
 */
package model.map;

/**
 *
 * @author Mike Nickels | mnickels@uw.edu
 */
public enum MapType {
	
	// REFLECT ALL ADDITIONAL MAP TYPE ADDS IN GAME MAP GEN METHOD
	AREA_MAP,
	HEIGHT_MAP,
	BIOME_MAP;
	
	public static MapType getType(String s) {
		for (MapType t : values()) {
			if (t.toString().equalsIgnoreCase(s)) {
				return t;
			}
		}
		return null;
	}

}
