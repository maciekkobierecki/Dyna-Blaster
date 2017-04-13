package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Klasa gracza, dziedzicz¹ca po klasie obiekt.
 * <p>
 * Jest odpowiedzialna za stworzenie gracza na planszy.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Player extends Charakter{

	private int score;
	
	public Player(Board plansza, int x,int y, int width, int height)
	{	//ustawia zerow¹ przedkoœæ poczatkowa
		super(plansza,x,y, width, height,0,0);
		
		
	}
	
	public void move()
	{		
		this.setX(this.getX()+dx);
		this.setY(this.getY()+dy);
	}
	
	public void draw(Graphics g)
	{
		
		this.move();
		g.setColor(Color.ORANGE);
		g.fillOval(getX(),getY(), width/2, height/2);
	}
	
	
	
	
	
}
