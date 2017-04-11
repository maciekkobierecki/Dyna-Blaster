package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;

public class Enemy extends Charakter{

	
	
	private int dx, dy;
	

	public Enemy(Board plansza, int x, int y, int width, int height) {
		super(plansza, x, y, width, height);
		dx=4;
		dy=4;
	}

	@Override
	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(this.x, this.y,width, height);
		g.setColor(Color.black);
		g.drawLine(this.x+width/3, this.y+2*height/3, this.x+2*width/3, this.y+2*height/3);
		g.fillOval(this.x+width/3,this.y+height/3, 5, 5);
		g.fillOval(this.x+2*width/3,this.y+height/3, 5, 5);
	}
	
	
	/* Metody ustawiaj¹ce szybkoœæ potworow */
	public void setDX(int dx){ this.dx=dx; }
	public void setDY(int dy){ this.dy=dy; }
	
	public void move()
	{
		if(x+width>plansza.getPanelWidth()|| x<0)
			dx=-dx;
		if(y+height>plansza.getPanelHeight()|| y<0)
			dy=-dy;
		this.y+=dy;
		this.x+=dx;
	}
	
}
