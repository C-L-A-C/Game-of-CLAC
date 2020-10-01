package jeu;

import processing.core.PApplet;
import java.util.HashMap;
import java.util.Map;

import collision.Rectangle;

public class DonneesJeu {
	private Carte carte;
	private Joueur joueur;
	private Scroll scroll;
	private Map<GridPosition, Entite> builds;

	public DonneesJeu() {
		int viewW = 640, viewH = 480;
		carte = new Carte(100, 100);
		joueur = new Joueur(0, 0);
		scroll = new Scroll(carte.getPixelWidth(), carte.getPixelHeight(), viewW, viewH);
		builds = new HashMap<>();
	}

	public void saveBuild(Entite newBuild) {
		if (checkCollision(newBuild) == null)
			builds.put(newBuild.getPos(), newBuild);
	}

	public Entite checkCollision(Entite e) {
		float eW = e.getForme().getW(), eH = e.getForme().getH();
		
		// TODO: retourner une vraie entite ici parceque la c'est un peu bullshit
		Rectangle rectMonde = new Rectangle(eW, eH, carte.getPixelWidth() - 2 * eW + 1, carte.getPixelHeight() - 2 * eH + 1);
		if (!e.collision(rectMonde))
			return new Mur(0, 0, 0, 0);

		if (e != joueur && e.collision(joueur))
			return joueur;

		int xStart = (int) e.getX() / Carte.GRID_W, yStart = (int) e.getY() / Carte.GRID_H;

		// Le nombre de blocs que prend l'entite en largeur + 1 s'il est pas centre sur
		// la grille (et depasse sur le bloc suivant)
		int nbBlocsX = (int) Math.ceil(eW / Carte.GRID_W) + ((int) e.getX() % Carte.GRID_W == 0 ? 0 : 1);
		int nbBlocsY = (int) Math.ceil(eH / Carte.GRID_H) + ((int) e.getY() % Carte.GRID_H == 0 ? 0 : 1);

		for (int x = 0; x < nbBlocsX; x++) {
			for (int y = 0; y < nbBlocsY; y++) {
				Entite collider = builds.get(new GridPosition(x + xStart, y + yStart));
				if (collider != null)
					return collider;
			}
		}

		return null;
	}

	public void evoluer() {
		long t = System.currentTimeMillis();

		joueur.evoluer(t, this);
	}

	public void afficher(PApplet p) {
		scroll.update(joueur);
		p.noStroke();

		p.pushMatrix();
		p.translate(-(int) scroll.getX(), -(int) scroll.getY());

		carte.afficher(p, scroll.getBB());
		joueur.afficher(p);

		for (Entite value : builds.values()) {
			value.afficher(p);
		}

		p.popMatrix();
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Carte getCarte() {
		return carte;
	}

}
