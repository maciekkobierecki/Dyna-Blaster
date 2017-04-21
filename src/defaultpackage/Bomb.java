package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;
 
interface BombExplodedListener{
	void BombExploded(Bomb bomb);
}


public class Bomb extends Obiekt implements ActionListener{
	
	static int timeToExplode=Config.timeToExplodeBomb;
	private static ArrayList<BombExplodedListener> bombExplodedListeners=new ArrayList<>();
	private int range;
	Timer timer;
	public Bomb(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		timer=new Timer(timeToExplode, this);
		timer.start();
		setRange();		
	}
	
	public static void addBombExplodedListener(BombExplodedListener listener){
		bombExplodedListeners.add(listener);
	}
	
	public void setRange(){
		if(LevelWindow.level=="easy")
			range=Config.easyLevelBombRange;
		else if(LevelWindow.level=="medium")
			range=Config.mediumLevelBombRange;
		else range=Config.hardLevelBombRange;
	}
	
	public void callBombExplodedListeners(){
		for(BombExplodedListener listener: bombExplodedListeners)
			listener.BombExploded(this);
	}
	
	public Boolean isCollided(Obiekt ob){
		Rectangle strikingDistance=new Rectangle(x-range,y-range,2*range,2*range);
		Rectangle obRect=new Rectangle(ob.x, ob.y, ob.width,ob.height);
		if(strikingDistance.intersects(obRect))
			return true;
		return false;
	}
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
