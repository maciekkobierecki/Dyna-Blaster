package defaultpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Timer;

/**
 * Klasa okna rozgrywki.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class GameWindow extends JFrame implements PlayerEnemyCollisionListener, GameOverListener, ActionListener{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Obiekt klasy Game
	 */
	private Game game;
	
	
	/**
	 * Etykieta z wyœwietlanym stanem gry.
	 */
	private JLabel infoLabel;
	
	/**
	 * Zmienna tekstowa zawieraj¹ca wpisany nick
	 */
	private String nick_text;

	/**
	 * zegar liczacy czas rozgrywki poziomu
	 */
	Timer levelTime;
	/**
	 * Konstruktor klasy.
	 */
	public GameWindow()
	{	
		nick_text = NickWindow.a;
		Dimension dimension = new Dimension(Config.gameWindowWidth, Config.gameWindowHeight);
		setPreferredSize(dimension);
		setBackground(Color.black);
		game=new Game();
		this.setLayout(new BorderLayout());
		infoLabel=new JLabel(" " + nick_text + " Pozosta³y czas:" + Config.levelTime + " ¿ycia: " + Config.getAmountOfLives());
		infoLabel.setPreferredSize(new Dimension(200,40));
		infoLabel.setForeground(Color.yellow);
		infoLabel.setBackground(Color.black);
		infoLabel.setOpaque(true);
		this.add(infoLabel, BorderLayout.NORTH);
		this.add(game.getBoard());		
		pack();	
		game.runLevel();
		game.getBoard().getPlayer().addCollisionListener(this);
		game.getBoard().getPlayer().addGameOverListener(this);
		levelTime=new Timer(1000, this);
		levelTime.start();
		
		
		
	}


	/**
	 * Metoda wy³¹czaj¹ca okno w momencie koñca gry.
	 */
	@Override
	public void playerIsDead() {
		dispose();
		
	}


	@Override
	public void playerEnemyCollided() {
		infoLabel.setText(" " +nick_text+" Pozostaly czas "+ game.getRemainingTime() + " sekund. Liczba ¿yæ: "+ game.getBoard().getPlayer().getAmountOfLives());
		
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.setRemainingTime(game.getRemainingTime()-1);
		infoLabel.setText(" " +nick_text+" Pozostaly czas: "+ game.getRemainingTime() + " sekund. Liczba ¿yæ: "+ game.getBoard().getPlayer().getAmountOfLives());
		
	}
}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	