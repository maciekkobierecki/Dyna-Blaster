package defaultpackage;

import java.awt.Graphics;

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
	 * Konstruktor klasy.
	 * @param plansza
	 * @param x
	 * @param y
	 * @param xwidth
	 * @param xheight
	 */
	public Obstacle(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Metoda odpowiedzialna za rysowanie
	 */
	@Override
	void draw(Graphics g) {
		g.setColor(java.awt.Color.WHITE);
		g.fillRect(getX(),getY(), width,height);
		
	}
	
	
	
}