package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Klasa œciany labiryntu, dziedzicz¹ca po obiekcie.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Wall extends Obiekt{
	
	public Wall(Plansza gameField, int x, int y,int xwidth, int xheight)
	{
		super(gameField, x, y);
		width=xwidth;
		height=xheight;
		System.out.println("utworzono sciane");
	}
	
	
	public  static void setDimension(int xwidth, int xheight)
	{ 
		width=xwidth;
		height=xheight;
	}
	
	public void draw(Graphics g)
	{
		Dimension sizeOfGameField=plansza.getSize();
		g.setColor(Color.cyan);
		System.out.println("draw wall X" + x+"y"+y+ "width"+width+"height"+height);
		g.fillRect(getX(),getY(), width,height);
		
	}

}
