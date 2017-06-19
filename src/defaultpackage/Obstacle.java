package defaultpackage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Klasa przeszkody, dziedzicz¹ca po klasie Obiekt.
 * <p>
 * Tworzy przeszkody, mo¿liwe do zniszczenia za pomoc¹ bomb.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Obstacle extends Obiekt {
	
	/**
	 * Zmienna typu BufferedImage
	 */
	BufferedImage img;
	
	/**
	 * Konstruktor klasy.
	 */
	public Obstacle(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		loadImage();
	}
	
	/**
	 * Metoda odpowiedzialna za rysowanie
	 */
	public void loadImage(){

		try 
		{
		    img=ImageIO.read(new File(Config.graphicsPath+"obstacle.png"));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	/**
	 * Metoda odpowiedzialna za rysowanie.
	 */
	public void draw(Graphics g)
	{
		g.drawImage(img, this.x,this.y,this.width, this.height, null);		
	}
	
	
}