package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * interfejs deklaruj¹cy metode setNewSpeed()
 *
 */
interface NewRangeListener{
	void setNewRange();
}

/**
 * Klasa bonusu - zwiêksza prêdkoœæ gracza zale¿nie od poziomu.
 * <p>
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class PowerUpRange extends Obiekt implements ActionListener {
	
	/**
	 * Listener nowego zasiêgu.
	 */
	NewRangeListener newRangeListener;
	
	/**
	 * Bonusowa prêdkoœæ
	 */
	public int range;
	
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
	public PowerUpRange(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		timer=new Timer(Config.powerUpTime, this);
		timer.start();	
		newRangeListener=null;
		loadImage("pur2");
	}
	
	/**
	 * Metoda dodaj¹ca nowy listener
	 */
	public void addNewRangeListener(NewRangeListener listener){
		newRangeListener=listener;
	}
	
	/**
	 * Metoda wczytuj¹ca listener.
	 */
	public void callNewRangeListener(){
		newRangeListener.setNewRange();
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
		Rectangle purRect=new Rectangle(x,y,width,height);
		Rectangle playerRect=new Rectangle(player.x, player.y, player.width,player.height);
		if(purRect.intersects(playerRect))
			return true;
			return false;
		}
	
	/**
	 * Rysowanie
	 */
	@Override
	public void draw(Graphics g) {
		if(playerContains(plansza.getPlayer()))
			callNewRangeListener();
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}

	

/**
 * metoda obs³uguj¹ca zdarzenie
 */
public void actionPerformed(ActionEvent arg0) {
	timer.stop();
	callNewRangeListener();
}
}

	

