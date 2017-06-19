package defaultpackage;

import java.awt.Graphics;


/**
 * Klasa œciany labiryntu, dziedzicz¹ca po obiekcie.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Wall extends Obiekt{
	
	/**
	 * Konstruktor klasy.
	 */
	public Wall(Board gameField, int x, int y,int xwidth, int xheight)
	{
		super(gameField, x, y, xwidth, xheight);
		loadImage("wall");
	}

	
	/**
	 * Metoda odpowiedzialna za rysowanie.
	 */
	public void draw(Graphics g)
	{
		g.drawImage(img, this.x,this.y,  this.width, this.height,null);		
	}

}
