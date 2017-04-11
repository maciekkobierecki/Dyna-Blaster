package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

/**
 * Klasa gracza, dziedzicz¹ca po klasie obiekt.
 * <p>
 * Jest odpowiedzialna za stworzenie gracza na planszy.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Player extends Charakter{

	private int score;
	
	public Player(Board plansza, int x,int y, int width, int height)
	{
		super(plansza,x,y, width, height);
		
		
	}
	
	public void draw(Graphics g)
	{
		
		this.move();
		//Dimension sizeOfGameField=plansza.getSize();
		g.setColor(Color.ORANGE);
		g.fillOval(getX(),getY(), width/2, height/2);
	}
	
	
	
	
	
}
