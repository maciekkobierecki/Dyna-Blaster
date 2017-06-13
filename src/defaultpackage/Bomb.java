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
	 * Lista
	 */
	private static ArrayList<BombExplodedListener> bombExplodedListeners=new ArrayList<>();
	
	/**
	 * Zasiêg wybuchu
	 */
	private int range;
	
	/**
	 * Timer
	 */
	Timer timer;
	
	
	
	/**
	 * Konstruktor parametryczny klasy.
	 * @param plansza
	 * @param x 
	 * @param y 
	 * @param xwidth
	 * @param xheight
	 */
	public Bomb(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		timer=new Timer(timeToExplode, this);
		timer.start();
		setRange();		
		loadImage("bomb");
	}
	/**
	 * metoda zwracaj¹ca prostok¹t, który symbolizuje pole ra¿enia wybuchu bomby
	 * przyjmuje jako parametr rozmiar jednego pola gry
	 */
	public Rectangle getExplosionRange(Dimension dim){
		return new Rectangle(x+width/2-range*(int)dim.getWidth()/2, y+height/2-range*(int)dim.getHeight()/2, range*(int)dim.getWidth(),range*(int)dim.getHeight());
	}
	/**
	 * metoda dodaj¹ca Listener 
	 */
	public static void addBombExplodedListener(BombExplodedListener listener){
		bombExplodedListeners.add(listener);
	}
	
	/**
	 * metoda ustalaj¹ca zasiêg wybuchu w zale¿noœci od poziomu trudnoœci
	 */
	public void setRange(){
		if(LevelWindow.level=="easy")
			range=Config.easyLevelBombRange;
		else if(LevelWindow.level=="medium")
			range=Config.mediumLevelBombRange;
		else range=Config.hardLevelBombRange;
	}
	
	/**
	 * metoda ³aduj¹cy dany listener
	 */
	public void callBombExplodedListeners(){
		for(BombExplodedListener listener: bombExplodedListeners)
			listener.BombExploded(this);
	}

	/**
	 * metoda zwracaj¹ca true gdy obiekt klasy Obiekt jest w zasiêgu
	 * wybuchu bomby (zasiêg wybuchu definiowany jest w pliku konfiguracyjnym)
	 * jako paraametr przyjmuje obiekt, z którym sprawdzana jest kolizja oraz wymiar jednego pola na planszy
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.stop();
		callBombExplodedListeners();
		
		
	}
	
}
