package defaultpackage;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Klasa okna wyboru poziomu trudnoœci.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class LevelWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	public static String level;
	
	/**
	 * Deklaracja etykiety, która wyœwietla komunikat o wyborze poziomu trudnoœci.
	 */
	private JLabel n;
	
	/**
	 * Deklaracja przycisków, u¿ytych w oknie.
	 */
	private JButton easy, normal, hard;
	
	/**
	 * Konstruktor klasy okna wyboru nicku'u.
	 * <p>
	 * Ustala jego parametry i umo¿liwia wybór poziomu trudnoœci (spoœród trzech).
	 */
	public LevelWindow(){
		super( Config.levelWindowName );
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wróciæ?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
					new MainWindow();
				}
			}
		});
		setSize(Config.windowWidth,Config.windowHeight);
		setLocation(10,10);
		setLayout(new GridLayout(4,1));
		
		n = new JLabel("Wybierz poziom trudnoœci:");
		add(n);
		
		easy = new JButton(Config.level1);
		add(easy);
		easy.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e){
					level="easy";
					play();
				}
				});
		normal = new JButton(Config.level2);
		add(normal);
		normal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			level="medium";
			play();
		}
		});
		hard = new JButton(Config.level3);
		add(hard);
		hard.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
			level="hard";
			play();
		}
		});	
	}
	
	/**
	 * Metoda tworz¹ca okno gry.
	 */
	public void play(){
		GameWindow game = new GameWindow();
		game.setVisible(true);
		game.setTitle(Config.gameName + " - ROZGRYWKA");
		game.pack();
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setLocationRelativeTo(null);
		game.setVisible(true);
		dispose();
	}
	
	
	}
	
	


