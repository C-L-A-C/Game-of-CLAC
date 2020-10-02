package mapmanager;

import element.Cellule;
import element.Ressource;
import element.TypeElement;
import element.TypeRessource;
import utils.Utils;

/*
 * Descripteur de région
 */
public class Region {
	
	final static private int W = 7;		// Region Width
	final static private int H = 5;		// Region Height

	private int rx;						// Region x index
	private int ry;						// Region y index
	
	private Cellule[][] cellules;

	boolean isloaded;
	
	public Region(int x, int y){
		isloaded = false;
		this.rx = x / W;
		this.ry = y / H;
	}
	
	/*
	 * Cantor pairing function
	 * (x, y) game -> id
	 */
	static public int calcId(int x, int y) {
		// https://en.wikipedia.org/wiki/Pairing_function#Cantor_pairing_function
		x /= W; // Region index
		y /= H;
		return (x+y)*(x+y+1)/2 + y;
	}
	
	public void initFromSeed(int seed) {
		// TODO: create perlin grid witd seed2 = f(seed, id);
		cellules = new Cellule[W][H];
		for (int x = 0; x < cellules.length; x++) {
			for (int y = 0; y < cellules[x].length; y++) {				
				TypeElement[] typesElements = TypeElement.values();
				Ressource ressource = null;
				if (Math.random() < 0.3) {
					TypeRessource[] typesRessources = TypeRessource.values();
					ressource = new Ressource(Utils.random(typesRessources));
				}
				cellules[x][y] = new Cellule(Utils.random(typesElements), ressource);
			}
		}
		isloaded = true;
	}
	
	public void initFromFile(String path) {
		// TODO
		isloaded = true;
	}
	
	public void writeToFile(String path) {
		// TODO
	}
	
	public boolean isLoaded() {
		return isloaded;
	}
	
	public Cellule get(int x, int y) {
		return cellules[x - rx * W][y - ry * H];
	}
	
}
