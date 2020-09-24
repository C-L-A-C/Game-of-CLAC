package main;

import java.io.IOException;

import gui.Scene;
import gui.SceneHandler;
import processing.core.PApplet;
import processing.core.PFont;

public class Main {

	public static void main(String[] args) {
		SceneHandler.launch(new Scene() {
			private PApplet p;
			
			public void setup(PApplet p) {
				this.p = p;
				p.background(70);
				
				if (args.length != 0)
					p.textFont(p.createFont(args[0], 16));
			}

			@Override
			public void draw() {
				p.stroke(255, 0, 255);
				p.strokeWeight(3);
				p.point(p.mouseX, p.mouseY);
			}
			
			@Override
			public void mousePressed()
			{
				p.text("CLAC", p.mouseX, p.mouseY);
			}
			
		});
	}

}
