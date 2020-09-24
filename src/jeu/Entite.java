package jeu;

import collision.Forme;
import processing.core.PApplet;
import processing.core.PVector;

/**
 * Tout élément affichable, qui a des collisions
 * @author adrien
 *
 */
public abstract class Entite{
	/**
	 * Vrai si l'élément est détruit
	 */
	protected boolean detruit;
	/**
	 * Position de l'élément
	 */
	protected PVector pos;
	/**
	 * Forme de l'element, pour la collision (doit etre intialise par les sous-classes)
	 */
	protected Forme forme;
	
	public Entite(float x, float y)
	{
		pos = new PVector(x, y);
		detruit = false;
	}
	
	/**
	 * Permet de détruire l'élément
	 */
	public void detruire() {
		detruit = true;
	}
	
	/**
	 * Permet de savoir si l'élément est détruit
	 * @return Vrai s'il est détruit
	 */
	public boolean estDetruit()
	{
		return detruit;
	}
	/**
	 * Permet de faire la collision avec cet élément
	 * @param e Element avec lequel détecter la collision
	 * @return vrai s'il y a collision
	 */
	public boolean collision(Entite e) {
		return e.getForme() != null && !e.estDetruit() && collision(e.getForme());
	}
	/**
	 * Permet de faire la collision avec cette forme
	 * @param f Forme de l'élément
	 * @return vrai s'il y a collision
	 */
	public boolean collision(Forme f) {
		return !estDetruit() && forme != null && forme.collision(f);
	}
	
	/**
	 * Permet d'afficher l'élément
	 * @param p Là où on va dessiner
	 */
	public abstract void afficher(PApplet p);
	
	/**
	 * Que faire quand on est en collision avec un element ?
	 * @param collider element avec lequel on est en collision
	 * @param p 
	 */
	protected abstract void faireCollision(Entite collider, DonneesJeu d);


	//Un element n'evolue pas par defaut
	public void evoluer(long t, DonneesJeu d) {}
	
	
	/**
	 * Retourne la forme de l'élément
	 */
	public Forme getForme()
	{
		return forme;
	}
	
	public float getX()
	{
		return pos.x;
	}
	
	public float getY() {
		return pos.y;
	}
	
	public void setY(float y)
	{
		pos.y = y;
	}
	
	public void setX(float x)
	{
		pos.x = x;
	}
	
}
