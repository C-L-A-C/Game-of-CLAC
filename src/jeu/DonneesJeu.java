package jeu;

import processing.core.PApplet;

public class DonneesJeu {
	
	private Carte carte;
	private Joueur joueur;
	
	public DonneesJeu()
	{
		carte = new Carte(100, 100);
		joueur = new Joueur(0, 0);
	}
	
	public Element checkCollision(Element e)
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
		carte.afficher(p);
		joueur.afficher(p);
	}
	
	public Joueur getJoueur()
	{
		return joueur;
	}

}
