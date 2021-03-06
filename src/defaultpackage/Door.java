package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * interfejs deklarujący metode loadNextLevel()
 *
 */
interface NextLevelListener{
	void loadNextLevel();
}

/**
 * Klasa drzwi - przejścia do kolejnego poziomu.
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
	 */
	public Door(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		nextLevelListener=null;
		loadImage("door");
	}
	
	/**
	 * Metoda dodająca nowy listener
	 */
	public void addNextLevelListener(NextLevelListener listener){
		nextLevelListener=listener;
	}
	
	/**
	 * Metoda wczytująca listener.
	 */
	public void callNextLevelLisntener(){
		nextLevelListener.loadNextLevel();
	}
	
	/**
	 * Metoda logiczna obsługująca użycie drzwi
	 */
	public Boolean playerContains(Player player){
		Rectangle doorRect=new Rectangle(x+width/4,y+height/4,width/2,height/2);
		Rectangle playerRect=new Rectangle(player.x, player.y, player.width,player.height);
		if(doorRect.intersects(playerRect))
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
