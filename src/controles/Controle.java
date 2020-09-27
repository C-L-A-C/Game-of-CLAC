package controles;

public enum Controle {
	HAUT, BAS, DROITE, GAUCHE, A,
	HAUT_RELACHE(false), BAS_RELACHE(true), DROITE_RELACHE(false), GAUCHE_RELACHE(false), A_RELACHE;
	
	private boolean enfonce;

	private Controle()
	{
		this(true);
	}
	
	private Controle(boolean appuye)
	{
		this.enfonce = appuye;
	}
	
	public boolean estEnfonce() {
		return enfonce;
	}

	public int getIndex() {
		return ordinal();
	}
}
