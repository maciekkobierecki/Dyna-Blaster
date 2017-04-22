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
		timer=new Timer(15,this);		
		level=0;
	}
	
	public void run(){
		level++;
		board.createMap(configMapData);
		board.getPlayer().addGameOverListener(this);
		board.getPlayer().addCollisionListener(this);
		board.getDoor().addNextLevelListener(this);
		timer.start();
		gameRunning=true;
	}
	
	@Override
	public void loadNextLevel() {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void playerIsDead() {
    	timer.stop();
    	JOptionPane.showMessageDialog(null,"KONIEC GRY",null,JOptionPane.WARNING_MESSAGE);
    	gameRunning=false;
    	
	}
	
	@Override
	public void playerEnemyCollided() {
		board.recreate(configMapData);
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
	
	public void loadConfig(){
		read("maps.txt", mapNameList); //wczytywanie nazw plików z definicja kolejnych map 
		read(mapNameList.get(0), configMapData);
		read("game.txt", configList);
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
	
		
	
	
	
	public Board getBoard() { return board; }

	@Override
	public void actionPerformed(ActionEvent e) {
		 board.moveEnemies();
		 board.repaint();
	}

	

	
	
	
	
}
