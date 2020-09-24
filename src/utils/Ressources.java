package utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PImage;

public class Ressources {

	private static Map<String, PImage> ressources = new HashMap<String, PImage>();
	private static PApplet p;
	private static boolean enabled;
	
	public static void init(PApplet p)
	{
		Ressources.enabled = p != null;
		Ressources.p = p;
	}
	
	public static PImage getImage(String nom)
	{
		if (!enabled)
			return null;
		
		return (PImage) get(nom, "image");
	}
	
	public static void preload(String nom, String type) throws IOException
	{
		load(nom, type);
	}
	
	private static void load(String nom, String type) throws IOException
	{	
		String[] types = {"image"};
		String[] repertoires = {"Image"};
		
		String[][] exts = {
				{ ".png", ".jpg", ".jpeg"}
		};
		boolean ajoute = false;
		
		int typeI = Arrays.asList(types).indexOf(type);
		if (typeI == -1)
			throw new IllegalArgumentException("Type de ressource incorrect : " + type);

		PrintStream errStream = System.err;
		System.setErr(new PrintStream(new OutputStream() {
		    public void write(int b) {
		    }
		}));
		for(String ext : exts[typeI])
		{
			String filename = repertoires[typeI] + "/" + nom + ext;
			PImage img = null;
			switch (type)
			{
				case "image":
					img = p.loadImage(filename);
					break;
				
			}
			if (img != null)
			{
				ressources.put(nom, img);
				ajoute = true;
				break;
			}
		}

		System.setErr(errStream);
		if (!ajoute)
			throw new IOException("La ressource " + nom + " de type " + type + " n'existe pas ou ne peut pas être ouverte");
	}
	
	private static Object get(String nom, String type) {

		if (! ressources.containsKey(nom))
		{
			try{
				load(nom, type);
			} catch(IOException e)
			{
				Logger.printlnErr(e.getMessage());
				return null;
			}
		}
		
		return ressources.get(nom);
	}
}