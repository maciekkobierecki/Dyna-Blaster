package defaultpackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;


/**
 Klasa zarz�dzaj�ca rozgrywk�
 * 
 */
public class Game implements ActionListener, PlayerIsDeadListener,PlayerEnemyCollisionListener, NextLevelListener, NewSpeedListener, LowSpeedListener, NewRangeListener{
	
	/**
	 * Deklaracja pola rozgrywki.
	 */
	private Board board;
	
	/**
	 * Zmienna logiczna okre�laj�ca czy gra jest zapauzowana
	 */
	private Boolean isPaused;
	
	/**
	 * Pozosta�y czas
	 */
	private int remainingTime;
	
	/**
	 * Czas poziomu
	 */
	private int levelTime;
	
	/**
	 * Poziom, pr�dko��
	 */
	private int level,speed;
	
	/**
	 * Punkty za zabicie potwora
	 */
	private int pointsForEnemyKill;
	
	/**
	 * Zmienna okre�laj�ca czy rozgrywka jest w toku
	 */
	Boolean gameRunning;
	
	/**
	 * Lista obiekt�w u�ywana do wczytywana mapy z pliku.
	 */
	private ArrayList<String> configMapData;
	
	/**
	 * Lista przechowuj�ca nazwy kolejnych plik�w z mapami
	 */
	private ArrayList<String> mapNameList;
	
	/**
	 * Lista parametr�w konfiguracyjnych
	 */
	private ArrayList<String> configList;
	
	/**
	 *timer zajmuj�cy sie od�wie�aniem ekranu
	 */
	Timer timer;
	
	/**
	 * Konstruktor klasy
	 */
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
	 * @return zwraca warto�� logiczn�, kt�ra m�wi czy gra jest zapauzowana
	 */
	public Boolean isPaused(){
		return isPaused;
	}
	
	/**
	 * W zale�no�ci od warto�ci logicznej podanej w argumencie pauzuje lub wznawia gre
	 * @param value - wartosc logiczna m�wi�ca czy wznowic gre
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
	
	/**
	 * Metoda ustawiaj�ca pr�dko�� na domy�ln�
	 */
	public void setOldSpeed(){
		if(LevelWindow.level=="easy")
			board.getPlayer().setSpeed(Integer.parseInt(Config.easyLevelPlayerSpeed));
		else if(LevelWindow.level=="medium")
			board.getPlayer().setSpeed(Integer.parseInt(Config.mediumLevelPlayerSpeed));
		else board.getPlayer().setSpeed(Integer.parseInt(Config.hardLevelPlayerSpeed));
	}
	
	/**
	 * Metoda ustawiaj�ca wi�ksz� pr�dko�� 
	 */
	public void setNewSpeed(){
		if(LevelWindow.level=="easy")
			board.getPlayer().setSpeed((Integer.parseInt(Config.easyLevelPlayerSpeed))*2);
		else if(LevelWindow.level=="medium")
			board.getPlayer().setSpeed((Integer.parseInt(Config.mediumLevelPlayerSpeed))*2);
		else board.getPlayer().setSpeed((Integer.parseInt(Config.hardLevelPlayerSpeed))*2);
		}
	
	/**
	 * Metoda ustawiaj�ca ni�sz� pr�dko��
	 */
	public void setLowSpeed(){
		if(LevelWindow.level=="easy")
			board.getPlayer().setSpeed((Integer.parseInt(Config.easyLevelPlayerSpeed))/2);
		else if(LevelWindow.level=="medium")
			board.getPlayer().setSpeed((Integer.parseInt(Config.mediumLevelPlayerSpeed))/2);
		else board.getPlayer().setSpeed((Integer.parseInt(Config.hardLevelPlayerSpeed))/2);
		}
	
	/**
	 * Metoda ustawiaj�ca nowy zasi�g ra�enia bomby
	 */
	public void setNewRange(){
				if(LevelWindow.level=="easy")
					Bomb.range=7;
				else if(LevelWindow.level=="medium")
					Bomb.range=1;
				else 
					Bomb.range=1;
	}
	/**
	 * Metoda �aduj�ca nowy poziom
	 */
	@Override
	public void loadNextLevel() {
		runLevel();
	}
	
	/**
	 * getter zwracaj�cy pozosta�y czas gry
	 */
	public int getRemainingTime() { return remainingTime; }	
	
	/**
	 * setter ustawiaj�cy pozosta�y czas gry
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
	
	/**
	 * Metoda obs�uguj�ca kolizj� gracza i potwora
	 */
	@Override
	public void playerEnemyCollided() {
		recreateMap();
	}
	
	/**
	 * metoda wczytuj�ca kolejne poziomy
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
		for (int i=0; i<board.getPusList().size();i++){
			((PowerUpSpeed)(board.getPusList().get(i))).addNewSpeedListener(this);}
		for (int i=0; i<board.getPdsList().size();i++){
			((PowerDownSpeed)(board.getPdsList().get(i))).addLowSpeedListener(this);}
		for (int i=0; i<board.getPurList().size();i++){
			((PowerUpRange)(board.getPurList().get(i))).addNewRangeListener(this);}
		level++;
		timer.start();
	}
	
	/**
	 * Metoda przerysowuj�ca map�
	 */
	public void recreateMap(){
		board.createMap(configMapData);
		board.getDoor().addNextLevelListener(this);	
		for (int i=0; i<board.getPusList().size();i++){
			((PowerUpSpeed)(board.getPusList().get(i))).addNewSpeedListener(this);}
		for (int i=0; i<board.getPdsList().size();i++){
			((PowerDownSpeed)(board.getPdsList().get(i))).addLowSpeedListener(this);}
		for (int i=0; i<board.getPurList().size();i++){
			((PowerUpRange)(board.getPurList().get(i))).addNewRangeListener(this);}
		timer.stop();
		board.getPlayer().decrementLive();
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		} 
		if(gameRunning){
		timer.start();
		System.out.println("gamerunning");
		}
	}
	
	/**
	 * Metoda sczytuj�ca numer mapy
	 */
	public void readMapNb(int nb){
		read(mapNameList.get(nb)+".txt", configMapData);
		//read("game.txt", configList);
	}
	
	/**
	 * Wczytywanie config'u
	 */
	public void loadConfig(){
		read("maps.txt", mapNameList); //wczytywanie nazw plik�w z definicja kolejnych map 
		levelTime=Config.getLevelTime();
		Enemy.setSpeed(Config.getEnemySpeed());		
	}
	

	/**
	 * Metoda odpowiedzialna za wczytywanie mapy oraz ustawie� gry z pliku.
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
	 * Getter zwracaj�cy pole gry
	 */
	public Board getBoard() { return board; }

	/**
	 * Metoda obs�guj�ca zdarzenie
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		 if(remainingTime==0)
			 board.getPlayer().callPlayerIsDeadListeners();
		 board.moveEnemies();
		 board.repaint();
	}

	/**
	 * Metoda ustalaj�ca parametry domy�lne 
	 */
	public void setInitialSettings() {
		readMapNb(0);
		recreateMap();
		gameRunning=true;
		setRemainingTime(Integer.parseInt(Config.levelTime));
		board.getPlayer().setInitialSettings();
		timer.start();
	
	}
	
}
