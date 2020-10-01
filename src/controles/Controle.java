package controles;

public enum Controle {
	HAUT, BAS, DROITE, GAUCHE, POSER_MUR, MINER,
	HAUT_RELACHE(false), BAS_RELACHE(false), DROITE_RELACHE(false), GAUCHE_RELACHE(false), POSER_MUR_RELACHE(false), MINER_RELACHE(false);
	
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
