package defaultpackage;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;


/**
 * Klasa potwora.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Enemy extends Charakter implements ActionListener{
	
	/**
	 * Prêdkoœæ potwora.
	 */
	static int speed;
	
	/**
	 * Lista obrazów
	 */
	static ArrayList<BufferedImage> images;
	
	/**
	 * zmienna typu BufferedImage
	 */
	BufferedImage img;
	
	/**
	 * zmienna typu int
	 */
	private int count;
	
	/**
	 * Timer
	 */
	static Timer timer=new Timer(50, null);

	/**
	 * Konstruktor klasy.
	 */
	public Enemy(Board plansza, int x, int y, int width, int height) {
		super(plansza, x, y, width, height, speed,0);	
		img=null;
		count=0;		
		timer.addActionListener(this);
	}
	

	@Override
	/**
	 * Metoda odpowiedzialna za rysowanie
	 */
	void draw(Graphics g) {	
		g.drawImage(img, this.x,this.y,this.width, this.height, null);		
	}	
	
	/**
	 * metoda ³aduj¹ca kolejn¹ klatke reprezentacji graficznej obiektu
	 */
	public static void loadFrames(){
		images=new ArrayList<>();
		for(int i=0; i<10;i++){
			
			try 
			{
			    images.add(ImageIO.read(new File(Config.graphicsPath+"enemy"+i+".png")));
			} 
			catch (IOException e) 
			{
			    e.printStackTrace();
			}
	}
		try 
		{
		    images.add(ImageIO.read(new File(Config.graphicsPath+"enemyisdead.png")));
		} 
		catch (IOException e) 
		{
		    e.printStackTrace();
		}
	}
	
	/**
	 * Metoda ustalaj¹ca szybkoœæ potwora.
	 */
	public static void setSpeed(int xspeed){ speed=xspeed; }
	
	/**
	 * metoda odpowiadaj¹ca za ruch obiektu 
	 */
	public void move()
	{	
		int number=this.rand.nextInt();
		if(willCollide(dx,dy, plansza.getWallList()) || willCollide(dx,dy,plansza.getObstacleList())){
			switch (number % 4) {
			case 0: 
				dx=speed;
				dy=0;
				break;
			case 1:
				dx=-speed;
				dy=0;
				break;
			case 2:
				dx=0;
				dy=speed;
				break;
			case 3:
				dx=0;
				dy=-speed;
			}
		}
		
		if(!willCollide(dx,dy, plansza.getWallList()) && !willCollide(dx,dy,plansza.getObstacleList()))
		{
			this.y+=dy;
			this.x+=dx;
		}				
	}

	/**
	 * metoda obs³uguj¹ca zdarzenie
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		img=images.get(count++%10);
		
	}	
}
