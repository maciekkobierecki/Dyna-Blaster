package defaultpackage;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public abstract class Charakter extends Obiekt{
	
	//zmienne m�wi�ce o ile przy nastepnym move ma sie ruszyc obiekt
	protected int dx, dy;
	//losowanie liczb potrzebnych do okreslania kolejnego kierunku ruchu obiektu graficznego po napotkaniu kolizji 
	protected Random rand;
	public Charakter(Board plansza, int x, int y, int xwidth, int xheight,int dx,int dy) {
		super(plansza, x, y, xwidth, xheight);
		this.dx=dx;
		this.dy=dy;
		rand=new Random();
	}
	
	//zwraca punkt lewego g�rnego rogu obiektu z ktorym zachodzi kolizja
	
	public Point Collision(int dx, int dy, ArrayList<Obiekt>objects){
		Point point=new Point(-1,-1);
		ArrayList<Obiekt> mapObjects=objects;
		for(int i=0; i<mapObjects.size(); i++){
			Rectangle thisrect=this.getShape();
			thisrect.x+=dx;
			thisrect.y+=dy;
			Rectangle obrect=mapObjects.get(i).getShape();
			if(thisrect.intersects(obrect)){
				point.x=mapObjects.get(i).x;
				point.y=mapObjects.get(i).y;
			}
		}
		return point;
	}
	
	//zwraca boola m�wi�cego czy nast�pi�a kolizja
	
	public Boolean willCollide(int dx, int dy, ArrayList<Obiekt>objects){
		if(Collision(dx,dy, objects).getX()!=-1 && Collision(dx,dy, objects).getY()!=-1)
			return true;
		else return false;
	}
	
	
	abstract public void move();
	
	public void setPosition(int x, int y)
	{
		this.setX(x);
		this.setY(y);
	}
	
	public void setDX(int value)
	{
		dx=value;
	}
	
	public void setDY(int value)
	{
		dy=value;
	}

	
	

}
