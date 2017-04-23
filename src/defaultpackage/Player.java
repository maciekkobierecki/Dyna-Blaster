package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;



/**
 * interfejs deklaruj�cy metode playerIsDead()
 *
 */
interface GameOverListener {
	void playerIsDead();
}


/**
 * interfejs deklarujacy metode plyerEnemyCollided() 
 *
 */
interface PlayerEnemyCollisionListener {
	void playerEnemyCollided();
}

/**
 * Klasa gracza, dziedzicz�ca po klasie obiekt.
 * <p>
 * Jest odpowiedzialna za stworzenie gracza na planszy.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Player extends Charakter implements KeyListener{
	
	/**
	 * wynik
	 */
	private int score;
	
	/**
	 *pr�sko�� 
	 */
	private int speed;
	
	/**
	 * zmienna logiczna okre�laj�ca czy gracz jest "�ywy"
	 */
	private Boolean alive;
	
	/**
	 * Liczba �y�
	 */
	private int amountOfLives;
	
	/**
	 * lista s�uchaczy zdarzenia ko�ca gry
	 */
	private ArrayList<GameOverListener> gameOverListeners;
	
	/**
	 * lista s�uchaczy zdarzenia kolizji 
	 */
	private ArrayList<PlayerEnemyCollisionListener>  collisionListeners;
	
	/**
	 * Konstruktor klasy.
	 * @param plansza
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param speed
	 */
	public Player(Board plansza, int x,int y, int width, int height, int speed)
	{	//ustawia zerow� przedko�� poczatkowa
		super(plansza,x,y, width, height,0,0);
		score=0;
		this.speed=speed;
		alive=true;
		amountOfLives=Config.getAmountOfLives();
		gameOverListeners=new ArrayList<>();
		collisionListeners=new ArrayList<>();
		
	}
	
	/**
	 * metoda dodajaca nowy listener
	 */
	public void addGameOverListener(GameOverListener listener){
		gameOverListeners.add(listener);
	}
	
	/**
	 * metoda dodajaca nowy listener
	 */
	public void addCollisionListener(PlayerEnemyCollisionListener listener){
		collisionListeners.add(listener);
	}
	
	/**
	 * metoda dodajaca dodatkowe �ycia
	 */
	public void incrementLive(){
		amountOfLives++;
	}
	
	/**
	 * metoda odejmuj�ca liczb� �y�
	 */
	public void decrementLive(){
		amountOfLives--;
		if(amountOfLives==0)
			callGameOverListeners();
	}
	/**
	 * getter zwracajacy liczbe �y�
	 */
	public int getAmountOfLives() { return amountOfLives; }
	
	/**
	 * metoda ustalaj�ca liczb� �y�
	 */
	public void setAmountOfLives(int nb){
		if(nb==0){
			alive=false;
			callGameOverListeners();		
		}
		else
			amountOfLives=nb;
		
	}
	
	/**
	 * U�ycie s�uchacza
	 */
	public void callGameOverListeners(){
		for(GameOverListener listener : gameOverListeners){
			listener.playerIsDead();
		}
	}
	
	/**
	 * U�ycie s�uchacza
	 */
	public void callCollisionListeners(){
		for(PlayerEnemyCollisionListener listener : collisionListeners){
			listener.playerEnemyCollided();
		}
	}
	
	/**
	 * Metoda logiczna okre�laj�ca kolizj� gracza z potworem
	 */
	public Boolean isCollidedWithEnemy(){
		ArrayList<Obiekt> enemies=plansza.getEnemyList();
		for(int i=0; i<enemies.size(); i++){
			Rectangle thisrect=this.getShape();
			Rectangle obrect=enemies.get(i).getShape();
			if(thisrect.intersects(obrect)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Metoda odpowiedzialna za ruch
	 */
	public void move()
	{	
		if(!willCollide(dx,dy, plansza.getWallList()) && !willCollide(dx,dy,plansza.getObstacleList())){
			this.setX(this.getX()+dx);
			this.setY(this.getY()+dy);			
		}
	}
	
	/**
	 * Metoda ustalaj�ca wynik.
	 */
	public void setScore(int score){ this.score=score; }
	
	/**
	 * Metoda ustalaj�ca pr�dko��.
	 */
	public void setSpeed(int speed){ this.speed=speed; }
	
	/**
	 * Metoda odpowiedzialna za rysowanie
	 */
	public void draw(Graphics g)
	{
		if(isCollidedWithEnemy())
			callCollisionListeners();
		this.move();
		g.setColor(Color.ORANGE);
		g.fillOval(getX(),getY(), width, height);
	}

	/**
	 * metoda obs�uguj�ca wciskanie klawiszy
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		int c=arg0.getKeyCode();
		if(c== KeyEvent.VK_W)
		{
			this.dy=-speed;
				

		}
		if(c== KeyEvent.VK_S)
		{
			this.dy=speed;
		}
		if(c== KeyEvent.VK_A)
		{
			this.dx=-speed;
		}
		if(c== KeyEvent.VK_D)
		{
			this.dx=speed;
		}
		
		if(c==KeyEvent.VK_SPACE)
			plansza.addBomb(this.x,this.y);
		
	}

	/**
	 * metoda obs�uguj�ca akcj� zwi�zan� ze zwolnieniem klawisza
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		this.dx=0;
		this.dy=0;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}

