package defaultpackage;

import java.awt.Graphics;

public class Explosion extends Obiekt {

	public Explosion(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		loadImage("explosion");
	}

	@Override
	void draw(Graphics g) {
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}

}
