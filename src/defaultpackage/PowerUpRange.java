package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * interfejs deklaruj�cy metode setNewSpeed()
 *
 */
interface NewRangeListener{
	void setNewRange();
}
/**
 * interfejs deklaruj�cy metod� Collected3()
 *
 */
interface Bonus3IsOverListener{
	void Collected3(PowerUpRange power3);
}
/**
 * Klasa bonusu - zwi�ksza pr�dko�� gracza zale�nie od poziomu.
 * <p>
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class PowerUpRange extends Obiekt implements ActionListener {
	
	
	/**
	 * Listener usuni�cia bonusu
	 */
	static Bonus3IsOverListener bonus3IsOverListener;
	/**
	 * Listener nowego zasi�gu.
	 */
	NewRangeListener newRangeListener;
	
	/**
	 * Bonusowa pr�dko��
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
		newRangeListener=null;
		loadImage("pur");
	}
	
	/**
	 * Metoda dodaj�ca nowy listener
	 */
	public void addNewRangeListener(NewRangeListener listener){
		newRangeListener=listener;
	}
	
	/**
	 * Metoda wczytuj�ca listener.
	 */
	public void callNewRangeListener(){
		newRangeListener.setNewRange();
	}
	
	/**
	 * Metoda dodaj�ca nowy listener
	 */
	public static void addBonus3IsOverListener(Bonus3IsOverListener listener){
		bonus3IsOverListener=listener;
	}
	/**
	 * Metoda wczytuj�ca listener.
	 */
	public void callBonusIsOver(){
		bonus3IsOverListener.Collected3(this);
	}
		
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
		Rectangle purRect=new Rectangle(x+width/4,y+height/4,width/2,height/2);
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
		if(playerContains(plansza.getPlayer())){
			callNewRangeListener();
			callBonusIsOver();
			timer.start();	
		}
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}

	

/**
 * metoda obs�uguj�ca zdarzenie
 */
public void actionPerformed(ActionEvent arg0) {
	timer.stop();
	callNewRangeListener();
}
}

	

