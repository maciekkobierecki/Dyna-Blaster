package defaultpackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Klasa zwi¹zana z wczytywaniem pliku konfiguracyjnego.
 * 	
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class ReadConfig {

	/**
	 * Metoda s³u¿¹ca do wczytywania zawartoœci z pliku konfiguracyjnego z pliku properties.
	 * 
	 * @param propFilePath œcie¿ka dostêpu do pliku konfiguracyjnego.
	 * @return obiekt Properties zawieraj¹cy dane z pliku
	 * @throws IOException	w przypadku niepowodzenia z wczytywaniem pliku.
	 */
    public final Properties getProperties(String propFilePath) throws IOException {

        Properties prop = new Properties();
        try (InputStream inputStream = new FileInputStream(propFilePath)) {
            prop.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
 
    }
    
    /**
     * metoda s³u¿¹ca do zamiany listy ³añcuchów znaków na obiekt klasy Properties 
     * @param recievedData - lista stringów z parametrami w formacie key=value
     */

	public static Properties parseProperties(ArrayList<String> recievedData) {
		Properties prop=new Properties();
		for(int i=0; i<recievedData.size(); i++){
			String[] keyAndValue=new String[2];
			String line=recievedData.get(i);
			keyAndValue=line.split("=");
			prop.setProperty(keyAndValue[0], keyAndValue[1]);
		}
		return prop;
		
	}
}
