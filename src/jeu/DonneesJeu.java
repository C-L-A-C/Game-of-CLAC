package jeu;


import processing.core.PApplet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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
	
	public void save_build(Entite build){
		float key [] = {build.getX(), build.getY()};//si j'ai bien compris je dois travailler en flottant quelle angoisse
		if (builds.containsKey(key)) {
			throw new IllegalArgumentException("Quelue chose est déjà build sur cette case");
		}
		else {
			this.builds.put(key, build);
		}
	}
	
	public void afficher(PApplet p)
	{
		scroll.update(joueur);
		
		p.pushMatrix();
		p.translate(-scroll.getX(), -scroll.getY());
		
		carte.afficher(p, scroll.getBB());
		joueur.afficher(p);
		
		//on parcourt les builds pour les afficher, j'utilise un set et un iterator mais n'hésitez pas à changer si c'est débile
		for (Entite entite : this.builds.values()) {
			entite.afficher(p);
		}
		
		
		p.popMatrix();
	}
	
	public Joueur getJoueur()
	{
		return joueur;
	}

}
