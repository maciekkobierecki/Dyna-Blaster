package defaultpackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

/**
 * interfejs deklarujacy metode BombExploded() 
 *
 */
interface BombExplodedListener{
	void BombExploded(Bomb bomb);
}

/**
 * Klasa bomby.
 * <p>
 * Odpowiada za tworzenie bomb i zwiazanych z nimi mechanizmami (wybuchy, niszczenie).
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Bomb extends Obiekt implements ActionListener{
	
	/**
	 * Czas do wybuchu bomby.
	 */
	static int timeToExplode=Config.timeToExplodeBomb;
	
	/**
	 * Lista s�uchaczy
	 */
	private static ArrayList<BombExplodedListener> bombExplodedListeners=new ArrayList<>();
	
	/**
	 * Zasi�g wybuchu
	 */
	public static int range;
	
	/**
	 * Timer
	 */
	Timer timer;
	
	/**
	 * zmienna odpowiedzialna za przelicznik ra�enia (zastosowanie do bonusu)
	 */
	private int r=1;
	
	/**
	 * Konstruktor parametryczny klasy.
	 */
	public Bomb(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		timer=new Timer(timeToExplode, this);
		timer.start();
		setRange(r);
		loadImage("bomb");
	}
	
	/**
	 * metoda zwracaj�ca prostok�t, kt�ry symbolizuje pole ra�enia wybuchu bomby
	 * przyjmuje jako parametr rozmiar jednego pola gry
	 */
	public Rectangle getExplosionRange(Dimension dim){
		return new Rectangle(x+width/2-range*(int)dim.getWidth()/2, y+height/2-range*(int)dim.getHeight()/2, range*(int)dim.getWidth(),range*(int)dim.getHeight());
	}
	
	/**
	 * metoda dodaj�ca Listener 
	 */
	public static void addBombExplodedListener(BombExplodedListener listener){
		bombExplodedListeners.add(listener);
	}
	
	/**
	 * metoda ustalaj�ca zasi�g wybuchu w zale�no�ci od poziomu trudno�ci
	 */
	public void setRange(int r){
		if(LevelWindow.level=="easy")
			range=(Config.easyLevelBombRange)*r;
		else if(LevelWindow.level=="medium")
			range=(Config.mediumLevelBombRange)*r;
		else range=(Config.hardLevelBombRange)*r;
	}
	
	/*public void setBonusRange(){
		if(LevelWindow.level=="easy")
			{range=4;
			System.out.println("elo");
			}		
		else if(LevelWindow.level=="medium")
			range=Config.mediumLevelBombRange;
		else range=Config.hardLevelBombRange;
	}*/
	
	/**
	 * zatrzymuje odliczanie czasu do wybuchu. Nale�y jej u�y� w przypadku pauzowania gry.
	 */
	public void countDown(Boolean bool){
		if(bool)
			timer.stop();
		else 
			timer.start();
	}
	
	/**
	 * metoda �aduj�cy dany listener
	 */
	public void callBombExplodedListeners(){
		for(BombExplodedListener listener: bombExplodedListeners)
			listener.BombExploded(this);
	}

	/**
	 * metoda zwracaj�ca true gdy obiekt klasy Obiekt jest w zasi�gu
	 * wybuchu bomby (zasi�g wybuchu definiowany jest w pliku konfiguracyjnym)
	 * jako paraametr przyjmuje obiekt, z kt�rym sprawdzana jest kolizja oraz wymiar jednego pola na planszy
	 */
	public Boolean isCollided(Obiekt ob, Dimension dim){
		Rectangle strikingDistance=getExplosionRange(dim);
		Rectangle obRect=new Rectangle(ob.x, ob.y, ob.width,ob.height);
		if(strikingDistance.intersects(obRect))
			return true;
		return false;
	}

	/**
	 * Rysowanie
	 */
	@Override
	void draw(Graphics g) {
		g.drawImage(img, this.x,this.y, this.width, this.height,null);
		
	}
	
	/**
	 * metoda obs�uguj�ca zdarzenie
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.stop();
		callBombExplodedListeners();
	}
	
}
