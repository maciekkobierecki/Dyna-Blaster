package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Klasa okna wyboru nick'u.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */

public class NickWindow extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Deklaracja etykiety, która wyœwietla komunikat o wyborze nicku'u.
	 */
	private JLabel n;
	
	/**
	 * Deklaracja pola tekstowego, przeznaczonego na pole wyboru.
	 */
	private JTextField field;
	
	/**
	 * Deklaracja przycisków, u¿ytych w oknie.
	 */
	private JButton ok, cancel;
	
	/**
	 * Konstruktor klasy okna wyboru nicku'u.
	 * <p>
	 * Ustala jego parametry i umo¿liwia wybór w³asnego nick'u.
	 */
	public NickWindow(){
		super( Config.NickWindowName );
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
		
		n = new JLabel("Wpisz swój nick:");
		n.setPreferredSize(new Dimension(100, 100));
		n.setBounds(100, 10, 500, 100);
		add(n);
		
		field = new JTextField("");
		field.setBounds(150, 10, 500, 100);
		field.setEditable(true);
		field.setVisible(true);
		field.setForeground(Color.yellow);
		field.setBackground(Color.BLACK);
		field.setOpaque(true);
		add(field);
		
		ok = new JButton(Config.OkButton);
		ok.setBounds(10, 100, 250, 100);
		ok.addActionListener(this);
		add(ok);
		
		cancel = new JButton(Config.Cancelbutton);
		cancel.addActionListener(this);
		cancel.setBounds(270, 100, 250, 100);
		add(cancel);
		pack();
	}
	
	/**
	 * Metoda odpowiedzialna za ustalenie akcji po naciœniêciu w dany przycisk.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == cancel){
			dispose();
			new MainWindow().setVisible(true);
		}else if(source == ok){
			String a = field.getText();
			System.out.println(a);
			if(a.trim().equals("")){
				JOptionPane.showMessageDialog(null,"Proszê wpisaæ nick!",null,JOptionPane.WARNING_MESSAGE);
				field.requestFocus();
			}
			else {
				Game game = new Game();
				game.setVisible(true);
				game.read();
				//JFrame frame = new JFrame();
				game.setTitle(Config.gameName + " - ROZGRYWKA");
				//frame.add(game);
				game.pack();
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				game.setLocationRelativeTo(null);
				game.setVisible(true);
				dispose();
			}
		}
		
	}
}

