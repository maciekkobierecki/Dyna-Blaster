package defaultpackage;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JComponent;

import java.awt.Color;
import javafx.scene.image.*;




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
	 * metoda, która zwraca prostokąt o koordynatach i rozmiarach obiektu graficznego
	 */
		public Rectangle getShape(){
			return new Rectangle(x,y,width,height);
		}

	/**
	 * metoda ystalająca wymiary
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
	
	
	


	
	
	
	
}

