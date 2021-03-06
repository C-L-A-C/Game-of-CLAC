package jeu;

import collision.Rectangle;
import element.Cellule;
import element.Ressource;
import element.TypeRessource;
import element.TypeElement;
import processing.core.PApplet;
import utils.Utils;
import mapmanager.MapManager;

public class Carte {

	public static final int GRID_W = 20, GRID_H = 20;
	private long w, h;
	private Cellule[][] cellules;

	private MapManager mapManager;
	
	
	public Carte(long w, long h) {
		this.w = w;
		this.h = h;
		mapManager = new MapManager(0);
		//cellules = genererZoneCarte(new Rectangle(0, 0, w, h));
		
	}

	public void afficher(PApplet p, Rectangle zone) {
		int gridX = (int) (zone.getX() / GRID_W), gridY = (int) (zone.getY() / GRID_H);
		int gridW = (int) Math.ceil(zone.getW() / GRID_W), gridH = (int) Math.ceil(zone.getH() / GRID_H);
		
		//Compensation pour x et y : si on est au milieu d'une tile, on doit afficher une tile en plus pur completer le bloc
		int xComp = (int) (zone.getX()) % GRID_W == 0 ? 0 : 1, yComp = (int) (zone.getY()) % GRID_H == 0 ? 0 : 1;
		
		for (int x = gridX; x < gridX + gridW + xComp && x < w; x++) {
			for (int y = gridY; y < gridY + gridH + yComp && y < h; y++) {
				switch (mapManager.get(x, y).getTypeElement()) {
				case TERRE:
					p.fill(64, 9, 3);
					break;
				case MONTAGNE:
					p.fill(90, 90, 90);
					break;
				case HERBE:
					p.fill(0, 130, 10);
					break;
				}
				p.rect(x * GRID_W, y * GRID_H, GRID_W, GRID_H);
				//tracé des ressources
				if (mapManager.get(x, y).getRessource() != null) {
					switch (mapManager.get(x, y).getRessource().getType()) {
					case PETROLE:
						p.fill(128, 128, 128);
						break;
					case CHARBON:
						p.fill(0, 0, 0);
						break;
					}
					p.ellipse(x * GRID_W  + GRID_W / 2, y * GRID_H + GRID_H / 2, GRID_W / 2, GRID_H / 2);
				}
				// DEBUG Regions
				int k = MapManager.calcId(x, y) * (MapManager.calcId(x, y) + 125)  % 255;
				p.fill((float) k, 125, 255-k, (float)125);
				p.rect(x * GRID_W, y * GRID_H, GRID_W, GRID_H);
			}
		}
	}

	private Cellule[][] genererZoneCarte(Rectangle zone) {
		Cellule[][] cellules = new Cellule[(int) zone.getW()][(int) zone.getH()];
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
		return cellules;
	}
	
	public Cellule getCellule(GridPosition pos)
	{
		return mapManager.get(pos.getX(), pos.getY());
	}
	
	public Cellule getCellule(int xPixels, int yPixels)
	{
		return getCellule(GridPosition.fromPixels(xPixels, yPixels));
	}
	

	public long getWidth() {
		return w;
	}

	public long getHeight() {
		return h;
	}

	public long getPixelWidth() {
		return getWidth() * GRID_W;
	}

	public long getPixelHeight() {
		return getHeight() * GRID_H;
	}

}
