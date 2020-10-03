package mapmanager;

import java.util.Random;

import element.Cellule;
import element.Ressource;
import element.TypeElement;
import element.TypeRessource;
import utils.Utils;

/*
 * Descripteur de région
 */
public class Region {
	
	final static private int W = 16;		// Region Width
	final static private int H = 16;		// Region Height

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
	
	public void initFromSeed(long seed) {
		
		// create perlin grid
		final int PERLIN_PERIOD = 8;
		double[][][] perlinGrid = new double[W/PERLIN_PERIOD + 1][H/PERLIN_PERIOD + 1][2];
		for (int ix = 0; ix < perlinGrid.length; ix++) {
			for (int iy = 0; iy < perlinGrid[ix].length; iy++) {	
				
				// ReaL positions
				int rlx = (ix) * PERLIN_PERIOD + rx * W;
				int rly = (iy) * PERLIN_PERIOD + ry * H;
				//set generator with seed  witd seed2 = f(seed, position) = seed XOR pairing(x, y);
				Random rand = new Random(seed ^ ((rlx+rly)*(rlx+rly+1)/2 + rly));
				rand.nextDouble();	// skip for chaos
				double angle = rand.nextDouble() * 2 * Math.PI;
				perlinGrid[ix][iy][0] = Math.cos(angle);
				perlinGrid[ix][iy][1] = Math.sin(angle);
			}
		}
		
		cellules = new Cellule[W][H];
		TypeElement[] typesElements = TypeElement.values();
		TypeRessource[] typesRessources = TypeRessource.values();
		for (int x = 0; x < cellules.length; x++) {
			for (int y = 0; y < cellules[x].length; y++) {				
				int test = (perlin(perlinGrid, (double)y/PERLIN_PERIOD, (double)x/PERLIN_PERIOD) < 0.5) ? 0 : 1;
				cellules[x][y] = new Cellule(typesElements[test], new Ressource(typesRessources[test]));
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
	 // Function to linearly interpolate between a0 and a1
	 // Weight w should be in the range [0.0, 1.0]
	 private double lerp(double a0, double a1, double w) {
	     return (1.0 - w)*a0 + w*a1;
	 }
	 
	 private double smoothlerp(double a0, double a1, double w) {
		 // https://mrl.nyu.edu/~perlin/paper445.pdf
		 return lerp(a0, a1, w * w * w * (10 + w * (-15 + w * 6)));
	 }
	 
	 // Computes the dot product of the distance and gradient vectors.
	 private double dotGridGradient(double gradient[][][], int ix, int iy, double x, double y) {
	 
	     // Compute the distance vector
	     double dx = x - (double)ix;
	     double dy = y - (double)iy;
	 
	     // Compute the dot-product
	     return (dx*gradient[iy][ix][0] + dy*gradient[iy][ix][1]);
	 }
	 
	 // Compute Perlin noise at coordinates x, y
	 private double perlin(double gradient[][][], double x, double y) {
	 
	     // Determine grid cell coordinates
	     int x0 = (int)Math.floor(x);
	     int x1 = x0 + 1;
	     int y0 = (int)Math.floor(y);
	     int y1 = y0 + 1;
	 
	     // Determine interpolation weights
	     // Could also use higher order polynomial/s-curve here
	     double sx = x - (double)x0;
	     double sy = y - (double)y0;
	 
	     // Interpolate between grid point gradients
	     double n0, n1, ix0, ix1, value;
	     n0 = dotGridGradient(gradient, x0, y0, x, y);
	     n1 = dotGridGradient(gradient, x1, y0, x, y);
	     ix0 = smoothlerp(n0, n1, sx);
	     n0 = dotGridGradient(gradient, x0, y1, x, y);
	     n1 = dotGridGradient(gradient, x1, y1, x, y);
	     ix1 = smoothlerp(n0, n1, sx);
	     value = smoothlerp(ix0, ix1, sy);
	 
	     return (value + 1)/2;
	 }
	
}
