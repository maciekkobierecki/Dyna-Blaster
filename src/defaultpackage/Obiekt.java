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
	 * szerokość
	 */
	protected int  width;
	
	/**
	 * wysokość
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
	 * zmienna stanowiąca licznik
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
	 * metoda, która zwraca prostokąt o koordynatach i rozmiarach obiektu graficznego
	 */
		public Rectangle getShape(){
			return new Rectangle(x,y,width,height);
		}

	/**
	 * metoda ustalająca wymiary
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
	 * metoda ustalająca wymiar X
	 */
	public void setX(int x) { this.x=x; }
	
	/**
	 * metoda ustalająca wymiar Y
	 */
	public void setY(int y) { this.y=y; }
	
	/**
	 * metoda pobierająca wymiar X
	 */
	public int getX() { return x; }
	
	/**
	 * metoda pobierająca wymiar Y
	 */
	public int getY() { return y; }
	
	/**
	 * Metoda ładująca obraz o danej nazwie w formacie png
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

