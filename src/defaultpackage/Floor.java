package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
