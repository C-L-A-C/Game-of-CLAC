package gui;

import processing.core.PApplet;

/**
 *  Permet de définir un menu ou un etat du jeu (jeu normal, combat, ...)
 * @author adrien
 *
 */
public interface Scene {
	
	public void setup(PApplet p);
	
	public default void fermer() {};

	public void draw();

	public default void keyPressed() {};

	public default void keyReleased() {};

	public default void mouseReleased() {};

	public default void mousePressed() {};

}
