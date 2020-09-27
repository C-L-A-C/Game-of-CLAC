package jeu;

import processing.core.PApplet;
import java.util.HashMap;

import collision.Rectangle;


public class DonneesJeu {
	
	private Rectangle rectMonde;
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
		rectMonde = new Rectangle(0, 0, carte.getPixelWidth(), carte.getPixelHeight());
	}
	
	public void saveBuild(Entite newBuild) {
		float key [] = {newBuild.getX(), newBuild.getY()};
		this.builds.put(key, newBuild);
	}
	
	public Entite checkCollision(Entite e)
	{
		//TODO: faire un vrai truc ici parceque la c'est bullshit
		if (! e.collision(rectMonde))
			return joueur;
		
		if (e != joueur && e.collision(joueur))
			return joueur;
		
		for (Entite b : builds.values())
		{
			if (e != b && e.collision(b))
				return b;
		}
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
