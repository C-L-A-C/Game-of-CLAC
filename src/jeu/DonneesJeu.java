package jeu;

import processing.core.PApplet;
import java.util.HashMap;


public class DonneesJeu {
	
	private Carte carte;
	private Joueur joueur;
	private Scroll scroll;
	private HashMap <float[], Entite> builds;
	
	public DonneesJeu()
	{
		int viewW = 640, viewH = 480;
		carte = new Carte(100, 100);
		joueur = new Joueur(0, 0);
		scroll = new Scroll(carte.getPixelWidth(), carte.getPixelHeight(), viewW, viewH);
		builds = new HashMap <float[], Entite>();
	}
	
	public void saveBuild(Entite newBuild) {
		float key [] = {newBuild.getX(), newBuild.getY()};
		this.builds.put(key, newBuild);
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
		

		for (Entite value : this.builds.values()) {
			value.afficher(p);
		}
		
		p.popMatrix();
	}
	
	public Joueur getJoueur()
	{
		return joueur;
	}
	public HashMap <float[], Entite> getBuilds(){
		return this.builds;
	}

}
