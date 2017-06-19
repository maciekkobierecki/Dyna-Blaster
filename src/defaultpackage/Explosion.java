package defaultpackage;

import java.awt.Graphics;

/**
 * Klasa obs³uguj¹ca mechanizm eksplozji bomby
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Explosion extends Obiekt {

	/**
	 * Konstruktor klasy
	 */
	public Explosion(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		loadImage("explosion");
	}

	/**
	 * metoda obs³uguj¹ca zdarzenie
	 */
	@Override
	void draw(Graphics g) {
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}

}
