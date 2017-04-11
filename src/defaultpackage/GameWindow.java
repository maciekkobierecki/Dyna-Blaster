package defaultpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Klasa okna rozgrywki.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class GameWindow extends JFrame{

	private static final long serialVersionUID = 1L;
	private Game game;
	
	
	/**
	 * Etykieta z wyœwietlanym stanem gry.
	 */
	private JLabel infoLabel;
	
	
	/**
	 * Konstruktor klasy.
	 */
	public GameWindow()
	{
		Dimension dimension = new Dimension(Config.windowWidth, Config.windowHeight);
		setPreferredSize(dimension);
		setBackground(Color.black);
		game=new Game();
		this.setLayout(new BorderLayout());
		infoLabel=new JLabel(" NICK " + "Zdobyte punkty: 12  " + "Pozosta³y czas: 1:23");
		infoLabel.setPreferredSize(new Dimension(200,40));
		infoLabel.setForeground(Color.yellow);
		infoLabel.setBackground(Color.black);
		infoLabel.setOpaque(true);
		this.add(infoLabel, BorderLayout.NORTH);
		this.add(game.getBoard());	
		pack();	
		game.run();
		
		
	}
}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	