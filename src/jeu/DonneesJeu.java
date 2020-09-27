package jeu;

import processing.core.PApplet;
import java.util.HashMap;
import java.util.Map;

import collision.Rectangle;
import gui.SceneHandler;


public class DonneesJeu {
	
	private Rectangle rectMonde;
	private Carte carte;
	private Joueur joueur;
	private Scroll scroll;
	private Map <GridPosition, Entite> builds;
	
	public DonneesJeu()
	{
		int viewW = 640, viewH = 480;
		carte = new Carte(100, 100);
		joueur = new Joueur(0, 0);
		scroll = new Scroll(carte.getPixelWidth(), carte.getPixelHeight(), viewW, viewH);
		builds = new HashMap <>();
		rectMonde = new Rectangle(0, 0, carte.getPixelWidth(), carte.getPixelHeight());
	}
	
	public void saveBuild(Entite newBuild) {
		builds.put(newBuild.getPos(), newBuild);
	}
	
	public Entite checkCollision(Entite e)
	{
		//TODO: faire un vrai truc ici parceque la c'est bullshit
		if (! e.collision(rectMonde))
			return joueur;
		
		if (e != joueur && e.collision(joueur))
			return joueur;
		
		int xStart = (int) e.getX() / Carte.GRID_W, yStart = (int) e.getY() / Carte.GRID_H;
		
		// Le nombre de blocs que prend l'entite en largeur + 1 s'il est pas centre sur la grille (et depasse sur le bloc suivant)
		int nbBlocsX = (int) Math.ceil(e.getForme().getW() / Carte.GRID_W) + ((int) e.getX() % Carte.GRID_W == 0 ? 0 : 1);
		int nbBlocsY = (int) Math.ceil(e.getForme().getH() / Carte.GRID_H) + ((int) e.getY() % Carte.GRID_H == 0 ? 0 : 1);
		
		for (int x = 0; x < nbBlocsX; x++)
		{
			for (int y = 0; y < nbBlocsY; y++)
			{
				Entite collider = builds.get(new GridPosition(x + xStart, y + yStart));
				if (collider != null)
					return collider;
			}
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
		p.noStroke();
		
		p.pushMatrix();
		p.translate(-(int) scroll.getX(), - (int) scroll.getY());
		
		carte.afficher(p, scroll.getBB());
		joueur.afficher(p);
		

		for (Entite value : builds.values()) {
			value.afficher(p);
		}
		
		p.popMatrix();
	}
	
	public Joueur getJoueur()
	{
		return joueur;
	}

}
