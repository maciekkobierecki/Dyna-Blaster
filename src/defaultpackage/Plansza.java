package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
public class Plansza extends JPanel implements KeyListener {

	
	
	
	private int rows;
	private int columns;
	private Player player;
    ArrayList<Obiekt> mapList;
    BufferedImage bimage;
    
    public Plansza(int xrows, int xcol)
    {	
    	super();
    	rows=xrows;
    	columns=xcol;
       	this.setPreferredSize(new Dimension(300,300));
       	player= new Player(this,200,100, 15,15);
       	mapList=new ArrayList<>();
       	addKeyListener(this);
       	setFocusable(true);
       
       //	this.addComponentListener(new ComponentAdapter(){
       		//public void componentResized(ComponentEvent e){
       			//Component c=(Component)e.getSource();
       			//int width=getWidth();
       			//int height=getHeight();
       			//Obiekt.setDimension(width/columns, height/rows);
       			
       			//for(int i=0; i<mapList.size();i++){
       				//mapList.get(i).setX((i%columns)*width/columns);
       				//mapList.get(i).setY((i%rows)*height/rows);
       			//}
       			
       		//}
       	//});
       	}
       	
       	

    
   
    
    public int getAmountOfRows() { return rows; }
    public int getAmountOfColumns() { return columns; }
  
    public void createMap(ArrayList<String> mapData, ArrayList<String>enemyData)
    {
    	rows=mapData.size();
    	columns=mapData.get(0).length();
    	int width=this.getWidth()/columns;
    	int height=this.getHeight()/rows;
    	int panelWidth=this.getWidth();
    	int panelHeight=this.getHeight();
    	System.out.println("create map");
    	//0 oznacza brak sciany, 1 oznacza, ¿e jest sciana
    	for(int j=0; j<mapData.size(); j++)
    	{
    		String str=mapData.get(j);
    		for(int i=0; i<columns; i++)
    		{
    			if(str.charAt(i)=='1')
    				mapList.add(new Wall(this, i*width, j*height, width, height));   
    			else mapList.add(new Floor(this, i*width, j*height,Color.GRAY, width,height));
    		}
    	}
    	for(int j=0; j<enemyData.size(); j++)
    	{
    		String str=enemyData.get(j);
    		for(int i=0; i<columns; i++)
    		{
    			if(str.charAt(i)=='1')
    				mapList.add(new Enemy(this, i*width, j*height, width, height));   
    		}
    	}
    	
    	
    	repaint();
    }
    public int getColumns() { return columns; }
    public int getRows() { return rows; }

   public void paint(Graphics g)
   {
	   
	   BufferedImage dbImage=new BufferedImage(784, 521,BufferedImage.TYPE_INT_ARGB);
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
    	
    	for (Obiekt ob : mapList)
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
	
	
    
    
    
	
}