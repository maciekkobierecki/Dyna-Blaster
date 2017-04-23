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
 * interfejs deklaruj¹cy metode playerIsDead()
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
 * Klasa gracza, dziedzicz¹ca po klasie obiekt.
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
	 *prêskoœæ 
	 */
	private int speed;
	
	/**
	 * zmienna logiczna okreœlaj¹ca czy gracz jest "¿ywy"
	 */
	private Boolean alive;
	
	/**
	 * Liczba ¿yæ
	 */
	private int amountOfLives;
	
	/**
	 * lista s³uchaczy zdarzenia koñca gry
	 */
	private ArrayList<GameOverListener> gameOverListeners;
	
	/**
	 * lista s³uchaczy zdarzenia kolizji 
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
	{	//ustawia zerow¹ przedkoœæ poczatkowa
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
	 * metoda dodajaca dodatkowe ¿ycia
	 */
	public void incrementLive(){
		amountOfLives++;
	}
	
	/**
	 * metoda odejmuj¹ca liczbê ¿yæ
	 */
	public void decrementLive(){
		amountOfLives--;
		if(amountOfLives==0)
			callGameOverListeners();
	}
	/**
	 * getter zwracajacy liczbe ¿yæ
	 */
	public int getAmountOfLives() { return amountOfLives; }
	
	/**
	 * metoda ustalaj¹ca liczbê ¿yæ
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
	 * U¿ycie s³uchacza
	 */
	public void callGameOverListeners(){
		for(GameOverListener listener : gameOverListeners){
			listener.playerIsDead();
		}
	}
	
	/**
	 * U¿ycie s³uchacza
	 */
	public void callCollisionListeners(){
		for(PlayerEnemyCollisionListener listener : collisionListeners){
			listener.playerEnemyCollided();
		}
	}
	
	/**
	 * Metoda logiczna okreœlaj¹ca kolizjê gracza z potworem
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
	 * Metoda ustalaj¹ca wynik.
	 */
	public void setScore(int score){ this.score=score; }
	
	/**
	 * Metoda ustalaj¹ca prêdkoœæ.
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
	 * metoda obs³uguj¹ca wciskanie klawiszy
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
	 * metoda obs³uguj¹ca akcjê zwi¹zan¹ ze zwolnieniem klawisza
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

