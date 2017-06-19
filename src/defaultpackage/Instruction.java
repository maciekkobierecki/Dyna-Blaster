package defaultpackage;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 * Klasa okna instrukcji gry
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 */
public class Instruction extends JFrame{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Pole tekstowe
	 */
	JTextArea text;
	
	/**
	 * Konstruktor klasy
	 * Odpowiada za sczytanie instrukcji z pliku 
	 */
	public Instruction(){
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wr�ci�?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
					new MainWindow();
				}
			}
		});
		
		text = new JTextArea();
		this.add(text);
			File file = new File("instrukcja.txt");
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine()){
					text.append(scanner.nextLine() + "\n");
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			text.setEditable(false);
			text.setForeground(Color.yellow);
			text.setBackground(Color.black);
			text.setOpaque(true);
			pack();
		}
	}

