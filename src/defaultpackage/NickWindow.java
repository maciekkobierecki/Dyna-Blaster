package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
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
	public static String a;
	
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
		this.setPreferredSize(new Dimension(Config.windowWidth/2,Config.windowHeight));
		setLocation(10,10);
		setLayout(new GridLayout(4,1));

		
		n = new JLabel("Wpisz swój nick:");
		add(n);
		
		field = new JTextField("");
		field.setEditable(true);
		field.setVisible(true);
		field.setForeground(Color.yellow);
		field.setBackground(Color.BLACK);
		field.setOpaque(true);
		add(field);
		
		ok = new JButton(Config.OkButton);
		ok.addActionListener(this);
		add(ok);
		
		cancel = new JButton(Config.Cancelbutton);
		cancel.addActionListener(this);
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
			a = field.getText();
			System.out.println(a);
			if(a.trim().equals("")){
				JOptionPane.showMessageDialog(null,"Proszê wpisaæ nick!",null,JOptionPane.WARNING_MESSAGE);
				field.requestFocus();
			}
			else {
				
				LevelWindow levelWindow=new LevelWindow();
				levelWindow.setVisible(true);
				dispose();
				
				
				/*
				
				*/
			}
		}
		
	}
}

