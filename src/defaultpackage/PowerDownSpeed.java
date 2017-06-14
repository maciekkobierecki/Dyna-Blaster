package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * interfejs deklaruj¹cy metode setLowSpeed()
 *
 */
interface LowSpeedListener{
	void setLowSpeed();
	void setOldSpeed();
}

/**
 * Klasa bonusu - zwiêksza prêdkoœæ gracza zale¿nie od poziomu.
 * <p>
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class PowerDownSpeed extends Obiekt implements ActionListener{
	
	/**
	 * Listener nowej prêdkoœci.
	 */
	LowSpeedListener lowSpeedListener;
	
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
	public PowerDownSpeed(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		timer=new Timer(Config.powerUpTime, this);	
		lowSpeedListener=null;
		loadImage("pds");
	}
	
	/**
	 * Metoda dodaj¹ca nowy listener
	 */
	public void addLowSpeedListener(LowSpeedListener listener){
		lowSpeedListener=listener;
	}
	
	/**
	 * Metoda wczytuj¹ca listener.
	 */
	public void callLowSpeedListener(){
		lowSpeedListener.setLowSpeed();
	}
	
	public void callOldSpeedListener(){
		lowSpeedListener.setOldSpeed();
	}	
	
	
	/**
	 * zatrzymuje odliczanie czasu do koñca bonusu. Nale¿y jej u¿yæ w przypadku pauzowania gry.
	 */
	public void countDown(Boolean bool){
		if(bool)
			timer.stop();
		else 
			timer.start();
	}
	
	/**
	 * Metoda logiczna obs³uguj¹ca u¿ycie bonusu
	 */
	public Boolean playerContains(Player player){
		Rectangle pdsRect=new Rectangle(x+width/4,y+height/4,width/2,height/2);
		Rectangle playerRect=new Rectangle(player.x, player.y, player.width,player.height);
		if(pdsRect.intersects(playerRect))
			return true;
			return false;
		}
	
	/**
	 * Rysowanie
	 */
	@Override
	public void draw(Graphics g) {
		if(playerContains(plansza.getPlayer()))
			callLowSpeedListener();
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		timer.stop();
		callOldSpeedListener();
	}
	}
	

