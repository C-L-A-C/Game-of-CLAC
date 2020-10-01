package jeu;

public class GridPosition {
	private int x, y;

	/**
	 * Constructeur à partir de coordonnées sur la grille
	 * @param x
	 * @param y
	 */
	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public static GridPosition fromPixels(float xPixels, float yPixels) {
		return new GridPosition((int) xPixels / Carte.GRID_W, (int) yPixels / Carte.GRID_H);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GridPosition other = (GridPosition) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}
