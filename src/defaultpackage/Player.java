package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;



/**
 * interfejs deklaruj�cy metode playerIsDead()
 *
 */
interface PlayerIsDeadListener {
	void playerIsDead(int amountOfLives, String name, int score);
}

/**
 * interfejs deklarujacy metode plyerEnemyCollided() 
 *
 */
interface PlayerEnemyCollisionListener {
	void playerEnemyCollided();
}

/**
 * Klasa gracza, dziedzicz�ca po klasie charakter.
 * <p>
 * Jest odpowiedzialna za stworzenie gracza na planszy.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Player extends Charakter implements KeyListener{
	
	/**
	 * pole przechowuj�ce nazwe gracza
	 */
	private String name;
	
	/**
	 * wynik
	 */
	private int score;
	
	/**
	 *pr�dko�� 
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
	private ArrayList<PlayerIsDeadListener> playerIsDeadListeners;
	
	/**
	 * lista s�uchaczy zdarzenia kolizji 
	 */
	private ArrayList<PlayerEnemyCollisionListener>  collisionListeners;
	
	/**
	 * Zmienna typu integer
	 */
	public int value;
	
	/**
	 * Konstruktor klasy.
	 */
	public Player(Board plansza, int x,int y, int width, int height, int speed, String name)
	{	//ustawia zerow� przedko�� poczatkowa
		super(plansza,x,y, width, height,0,0);
		score=0;
		this.name=name;
		this.speed=speed;
		alive=true;
		amountOfLives=Config.getAmountOfLives();
		playerIsDeadListeners=new ArrayList<>();
		collisionListeners=new ArrayList<>();
		loadImage("player");
		Random rand=new Random();
		value=rand.nextInt();
		
	}	
	/**
	 * Metoda ustalaj�ca parametry domy�lne
	 */
	public void setInitialSettings(){
		score=0;
		amountOfLives=Config.getAmountOfLives();
		this.setDX(0);
		this.setDY(0);
	}
	
	/**
	 * metoda dodajaca nowy listener
	 */
	public void addPlayerIsDeadListener(PlayerIsDeadListener listener){
		playerIsDeadListeners.add(listener);
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
			callPlayerIsDeadListeners();
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
			callPlayerIsDeadListeners();		
		}
		else
			amountOfLives=nb;
	}
	
	/**
	 * U�ycie s�uchacza
	 */
	public void callPlayerIsDeadListeners(){
		for(PlayerIsDeadListener listener : playerIsDeadListeners){
			listener.playerIsDead(this.amountOfLives, this.name, this.score);
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
	 * metoda dodaj�ca punkty do wyniku gracza
	 */
	public void addPoints(int amount){ score+=amount; }
	
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
	 * Metoda zwracaj�ca kszta�t obiektu graficznego
	 */
	public Rectangle getShape(){
		return new Rectangle(x+(int)(1.0/12.0*width),y+(int)(1.0/12.0*height),width*5/6,height*5/6);
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
		g.drawImage(img, this.x,this.y,this.width, this.height, null);
		
	}
	/**
	 * metoda zmieniajaca rozmiar do 0.85 rozmairu kafelka po narysowaniu mapy od nowa
	 */
	public void resize(){
		this.width=(int)(0.85*plansza.getPanelWidth()/plansza.getColumns());
		this.height=(int)(0.85*plansza.getPanelHeight()/plansza.getRows());
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
			System.out.println("d"+ value);
		}
		
		if(c==KeyEvent.VK_SPACE)
			plansza.addBomb(this.x,this.y, this.width, this.height);
		
	}

	/**
	 * metoda obs�uguj�ca akcj� zwi�zan� ze zwolnieniem klawisza
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		this.dx=0;
		this.dy=0;
		
	}

	/**
	 * Metoda obs�uguj�ca zdarzenie
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * getter, kt�ry zwraca liczb� punkt�w uzyskanych przez gracza
	 */
	public int getPoints() {
		return score;
	}
	
	/**
	 * Getter zwracaj�cy nazw�
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter zwracaj�cy wynik
	 */
	public int getScore() {
		return score;
	}
	
}

