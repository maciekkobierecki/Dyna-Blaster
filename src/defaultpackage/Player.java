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
 *
 */

interface GameOverListener {
	void playerIsDead();
}


/**
 * interfejs deklarujacy metode plyerEnemyCollided() 
 * @author Maciek
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

	private int score;
	private int speed;
	private Boolean alive;
	private int amountOfLives;
	private ArrayList<GameOverListener> gameOverListeners;
	private ArrayList<PlayerEnemyCollisionListener>  collisionListeners;
	public Player(Board plansza, int x,int y, int width, int height, int speed)
	{	//ustawia zerow¹ przedkoœæ poczatkowa
		super(plansza,x,y, width, height,0,0);
		score=0;
		this.speed=speed;
		alive=true;
		gameOverListeners=new ArrayList<>();
		collisionListeners=new ArrayList<>();
		
	}
	
	public void addGameOverListener(GameOverListener listener){
		gameOverListeners.add(listener);
	}
	public void addCollisionListener(PlayerEnemyCollisionListener listener){
		collisionListeners.add(listener);
	}
	
	public void incrementLive(){
		amountOfLives++;
	}
	public void decrementLive(){
		amountOfLives--;
	}
	
	
	public void setAmountOfLives(int nb){
		if(nb==0){
			alive=false;
			callGameOverListeners();		
		}
		else
			amountOfLives=nb;
		
	}
	public void callGameOverListeners(){
		for(GameOverListener listener : gameOverListeners){
			listener.playerIsDead();
		}
	}
	public void callCollisionListeners(){
		for(PlayerEnemyCollisionListener listener : collisionListeners){
			listener.playerEnemyCollided();
		}
	}
	
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
	
	public void move()
	{	
		if(!willCollide(dx,dy, plansza.getWallList()) && !willCollide(dx,dy,plansza.getObstacleList())){
			this.setX(this.getX()+dx);
			this.setY(this.getY()+dy);			
		}
	}
	
	public void setScore(int score){ this.score=score; }
	public void setSpeed(int speed){ this.speed=speed; }
	
	public void draw(Graphics g)
	{
		if(isCollidedWithEnemy())
			callCollisionListeners();
		this.move();
		g.setColor(Color.ORANGE);
		g.fillOval(getX(),getY(), width, height);
	}

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
