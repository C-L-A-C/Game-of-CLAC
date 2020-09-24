package jeu;

import collision.Rectangle;
import processing.core.PApplet;

public class Mur extends Element{
		
	protected float w, h;
	
	public Mur(float x, float y, float w, float h) {
		super(x, y);
		forme = new Rectangle(pos, w, h);
		this.w = w;
		this.h = h;
	}

	@Override
	public void afficher(PApplet p) {
		if (estDetruit())
			return; 
		p.fill(255, 255, 255);
		p.rect(pos.x, pos.y, w, h);
	}

	@Override
	protected void faireCollision(Element collider, DonneesJeu d) {
	}
}
