package jeu;

import collision.Rectangle;
import element.Element;
import element.TypeElement;
import processing.core.PApplet;

public class Carte {

	public static final int GRID_W = 15, GRID_H = 15;
	private long w, h;
	private Element[][] elements;

	public Carte(long w, long h) {
		this.w = w;
		this.h = h;
		elements = genererZoneCarte(new Rectangle(0, 0, w, h));
	}

	public void afficher(PApplet p, Rectangle zone) {
		int gridX = (int) (zone.getX() / GRID_W), gridY = (int) (zone.getY() / GRID_H);
		int gridW = (int) Math.ceil(zone.getW() / GRID_W), gridH = (int) Math.ceil(zone.getH() / GRID_H);
		
		//Compensation pour x et y : si on est au milieu d'une tile, on doit afficher une tile en plus pur completer le bloc
		int xComp = (int) (zone.getX()) % GRID_W == 0 ? 0 : 1, yComp = (int) (zone.getY()) % GRID_H == 0 ? 0 : 1;
		
		for (int x = gridX; x < gridX + gridW + xComp && x < w; x++) {
			for (int y = gridY; y < gridY + gridH + yComp && y < h; y++) {
				switch (elements[x][y].getType()) {
				case TERRE:
					p.fill(64, 9, 3);
					break;
				case PIERRE:
					p.fill(90, 90, 90);
					break;
				case HERBE:
					p.fill(0, 130, 10);
					break;
				case CHARBON:
					p.fill(10, 10, 10);
					break;
				}
				p.rect(x * GRID_W, y * GRID_H, GRID_W, GRID_H);
			}
		}
	}

	private Element[][] genererZoneCarte(Rectangle zone) {
		Element[][] elements = new Element[(int) zone.getW()][(int) zone.getH()];
		for (int x = 0; x < elements.length; x++) {
			for (int y = 0; y < elements[x].length; y++) {
				TypeElement[] types = TypeElement.values();
				int index = (int) (Math.random() * types.length);
				elements[x][y] = new Element(types[index]);
			}
		}
		return elements;
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
