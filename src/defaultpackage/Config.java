package defaultpackage;

import java.util.ArrayList;
import java.util.Properties;

/**
 * Klasa zawieraj�ca r�nego typu sta�e, w�a�ciwe dla ich wykorzystania w programie.
 * S� one udost�pniane dla wszystkich komponent�w programu.
 * 
 * @author Patryk Gozdera
 * @author Maciej Kobierecki
 *
 */
public class Config {

	/**
	 * Nazwa gry.
	 */
    public static String gameName;
    
    /**
	 * Przycisk nowej gry.
	 */
    public static String newGame;
    
    /**
	 * Przycisk listy najlepszych wynik�w.
	 */
    public static String rank;
    
    /**
	 * Przycisk poziom�w trudno�ci.
	 */
    public static String level;
    
    /**
	 * Przycisk wyj�cia z gry.
	 */
    public static String exit;
    
    /**
   	 * Przycisk wczytywania konfiguracji gry z serwera.
   	 */
       public static String configuration;
       
    /**
     * adres serwera 
     */
    public static String serverAddress;
    
    /**
     * port 
     */
    public static int port;
    /**
	 * Szeroko�� okna.
	 */
    public static int windowWidth;
    
    /**
	 * Wysoko�� okna.
	 */
    public static int windowHeight;
    
    /**
	 * Nazwa okna wyboru poziomu.
	 */
    public static String levelWindowName;
    
    /**
	 * Poziom �atwy.
	 */
    public static String level1;
    
    /**
	 * Poziom �redni.
	 */
    public static String level2;
    
    /**
	 * Poziom trudny.
	 */
    public static String level3;
    
    /**
	 * Nazwa okna najlepszych wynik�w.
	 */
    public static String HighscoreWindowName;
    
    /**
	 * Nazwa okna wybotu nick;u.
	 */
    public static String NickWindowName;
    
    /**
	 * Przycisk OK.
	 */
    public static String OkButton;
    
    /**
	 * Przycik Anuluj.
	 */
    public static String Cancelbutton;
  
   
    
    
    //parametry zwi�zane z rozgrywka
   /**
    * szeroko�� okna gry
    */
    public static int gameWindowWidth;
    
    
    /**
     * wysokosc okna gry
     */
     public static int gameWindowHeight;
     
     
     
    /**
     * String przechowujacy predkosc potworow na latwym poziomie
     */
    private static String easyLevelEnemySpeed;
    
    /**
     * String przechowujacy predkosc potworow na srednim poziomie
     */
    private static String mediumLevelEnemySpeed;
    
    /**
     * String przechowujacy predkosc potworow na trudnym poziomie
     */
    private static String hardLevelEnemySpeed;
    
    /** 
     * przechowuje predkosc poczatkowa predkosc gracza na latwym poziomie
     */
    private static String easyLevelPlayerSpeed;
    /** 
     * przechowuje predkosc poczatkowa predkosc gracza na srednim poziomie
     */
    private static String mediumLevelPlayerSpeed;
    /** 
     * przechowuje predkosc poczatkowa predkosc gracza na trudnym poziomie
     */
    private static String hardLevelPlayerSpeed;
    
    /**
     * czas trwania poziomu
     */
    public static String levelTime;
    
    /**
     * liczba zyc gracza
     */
    private static String amountOfLives;
    /**
     * czas od postawienia bomby do wybuchu
     */
    
    public static int timeToExplodeBomb;
    /**
     * zasieg bomby na srednim poziomie
     */
    public static int mediumLevelBombRange;
    /**
     * zasieg bomby na trudnym poziomie
     */
    public static int hardLevelBombRange;
    /**
     * zasieg bomby na latwym poziomie
     */
    public static int easyLevelBombRange;
    /**
     * punkty dodawane graczowi za zabicie potwora na latwym poziomie
     */
    public static int pointsForEnemyDeathAtEasyLevel;
    /**
     * punkty dodawane graczowi za zabicie potwora na srednim poziomie
     */
    public static int pointsForEnemyDeathAtMediumLevel;
    /**
     * punkty dodawane graczowi za zabicie potwora na trudnym poziomie
     */
    public static int pointsForEnemyDeathAtHardLevel;
    
    /**
     * Metoda parsuj�ca dane konfiguracyjne.
     * @param config obiekt Properties, w kt�rym znajduj� si� dane konfiguracyjne.
     */
    public static int getPlayerSpeed(){
    	switch(LevelWindow.level)
    	{
	    	case "easy":
	    		return Integer.parseInt(easyLevelPlayerSpeed);
	    	case "medium":
	    		return Integer.parseInt(mediumLevelPlayerSpeed);
	    	case "hard":
	    		return Integer.parseInt(hardLevelPlayerSpeed);
	    	default: return -1;
    	}
    		
    		
    }
    	
    public static int getEnemySpeed(){
    	switch(LevelWindow.level)
    	{
    	    case "easy":
    	    	return Integer.parseInt(easyLevelEnemySpeed);
    	    case "medium":
    	    	return Integer.parseInt(mediumLevelEnemySpeed);
    	    case "hard":
    	    	return Integer.parseInt(hardLevelEnemySpeed);
    	    default: return -1;
    	    		
    	    		
    	}
    }
    
    public static int getAmountOfLives(){
    	return Integer.parseInt(amountOfLives);
    }
    	 
    public static int getLevelTime() { return Integer.parseInt(levelTime); }
    
    /**
     * zwraca liczbe punkt�w za zabicie potwora na wybranym poziomie trudnosci
     */
    public static int getPoints(){
    	switch(LevelWindow.level){
    	case "easy":
    		return pointsForEnemyDeathAtEasyLevel;
    	case "medium":
    		return pointsForEnemyDeathAtMediumLevel;
    	case "hard":
    		return pointsForEnemyDeathAtHardLevel;
    	default:
    		return pointsForEnemyDeathAtEasyLevel;
    	}
    }
    public static void readConstants(Properties config) {

        gameName = config.getProperty("gameName");
        windowWidth = Integer.parseInt(config.getProperty("windowWidth"));
        windowHeight = Integer.parseInt(config.getProperty("windowHeight"));
        gameWindowWidth = Integer.parseInt(config.getProperty("gameWindowWidth"));
        gameWindowHeight = Integer.parseInt(config.getProperty("gameWindowHeight"));
        newGame = config.getProperty("newGame");
        rank = config.getProperty("rank");
        level = config.getProperty("level");
        exit = config.getProperty("exit");
        configuration= config.getProperty("config");
        levelWindowName = config.getProperty("levelWindowName");
        level1 = config.getProperty("level1");
        level2 = config.getProperty("level2");
        level3 = config.getProperty("level3");
        HighscoreWindowName = config.getProperty("HighscoreWindowName");
        NickWindowName = config.getProperty("NickWindowName");
        OkButton = config.getProperty("OkButton");
        Cancelbutton = config.getProperty("CancelButton");
        easyLevelEnemySpeed=config.getProperty("easyLevelEnemySpeed");
        mediumLevelEnemySpeed=config.getProperty("mediumLevelEnemySpeed");
        hardLevelEnemySpeed=config.getProperty("hardLevelEnemySpeed");
        easyLevelPlayerSpeed=config.getProperty("easyLevelPlayerSpeed");
        mediumLevelPlayerSpeed=config.getProperty("mediumLevelPlayerSpeed");
        hardLevelPlayerSpeed=config.getProperty("hardLevelPlayerSpeed");
        levelTime=config.getProperty("levelTime");
        amountOfLives=config.getProperty("amountOfLives");
        timeToExplodeBomb=Integer.parseInt(config.getProperty("timeToExplodeBomb"));
        easyLevelBombRange=Integer.parseInt(config.getProperty("easyLevelBombRange"));
        mediumLevelBombRange=Integer.parseInt(config.getProperty("mediumLevelBombRange"));
        hardLevelBombRange=Integer.parseInt(config.getProperty("hardLevelBombRange"));
        serverAddress=config.getProperty("serverAddress");
        port=Integer.parseInt(config.getProperty("port"));
        pointsForEnemyDeathAtEasyLevel=Integer.parseInt(config.getProperty("pointsForEnemyDeathAtEasyLevel"));
        pointsForEnemyDeathAtMediumLevel=Integer.parseInt(config.getProperty("pointsForEnemyDeathAtMediumLevel"));
        pointsForEnemyDeathAtHardLevel=Integer.parseInt(config.getProperty("pointsForEnemyDeathAtHardLevel"));
        
          
    }

	


    


}


