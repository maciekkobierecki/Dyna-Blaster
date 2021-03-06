package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;

/**
 * Klasa planszy.
 * <p>
 * Odpowiada za tworzenie mapy oraz poruszanie gracza.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Board extends JPanel implements ActionListener,Bonus3IsOverListener, BombExplodedListener, BonusIsOverListener, Bonus2IsOverListener{


	private static final long serialVersionUID = 1L;
	
	/**
	 * ilo�� rz�d�w
	 */
	private int rows;
	
	/**
	 * ilo�� kolumn
	 */
	private int columns;
	
	/**
	 * obiekt z reprezentacj� graficzn� gracza
	 */
	private Player player;
	
	/**
	 * obiekt z reprezentacj� graficzn� bomby
	 */
	private Bomb bomb;
	
	/**
	 * lista przetrzymuj�ca obiekty �cian
	 */
    ArrayList<Obiekt> wallList;
    
    /**
     * lista przetrzymuj�ca obiekty pod�ogi
     */
    ArrayList<Obiekt>floorList;
    
    /**
     * lista zawierajaca potwory
     */
    ArrayList<Obiekt>enemyList;
    
    /**
     * lista przechowuj�ca przeszk�d
     */
    ArrayList<Obiekt>obstacleList;
    
    /**
     * lista przechowuj�ca bomby
     */
    ArrayList<Obiekt>bombList;
    
    /**
     * Lista przechowuj�ca obiekty wybuchu
     */
    ArrayList<Obiekt>explosionList;
    
    /**
     * obiekt reprezentuj�cy przejscie do nast�pnego poziomu
     */
    Door door;
    
    /**
     * Lista przechowuj�ca s�uchaczy bonusu
     */
    ArrayList<Obiekt>pusList;
    
    /**
     * Lista przechowuj�ca s�uchaczy bonusu
     */
    ArrayList<Obiekt>purList;
    
    /**
     * Lista przechowuj�ca s�uchaczy bonusu
     */
    ArrayList<Obiekt>pdsList;
    
    /**
     * obraz na kt�rym rysuje si� gra a nastepnie wy�wietlany jest na ekranie
     */
    BufferedImage bufferedImage;
   
    /**
     * obiekt typu Graphics
     */
    Graphics dbg;
    
    /**
     * zmienna logiczna
     */
	Boolean firstDrawing;
    
    /**
     * szeroko�� obszaru gry
     */
    private int panelWidth;
    
    /**
     * wysoko�� obszaru gry
     */
    private int panelHeight;
    
    /**
     * konstruktor klasy
     */
    public Board()
    {	
    	super();
    	rows=0;
    	columns=0;
       	this.setPreferredSize(new Dimension(300,300));
        wallList=new ArrayList<>();
       	floorList=new ArrayList<>();
       	enemyList=new ArrayList<>();
       	bombList=new ArrayList<>();
        obstacleList=new ArrayList<>();
        explosionList=new ArrayList<>();
       	setFocusable(true);
       	panelWidth=0;
       	panelHeight=0;
       	player=null;
       	bomb=null;
       	door=null;
       	pusList=new ArrayList<>();
       	pdsList=new ArrayList<>();
       	purList=new ArrayList<>();
       	Bomb.addBombExplodedListener(this);
        PowerUpSpeed.addBonusIsOverListener(this);
        PowerDownSpeed.addBonus2IsOverListener(this);
        PowerUpRange.addBonus3IsOverListener(this);
       	
       	firstDrawing=true;
       	
   }
       	
       	
    /**
     * getter zwracaj�cy szeroko�� pola gry
     */
    public int getPanelWidth() { return panelWidth; }
    
    /**
     * getter zwracaj�cy wysoko�� pola gry
     */
    public int getPanelHeight() { return panelHeight; }
    
    /**
     * getter zwracaj�cy liste �cian
     */
    public ArrayList<Obiekt> getWallList() { return wallList; }
    
    /**
     * getter zwracaj�cy liste potwor�w
     */
    public ArrayList<Obiekt> getEnemyList() { return enemyList; }
    
    /**
     * getter zwracaj�cy liste przeszk�d
     */
    public ArrayList<Obiekt> getObstacleList() { return obstacleList; }
    
    /**
     * getter zwracaj�cy liczbe wierszy pola gry
     */
    public int getAmountOfRows() { return rows; }
    
    /**
     * getter zwracaj�cy liczbe kolumn pola gry
     */
    public int getAmountOfColumns() { return columns; }
    
    /**
     * getter zwracaj�cy obiekt klasy Player
     */   
    public Player getPlayer() { return player; }
    
    /**
     * getter zwracaj�cy obiekt klasy Bomb
     */   
    public Bomb getBomb() { return bomb; }
    
    /**
     * getter zwracaj�cy referencje do obiektu przejscia do nastepnegu poziomu
     */
    public Door getDoor() { return door; }
    
    /**
     * getter zwracaj�cy liste bonus�w
     */
    public ArrayList<Obiekt> getPusList() { return pusList; }
    
    /**
     * getter zwracaj�cy liste bonus�w
     */
    public ArrayList<Obiekt> getPdsList() { return pdsList; }
    
    /**
     * getter zwracaj�cy liste bonus�w
     */
    public ArrayList<Obiekt> getPurList() { return purList; }
    
    /**
     * getter zwracaj�cy liste bomb
     */
    public ArrayList<Obiekt> getBombList() { return bombList; }
    
    /**
     * pole przechowuj�ce rozmiar jednego pola w grze
     */
    Dimension fieldDimension;
    
    /**
     * metoda dodaj�ca bomby do listy bomb
     * @param x - koordynata x w uk�adzie wsp�rz�dnych
     * @param y -koordynata y w uk�adzie wsp�rz�dnych
     */
    public void addBomb(int x, int y, int width, int height) {
		bombList.add(new Bomb(this,x,y, width, height));
		
	}
    
    /**
     * metoda usuwaj�ca z planszy bonus po zjedzeniu
     */
    public void Collected(PowerUpSpeed power){
    	pusList.remove(power);
    }
    
    /**
     * metoda usuwaj�ca z planszy bonus po zjedzeniu
     */
    public void Collected2(PowerDownSpeed power2){
    	pdsList.remove(power2);
    }
    
    /**
     * metoda usuwaj�ca bombe przekazywan� w parametrze
     */
    public void removeBomb(Bomb bomb){
    	bombList.remove(bomb);
    }
    
    /** 
     * metoda wywo�ywana podczas eksplozji bomby
     */
    @Override
	public void BombExploded(Bomb bomb) {
    	showExplosionAnimation(bomb);
    	removeDestructedObjects(bomb);
		removeBomb(bomb);
		Thread t1=new Thread(new Runnable(){
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				explosionList.clear();
			}
		});
		t1.start();
	}
    
    /**
     * metoda odpowiedzialna za graficzn� reprezentacj� eksplozji
     * @param bomb obiekt klasy Bomb
     */
    private void showExplosionAnimation(Bomb bomb) {
		Rectangle explosionRange=bomb.getExplosionRange(fieldDimension);
		for(int i=0; i<this.floorList.size(); i++){
			Rectangle floorRect=floorList.get(i).getShape();
			if(explosionRange.intersects(floorRect))
				explosionList.add((Obiekt)new Explosion(this, floorRect.x, floorRect.y, floorRect.width, floorRect.height));
		}
		
	}

	/**
     * metoda usuwaj�ca obiekty, kt�re zosta�y zniszczone przez bombe
     */
    public void removeDestructedObjects(Bomb bomb){
    	for(Iterator<Obiekt> it=enemyList.iterator(); it.hasNext();){
    		Obiekt enemy=it.next();
    		if(bomb.isCollided(enemy, fieldDimension)){
    			it.remove();
    			player.addPoints(Config.getPoints());
    		}
    	}
    	for(Iterator<Obiekt> iter=obstacleList.iterator(); iter.hasNext();){
    		Obiekt obstacle=iter.next();
    		if(bomb.isCollided(obstacle, fieldDimension))
    		iter.remove();
    	}
    	if(bomb.isCollided(player, fieldDimension))
    		player.callPlayerIsDeadListeners();
    	
    		
    }

  /**
   * metoda tworz�ca mape
   * @param mapData- tablica przechowuj�ca informacje o wygl�dzie pola gry
   * f - tworzenie pod�ogi
   * w - tworzenie �ciany
   * e - tworzenie potwora
   * p - tworzenie gracza
   * o - twoprzenie przeszkody
   * d - tworzenie drzwi
   * s - powerUp zwi�kszaj�cy pr�dko��
   * q - powerUp zmiejszaj�cy pr�dko��
   * s - powerUp zwi�kszaj�cy si�� ra�enia bomby
   */
    public void createMap(ArrayList<String> mapData)
    {
    	
    	rows=mapData.size();
    	columns=mapData.get(0).length();
    	int width=this.getWidth()/columns;
    	int height=this.getHeight()/rows;
    	panelWidth=this.getWidth();
    	panelHeight=this.getHeight();
    	floorList.clear();
		wallList.clear();
		enemyList.clear();
		obstacleList.clear();
		pusList.clear();
		pdsList.clear();
		purList.clear();
    	//0 oznacza brak sciany, 1 oznacza, ¿e jest sciana, 2 oznacza ze jest potwor, 3 player, 4 przeszkoda, 5 przeszkoda a pod ni� drzwi do nastepnego poziomu
    	for(int j=0; j<mapData.size(); j++)
    	{
    		String str=mapData.get(j);
    		for(int i=0; i<columns; i++)
    		{
    			switch(str.charAt(i))
    			{
    				case 'f':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 	
    					 break;
    				case 'w':
    					wallList.add(new Wall(this, i*width, j*height, width, height));   
    					break;
    				case 'e':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 	
    					 enemyList.add(new Enemy(this, i*width, j*height, width, height));   
    					 break;
    				case 'p':
    					if(player==null){
    						player= new Player(this,i*width,j*height, (int)(0.85*width), (int)(0.85*height), Config.getPlayerSpeed(), NickWindow.playerName);
    						addKeyListener(player);					
    					}
    					else{
    					player.setPosition(i*width, j*height);
    					player.resize();
    					}
    					floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					
    					break;
    				case 'o':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
    					 break;
    				case 'd':
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
    					 door=new Door(this, i*width, j*height,width,height);
    					 break;
    				case 's':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
   					 	 pusList.add(new PowerUpSpeed(this, i*width, j*height,width,height));
    					 break;
    				case 'q':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
  					 	 pdsList.add(new PowerDownSpeed(this, i*width, j*height,width,height));
  					 	 break;
    				case 'r':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
  					 	 purList.add(new PowerUpRange(this, i*width, j*height,width,height));
   					 break;
    					
    					
    					
    			}
    		}
    	}
    	fieldDimension=new Dimension(panelWidth/columns, panelHeight/rows);
       	
    }
    

    /**
     * metoda zwracaj�ca liczb� kolumn
     */
    public int getColumns() { return columns; }
    
    /**
     * metoda zwracaj�ca liczb� rz�d�w
     */
    public int getRows() { return rows; }

    /**
     * metoda odpowiedzialna za ruch potwor�w
     */
    public void moveEnemies(){
    	for(Obiekt enemy : enemyList){
    		((Enemy) enemy).move();
    	}
    }

   
    
   /**
    * metoda odpowiedzialna za rysowanie obiekt�w
    */
   public void paintComponent(Graphics g)
   {
	BufferedImage dbImage=new BufferedImage(panelWidth, panelHeight,BufferedImage.TYPE_INT_ARGB);
    Graphics dbg=dbImage.createGraphics();
	super.paintComponent(dbg); 
    dbg.setColor(Color.white);
   	dbg.fillRect(0, 0, getWidth(), getHeight());
   	//drawGridOnBoard(g);
   
   	for (Obiekt ob : wallList)
   		ob.draw(dbg);   	
  	door.draw(dbg);
  	for (Obiekt ob : floorList)
   		ob.draw(dbg); 
  	for (int i=0; i<pusList.size();i++)
   		pusList.get(i).draw(dbg);
  	for (int i=0; i<pdsList.size();i++)
   		pdsList.get(i).draw(dbg);
  	for (Obiekt ob : purList)
   		ob.draw(dbg);    	
   	for (Obiekt ob: obstacleList)
   		ob.draw(dbg);
   	for (Obiekt ob: enemyList)
   		ob.draw(dbg);
   	for (Obiekt ob: obstacleList)
   		ob.draw(dbg);
   	for (Obiekt ob: bombList)
   		ob.draw(dbg);
   	for(int i=0; i<explosionList.size(); i++)
   		explosionList.get(i).draw(dbg);   	
   	player.draw(dbg);
   	BufferedImage scaled=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	   Graphics2D gg=scaled.createGraphics();
	   gg.drawImage(dbImage,0, 0, getWidth(), getHeight(),null);
	   g.drawImage(scaled,0,0, this);
   	
   }
   
    /**
     * rysuje siatke na calej planszy
     * @param g - kontekst graficzny
     */
    public void drawGridOnBoard(Graphics g){
    	Color color=new Color(153,153,255, 128);
    	for(int i=10; i<panelWidth; i+=15){
    		g.setColor(color);
    		g.drawLine(i, 0, i, panelHeight);
    	}
    	for(int i=10; i<panelHeight; i+=15){
    		g.drawLine(0, i, panelWidth, i);
    	}
    }
	

    /**
     * metoda obs�uguj�ca zdarzenie
     */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}


	@Override
	public void Collected3(PowerUpRange power3) {
		purList.remove(power3);
		
	}


	


	


	


	


	
	
	
    
    
    
	
}