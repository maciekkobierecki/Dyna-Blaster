package defaultpackage;

import java.awt.Graphics;

public abstract class Charakter extends Obiekt{
	
	
	private int dx, dy;
	
	public Charakter(Plansza plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		// TODO Auto-generated constructor stub
	}
	
	
	protected void move()
	{
		this.setX(this.getX()+dx);
		this.setY(this.getY()+dy);
	}
	public void setPosition(int x, int y)
	{
		this.setX(x);
		this.setX(y);
	}
	
	public void setDX(int value)
	{
		dx=value;
	}
	
	public void setDY(int value)
	{
		dy=value;
	}

	
	

}
