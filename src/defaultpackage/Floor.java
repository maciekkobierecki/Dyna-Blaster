package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;

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
	 * 
	 */
	public Floor(Board plansza, int x, int y, Color color, int xwidth, int xheight) 
	{
		super(plansza,x,y, xwidth, xheight);
		this.color=color;
		width=xwidth;
		height=xheight;
		loadImage("floor");
	}

	/**
	 * Metoda odpowiedzialna za rysowanie.
	 */
	public void draw(Graphics g)
	{
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}
	
	
}
