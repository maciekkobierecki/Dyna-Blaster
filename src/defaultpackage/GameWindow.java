package defaultpackage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Klasa okna rozgrywki.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class GameWindow extends JFrame implements PlayerEnemyCollisionListener, PlayerIsDeadListener, ActionListener{

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
	 * komponent który zawiera informacje o grze oraz przycisk pauzy
	 */
	JPanel topPanel;
	
	/**
	 * przycisk obs³uguj¹cy pauzowanie gry
	 */
	JButton pauseButton;
	
	/**
	 * Konstruktor klasy.
	 */
	public GameWindow()
	{	
		pauseButton=new JButton("Pauza");
		
		pauseButton.setFocusable(false);
		topPanel=new JPanel();
		topPanel.setBackground(Color.black);
		topPanel.setLayout(new FlowLayout());
		nick_text = NickWindow.playerName;
		Dimension dimension = new Dimension(Config.gameWindowWidth, Config.gameWindowHeight);
		setPreferredSize(dimension);
		setBackground(Color.black);
		this.setLayout(new BorderLayout());	
		infoLabel=new JLabel(" " + nick_text + " Pozosta³y czas:" + Config.levelTime + " ¿ycia: " + Config.getAmountOfLives());
		infoLabel.setPreferredSize(new Dimension(200,40));
		topPanel.add(infoLabel);
		topPanel.add(pauseButton);
		this.add(topPanel, BorderLayout.NORTH);
		infoLabel.setForeground(Color.yellow);
		infoLabel.setPreferredSize(new Dimension(450,50));
		infoLabel.setBackground(Color.black);
		infoLabel.setOpaque(true);
		game=createNewGame();
		game.getBoard().requestFocus();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pauseButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(game.isPaused()){
					game.pauseGame(false);
					pauseButton.setText("pauza");
					levelTime.start();
				}
				else {
					game.pauseGame(true);
					pauseButton.setText("graj");
					levelTime.stop();
				}
			}
		});			
		levelTime=new Timer(1000, this);
		levelTime.start();

	}
	
	/**
	 * metoda odpowiadaj¹ca za stworzenie nowej gry. U¿ywana na przyk³ad, gdy gracz zechce zagraæ 
	 * od nowa po przegranej
	 * @return obiekt typu Game - obiekt odpowiadaj¹cy za logikê gry
	 */
	public Game createNewGame(){
		Game game=new Game();
		infoLabel.setText(" " + nick_text + " Pozosta³y czas:" + Config.levelTime + " ¿ycia: " + Config.getAmountOfLives());
		this.add(game.getBoard());
		pack();
		game.runLevel();
		game.getBoard().getPlayer().addCollisionListener(this);
		game.getBoard().getPlayer().addPlayerIsDeadListener(this);
		return game;
	}

	/**
	 * Metoda wy³¹czaj¹ca okno w momencie koñca gry.
	 */
	@Override
	public void playerIsDead(int amountOfLives, String name, int score) {
		if(amountOfLives==0){
			levelTime.stop();
			int x = JOptionPane.showConfirmDialog(null, "Koniec gry. Twój wynik to: "+ game.getBoard().getPlayer().getPoints()+"\n Czy chcesz rozpocz¹æ ponownie?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if(x == JOptionPane.NO_OPTION){
				System.exit(0);
			}
			else{
				
				playAgain();
			}
		}
		
	}

	/**
	 * Metoda obs³uguj¹ca kolizjê gracza z potworem 
	 */
	@Override
	public void playerEnemyCollided() {
		infoLabel.setText(" " +nick_text+" Pozostaly czas "+ game.getRemainingTime() + " sekund. Liczba ¿yæ: "+ game.getBoard().getPlayer().getAmountOfLives());
		
	}

	/**
	 * Metoda umo¿liwiaj¹ca ponown¹ rozgrywkê 
	 */
	public void playAgain(){
		game.setInitialSettings();
		levelTime.start();
		
	}
	
	/**
	 * Metoda obs³uguj¹ca zdarzenie
	 */
	@Override
	public void actionPerformed(ActionEvent arg0) {
		game.setRemainingTime(game.getRemainingTime()-1);
		infoLabel.setText(" " +nick_text+" |"+" Pozostaly czas: "+ game.getRemainingTime() + " sekund. | Liczba ¿yæ: "+ game.getBoard().getPlayer().getAmountOfLives()+" |"+ " PUNKTÓW: "+ game.getBoard().getPlayer().getPoints());
		if(game.getRemainingTime()==0){
			game.pauseGame(true);
			//JOptionPane.showMessageDialog(null,"KONIEC GRY! \n Twój wynik to: "+ game.getBoard().getPlayer().getPoints(),null,JOptionPane.WARNING_MESSAGE);
			//dispose();
			
					int x = JOptionPane.showConfirmDialog(null, "Koniec gry. Twój wynik to: "+ game.getBoard().getPlayer().getPoints()+"\n Czy chcesz rozpocz¹æ ponownie?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if(x == JOptionPane.NO_OPTION){
						System.exit(0);
						
					}
					else{
						
						playAgain();
					}
		}
	}
}
	
