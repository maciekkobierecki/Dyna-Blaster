package defaultpackage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
}
