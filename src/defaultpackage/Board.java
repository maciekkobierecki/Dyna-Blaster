package defaultpackage;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;

/**
 * Klasa planszy.
 * <p>
 * Odpowiada za tworzenie mapy oraz poruszanie gracza.
 * 
<<<<<<< HEAD
 * 
=======
>>>>>>> origin/master
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Board extends JPanel implements ActionListener, BombExplodedListener{


	private static final long serialVersionUID = 1L;

	
	/**
	 * iloœæ rzêdów
	 */
	private int rows;
	/**
	 * iloœæ kolumn
	 */
	private int columns;
	/**
	 * obiekt z reprezentacj¹ graficzn¹ gracza
	 */
	private Player player;
	/**
	 * lista przetrzymuj¹ca obiekty œcian
	 */
    ArrayList<Obiekt> wallList;
    /**
     * lista przetrzymuj¹ca obiekty pod³ogi
     */
    ArrayList<Obiekt>floorList;
    /**
     * lista zawierajaca potwory
     */
    ArrayList<Obiekt>enemyList;
    /**
     * lista przechowuj¹ca przeszkód
     */
    ArrayList<Obiekt>obstacleList;
    /**
     * lista przechowuj¹ca bomby
     */
    ArrayList<Obiekt>bombList;
    /**
     * obiekt reprezentuj¹cy przejscie do nastêpnego poziomu
     */
    Door door;
    /**
     * obraz na którym rysuje siê gra a nastepnie wyœwietlany jest na ekranie
     */
    BufferedImage bimage;
    /**
     * szerokoœæ obszaru gry
     */
    private int panelWidth;
    /**
     * wysokoœæ obszaru gry
     */
    private int panelHeight;
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
       	setFocusable(true);
       	panelWidth=0;
       	panelHeight=0;
       	player=null;
       	door=null;
       	Bomb.addBombExplodedListener(this);
       	
   }
       	
       	
    /**
     * getter zwracaj¹cy szerokoœæ pola gry
     */
    public int getPanelWidth() { return panelWidth; }
    /**
     * getter zwracaj¹cy wysokoœæ pola gry
     */
    public int getPanelHeight() { return panelHeight; }
    /**
     * getter zwracaj¹cy liste œcian
     */
    public ArrayList<Obiekt> getWallList() { return wallList; }
    /**
     * getter zwracaj¹cy liste potworów
     */
    public ArrayList<Obiekt> getEnemyList() { return enemyList; }
    /**
     * getter zwracaj¹cy liste przeszkód
     */
    public ArrayList<Obiekt> getObstacleList() { return obstacleList; }
    /**
     * getter zwracaj¹cy liczbe wierszy pola gry
     */
    public int getAmountOfRows() { return rows; }
    /**
     * getter zwracaj¹cy liczbe kolumn pola gry
     */
    public int getAmountOfColumns() { return columns; }
    /**
     * getter zwracaj¹cy obiekt klasy Player
     */   
    public Player getPlayer() { return player; }
    /**
     * getter zwracaj¹cy referencje do obiektu przejscia do nastepnegu poziomu
     */
    public Door getDoor() { return door; }
    
    
    /**
     * metoda dodaj¹ca bomby do listy bomb
     * @param x - koordynata x w uk³adzie wspó³rzêdnych
     * @param y -koordynata y w uk³adzie wspó³rzêdnych
     */
    public void addBomb(int x, int y) {
		bombList.add(new Bomb(this,x,y, getWidth()/(2*columns), getHeight()/(2*rows)));
		
	}
    /**
     * metoda usuwaj¹ca bombe przekazywan¹ w parametrze
     */
    public void removeBomb(Bomb bomb){
    	bombList.remove(bomb);
    }
    
    /** 
     * metoda wywo³ywana podczas eksplozji bomby
     */
    @Override
	public void BombExploded(Bomb bomb) {
    	removeDestructedObjects(bomb);
		removeBomb(bomb);
		
	}
    /**
     * metoda usuwaj¹ca obiekty, które zosta³y zniszczone przez bombe
     */
    public void removeDestructedObjects(Bomb bomb){
    	for(Iterator<Obiekt> it=enemyList.iterator(); it.hasNext();){
    		Obiekt enemy=it.next();
    		if(bomb.isCollided(enemy))
    			it.remove();
    	}
    	for(Iterator<Obiekt> iter=obstacleList.iterator(); iter.hasNext();){
    		Obiekt obstacle=iter.next();
    		if(bomb.isCollided(obstacle))
    		iter.remove();
    	}
    	
    }

  /**
   * metoda tworz¹ca mape
   * @param mapData- tablica przechowuj¹ca informacje o wygl¹dzie pola gry
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
    
    	//0 oznacza brak sciany, 1 oznacza, Â¿e jest sciana, 2 oznacza ze jest potwor, 3 player, 4 przeszkoda, 5 przeszkoda a pod ni¹ drzwi do nastepnego poziomu
    	for(int j=0; j<mapData.size(); j++)
    	{
    		String str=mapData.get(j);
    		for(int i=0; i<columns; i++)
    		{
    			switch(str.charAt(i))
    			{
    				case '0':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 	
    					 break;
    				case '1':
    					wallList.add(new Wall(this, i*width, j*height, width, height));   
    					break;
    				case '2':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 	
    					 enemyList.add(new Enemy(this, i*width, j*height, width, height));   
    					 break;
    				case '3':
    					if(player==null){
    						player= new Player(this,i*width,j*height, (int)(0.85*width), (int)(0.85*height), Config.getPlayerSpeed());
    						addKeyListener(player);					
    					}
    					else{
    					player.setPosition(i*width, j*height);
    					player.resize();
    					}
    					floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					
    					break;
    				case '4':
    					 floorList.add(new Floor(this, i*width, j*height,Color.white, width,height)); 
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
    					 break;
    				case '5':
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
    					 door=new Door(this, i*width, j*height,width,height);
    					 
    					
    					
    					
    			}
    		}
    	}
       	
    }
    

    /**
     * metoda zwracaj¹ca liczbê kolumn
     */
    public int getColumns() { return columns; }
    
    /**
     * metoda zwracaj¹ca liczbê rzêdów
     */
    public int getRows() { return rows; }

    /**
     * metoda odpowiedzialna za ruch potworów
     
     */
    public void moveEnemies(){
    	for(Obiekt enemy : enemyList)
    	{
    		((Enemy) enemy).move();
    		
    	}
   
    }

    /**
     * Rysowanie
     * 
     */
   public void paint(Graphics g)
   {
	   
	   BufferedImage dbImage=new BufferedImage(panelWidth, panelHeight,BufferedImage.TYPE_INT_ARGB);
	   Graphics dbg=dbImage.createGraphics();
	   paintComponent(dbg);
	   
	   BufferedImage scaled=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	   Graphics2D gg=scaled.createGraphics();
	   gg.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
	   g.drawImage(scaled, 0, 0, this);
   }
    
   /**
    * metoda odpowiedzialna za rysowanie obiektów
    */
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.white);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	door.draw(g);
    	drawGridOnBoard(g);
    	for (Obiekt ob : wallList)
    		ob.draw(g);
    	for (Obiekt ob : floorList)
    		ob.draw(g);    	
    	for (Obiekt ob: obstacleList)
    		ob.draw(g);
    	for (Obiekt ob: enemyList)
    		ob.draw(g);
    	for (Obiekt ob: bombList)
    		ob.draw(g);
    	player.draw(g);
    	
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
	
	





	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}



	


	


	


	
	
	
    
    
    
	
}