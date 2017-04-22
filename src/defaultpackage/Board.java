package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Board extends JPanel implements ActionListener, BombExplodedListener{

	
	
	
	private int rows;
	private int columns;
	private Player player;
    ArrayList<Obiekt> wallList;
    ArrayList<Obiekt>floorList;
    ArrayList<Obiekt>enemyList;
    ArrayList<Obiekt>obstacleList;
    ArrayList<Obiekt>bombList;
    Door door;
    BufferedImage bimage;
    private int panelWidth;
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
       	
       	

    public int getPanelWidth() { return panelWidth; }
    public int getPanelHeight() { return panelHeight; }
    public ArrayList<Obiekt> getWallList() { return wallList; }
    public ArrayList<Obiekt> getEnemyList() { return enemyList; }
    public ArrayList<Obiekt> getObstacleList() { return obstacleList; }
    public int getAmountOfRows() { return rows; }
    public int getAmountOfColumns() { return columns; }
    public Player getPlayer() { return player; }
    public Door getDoor() { return door; }
    
    
    
    public void addBomb(int x, int y) {
		bombList.add(new Bomb(this,x,y, getWidth()/(2*columns), getHeight()/(2*rows)));
		
	}
    
    public void removeBomb(Bomb bomb){
    	bombList.remove(bomb);
    }
    
    @Override
	public void BombExploded(Bomb bomb) {
    	removeDestructedObjects(bomb);
		removeBomb(bomb);
		
	}
    
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
    					 floorList.add(new Floor(this, i*width, j*height,Color.GRAY, width,height)); 	
    					 break;
    				case '1':
    					wallList.add(new Wall(this, i*width, j*height, width, height));   
    					break;
    				case '2':
    					 floorList.add(new Floor(this, i*width, j*height,Color.GRAY, width,height)); 	
    					 enemyList.add(new Enemy(this, i*width, j*height, width, height));   
    					 break;
    				case '3':
    					if(player==null){
    						player= new Player(this,i*width,j*height, (int)(0.75*width), (int)(0.75*height), Config.getPlayerSpeed());
    						addKeyListener(player);					
    					}
    					else
    					player.setPosition(i*width, j*height);
    					floorList.add(new Floor(this, i*width, j*height,Color.GRAY, width,height)); 
    					
    					break;
    				case '4':
    					 floorList.add(new Floor(this, i*width, j*height,Color.GRAY, width,height)); 
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
    					 break;
    				case '5':
    					 obstacleList.add(new Obstacle(this, i*width, j*height, width,height));
    					 door=new Door(this, i*width, j*height,width,height);
    					 
    					
    					
    					
    			}
    		}
    	}
       	
    }
    
    public void recreate(ArrayList<String> mapData) {
    	createMap(mapData);
		
	}

    public int getColumns() { return columns; }
    public int getRows() { return rows; }

    public void moveEnemies(){
    	for(Obiekt enemy : enemyList)
    	{
    		((Enemy) enemy).move();
    		
    	}
   
    }
   public void paint(Graphics g)
   {
	   
	   BufferedImage dbImage=new BufferedImage(panelWidth, panelHeight,BufferedImage.TYPE_INT_ARGB);
	   Graphics dbg=dbImage.createGraphics();
	   paintComponent(dbg);
	   
	   BufferedImage scaled=new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
	   Graphics gg=scaled.createGraphics();
	   gg.drawImage(dbImage, 0, 0, getWidth(), getHeight(), null);
	   g.drawImage(scaled, 0, 0, this);
   }
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	
    	door.draw(g);
    	for (Obiekt ob : wallList)
    		ob.draw(g);
    	for (Obiekt ob : floorList)
    		ob.draw(g);
    	for (Obiekt ob: enemyList)
    		ob.draw(g);
    	for (Obiekt ob: obstacleList)
    		ob.draw(g);
    	for (Obiekt ob: bombList)
    		ob.draw(g);
    	player.draw(g);
    	
    }
	
	





	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
	}



	


	


	


	
	
	
    
    
    
	
}