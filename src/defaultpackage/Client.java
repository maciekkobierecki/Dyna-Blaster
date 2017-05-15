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
/**
 * klasa implementuj�ca klienta komunikuj�cego si� z serwerem w celu pobierania
 * danych takich jak: konfiguracje map, parametry konfiguracyjne programu, ranking
 * @author Maciek
 *
 */

public class Client {

	/**
	 * konstruktor przyjmuj�cy za parametr tablic� nazw plik�w, kt�re maj� zosta� pobrane z serwera 
	 * i zaktualizowane (lub je�li nie istniej�, utworozne)
	 * @param fileNames-nazwy plik�w, kt�re chcemy pobra� z serwera
	 */
	Client(String[] fileNames){
		for(int i=0; i<fileNames.length; i++){
			getDataFromServer(fileNames[i]);
		}
	}
	/**
	 * metoda wysy�aj�ca ��danie wys�ania danych zawieraj�cych si� w pliku o danej nazwie
	 * i wywo�uj�ca metody zwi�zane z parsowaniem odebranych danych. Metoda b�dzie czeka�
	 * na dane do czasu, a� po�aczenie zostanie zamkni�te przez serwer.
	 * @param fileName
	 */
	public void getDataFromServer(String fileName){
		ArrayList<String>recievedData=new ArrayList<>();
		try {
			Socket socket=new Socket(Config.serverAddress, Config.port);
			//socket.setSoTimeout(2000);
			OutputStream os=socket.getOutputStream();
			PrintWriter pw= new PrintWriter(os, true);
			pw.println(fileName);
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
				createFileAndWrite(fileName, recievedData);
			}
			socket.close();
	}
		catch(SocketTimeoutException e){
			System.out.println("przekroczono czas oczekiwania");
		}
		catch (Exception e){
			System.err.println("Client exception: "+ e);
		}
		
	}
	/**
	 * metoda inicjuj�ca wys�anie ��dania wszystkich map, kt�rych nazwy
	 * zawarte s� w pliku maps.txt. Gdy Wcze�niej zostanie wys�ane ��danie
	 * pobrania zawarto�ci pliku maps.txt, z serwera zostan� pobran wszystkie
	 * definicje map znajduj�ce si� na serwerze
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
 * metoda zapisuj�ca/nadpisuj�ca pliki (konfiguracyjne, rankingu i konfiguracji map)
 * danymi pobranymi z serwera
 * @param fileName- nazwa pliku, do kt�rego zostan� zapisane dane konfiguracyjne
 * @param data-Lista danych w postaci �a�cuch�w znak�w
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
