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
public class Game extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Deklaracja pola rozgrywki.
	 */
	private Plansza gameField;
	
	/**
	 * Etykieta z wyœwietlanym stanem gry.
	 */
	private JLabel infoLabel;
	
	/**
	 * Lista obiektów u¿ywana do wczytywana mapy z pliku.
	 */
	private ArrayList<String> configData;
	
	/**
	 * Konstruktor klasy.
	 */
	public Game()
	{
		Dimension dimension = new Dimension(Config.windowWidth, Config.windowHeight);
		setPreferredSize(dimension);
		//setMinimumSize(dimension);
		//setMaximumSize(dimension);
		setBackground(Color.black);
		gameField=new Plansza(2,2);
		this.setLayout(new BorderLayout());
		infoLabel=new JLabel(" NICK " + "Zdobyte punkty: 12  " + "Pozosta³y czas: 1:23");
		infoLabel.setPreferredSize(new Dimension(200,40));
		infoLabel.setForeground(Color.yellow);
		infoLabel.setBackground(Color.black);
		infoLabel.setOpaque(true);
		infoLabel.repaint();
		this.add(infoLabel, BorderLayout.NORTH);
		this.add(gameField);
		configData=new ArrayList<String>();
		read();
		pack();
		gameField.createMap(configData);
	
	
	}
	
	
/*	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, Config.windowWidth, Config.windowHeight);
		g.dispose();
		bs.show();
	}*/
	
	/**
	 * Metoda odpowiedzialna za wczytywanie mapy z pliku.
	 * <p>
	 * @throws IOException w przypadku niepowodzenia z wczytywaniem pliku.
	 */
	public void read(){
		try {
			   FileReader fileReader = new FileReader("map.txt");
			   BufferedReader bufferReader = new BufferedReader(fileReader);
			   
			   String line;
			   
			   while((line = bufferReader.readLine()) != null) {
				  configData.add(line);
				  System.out.println(line); 
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
}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	