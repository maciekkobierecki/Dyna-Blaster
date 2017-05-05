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
 
public class Game implements ActionListener, GameOverListener,PlayerEnemyCollisionListener, NextLevelListener{
	/**
	 * Deklaracja pola rozgrywki.
	 */
	
	private Board board;
	private int remainingTime;
	private int levelTime;
	private int level;
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
		remainingTime=Integer.parseInt(Config.levelTime);
		timer=new Timer(15,this);		
		level=0;
		Enemy.loadFrames();
		Enemy.timer.start();
	}
	/**
	 * metoda wczytuj¹ca kolejne poziomy
	 */

	public void runLevel(){
		timer.stop();
		readMapNb(level);

		board.createMap(configMapData);
		if(level==0){
		board.getPlayer().addGameOverListener(this);
		board.getPlayer().addCollisionListener(this);
		gameRunning=true;
		}	
		board.getDoor().addNextLevelListener(this);		
		level++;
		timer.start();
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
	public void playerIsDead() {
    	timer.stop();
    	JOptionPane.showMessageDialog(null,"KONIEC GRY",null,JOptionPane.WARNING_MESSAGE);
    	gameRunning=false;
    	
	}
	
	@Override
	public void playerEnemyCollided() {
		board.createMap(configMapData);
		board.getDoor().addNextLevelListener(this);		
		timer.stop();
		board.getPlayer().decrementLive();
		try {
			Thread.sleep(3000);
		}
		catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		} 
		if(gameRunning)
		timer.start();
	}
	
	public void readMapNb(int nb){
		read(mapNameList.get(nb), configMapData);
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
			 board.getPlayer().callGameOverListeners();
		 board.moveEnemies();
		 board.repaint();
	}

	

	
	
	
	
}
