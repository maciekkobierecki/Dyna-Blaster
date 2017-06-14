package defaultpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;
import javax.swing.Timer;


/* 
 Klasa zarz¹dzaj¹ca rozgrywk¹
 * 
 * */
 
public class Game implements ActionListener, PlayerIsDeadListener,PlayerEnemyCollisionListener, NextLevelListener, NewSpeedListener{
	/**
	 * Deklaracja pola rozgrywki.
	 */
	
	private Board board;
	private Boolean isPaused;
	private int remainingTime;
	private int levelTime;
	private int level,speed;
	private int pointsForEnemyKill;
	Boolean gameRunning;
	/**
	 * Lista obiektów u¿ywana do wczytywana mapy z pliku.
	 */
	private ArrayList<String> configMapData;
	/**
	 * Lista przechowuj¹ca nazwy kolejnych plików z mapami
	 */
	private ArrayList<String> mapNameList;
	/**
	 * Lista parametrów konfiguracyjnych
	 */
	private ArrayList<String> configList;
	
	/*
	 timer zajmuj¹cy sie odœwie¿aniem ekranu
	 */
	Timer timer;
	
	
	
	Game(){
		remainingTime=0;
		configMapData=new ArrayList<String>();
		mapNameList=new ArrayList<String>();
		configList=new ArrayList<>();
		loadConfig();
		board=new Board();
		isPaused=false;
		remainingTime=Integer.parseInt(Config.levelTime);
		timer=new Timer(15,this);		
		level=0;
		Enemy.loadFrames();
		Enemy.timer.start();
		switch(LevelWindow.level){
		case "easy":
			this.pointsForEnemyKill=Config.pointsForEnemyDeathAtEasyLevel;
		case "medium":
			this.pointsForEnemyKill=Config.pointsForEnemyDeathAtMediumLevel;
		case "hard":
			this.pointsForEnemyKill=Config.pointsForEnemyDeathAtHardLevel;
		}
	}
	
	/**
	 * @return zwraca wartoœæ logiczn¹, która mówi czy gra jest zapauzowana
	 */
	public Boolean isPaused(){
		return isPaused;
	}
	/**
	 * W zale¿noœci od wartoœci logicznej podanej w argumencie pauzuje lub wznawia gre
	 * @param value - wartosc logiczna mówi¹ca czy wznowic gre
	 */
	public void pauseGame(Boolean value){
		if(value==true){
			timer.stop();
			isPaused=true;
			
		}
		else{
			timer.start();
			isPaused=false;
		}
	}
	
	//public void addListenerToEveryPowerUP(NewSpeedListener){
		
	//}
	
	/**
	 * metoda wczytuj¹ca kolejne poziomy
	 */
	public void runLevel(){
		timer.stop();
		readMapNb(level);

		board.createMap(configMapData);
		if(level==0){
		board.getPlayer().addPlayerIsDeadListener(this);
		board.getPlayer().addCollisionListener(this);
		gameRunning=true;
		}	
		board.getDoor().addNextLevelListener(this);	
		for (int i=0; i<=board.getPusList().size()-1;i++){
			((PowerUpSpeed)(board.getPusList().get(i))).addNewSpeedListener(this);}
		level++;
		timer.start();
	}
	
	public void setNewSpeed(){
		if(LevelWindow.level=="easy")
			board.getPlayer().setSpeed(5);
		else if(LevelWindow.level=="medium")
			board.getPlayer().setSpeed(4);
		else board.getPlayer().setSpeed(3);
		}
	
	@Override
	public void loadNextLevel() {
		runLevel();
		
	}
	/**
	 * getter zwracaj¹cy poosta³y czas gry
	 */
	public int getRemainingTime() { return remainingTime; }	
	
	/**
	 * setter ustawij¹cy pozosta³y czas gry
	 */
	public void setRemainingTime(int time) { remainingTime=time; }
	@Override
	public void playerIsDead(int amountOfLives, String name, int score) {
		if(amountOfLives==0){
	    	timer.stop();
	    	gameRunning=false;
	    	Highscores.addScore(board.getPlayer().getName(), board.getPlayer().getScore());
		}
    	else
    		this.recreateMap();
    	
	}
	
	@Override
	public void playerEnemyCollided() {
		recreateMap();
	}
	
	public void recreateMap(){
		board.createMap(configMapData);
		board.getDoor().addNextLevelListener(this);	
		for (int i=0; i<=board.getPusList().size()-1;i++){
			((PowerUpSpeed)(board.getPusList().get(i))).addNewSpeedListener(this);}
		timer.stop();
		board.getPlayer().decrementLive();
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		} 
		if(gameRunning)
		timer.start();
	}
	
	public void readMapNb(int nb){
		read(mapNameList.get(nb)+".txt", configMapData);
		//read("game.txt", configList);
	}
	
	public void loadConfig(){
		read("maps.txt", mapNameList); //wczytywanie nazw plików z definicja kolejnych map 
		levelTime=Config.getLevelTime();
		Enemy.setSpeed(Config.getEnemySpeed());		
	}
	

	
	/**
	 * Metoda odpowiedzialna za wczytywanie mapy oraz ustawieñ gry z pliku.
	 * <p>
	 * @throws IOException w przypadku niepowodzenia z wczytywaniem pliku.
	 */
	
	
	public void read(String fileName, ArrayList<String> list){
		try {
			   FileReader fileReader = new FileReader(fileName);
			   BufferedReader bufferReader = new BufferedReader(fileReader);
			   list.clear();
			   String line;
			   
			   while((line = bufferReader.readLine()) != null) {
				  list.add(line);
				
			   }
			   fileReader.close();
			  }
			  catch (FileNotFoundException e) {
			   e.printStackTrace();
			  } 
			  catch (IOException e) {
			   e.printStackTrace();
			  }
	}
	
		
	
	
	/**
	 *
	 * @return zwraca pole gry
	 */
	public Board getBoard() { return board; }

	@Override
	public void actionPerformed(ActionEvent e) {
		 if(remainingTime==0)
			 board.getPlayer().callPlayerIsDeadListeners();
		 board.moveEnemies();
		 board.repaint();
	}


	public void setInitialSettings() {
		readMapNb(0);
		recreateMap();
		setRemainingTime(Integer.parseInt(Config.levelTime));
		board.getPlayer().setInitialSettings();
		timer.start();
		
		
	}

	

	
	
	
	
}
