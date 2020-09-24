package controles;

public enum Controle {
	SAUT, BAS, DROITE, GAUCHE, TIR, SPRINT,
	SAUT_RELACHE(false), BAS_RELACHE(true), DROITE_RELACHE(false), GAUCHE_RELACHE(false), TIR_RELACHE(false), SPRINT_RELACHE(true);
	
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
