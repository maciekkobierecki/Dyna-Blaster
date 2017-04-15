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
  
   
    
    
    //parametry zwi¹zane z rozgrywka
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
    private static String levelTime;
    
    
    private static String amountOfLives;
    
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
    public static void readConstants(Properties config) {

        gameName = config.getProperty("gameName");
        windowWidth = Integer.parseInt(config.getProperty("windowWidth"));
        windowHeight = Integer.parseInt(config.getProperty("windowHeight"));
        newGame = config.getProperty("newGame");
        rank = config.getProperty("rank");
        level = config.getProperty("level");
        exit = config.getProperty("exit");
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
        
        
        
          
    }


    


}

