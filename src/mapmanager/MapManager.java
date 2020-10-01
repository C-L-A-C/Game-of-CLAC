package mapmanager;


import element.Cellule;

import java.util.*;

import config.Config;
import config.ConfigKey;

/*
 * Server MAP manager
 */
public class MapManager{
	final static private String MAP_FOLDER = Config.readString(ConfigKey.MAP_DIRECTORY);
	final static private String MAP_FILE_DESC = new String(MAP_FOLDER + "RegionsIds.txt");

	private int seed;
	
	private Map<Integer, Region> hashmap; 
	
	public MapManager(int seed) {
		this.seed = seed;
		this.hashmap = new HashMap<>();
		/* 
		 * TODO 
		 * for id in MAP_FILE_DESC:
		 * 		region = hashmap.put(Region.calcId(x, y),  new Region(id));
		 */
	}
	
	public Cellule get(int x, int y) {
		int key = Region.calcId(x, y);
		Region region;
		if (hashmap.containsKey(key)){
			region = hashmap.get(key);
			if(!region.isLoaded()) {
				region.initFromFile(MAP_FOLDER);
			}
		}else {
			region = new Region(x, y);
			region.initFromSeed(seed);
			region.writeToFile(MAP_FOLDER);
			/* TODO : MAP_FILE_DESC << regionid */
			hashmap.put(key, region);
			System.out.println("Region created :" + key);

		}
		return region.get(x, y);
	}
	
	static public int calcId(int x, int y) {
		return Region.calcId(x, y);
	}
	
	private void openRegion(int regionId) {
		// TODO
	}
	
	private void closeRegion(int regionId) {
		// TODO
	}
	
}
