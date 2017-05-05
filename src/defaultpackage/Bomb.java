package defaultpackage;

import java.awt.Color;
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
	 * Zasi�g wybuchu
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
	public void setRange(){
		if(LevelWindow.level=="easy")
			range=Config.easyLevelBombRange;
		else if(LevelWindow.level=="medium")
			range=Config.mediumLevelBombRange;
		else range=Config.hardLevelBombRange;
	}
	
	/**
	 * metoda �aduj�cy dany listener
	 */
	public void callBombExplodedListeners(){
		for(BombExplodedListener listener: bombExplodedListeners)
			listener.BombExploded(this);
	}

	/**
	 * metoda logiczna odpowiedzalna za obs�ug� kolizji
	 */
	public Boolean isCollided(Obiekt ob){
		Rectangle strikingDistance=new Rectangle(x-range,y-range,2*range,2*range);
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
		g.setColor(Color.RED);
		g.fillOval(getX(),getY(), width, height);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.stop();
		callBombExplodedListeners();
		
		
	}
	
}