package defaultpackage;

import java.awt.Graphics;


public class Obstacle extends Obiekt {

	public Obstacle(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		// TODO Auto-generated constructor stub
	}

	@Override
	void draw(Graphics g) {
		g.setColor(java.awt.Color.WHITE);
		g.fillRect(getX(),getY(), width,height);
		
	}
	
	
	
}