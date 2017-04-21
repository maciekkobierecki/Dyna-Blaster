package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Klasa pod�o�a, dziedzicz�ca po obiekcie.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Floor extends Obiekt {

	private Color color;
	public Floor(Board plansza, int x, int y, Color color, int xwidth, int xheight) 
	{
		super(plansza,x,y, xwidth, xheight);
		this.color=color;
		width=xwidth;
		height=xheight;
		

	
		
	}
	
	public void draw(Graphics g)
	{
		g.setColor(color);
		g.fillRect(getX(),getY(), width,height);
	}
	
	
}
