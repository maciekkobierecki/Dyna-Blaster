package defaultpackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
/**
 * klasa implementuj¹ca klienta komunikuj¹cego siê z serwerem w celu pobierania
 * danych takich jak: konfiguracje map, parametry konfiguracyjne programu, ranking
 * @author Maciek Kobierecki
 * @author Patryk Gozdera
 *
 */
public class Client {

	/**
	 * konstruktor przyjmuj¹cy za parametr wartoœæ logiczn¹, która mówi
	 * czy wysy³aæ od razu zapytania na serwer oraz  tablicê nazw plików, 
	 * które maj¹ zostaæ pobrane z serwera 
	 * i zaktualizowane (lub jeœli nie istniej¹, utworzne)
	 * @param fileNames-nazwy plików, które chcemy pobraæ z serwera
	 */
	Client(Boolean requestImmediately, String[] fileNames){
		if(requestImmediately)
		for(int i=0; i<fileNames.length; i++){
			getDataFromServer(fileNames[i]);
		}
	}
	
	/**
	 * metoda t³umacz¹ca nazwy plików an requesty protoko³u
	 * @param fileName-nazwa pliku
	 * @return-request protoko³u
	 */
	public String translateFileNameToRequest(String fileName){
		String translatedFileNameToRequest="error";
		if(fileName.equals("config.properties"))
			translatedFileNameToRequest="GET CONFIG PROPERTIES";
		else if(fileName.equals("highscores"))
			translatedFileNameToRequest="GET HIGHSCORES";
		else if(fileName.equals("maps"))
			translatedFileNameToRequest="GET MAPLIST";
		else if(fileName.substring(0,3).equals("map"))
			translatedFileNameToRequest="GET LEVEL INFO "+ fileName.substring(3, fileName.length());
			return translatedFileNameToRequest;
	}
	/**
	 * metoda wysy³aj¹ca na serwer wynik gracza
	 * @return "GAME SCORE ACCEPTED"- gdy wynik znalaz³ siê w top10
	 * "GAME SCORE NOT ACCEPTED"- gdy wynik nie znalaz³ siê w top10
	 */
	public String AddScoreToServer(String name, int score){
		try {
			Socket socket=new Socket(Config.serverAddress, Config.port);
			OutputStream os=socket.getOutputStream();
			PrintWriter pw= new PrintWriter(os, true);
			pw.println("GAME SCORE");
			pw.println(name);
			pw.println(score);
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String answer=br.readLine();
			socket.close();
			os.close();
			pw.close();
			return answer;
		
		}
		catch(SocketTimeoutException e){
			System.out.println("przekroczono czas oczekiwania");
		}
		catch (Exception e){
			System.err.println("Client exception: "+ e);
			JOptionPane.showMessageDialog(null,"Blad!",null,JOptionPane.WARNING_MESSAGE);
		}
		
		return "1";
	}
	
	/**
	 * metoda wysy³aj¹ca ¿¹danie wys³ania danych zawieraj¹cych siê w pliku o danej nazwie
	 * i wywo³uj¹ca metody zwi¹zane z parsowaniem odebranych danych. Metoda bêdzie czekaæ
	 * na dane do czasu, a¿ po³aczenie zostanie zamkniête przez serwer.
	 * @param fileName
	 */
	public void getDataFromServer(String fileName){
		ArrayList<String>recievedData=new ArrayList<>();
		try {
			Socket socket=new Socket(Config.serverAddress, Config.port);
			//socket.setSoTimeout(2000);
			OutputStream os=socket.getOutputStream();
			PrintWriter pw= new PrintWriter(os, true);
			String translatedFileNameToRequest=translateFileNameToRequest(fileName);			
			pw.println(translatedFileNameToRequest);
			InputStream is=socket.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(is));
			String line;
			while((line=br.readLine()) != null)
				recievedData.add(line);
			switch(fileName){
			case "config.properties":
				Config.readConstants(ReadConfig.parseProperties(recievedData));
				break;
			case "maps":
				createFileAndWrite("maps.txt", recievedData);
				getFromServerEveryMap();
				break;
			case "highscores":
				createFileAndWrite("highscores.txt", recievedData);
			default:
				createFileAndWrite(fileName+".txt", recievedData);
				
			}
			socket.close();
			os.close();
			pw.close();
			
	}
		catch(SocketTimeoutException e){
			System.out.println("przekroczono czas oczekiwania");
		}
		catch (Exception e){
			System.err.println("Client exception: "+ e);
			JOptionPane.showMessageDialog(null,"Blad!",null,JOptionPane.WARNING_MESSAGE);
		}
		
	}
	
	/**
	 * metoda inicjuj¹ca wys³anie ¿¹dania wszystkich map, których nazwy
	 * zawarte s¹ w pliku maps.txt. Gdy Wczeœniej zostanie wys³ane ¿¹danie
	 * pobrania zawartoœci pliku maps.txt, z serwera zostan¹ pobran wszystkie
	 * definicje map znajduj¹ce siê na serwerze
	 */
	private void getFromServerEveryMap() {
		try	(BufferedReader br = new BufferedReader(new FileReader("maps.txt"))) {
		    	String line;
			    while ((line = br.readLine()) != null) {
			      getDataFromServer(line);
			    }
			}
		    catch(Exception e){
		    	System.out.println("error");
		    }
		    
		}
		
	/**
	 * metoda zapisuj¹ca/nadpisuj¹ca pliki (konfiguracyjne, rankingu i konfiguracji map)
	 * danymi pobranymi z serwera
	 * @param fileName- nazwa pliku, do którego zostan¹ zapisane dane konfiguracyjne
	 * @param data-Lista danych w postaci ³añcuchów znaków
	 */
	public void createFileAndWrite(String fileName, ArrayList<String>data){
		try{
			PrintWriter writer= new PrintWriter(fileName, "UTF-8");
			for(String str : data)
			writer.println(str);
			writer.close();
		}
		catch(IOException e){
			System.out.println("error");
		}
	}
	
	
	
}
