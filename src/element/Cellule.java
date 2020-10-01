package element;

public class Cellule {
	private TypeElement type;
	private Ressource ressource;
	
	public Cellule(TypeElement type, Ressource ressource)
	{
		this.type = type;
		this.ressource = ressource;
	}

	public TypeElement getTypeElement()
	{
		return type;
	}
	
	public Ressource getRessource()
	{
		return ressource;
	}
	
	public Ressource miner()
	{
		Ressource obtenue = ressource;
		ressource = null;
		return obtenue;
	}
}
