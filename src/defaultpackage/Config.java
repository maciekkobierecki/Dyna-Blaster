package defaultpackage;

import java.util.Properties;

/**
 * Klasa zawieraj¹ca ró¿nego typu sta³e, w³aœciwe dla ich wykorzystania w programie.
 * S¹ one udostêpniane dla wszystkich komponentów programu.
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
	 * Przycisk listy najlepszych wyników.
	 */
    public static String rank;
    
    /**
	 * Przycisk poziomów trudnoœci.
	 */
    public static String level;
    
    /**
	 * Przycisk wyjœcia z gry.
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
	 * Szerokoœæ okna.
	 */
    public static int windowWidth;
    
    /**
	 * Wysokoœæ okna.
	 */
    public static int windowHeight;
    
    /**
	 * Nazwa okna wyboru poziomu.
	 */
    public static String levelWindowName;
    
    /**
	 * Poziom ³atwy.
	 */
    public static String level1;
    
    /**
	 * Poziom œredni.
	 */
    public static String level2;
    
    /**
	 * Poziom trudny.
	 */
    public static String level3;
    
    /**
	 * Nazwa okna najlepszych wyników.
	 */
    public static String HighscoreWindowName;
    
    /**
	 * Nazwa okna wyboru nick;u.
	 */
    public static String NickWindowName;
    
    /**
  	 * Nazwa okna instrukcji.
  	 */
      public static String InstructionWindowName;
    
    /**
	 * Przycisk OK.
	 */
    public static String OkButton;
    
    /**
	 * Przycik Anuluj.
	 */
    public static String Cancelbutton;
  
   /**
    * pole przechowuj¹ce przedrostek scie¿ki grafik obiektów graficznych
    */
    public static String graphicsPath="";
    
    //parametry zwi¹zane z rozgrywka
   /**
    * szerokoœæ okna gry
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
    public static String easyLevelPlayerSpeed;
    /** 
     * przechowuje predkosc poczatkowa predkosc gracza na srednim poziomie
     */
    public static String mediumLevelPlayerSpeed;
    /** 
     * przechowuje predkosc poczatkowa predkosc gracza na trudnym poziomie
     */
    public static String hardLevelPlayerSpeed;
    
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
     * czas trwania bonusu
     */
    public static int powerUpTime;
    
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
     * Metoda parsuj¹ca dane konfiguracyjne.
     * @param config obiekt Properties, w którym znajduj¹ siê dane konfiguracyjne.
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
    
    /**
     * getter zwracaj¹cy prêdkoœæ potwora
     */
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
    
    /**
     * getter zwracaj¹cy liczbê ¿yæ
     */
    public static int getAmountOfLives(){
    	return Integer.parseInt(amountOfLives);
    }
    
    /**
     * getter zwracaj¹cy czas poziomu
     */
    public static int getLevelTime() { return Integer.parseInt(levelTime); }
    
    /**
     * zwraca liczbe punktów za zabicie potwora na wybranym poziomie trudnosci
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
    
    /**
     * metoda sczytuj¹ca sta³e 
     */
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
        InstructionWindowName = config.getProperty("InstructionWindowName");
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
        powerUpTime=Integer.parseInt(config.getProperty("powerUpTime"));
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


