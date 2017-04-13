package defaultpackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy extends Charakter{
	
	static int speed=1; //DAC DO WCZYTYWANIA Z PLIKU

	public Enemy(Board plansza, int x, int y, int width, int height) {
		super(plansza, x, y, width, height, 1,0);	
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
	
	public Point Colided()
	{
		Point point=new Point(-1,-1);
		ArrayList<Wall> mapObjects=plansza.getWallList();
		for(int i=0; i<mapObjects.size(); i++){
			Rectangle thisrect=this.getShape();
			Rectangle obrect=mapObjects.get(i).getShape();
			if(thisrect.intersects(obrect)){
				point.x=mapObjects.get(i).x;
				point.y=mapObjects.get(i).y;
			}
		}
		return point;
	}
	
	
	//metoda odpowiadaj¹ca za ruch obiektu 
	public void move()
	{	
		int panelWidth=plansza.getPanelWidth();
		int panelHeight=plansza.getPanelHeight();
		int number=this.rand.nextInt();
		
		if(Colided().getX()!=-1 && Colided().getY()!=-1)
		{
			Point CoordinatesOfColidedObject=Colided();
			x-=2*dx;
			y-=2*dy;
			switch (number % 3) {
			case 1: 
				if(dx!=0)
					dy=dx;
				else 
					dy=-dy;
				dx=0;
				break;
			case 2:
				if(dx!=0)
					dy=-dx;
				else 
					dy=-dy;
				dx=0; 
				break;
			default:
				if(dx!=0)
					dx=-dx;
				else 
					dx=speed;
				dy=0;
				break;

			}

			
				
				
		}
		
		
		//instrukcja obslugujaca zachowanie obiektu w rogu panelu
		if(((x+width+dx>panelWidth|| x+dx<0) && y==0)||((y+height+dy>panelHeight|| y+dy<0)&& x==0)|| ((x+width+dx>panelWidth|| x+dx<0) && y>panelHeight-1/10*height)||((y+height+dy>panelHeight|| y+dy<0)&& x>panelWidth-1/10*width))
		{
			if(dx==0){
				dy=-dy;							
			}
			else
				dx=-dx;
		}
		//instrukcja obejmujaca pozosta³e sytuacje
		else{
			if(x+width+dx>panelWidth|| x+dx<0){
				switch (number % 3) {
				case 1: 
					dy=dx;
					dx=0;
					break;
				case 2:
					dy=-dx;
					dx=0; 
					break;
				default:
					dx=-dx;
					dy=0;
					break;
	
				}
			}
				
			if(y+height+dy>panelHeight|| y+dy<0){
				switch (number % 3) {
				case 1: 
					dx=dy;
					dy=0;
					break;
				case 2:
					dx=-dy;
					dy=0;
					break;
				default:
					dy=-dy;
					dx=0;
					break;
				
				}
			}
		}
			
		this.y+=dy;
		this.x+=dx;
	}
	
}
