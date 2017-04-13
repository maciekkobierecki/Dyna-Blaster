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
	protected int  width;
	protected int height;
	protected int x;
	protected int y;
	static int licznik=0;
	Board plansza;
	
	public Obiekt(Board plansza,int x, int y,int xwidth,int xheight)
	{
		this.x=x;
		this.y=y;
		this.plansza=plansza;
		width=xwidth;
		height=xheight;		
	}
	//zwraca prostok¹t o koordynatach i rozmiarach obiektu graficznego
		public Rectangle getShape(){
			return new Rectangle(x,y,width,height);
		}

	public void setDimension(int xwidth, int xheight)
	{ 
		width=xwidth;
		height=xheight;
	}
	abstract void draw(Graphics g);	
	public void setX(int x) { this.x=x; }
	public void setY(int y) { this.y=y; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	
	


	
	
	
	
}
