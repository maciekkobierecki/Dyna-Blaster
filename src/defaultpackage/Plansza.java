package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
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

	
	private Image dbImage;
	private Graphics dbg;
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
       	
       	

    }
   
    
    public int getAmountOfRows() { return rows; }
    public int getAmountOfColumns() { return columns; }
  
    public void createMap(ArrayList<String> data)
    {
    	rows=data.size();
    	columns=data.get(0).length();
    	int width=this.getWidth()/columns;
    	int height=this.getHeight()/rows;
    	int panelWidth=this.getWidth();
    	int panelHeight=this.getHeight();
    	System.out.println("create map");
    	//0 oznacza brak sciany, 1 oznacza, ¿e jest sciana
    	for(int j=0; j<data.size(); j++)
    	{
    		String str=data.get(j);
    		for(int i=0; i<columns; i++)
    		{
    			if(str.charAt(i)=='1')
    				mapList.add(new Wall(this, i*width, j*height, width, height));   
    			else mapList.add(new Floor(this, i*width, j*height,Color.RED, width,height));
    		}
    	}
    	repaint();
    }
    

    
    
    public void paintComponent(Graphics g)
    {
    	super.paintComponent(g);
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	
    	for (Obiekt ob : mapList)
    		ob.draw(g);
    	player.draw(g);
    	System.out.println("paintComponent plansza");
    	    	
    }
	
	@Override
	public void keyPressed(KeyEvent arg0)
	{
		int c=arg0.getKeyCode();
		if(c== KeyEvent.VK_W)
		{
			player.setDY(-3);
			System.out.println("w");
			repaint();
		}
		if(c== KeyEvent.VK_S)
		{
			player.setDY(3);
			System.out.println("s");
			repaint();
		}
		if(c== KeyEvent.VK_A)
		{
			player.setDX(-3);
			System.out.println("a");
			repaint();
		}
		if(c== KeyEvent.VK_D)
		{
			player.setDX(3);
			System.out.println("d");
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