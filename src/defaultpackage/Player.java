package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Klasa gracza, dziedzicz¹ca po klasie obiekt.
 * <p>
 * Jest odpowiedzialna za stworzenie gracza na planszy, jak równie¿ za poruszanie.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Player extends Obiekt{

	private int dx, dy;
	public Player(Plansza plansza, int x,int y, int width, int height)
	{
		super(plansza,x,y);
		this.width=width;
		this.height=height;
		//dx=0;
		//dy=0;
		
	}
	
	public void draw(Graphics g)
	{
		this.move();
		Dimension sizeOfGameField=plansza.getSize();
		g.setColor(Color.ORANGE);
		g.fillOval(getX(),getY(), width, height);
	}
	
	private void move()
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
