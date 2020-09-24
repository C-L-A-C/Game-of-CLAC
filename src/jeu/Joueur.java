package jeu;

import collision.Rectangle;
import controles.Controlable;
import controles.Controle;
import processing.core.*;


public class Joueur extends EntiteMobile implements Controlable {
	
	public final static int W = Carte.GRID_W, H = Carte.GRID_H;
	

	public Joueur(float x, float y) {
		super(x, y);
		forme = new Rectangle(pos, W, H);
		loadImages();
	}
	
	protected void loadImages()
	{
		
	}
	
	// Implementer evoluer si on veut appliquer des effets

	@Override
	public void afficher(PApplet p) {		
		p.fill(255, 0, 0);
		p.noStroke();
		p.rect(getX(), getY(), W, H);
	}

	@Override
	public void action(Controle c, DonneesJeu jeu) {		
		float vitesseMax = 140;
		
		switch(c)
		{
		case DROITE_RELACHE:
		case GAUCHE_RELACHE:
			vitesse.x = 0;
			break;
		case HAUT_RELACHE:
		case BAS_RELACHE:
			vitesse.y = 0;
			break;
		case DROITE:
			vitesse.x = vitesseMax;
			break;
		case GAUCHE:
			vitesse.x = -vitesseMax;
			break;
		case HAUT:
			vitesse.y = -vitesseMax;
			break;
		case BAS:
			vitesse.y = vitesseMax;
			break;
		default:
			break;
		}
		
		vitesse.limit(vitesseMax);

	}
}
