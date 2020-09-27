package jeu;

import processing.core.PApplet;

public class DonneesJeu {
	
	private Carte carte;
	private Joueur joueur;
	private Scroll scroll;
	
	public DonneesJeu()
	{
		int viewW = 640, viewH = 480;
		carte = new Carte(100, 100);
		joueur = new Joueur(0, 0);
		scroll = new Scroll(carte.getPixelWidth(), carte.getPixelHeight(), viewW, viewH);
	}
	
	public Entite checkCollision(Entite e)
	{
		if (e != joueur && e.collision(joueur))
			return joueur;
		return null;
	}
	
	public void evoluer()
	{
		long t = System.currentTimeMillis();
		
		joueur.evoluer(t, this);
	}
	
	public void afficher(PApplet p)
	{
		scroll.update(joueur);
		
		p.pushMatrix();
		p.translate(-(int) scroll.getX(), - (int) scroll.getY());
		
		carte.afficher(p, scroll.getBB());
		joueur.afficher(p);
		
		p.popMatrix();
	}
	
	public Joueur getJoueur()
	{
		return joueur;
	}

}
