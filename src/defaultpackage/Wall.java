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
	
	public Wall(Board gameField, int x, int y,int xwidth, int xheight)
	{
		super(gameField, x, y, xwidth, xheight);

	}
	
	
	
	public void draw(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(getX(),getY(), width,height);
		
	}

}
