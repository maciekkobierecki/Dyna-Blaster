package defaultpackage;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Klasa okna g³ównego programu.
 * <p>
 * @author Patryk Gozdera 
 * @author Maciej Kobierecki
 *
 */
public class MainWindow extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Przyciski wykorzystywane w MENU.
	 */
	private JButton new_game,rank,instruction,config,exit;
	
	/**
	 * checkbox mowiacy o tym, czy tekstury bêd¹ "podstawowe" czy ulepszone
	 */
	private JCheckBox simpleTextures, synchronizeOnline;
	
	/**
	 * zmienna statyczna mówi¹ca o tym, czy gracz chce synchronizacji online
	 */
	private static Boolean synchronize;
	
	/**
	 * Konstruktor okna g³ównego. Ustala jego parametry i definiuje przyciski.
	 */
	public MainWindow() {
		super(Config.gameName);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(Config.windowWidth,Config.windowHeight));
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				int x = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz wyjœæ?","", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(x == JOptionPane.YES_OPTION){
					e.getWindow().dispose();
				}
			}
		});
		synchronize=false;
		setLocation(100,100);
		setLayout(new GridLayout(7, 1));
		new_game = new JButton(Config.newGame);
		new_game.addActionListener(this);
		add(new_game);
		rank = new JButton(Config.rank);
		rank.addActionListener(this);
		add(rank);
		instruction = new JButton(Config.InstructionWindowName);
		instruction.addActionListener(this);
		add(instruction);
		config=new JButton(Config.configuration);
		config.addActionListener(this);
		add(config);
		exit = new JButton(Config.exit);
		exit.addActionListener(this);
		add(exit);
		simpleTextures=new JCheckBox("Podstawowe tekstury");
		synchronizeOnline=new JCheckBox("synchronizacja online");
		add(simpleTextures);
		add(synchronizeOnline);
		simpleTextures.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(simpleTextures.isSelected())
					Config.graphicsPath="simpleTextures\\";
				else 
					Config.graphicsPath="";
			}
		});
		synchronizeOnline.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(synchronizeOnline.isSelected())
					synchronize=true;
				else 
					synchronize=false;
			}
		});
		pack();
		setVisible(true);
	}
	
	/**
	 * getter zwracaj¹cy wyra¿enie logiczne mówi¹ce czy program ma sie ³¹czyæ z sieci¹
	 */
	public static Boolean synchronize(){
		return synchronize;
	}
	
	/**
	 * Metoda odpowiedzialna za ustalenie akcji po naciœniêciu w dany przycisk.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == new_game){
			new NickWindow().setVisible(true);
			dispose();
		}else if(source == rank){
			new Highscores().setVisible(true);
			dispose();
		}else if(source == exit){
			dispose();
		}else if(source ==config){
			String[] files={"maps", "highscores","config.properties"};
			Client client=new Client(true, files);
		}else if (source == instruction){
			new Instruction().setVisible(true);
			dispose();
		}
		
	}

}


