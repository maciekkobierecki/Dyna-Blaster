package defaultpackage;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Klasa okna g³ównego programu.
 * <p>
 * @author Patryk Gozdera 
 * @author Maciej Kobierecki
 *
 */

public class MainWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Przyciski wykorzystywane w MENU.
	 */
	
	private JButton new_game,rank,level,exit;
	
	/**
	 * Konstruktor okna g³ównego. Ustala jego parametry i definiuje przyciski.
	 */
	public MainWindow() {
		super(Config.gameName);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjœæ?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
				}
			}
		});
		setLocation(100,100);
		setLayout(new GridLayout(4, 1));
		new_game = new JButton(Config.newGame);
		new_game.addActionListener(this);
		add(new_game);
		rank = new JButton(Config.rank);
		rank.addActionListener(this);
		add(rank);
		level = new JButton(Config.level);
		level.addActionListener(this);
		add(level);
		exit = new JButton(Config.exit);
		exit.addActionListener(this);
		add(exit);
		pack();
		setSize(Config.windowWidth,Config.windowHeight);
		setVisible(true);
	}

	/**
	 * Metoda odpowiedzialna za ustalenie akcji po naciœniêciu w dany przycisk.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == new_game){
			new NickWindow().setVisible(true);
			dispose();
		}else if(source == rank){
			new Highscores().setVisible(true);
			dispose();
		}else if(source == level){
			new LevelWindow().setVisible(true);
			dispose();
		}else if(source == exit){
			dispose();
		}
		
	}

}




