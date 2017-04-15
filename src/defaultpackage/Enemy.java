package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy extends Charakter{
	
	static int speed;

	public Enemy(Board plansza, int x, int y, int width, int height) {
		super(plansza, x, y, width, height, speed,0);	
	}

	@Override
	void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(this.x, this.y,this.width, this.height);
		g.setColor(Color.black);
		g.drawLine(this.x+width/3, this.y+2*height/3, this.x+2*width/3, this.y+2*height/3);
		g.fillOval(this.x+width/3,this.y+height/3, 5, 5);
		g.fillOval(this.x+2*width/3,this.y+height/3, 5, 5);
		
	}	
	
	public static void setSpeed(int xspeed){ speed=xspeed; }
	
	//metoda odpowiadaj¹ca za ruch obiektu 
	public void move()
	{	
		int number=this.rand.nextInt();
		if(willCollide(dx,dy)){
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
		
		if(!willCollide(dx,dy))
		{
			this.y+=dy;
			this.x+=dx;
		}				
	}	
}
