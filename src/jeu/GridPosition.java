package jeu;

public class GridPosition {
	private int x, y;

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

	/**
	 * Constructeur à partir de coordonnées sur la grille
	 * @param x
	 * @param y
	 */
	public GridPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Constructeur à partir de coordonnées de pixels
	 * @param x
	 * @param y
	 */
	public GridPosition(float x, float y) {
		this.x = (int) x / Carte.GRID_W;
		this.y = (int) y / Carte.GRID_H;
	}
}
