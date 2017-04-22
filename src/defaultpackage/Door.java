package defaultpackage;

import java.awt.Graphics;
import java.awt.Rectangle;

interface NextLevelListener{
	void loadNextLevel();
}


public class Door extends Obiekt{

	
	NextLevelListener nextLevelListener;
	public Door(Board plansza, int x, int y, int xwidth, int xheight) {
		super(plansza, x, y, xwidth, xheight);
		nextLevelListener=null;
	}
	
	public void addNextLevelListener(NextLevelListener listener){
		nextLevelListener=listener;
	}
	public void callNextLevelLisntener(){
		nextLevelListener.loadNextLevel();
	}
	
	public Boolean playerContains(Player player){
		Rectangle doorRect=new Rectangle(x,y,width,height);
		Rectangle playerRect=new Rectangle(player.x, player.y, player.width,player.height);
		if(doorRect.contains(playerRect))
			return true;
		return false;

	}
	
	@Override
	public void draw(Graphics g)
	{
		if(playerContains(plansza.getPlayer()))
			callNextLevelLisntener();
		g.setColor(java.awt.Color.GREEN);
		g.fillRect(getX(),getY(), width,height);
	}

}
