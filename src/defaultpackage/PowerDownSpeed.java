package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * interfejs deklaruj¹cy metode setLowSpeed() oraz setOldSpeed()
 */
interface LowSpeedListener{
	void setLowSpeed();
	void setOldSpeed();
}

/**
 * interfejs deklaruj¹cy metodê Collected2()
 */
interface Bonus2IsOverListener{
	void Collected2(PowerDownSpeed power2);
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
	 * Listener usuniêcia bonusu
	 */
	static Bonus2IsOverListener bonus2IsOverListener;
	
	/**
	 * Timer
	 */
	Timer timer;
	
	/**
	 * Konstruktor parametryczny bonusu
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
	 * Metoda dodaj¹ca nowy listener
	 */
	public static void addBonus2IsOverListener(Bonus2IsOverListener listener){
		bonus2IsOverListener=listener;
	}
	
	/**
	 * Metoda wczytuj¹ca listener.
	 */
	public void callLowSpeedListener(){
		lowSpeedListener.setLowSpeed();
	}
	
	/**
	 * Metoda wczytuj¹ca listener.
	 */
	public void callOldSpeedListener(){
		lowSpeedListener.setOldSpeed();
	}	
	
	/**
	 * Metoda wczytuj¹ca listener.
	 */
	public void callBonus2IsOver(){
		bonus2IsOverListener.Collected2(this);
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
		if(playerContains(plansza.getPlayer())){
			callLowSpeedListener();
			callBonus2IsOver();
			timer.start();
		}
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}
	
	/**
	 * metoda obs³uguj¹ca zdarzenie
	 */
	public void actionPerformed(ActionEvent arg0) {
		timer.stop();
		callOldSpeedListener();
	}
	}
	

