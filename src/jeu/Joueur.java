package jeu;

import collision.Rectangle;
import controles.Controlable;
import controles.Controle;
import graphiques.AffichageRectangle;
import utils.Utils;


public class Joueur extends EntiteMobile implements Controlable {
	
	public final static int W = Carte.GRID_W, H = Carte.GRID_H;	

	public Joueur(float x, float y) {
		super(x, y, new AffichageRectangle(Utils.color(255, 255, 0)));
		forme = new Rectangle(pos, W, H);
	}
	
	// Implementer evoluer si on veut appliquer des effets

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
		case POSER_MUR:
			Mur newMur = new Mur(jeu.getJoueur().getX() + 1, jeu.getJoueur().getY(), 1, 1);
			float key [] = {newMur.getX(), newMur.getY()};
			jeu.getBuilds().put(key , newMur);
		default:
			break;
		}
		
		vitesse.limit(vitesseMax);

	}
}
