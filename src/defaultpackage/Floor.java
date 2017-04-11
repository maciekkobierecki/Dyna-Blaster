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

	private Color color;
	public Floor(Board plansza, int x, int y, Color color, int xwidth, int xheight) 
	{
		super(plansza,x,y, xwidth, xheight);
		this.color=color;
		System.out.println("utworzono podloge");
		width=xwidth;
		height=xheight;
		

	
		
	}
	
	public void draw(Graphics g)
	{
		
		Dimension sizeOfGameField=plansza.getSize();
		g.setColor(color);
		//System.out.println("draw floor" +x+"y"+y+ "width"+width+"height"+height);
		
		g.fillRect(getX(),getY(), width,height);
		
	}
	
	
}
