package defaultpackage;

import java.io.IOException;
import java.util.Properties;
/**
 * Dyna Blaster
 * <p>
 * Celem gry jest niszczenie obiektów poruszaj¹cych siê na planszy i odnajdowanie przejœæ do kolejnych poziomów. 
 * <p>
 * Plansza gry przedstawia labirynt z przeszkodami, po którym porusza siê bohater gry (Bomber) oraz próbuj¹ce go zniszczyæ potwory. 
 * Bomber oczyszcza przejœcia za pomoc¹ bomb. Wybuchaj¹ca bomba mo¿e zniszczyæ obiekty w pewnym otoczeniu – potwory, przeszkody, a tak¿e samego Bombera. 
 * Niektóre obiekty, np. œciany labiryntu, s¹ niezniszczalne, zatrzymuj¹ równie¿ falê uderzeniow¹. 
 * Zadaniem Bombera jest odnalezienie, pocz¹tkowo ukrytego, np. pod jedn¹ z przeszkód, przejœcia do kolejnego poziomu.
 * <p>
 * Gra sk³ada siê z poziomów – plansz, przedstawiaj¹cych ró¿ne uk³ady labiryntu, przeszkód i potworów. 
 * Przejœcie na kolejny poziom nastêpuje po pomyœlnym ukoñczeniu aktualnego. Gracz ma ograniczon¹ liczbê "¿yæ".
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 * @version 1.0
 */
public class Main {
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {*/
		/**
		 * Wczytywanie danych z pliku konfiguracyjnego
		 * i u¿ycie ich w programie.
		 * Tworzenie okna g³ównego.
		 */
				String configPath = "config.properties";
				ReadConfig readConfig = new ReadConfig();
				try{
				Properties prop = readConfig.getProperties(configPath);
                Config.readConstants(prop);
				
				new MainWindow();
				} catch (IOException e) {
		            System.out.println(e);
		        }
		}
	}

