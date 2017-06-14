package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;




/**
 * Abstrakcyjna klasa obiektu
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public abstract class Obiekt {
	
	/**
	 * szerokoœæ
	 */
	protected int  width;
	
	/**
	 * wysokoœæ
	 */
	protected int height;
	
	/**
	 * koordynata x 
	 */
	protected int x;
	
	/**
	 * koordynata y
	 */
	protected int y;
	
	/**
	 * zmienna stanowi¹ca licznik
	 */
	static int licznik=0;
	
	/**
	 * obiekt klasy Board
	 */
	Board plansza;
	
	/**
	 * reprezentacja graficzna obiektu
	 */
	BufferedImage img;
	
	/**
	 * Konstruktor klasy.
	 * @param plansza
	 * @param x
	 * @param y
	 * @param xwidth
	 * @param xheight
	 */
	public Obiekt(Board plansza,int x, int y,int xwidth,int xheight)
	{
		
		this.x=x;
		this.y=y;
		this.plansza=plansza;
		width=xwidth;
		height=xheight;		
	}
	
	/**
	 * metoda, która zwraca prostok¹t o koordynatach i rozmiarach obiektu graficznego
	 */
		public Rectangle getShape(){
			return new Rectangle(x,y,width,height);
		}

	/**
	 * metoda ystalaj¹ca wymiary
	 */
	public void setDimension(int xwidth, int xheight)
	{ 
		width=xwidth;
		height=xheight;
	}
	
	/**
	 * metoda odpowiedzialna za rysowanie
	 */
	abstract void draw(Graphics g);	
	
	/**
	 * metoda ustalaj¹ca wymiar X
	 */
	public void setX(int x) { this.x=x; }
	
	/**
	 * metoda ustalaj¹ca wymiar Y
	 */
	public void setY(int y) { this.y=y; }
	
	/**
	 * metoda pobieraj¹ca wymiar X
	 */
	public int getX() { return x; }
	
	/**
	 * metoda pobieraj¹ca wymiar Y
	 */
	public int getY() { return y; }
	
	/**
	 * Metoda ³aduj¹ca obraz o danej nazwie w formacie png
	 */
	public void loadImage(String name){
		try 
		{
		    this.img=ImageIO.read(new File(Config.graphicsPath+name+".png"));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	


	
	
	
	
}

