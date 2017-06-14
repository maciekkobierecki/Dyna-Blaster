package defaultpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Klasa okna najlepszych wyników, wczytywanych z pliku.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Highscores extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Deklaracja pola tekstowego.
	 */
	JTextArea text;
	
	/**
	 * Konstruktor klasy okna najlepszych wyników.
	 * <p>
	 * Ustala jego parametry i odpowiada za wczytanie listy TOP10 z pliku tekstowego.
	 */
	public Highscores(){
		
		super( Config.HighscoreWindowName );
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
		
		text = new JTextArea();
		this.add(text);
			int i=1;
			File file = new File("highscores.txt");
			try {
				@SuppressWarnings("resource")
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine() && i<11){
					text.append(i+++"."+scanner.nextLine() + ":  " +  scanner.nextLine() + "\n");
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
	public static ArrayList<String> loadHighscoresToArrayList(){
		ArrayList<String> rank=new ArrayList<>();
		try(BufferedReader br=new BufferedReader(new FileReader("highscores.txt"))) {
			String line;
			while((line=br.readLine())!=null){
				rank.add(line);
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return rank;
	}
	/**
	 * Metoda dodaj¹ca punkty po skoñczonej grze. Gdy gracz wyrazi chêc aby
	 * jego wynik trafi³ na serwer to tak siê dzieje. Gracz wyra¿a tê chêæ 
	 * poprzez zaznaczenie checkboxa w g³ównym oknie aplikacji.
	 * W przeciwnym razie wynik zapisywany jest w pliku lokalnym.
	 * @param name
	 * @param score
	 */
	public static void addScore(String name, int score){
		if(MainWindow.synchronize()){
			Client client=new Client(false, null);
			if(client.AddScoreToServer(name, score).equals("GAME SCORE ACCEPTED"))
				JOptionPane.showMessageDialog(null,"Jesteœ w TOP10!",null,JOptionPane.WARNING_MESSAGE);
			else
				JOptionPane.showMessageDialog(null,"Twój wynik jest za niski by znalezæ siê w TOP10!",null,JOptionPane.WARNING_MESSAGE);
		}
		else{
		ArrayList<String>rank=loadHighscoresToArrayList();
		for(int i=0; i<rank.size(); i+=2){
			if(i+1<rank.size()){
				int scoreFromHighscores=Integer.parseInt(rank.get(i+1));
				if(scoreFromHighscores<score){
					rank.add(i, Integer.toString(score));
					rank.add(i, name);
					break;
				}
			}
			else {
				rank.add(name);
				rank.add(Integer.toString(score));
			}				
		}
		saveHighscoresToFile(rank);
		}
	}
	
	public static void saveHighscoresToFile(ArrayList<String> highscores){
		File fold = new File("highscores.txt");
		fold.delete();
		File fnew= new File("highscores.txt");
		try{
			FileWriter f2=new FileWriter(fnew, false);
			BufferedWriter bw=new BufferedWriter(f2);
			for(int i=0; i<highscores.size(); i++){
				bw.write(highscores.get(i));
				bw.newLine();
			}
			bw.close();
		}		
		catch(IOException e){
			e.printStackTrace();
		}
	}
	


	
}
