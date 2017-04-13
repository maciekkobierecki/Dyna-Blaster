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
public class Board extends JPanel implements KeyListener, ActionListener{

	
	
	
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
       	player= new Player(this,200,100, 15,15);
        wallList=new ArrayList<>();
       	floorList=new ArrayList<>();
       	enemyList=new ArrayList<>();
       	addKeyListener(this);
       	setFocusable(true);
       	panelWidth=0;
       	panelHeight=0;
       	
   }
       	
       	

    public int getPanelWidth() { return panelWidth; }
    public int getPanelHeight() { return panelHeight; }
    public ArrayList<Wall> getWallList() { return wallList; }
    
   
    
    public int getAmountOfRows() { return rows; }
    public int getAmountOfColumns() { return columns; }
  
    public void createMap(ArrayList<String> mapData)
    {
    	rows=mapData.size();
    	columns=mapData.get(0).length();
    	int width=this.getWidth()/columns;
    	int height=this.getHeight()/rows;
    	panelWidth=this.getWidth();
    	panelHeight=this.getHeight();
    	System.out.println("create map");
    	//0 oznacza brak sciany, 1 oznacza, ¿e jest sciana
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
    					 enemyList.add(new Enemy(this, i*width+(int)(0.1*width), j*height+(int)(0.1*height), 4*width/5, 4*height/5));   
    					 break;
    					
    			}
    		}
    	}
    	repaint();
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
	public void keyPressed(KeyEvent arg0)
	{
		int c=arg0.getKeyCode();
		if(c== KeyEvent.VK_W)
		{
			player.setDY(-3);
			repaint();
		}
		if(c== KeyEvent.VK_S)
		{
			player.setDY(3);
			repaint();
		}
		if(c== KeyEvent.VK_A)
		{
			player.setDX(-3);
			repaint();
		}
		if(c== KeyEvent.VK_D)
		{
			player.setDX(3);
			repaint();
		}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		player.setDX(0);
		player.setDY(0);
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
    
    
    
	
}