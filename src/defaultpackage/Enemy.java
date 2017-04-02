package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Charakter{

	
	

	

	public Enemy(Plansza plansza, int x, int y, int width, int height) {
		super(plansza, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(this.x+width/4, this.y+height/4,width/2, height/2);
		g.setColor(Color.black);
		g.drawLine(this.x+width/3, this.y+2*height/3, this.x+2*width/3, this.y+2*height/3);
		g.fillOval(this.x+width/3,this.y+height/3, 5, 5);
		g.fillOval(this.x+2*width/3,this.y+height/3, 5, 5);
	}
	
}
