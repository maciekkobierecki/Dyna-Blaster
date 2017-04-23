package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa pod³o¿a, dziedzicz¹ca po obiekcie.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Floor extends Obiekt {
	
	/**
	 * Obiekt typu Color
	 */
	private Color color;
	
	/**
	 * Konstruktor klasy.
	 * @param plansza
	 * @param x
	 * @param y
	 * @param color
	 * @param xwidth
	 * @param xheight
	 * 
	 */
	public Floor(Board plansza, int x, int y, Color color, int xwidth, int xheight) 
	{
		super(plansza,x,y, xwidth, xheight);
		this.color=color;
		width=xwidth;
		height=xheight;
		

	
		
	}
	
	/**
	 * Metoda odpowiedzialna za rysowanie.
	 */
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(getX(),getY(), width,height);
	}
	
	
}
