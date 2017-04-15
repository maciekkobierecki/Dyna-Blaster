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
public class Board extends JPanel implements ActionListener{

	
	
	
	private int rows;
	private int columns;
	private Player player;
    ArrayList<Wall> wallList;
    ArrayList<Floor>floorList;
    ArrayList<Enemy>enemyList;
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
        
       	setFocusable(true);
       	panelWidth=0;
       	panelHeight=0;
       	
   }
       	
       	

    public int getPanelWidth() { return panelWidth; }
    public int getPanelHeight() { return panelHeight; }
    public ArrayList<Wall> getWallList() { return wallList; }
    public ArrayList<Enemy> getEnemyList() { return enemyList; }
    public int getAmountOfRows() { return rows; }
    public int getAmountOfColumns() { return columns; }
    public Player getPlayer() { return player; }
    
    
  
    public void createMap(ArrayList<String> mapData)
    {
    	
    	rows=mapData.size();
    	columns=mapData.get(0).length();
    	int width=this.getWidth()/columns;
    	int height=this.getHeight()/rows;
    	panelWidth=this.getWidth();
    	panelHeight=this.getHeight();
    
    	//0 oznacza brak sciany, 1 oznacza, ¿e jest sciana, 2 oznacza ze jest potwor, 3 player
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
    					player= new Player(this,i*width,j*height, (int)(0.75*width), (int)(0.75*height), Config.getPlayerSpeed());
    					floorList.add(new Floor(this, i*width, j*height,Color.GRAY, width,height)); 
    					addKeyListener(player);
    					break;
    					
    					
    			}
    		}
    	}
       	
    }
    
    public void recreate(ArrayList<String> mapData) {
		floorList.clear();
		wallList.clear();
		enemyList.clear();
		
		createMap(mapData);//tworzy sie nowy player ZMIENIC
		
	}

    public int getColumns() { return columns; }
    public int getRows() { return rows; }

    public void moveEnemies(){
    	for(Enemy enemy : enemyList)
    	{
    		enemy.move();
    		
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
    wwww
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	
    	for (Obiekt ob : wallList)
    		ob.draw(g);
    	for (Obiekt ob : floorList)
    		ob.draw(g);
    	for (Obiekt ob: enemyList)
    		ob.draw(g);
    	player.draw(g);
    	    	
    }
	
	





	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}



	


	
	
	
    
    
    
	
}