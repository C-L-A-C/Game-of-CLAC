package jeu;

import collision.Rectangle;
import element.Cellule;
import element.Ressource;
import element.TypeRessource;
import element.TypeElement;
import processing.core.PApplet;
import utils.Utils;

public class Carte {

	public static final int GRID_W = 64, GRID_H = 64;
	private long w, h;
	private Cellule[][] cellules;

	public Carte(long w, long h) {
		this.w = w;
		this.h = h;
		cellules = genererZoneCarte(new Rectangle(0, 0, w, h));
	}

	public void afficher(PApplet p, Rectangle zone) {
		int gridX = (int) (zone.getX() / GRID_W), gridY = (int) (zone.getY() / GRID_H);
		int gridW = (int) Math.ceil(zone.getW() / GRID_W), gridH = (int) Math.ceil(zone.getH() / GRID_H);
		
		//Compensation pour x et y : si on est au milieu d'une tile, on doit afficher une tile en plus pur completer le bloc
		int xComp = (int) (zone.getX()) % GRID_W == 0 ? 0 : 1, yComp = (int) (zone.getY()) % GRID_H == 0 ? 0 : 1;
		
		for (int x = gridX; x < gridX + gridW + xComp && x < w; x++) {
			for (int y = gridY; y < gridY + gridH + yComp && y < h; y++) {
				switch (cellules[x][y].getTypeElement()) {
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
				if (cellules[x][y].getRessource() != null) {
					switch (cellules[x][y].getRessource().getType()) {
					case PETROLE:
						p.fill(128, 128, 128);
						break;
					case CHARBON:
						p.fill(0, 0, 0);
						break;
					}
					p.ellipse(x * GRID_W  + GRID_W / 2, y * GRID_H + GRID_H / 2, GRID_W / 2, GRID_H / 2);
				}
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
