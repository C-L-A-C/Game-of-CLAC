package jeu;

import collision.Rectangle;
import controles.Controlable;
import controles.Controle;
import graphiques.AnimationSet;
import graphiques.Assets;
import graphiques.AffichageRectangle;
import graphiques.Animation;
import graphiques.Tileset;
import utils.Utils;


public class Joueur extends EntiteMobile implements Controlable {
	
	public final static int W = Carte.GRID_W, H = Carte.GRID_H;	

	public Joueur(float x, float y) {
		super(x, y, new Animation(new Tileset(Assets.getImage("spritesheet1"), 12, 8), 0, 2, 3));
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
			int xMur = ((int) jeu.getJoueur().getX() / Carte.GRID_W - 1) * Carte.GRID_W;
			int yMur = ((int) jeu.getJoueur().getY() / Carte.GRID_H - 1) * Carte.GRID_H;
			Mur newMur = new Mur(xMur, yMur, Carte.GRID_W, Carte.GRID_H);
			jeu.saveBuild(newMur);
		default:
			break;
		}
		
		vitesse.limit(vitesseMax);

	}
}
