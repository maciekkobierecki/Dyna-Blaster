package defaultpackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 * interfejs deklaruj¹cy metode loadNextLevel()
 *
 */
interface NextLevelListener{
	void loadNextLevel();
}

/**
 * Klasa drzwi - przejœcia do kolejnego poziomu.
 * <p>
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Door extends Obiekt{

	/**
	 * Listener kolejnego poziomu.
	 */
	NextLevelListener nextLevelListener;
	
	/**
	 * Konstruktor klasy.
	 * @param plansza
	 * @param x
	 * @param y
	 * @param xwidth
	 * @param xheight
	 */
	public Door(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		nextLevelListener=null;
		loadImage("door");
	}
	
	/**
	 * Metoda dodaj¹ca nowy listener
	 */
	public void addNextLevelListener(NextLevelListener listener){
		nextLevelListener=listener;
	}
	
	/**
	 * Metoda wczytuj¹ca listener.
	 */
	public void callNextLevelLisntener(){
		nextLevelListener.loadNextLevel();
	}
	
	/**
	 * Metoda logiczna obs³uguj¹ca u¿ycie drzwi
	 */
	public Boolean playerContains(Player player){
		Rectangle doorRect=new Rectangle(x,y,width,height);
		Rectangle playerRect=new Rectangle(player.x, player.y, player.width,player.height);
		if(doorRect.contains(playerRect))
			return true;
		return false;

	}
	
	/**
	 *metoda odpowiedzialn a za rysowanie reprezentacji graficznej obiektu
	 */

	public void draw(Graphics g)
	{
		if(playerContains(plansza.getPlayer()))
			callNextLevelLisntener();
		g.drawImage(img, this.x,this.y,this.width, this.height,  null);
		
	}
	
	

}
