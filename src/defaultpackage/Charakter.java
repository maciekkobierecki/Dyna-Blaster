package defaultpackage;

import java.awt.Graphics;
import java.util.Random;

public abstract class Charakter extends Obiekt{
	
	//zmienne mówi¹ce o ile przy nastepnym move ma sie ruszyc obiekt
	protected int dx, dy;
	//losowanie liczb potrzebnych do okreslania kolejnego kierunku ruchu obiektu graficznego po napotkaniu kolizji 
	protected Random rand;
	public Charakter(Board plansza, int x, int y, int xwidth, int xheight,int dx,int dy) {
		super(plansza, x, y, xwidth, xheight);
		this.dx=dx;
		this.dy=dy;
		rand=new Random();
	}
	
	
	abstract public void move();
	
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
