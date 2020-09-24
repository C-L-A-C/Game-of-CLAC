package jeu;

import collision.Rectangle;
import element.Element;
import element.TypeElement;
import processing.core.PApplet;

public class Carte {
	
	public static final int GRID_W = 15, GRID_H = 15;
	private long w, h;
	
	public Carte(long w, long h)
	{
		this.w = w;
		this.h = h;
	}
	
	public void afficher(PApplet p, Rectangle zone)
	{
		Element[][] elements = getZoneCarte(zone, p);
		for (int x = 0; x < elements.length; x++)
		{
			for (int y = 0; y < elements[x].length; y++)
			{
				switch(elements[x][y].getType())
				{
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
				p.rect(zone.getX() + x * GRID_W, zone.getY() + y * GRID_H, GRID_W, GRID_H);
			}
		}
	}
	
	private Element[][] getZoneCarte(Rectangle zone, PApplet p) {
		Rectangle gridZone = new Rectangle(zone.getX() / GRID_W, zone.getY() / GRID_H, zone.getW() / GRID_W, zone.getH() / GRID_H);
		
		Element[][] elements = new Element[(int) gridZone.getW()][ (int) gridZone.getH()];
		for (int x = 0; x < elements.length; x++)
		{
			for (int y = 0; y < elements[x].length; y++)
			{
				TypeElement[] types = TypeElement.values();
				int xPos = (int) gridZone.getX() + x, yPos = (int) gridZone.getY() + y;
				int index = (int) (p.noise(xPos / (float) w, yPos / (float) h, 0) * types.length);
				elements[x][y] = new Element(types[index]); 	
			}
		}
		return elements;
	}

	public long getWidth()
	{
		return w;
	}
	
	public long getHeight()
	{
		return h;
	}

	public long getPixelWidth() {
		return getWidth() * GRID_W;
	}

	public long getPixelHeight() {
		return getHeight() * GRID_H;
	}

}
