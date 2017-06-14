package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * interfejs deklaruj�cy metode setSpeed()
 *
 */
interface NewSpeedListener{
	void setNewSpeed();
}

/**
 * Klasa bonusu - zwi�ksza pr�dko�� gracza zale�nie od poziomu.
 * <p>
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class PowerUpSpeed extends Obiekt /*implements ActionListener */{
	
	/**
	 * Listener nowej pr�dko�ci.
	 */
	NewSpeedListener newSpeedListener;
	
	
	/**
	 * Czas trwania bonusu
	 */
	static int time = 5000;
	
	//private static ArrayList<SpeedListener> speedListeners=new ArrayList<>();
	
	/**
	 * Bonusowa pr�dko��
	 */
	public int speed;
	
	/**
	 * Timer
	 */
	Timer timer;
	
	/**
	 * Konstruktor parametryczny bonusu 
	 * @param plansza
	 * @param x
	 * @param y
	 * @param xwidth
	 * @param xheight
	 */
	public PowerUpSpeed(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		//timer=new Timer(time, this);
		//timer.start();	
		newSpeedListener=null;
		loadImage("pus");
	}
	
	/**
	 * Metoda dodaj�ca nowy listener
	 */
	public void addNewSpeedListener(NewSpeedListener listener){
		newSpeedListener=listener;
	}
	
	/**
	 * Metoda wczytuj�ca listener.
	 */
	public void callNewSpeedListener(){
		newSpeedListener.setNewSpeed();
	}
		
	/**
	 * metoda ustalaj�ca zasi�g wybuchu w zale�no�ci od poziomu trudno�ci
	 */
	
	
	/**
	 * zatrzymuje odliczanie czasu do ko�ca bonusu. Nale�y jej u�y� w przypadku pauzowania gry.
	 */
	public void countDown(Boolean bool){
		if(bool)
			timer.stop();
		else 
			timer.start();
	}
	
	/**
	 * Metoda logiczna obs�uguj�ca u�ycie bonusu
	 */
	public Boolean playerContains(Player player){
		Rectangle pusRect=new Rectangle(x+width/4,y+height/4,width/2,height/2);
		Rectangle playerRect=new Rectangle(player.x, player.y, player.width,player.height);
		if(pusRect.intersects(playerRect))
			return true;
			return false;
		}
	
	/**
	 * Rysowanie
	 */
	@Override
	public void draw(Graphics g) {
		if(playerContains(plansza.getPlayer()))
			callNewSpeedListener();
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}

	}
	

