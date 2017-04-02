package defaultpackage;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

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
	protected static int  width;
	protected static int height;
	protected int x;
	protected int y;
	static int licznik=0;
	Plansza plansza;
	
	public Obiekt(Plansza plansza,int x, int y)
	{
		this.x=x;
		this.y=y;
		this.plansza=plansza;
		width=0;
		height=0;		
	}
	
	abstract void draw(Graphics g);
	
	public void setX(int x) { this.x=x; }
	public void setY(int y) { this.y=y; }
	public int getX() { return x; }
	public int getY() { return y; }
	
	
	


	
	
	
	
}
