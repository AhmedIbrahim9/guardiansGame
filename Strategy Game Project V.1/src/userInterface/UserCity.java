package userInterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicScrollBarUI;

import game.ActionHandler;
import game.Archer;
import game.Barracks;
import game.Blacksmith;
import game.Chariot;
import game.Defense;
import game.Knight;
import game.Memory;
import game.Nations;
import game.ResourcesCollector;
import game.Warrior;
import game.Worker;
import server.Connection;
import server.commandToSend;
import game.SpearMan;
import game.Stable;
import game.Storage;
import game.Timer;
import game.Upgrade;



@SuppressWarnings("serial")
public class UserCity extends JFrame implements MouseListener
{	
	//*****************************************Variables*****************************************\
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int) screenSize.getWidth();
	int height = (int) screenSize.getHeight();
	
	public static String buildingName = "";
	public static String folder = Nations.getName();
	
	boolean isBuilding          = false;
	boolean buildingsPriceInit  = false;
	boolean unitsPriceInit      = false;
	boolean blacksmithPriceInit = false;
	boolean stablePriceInit     = false;
	
	boolean isWorkerSelected    = false;
	
	//*****************************************Panels*****************************************\
	JPanel statesPanel     = new JPanel();
	JPanel mapPanel        = new JPanel();
	JPanel actionPanel     = new JPanel();
	JPanel buildingsPanel  = new JPanel();
	JPanel unitsPanel      = new JPanel();
	JPanel blacksmithPanel = new JPanel();
	JPanel upgradesPanel   = new JPanel();
	JPanel pricesPanel     = new JPanel();
	JPanel stablePanel     = new JPanel();
	JPanel logPanel        = new JPanel();
		
	//*****************************************Icons*****************************************\
	ImageIcon goldIcon    = new ImageIcon(new ImageIcon("Images & Sounds/symbols/goldIcon.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon soldierIcon = new ImageIcon(new ImageIcon("Images & Sounds/symbols/sword2.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon archerIcon  = new ImageIcon(new ImageIcon("Images & Sounds/symbols/bow2.png").getImage().getScaledInstance(65, 50, Image.SCALE_DEFAULT));
	ImageIcon knightIcon  = new ImageIcon(new ImageIcon("Images & Sounds/symbols/knight.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon chariotIcon = new ImageIcon(new ImageIcon("Images & Sounds/symbols/chariot.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon spearIcon   = new ImageIcon(new ImageIcon("Images & Sounds/symbols/spear2.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
	
	
	ImageIcon nationIcon         = new ImageIcon(new ImageIcon(folder + "/nation.png").getImage().getScaledInstance(250, height - 840, Image.SCALE_DEFAULT));
	ImageIcon mapPanelBackground = new ImageIcon(new ImageIcon("Images & Sounds/map/FinalMap.png").getImage().getScaledInstance(width , 1000, Image.SCALE_DEFAULT));
	
	public static ImageIcon barraksIcon     = new ImageIcon(new ImageIcon(folder + "/buildings/barracks.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon storageIcon     = new ImageIcon(new ImageIcon(folder + "/buildings/storage.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon defenceIcon     = new ImageIcon(new ImageIcon(folder + "/buildings/defence.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
	public static ImageIcon stableIcon      = new ImageIcon(new ImageIcon(folder + "/buildings/stable.png").getImage().getScaledInstance(140, 95, Image.SCALE_DEFAULT));
	public static ImageIcon goldMineIcon    = new ImageIcon(new ImageIcon(folder + "/buildings/goldMine.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));
	public static ImageIcon castleIcon      = new ImageIcon(new ImageIcon(folder + "/buildings/castle.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
	public static ImageIcon blacksmithIcon  = new ImageIcon(new ImageIcon(folder + "/buildings/blacksmith.png").getImage().getScaledInstance(135, 135, Image.SCALE_DEFAULT));
	
	static ImageIcon workShop     = new ImageIcon(new ImageIcon("Images & Sounds/workerBuilding.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	
	static ImageIcon buttonIcon  = new ImageIcon(new ImageIcon("Images & Sounds/Images/button1.png").getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT));
	static ImageIcon buttonIcon2 = new ImageIcon(new ImageIcon("Images & Sounds/Images/button2.png").getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT));
	static ImageIcon timerIcon   = new ImageIcon(new ImageIcon("Images & Sounds/Images/button1.png").getImage().getScaledInstance(300, 130, Image.SCALE_DEFAULT));
	
	static ImageIcon knightCharIcon   = new ImageIcon(new ImageIcon(folder + "/charachters/knight.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));	
	static ImageIcon soldierCharIcon  = new ImageIcon(new ImageIcon(folder + "/charachters/soldier.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));	
	static ImageIcon archerCharIcon   = new ImageIcon(new ImageIcon(folder + "/charachters/archer.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));	
	static ImageIcon spearmanCharIcon = new ImageIcon(new ImageIcon(folder + "/charachters/spearman.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));
	
	ImageIcon chariotCarIcon   = new ImageIcon(new ImageIcon("Images & Sounds/items/chariot.jpg").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));
	ImageIcon saddleIcon       = new ImageIcon(new ImageIcon("Images & Sounds/items/saddle.jpg").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));
	static ImageIcon warChariotIcon   = new ImageIcon(new ImageIcon(folder + "/charachters/chariotF.png").getImage().getScaledInstance(150, 130, Image.SCALE_DEFAULT));
	ImageIcon horseIcon        = new ImageIcon(new ImageIcon("Images & Sounds/items/horse.png").getImage().getScaledInstance(130, 130, Image.SCALE_DEFAULT));	
	
	
	ImageIcon upgradeIcon = new ImageIcon(new ImageIcon("Images & Sounds/Images/upgrades.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	
	ImageIcon doneIcon    = new ImageIcon(new ImageIcon("Images & Sounds/Images/done.png").getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
	
	ImageIcon selectedIcon    = new ImageIcon(new ImageIcon("Images & Sounds/selected.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
	
	ImageIcon squareIcon  = new ImageIcon(new ImageIcon("Images & Sounds/map/sqr.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));	
	
	ImageIcon backIcon    = new ImageIcon(new ImageIcon("Images & Sounds/Images/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));	
	
	ImageIcon controlsIcon = new ImageIcon(new ImageIcon("Images & Sounds/Images/controls.jpg").getImage().getScaledInstance(1200,700 ,Image.SCALE_DEFAULT));
	
	ImageIcon actionIcon = new ImageIcon(new ImageIcon("Images & Sounds/Images/action.png").getImage().getScaledInstance(1250,200 ,Image.SCALE_DEFAULT));
	
	ImageIcon decoration = new ImageIcon(new ImageIcon("Images & Sounds/symbols/one.png").getImage().getScaledInstance(200,30 ,Image.SCALE_DEFAULT));
	
	static ImageIcon workerIcon    = new ImageIcon(new ImageIcon("Images & Sounds/worker.png").getImage().getScaledInstance(175, 225, Image.SCALE_DEFAULT));	

	//*****************************************Map*****************************************\
	JLabel map[][] = new JLabel[6][6];
	
	public static JLabel target;
	
	//*****************************************Labels*****************************************\
	
	static JLabel timeLabel = new JLabel(timerIcon);
	
	JLabel goldLabel     = new JLabel(goldIcon);
	JLabel soldierLabel  = new JLabel(soldierIcon);
	JLabel archerLabel   = new JLabel(archerIcon);
	JLabel knightLabel   = new JLabel(knightIcon);
	JLabel chariotLabel  = new JLabel(chariotIcon);
	JLabel spearmanLabel = new JLabel(spearIcon);
	
	
	JLabel nationLabel   = new JLabel(nationIcon);
	JLabel mapPanelLabel = new JLabel(mapPanelBackground);
	
	static JLabel goldCount     = new JLabel(String.valueOf(ResourcesCollector.getStock()));
	static JLabel soldierCount  = new JLabel("0");
	static JLabel archerCount   = new JLabel("0");
	static JLabel knightCount   = new JLabel("0");
	static JLabel chariotCount  = new JLabel("0");
	static JLabel spearmanCount = new JLabel("0");
	
	JLabel barraksLabel           = new JLabel(barraksIcon);
	JLabel storageLabel           = new JLabel(storageIcon);
	JLabel defenceLabel           = new JLabel(defenceIcon);
	JLabel stableLabel            = new JLabel(stableIcon);
	JLabel goldMineLabel          = new JLabel(goldMineIcon);
	JLabel castleLabel    		  = new JLabel(castleIcon);
	JLabel blacksmithLabel 		  = new JLabel(blacksmithIcon);
	public static JLabel workerBuildingLabel    = new JLabel(workShop);
	
	public static JLabel knightCharLabel   = new JLabel(knightCharIcon);
	public static JLabel soldierCharLabel  = new JLabel(soldierCharIcon);
	public static JLabel archerCharLabel   = new JLabel(archerCharIcon);
	public static JLabel spearmanCharLabel = new JLabel(spearmanCharIcon);
	JLabel chariotBigLabel   = new JLabel(chariotCarIcon);
	public static JLabel warChariotLabel   = new JLabel(warChariotIcon);
	JLabel saddleLabel       = new JLabel(saddleIcon);
	JLabel horseLabel        = new JLabel(horseIcon);
	
	JLabel []upgrades = new JLabel[3];
	JLabel []prices   = new JLabel[6];
	
	static JLabel chariotCountLabel = new JLabel("Chariots  0");
	static JLabel saddleCountLabel  = new JLabel("Saddles   0");
	static JLabel horseCountLabel   = new JLabel("Horses    0");
	
	JLabel controls = new JLabel(controlsIcon);
	
	JLabel actionLabel = new JLabel(actionIcon);
	
	JLabel logsMark = new JLabel("~ Log : ");
	
	public static JLabel logs = new JLabel();
	
	JLabel decorationL = new JLabel(decoration);

	public static ArrayList<JLabel> workers = new ArrayList<JLabel>();
	
	JLabel selected = new JLabel(selectedIcon);
	
		
	//*****************************************Buttons*****************************************\
	JButton attackButton  = new JButton(buttonIcon);
	JButton buildButton   = new JButton(buttonIcon);
	JButton upgradeButton = new JButton(buttonIcon);
	JButton backButton    = new JButton(backIcon);
	JButton menuButton    = new JButton(buttonIcon);
	
	JButton test    = new JButton(buttonIcon);
	
	JButton mainMenuButton = new JButton(buttonIcon);
	JButton helpButton = new JButton(buttonIcon);
	JButton exitButton = new JButton(buttonIcon);
	
	JButton items = new JButton(buttonIcon);
	
	JPopupMenu popup = new JPopupMenu();
	JPopupMenu itemsPopup = new JPopupMenu();

	//*****************************************Scroll Pane*****************************************\
	JScrollPane mapPane = new JScrollPane(mapPanelLabel);
	
	//*****************************************Clips*****************************************\
	AudioInputStream audioInputStream ;
	Clip clip; 
	
	//*****************************************Cursor*****************************************\
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image     = toolkit.getImage("Images & Sounds/mouse/glove.png");
	Image image2    = toolkit.getImage("Images & Sounds/mouse/no.png");
	Cursor c        = toolkit.createCustomCursor(image , new Point(this.getX(), this.getY()), "img");
	Cursor n		= toolkit.createCustomCursor(image2 , new Point(this.getX(), this.getY()), "img");
	
	//*****************************************Layouts*****************************************\
	FlowLayout flow = new FlowLayout(FlowLayout.LEFT, 20, 5);
	
	ActionHandler action = new ActionHandler();
	Worker workerC = new Worker();
	
	Thread t;
	Thread work;
	
	public static int w = 0;
	public static int a = 0;
	public static int s = 0;
	public static int k = 0;
	public static int ch = 0;
	
	static int pos;
	

	public static Queue<String> q = new LinkedList<String>();
	
	static UserCity temp;
	
	//UserCity Constructor
	public UserCity() throws UnsupportedAudioFileException, IOException, LineUnavailableException 
	{	
		temp = this;

		t = new Thread(action);	
		t.start();
		
	    work = new Thread(workerC);		

		addMouseListener(this); 
		this.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_A)
				{
					try {
						attackFunction();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				} else if (e.getKeyCode() == KeyEvent.VK_B) {
					
					buildFunction();
					
				} else if (e.getKeyCode() == KeyEvent.VK_U) {
					
					upgradeFunction();
				} else if (e.getKeyCode() == KeyEvent.VK_M) {
					
					popup.show(e.getComponent(),menuButton.getX() - 5, menuButton.getY() +  menuButton.getHeight() + 35);
					try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					
					backAction();
				}
			}
		});
		
		Timer timer = new Timer();
		timer.startTimer();
		
		//Main frame settings
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
		setLayout(null);

			
		//Panels Layouts
		statesPanel.setLayout(null);
		mapPanel.setLayout(null);
		actionPanel.setLayout(null);
		logPanel.setLayout(new FlowLayout());
		logPanel.setVisible(true);
		
	
		buildingsPanel.setLayout(new FlowLayout(FlowLayout.LEFT,50,5));
		buildingsPanel.setVisible(false);
		
		unitsPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 80, 5));
		unitsPanel.setVisible(false);
		
		blacksmithPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 5));
		blacksmithPanel.setVisible(false);

		
		upgradesPanel.setLayout(flow);
		upgradesPanel.setVisible(false);
		
		pricesPanel.setLayout(flow);
		pricesPanel.setVisible(false);
		
		stablePanel.setLayout(flow);
		stablePanel.setVisible(false);
		
		//Panels Bounds
		statesPanel.setBounds(0,0,(int) width,58);
		mapPanel.setBounds(0, statesPanel.getHeight(), (int) width, 800);
		actionPanel.setBounds(0, mapPanel.getHeight() +  statesPanel.getHeight() , (int) width,(int) (height - 775));
		buildingsPanel.setBounds(260 , 10 , 1447 , 130);
		unitsPanel.setBounds(260 , 10 , 1447 , 130);
		blacksmithPanel.setBounds(260 , 10 , 1447 , 130);
		upgradesPanel.setBounds(260 , 10 , 1447 , 175);
		pricesPanel.setBounds(260 ,actionPanel.getHeight() - buildingsPanel.getHeight() - 30 , 1447 , 40);
		stablePanel.setBounds(260 , 10 , 1447 , 130);	
		
		//Panels Background color
		statesPanel.setBackground(Color.GRAY);
		mapPanel.setBackground(Color.DARK_GRAY);
		actionPanel.setBackground(Color.DARK_GRAY);
	    buildingsPanel.setBackground(Color.GRAY);
	    unitsPanel.setBackground(Color.GRAY);
	    blacksmithPanel.setBackground(Color.GRAY);
	    upgradesPanel.setBackground(Color.GRAY);
	    pricesPanel.setBackground(Color.GRAY);
	    stablePanel.setBackground(Color.GRAY);
	    
	    //adding main frame components
	    add(controls);
		add(statesPanel);
		add(mapPanel);
		add(actionPanel);
		
	
		initMap(); //set up map
	
		initStatesPanel(); 
		
		initActionPanel();
		
		initMapPanel();
		
		initBuildPanel();
			
		initUnitsPanel();
		
		initBlacksmithPanel();
		
		initUpgradesPanel();
		
		initstablePanel();
		
		initMenu();
		
		initItemsMenu();
		
		initPrices("Buildings");
		
		this.setFocusable(true);
		requestFocus();
	
		
		buildButton.setFocusable(false);
		attackButton.setFocusable(false);
		upgradeButton.setFocusable(false);
		mainMenuButton.setFocusable(false);
		backButton.setFocusable(false);
		items.setFocusable(false);
		helpButton.setFocusable(false);
		menuButton.setFocusable(false);
		exitButton.setFocusable(false);
		
	
		repaint();
	
	}

	//returns curent city
	public static UserCity getCity()
	{
		return 	temp;
		
	}
	//------return the current selected worker label
	public static JLabel getWorker()
	{
		return workers.get(pos);
	}
	
	//------Play click Function
	private void playClick() throws UnsupportedAudioFileException, IOException, LineUnavailableException
		{
			audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/click.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
				
	//------Initialize Upgrades Panel components
	private void initUpgradesPanel()
	{
		String text = "";
		for(int i=0 ; i<3 ; i++)
		{
			if(i == 0)
				text = "Worrior" + " " + String.valueOf(Memory.UpgradeWarrior) + " G";
			else if(i == 1)
				text = "Archer" + " " + String.valueOf(Memory.UpgradeArcher) + " G";
			else if(i == 2)
				text = "Spearman" + " " + String.valueOf(Memory.UpgradeSpearMan) + " G";
			
			upgrades[i] = new JLabel(upgradeIcon);
			upgrades[i].setHorizontalTextPosition(JLabel.CENTER);
			upgrades[i].setFont(new Font(getName() , Font.BOLD , 25));
			upgrades[i].setForeground(Color.WHITE);
			upgrades[i].setText(text);
			upgrades[i].addMouseListener(this);
			upgradesPanel.add(upgrades[i]);
					
		}
	}
	
	//------Initialize items menu
	private void initItemsMenu()
	{
		JMenuItem horse = new JMenuItem();
		JMenuItem saddle = new JMenuItem();
		JMenuItem chariot = new JMenuItem();

		horseCountLabel.setFont(new Font(getName() , Font.BOLD , 25));

		saddleCountLabel.setFont(new Font(getName() , Font.BOLD , 25));
	
		chariotCountLabel.setFont(new Font(getName() ,Font.BOLD , 25));
	
		horse.setPreferredSize(new Dimension(200, 25));
		saddle.setPreferredSize(new Dimension(200, 25));
		chariot.setPreferredSize(new Dimension(200, 25));
	
		horse.add(horseCountLabel);
		saddle.add(saddleCountLabel);
		chariot.add(chariotCountLabel);
		
		itemsPopup.add(horse) ;
		itemsPopup.add(saddle) ;
		itemsPopup.add(chariot) ;
			
	}
	
	//------Initialize Menu items
	private void initMenu()
	{
		JMenuItem mainMenu = new JMenuItem();
		JMenuItem help = new JMenuItem();
		JMenuItem exit = new JMenuItem();

		mainMenuButton.setFont(new Font(getName() ,Font.BOLD , 25));
		mainMenuButton.setHorizontalTextPosition(JLabel.CENTER);
		mainMenuButton.setForeground(Color.WHITE);
		mainMenuButton.setText("MAIN MENU");
		mainMenuButton.setBackground(null);
		mainMenuButton.setBorder(null);
		mainMenuButton.addMouseListener(this);
		
		helpButton.setFont(new Font(getName() , Font.BOLD , 25));
		helpButton.setHorizontalTextPosition(JLabel.CENTER);
		helpButton.setForeground(Color.WHITE);
		helpButton.setText("HELP");
		helpButton.setBackground(null);
		helpButton.setBorder(null);
		helpButton.addMouseListener(this);
		
		exitButton.setFont(new Font(getName() , Font.BOLD , 25));
		exitButton.setHorizontalTextPosition(JLabel.CENTER);
		exitButton.setForeground(Color.WHITE);
		exitButton.setText("EXIT");
		exitButton.setBackground(null);
		exitButton.setBorder(null);
		exitButton.addMouseListener(this);
		
		
		mainMenu.setPreferredSize(new Dimension(200, 60));
		help.setPreferredSize(new Dimension(200, 60));
		exit.setPreferredSize(new Dimension(200, 60));

	
		mainMenu.add(mainMenuButton);
		help.add(helpButton);
		exit.add(exitButton);
	
		popup.add(mainMenu) ;
		popup.add(help) ;
		popup.add(exit);
		
	}
	
	//------Initialize Buildings Panel components
	private void initBuildPanel()
	{			
		
		barraksLabel.addMouseListener(this);
		storageLabel.addMouseListener(this);
		defenceLabel.addMouseListener(this);
		stableLabel.addMouseListener(this);
		goldMineLabel.addMouseListener(this);
		blacksmithLabel.addMouseListener(this);
		
		buildingsPanel.add(stableLabel);
		buildingsPanel.add(barraksLabel);
		buildingsPanel.add(storageLabel);
		buildingsPanel.add(defenceLabel);	
		buildingsPanel.add(goldMineLabel);
		buildingsPanel.add(blacksmithLabel);
	}
	
	//------Initialize Prices Panel
	private void initPrices(String type)
	{ 
		if(type.equals("Buildings"))
		{
			if(unitsPriceInit ||  blacksmithPriceInit || stablePriceInit)
				pricesPanel.removeAll();
			
			buildingsPriceInit = true;
			String text = "";
			
	        for(int i=0 ; i<6 ; i++)
	        {
	        	switch(i)
	        	{
	        		case 0:
	        			text = "         " + String.valueOf(Memory.StablePrice) + " G    ";
	        			break;
	        		case 1:
	        			text = "       " + String.valueOf(Memory.BarracksPrice) + " G   ";
	        			break;
	        		case 2:
	        			text = "  " + String.valueOf(Memory.StoragePrice) + " G   ";
	        			break;
	        		case 3:
	        			text = "    " + String.valueOf(Memory.TowerPrice) + " G     ";
	        			break;
	        		case 4:
	        			text = "     " + String.valueOf(Memory.MinePrice) + " G      ";
	        			break;
	        		case 5:
	        			text = "      " + String.valueOf(Memory.BlacksmithPrice) + " G   ";
	        			break;
	        	
	        	}
	        	prices[i] = new JLabel(text);
	        	prices[i].setFont(new Font(getName() ,Font.BOLD ,25));
	        	pricesPanel.add(prices[i]);
	        	
	        } 
	        
	        unitsPriceInit = false;
	        blacksmithPriceInit = false;
	        stablePriceInit = false;
			
		} else if (type.equals("Units")) {
			
			if(buildingsPriceInit  ||  blacksmithPriceInit || stablePriceInit)
				pricesPanel.removeAll();
        	
			unitsPriceInit = true;
			String text = "";
			
	        for(int i=0 ; i<5 ; i++)
	        {
	        	switch(i)
	        	{
	        	case 0:
        			text = "             " + String.valueOf(Memory.GetWarriorPrice()) + " G   ";
        			break;
        		case 1:
        			text = "                " + String.valueOf(Memory.GetArcherPrice()) + " G   ";
        			break;
        		case 2:
        			text = "                " + String.valueOf(Memory.GetSpearManPrice()) + " G   ";
        			break;
        		case 3:
        			text = "     " + String.valueOf(Memory.knightPrice) + "G 1H 1S 1UW";
        			break;
        		case 4:
        			text =   String.valueOf(Memory.ChariotPrice) + "G 2H 1UW 1UA  ";
        			break;
	        	
	        	}
	        	prices[i] = new JLabel(text);
	        	prices[i].setFont(new Font(getName() ,Font.BOLD ,25));
	        	pricesPanel.add(prices[i]);
	        	
	        } 
	        
	        buildingsPriceInit = false;
	        blacksmithPriceInit = false;
	        stablePriceInit = false;
	        
        } else if (type.equals("Blacksmith")) {
			
        	if(buildingsPriceInit ||  unitsPriceInit || stablePriceInit)
				pricesPanel.removeAll();
        	
			blacksmithPriceInit = true;
			String text = "";
			
	        for(int i=0 ; i<2 ; i++)
	        {
	        	switch(i)
	        	{
	        		case 0:
	        			text = "       " + String.valueOf(Memory.SaddlePrice) + " G   ";
	        			break;
	        		case 1:
	        			text = "         " + String.valueOf(Memory.ChariotCarPrice) + " G   ";
	        			break;
	        	
	        	}
	        	prices[i] = new JLabel(text);
	        	prices[i].setFont(new Font(getName() ,Font.BOLD ,25));
	        	pricesPanel.add(prices[i]);
	        	
	        } 
	        
	        buildingsPriceInit = false;
	        unitsPriceInit = false;
	        stablePriceInit = false;
	        
        } else if (type.equals("stable")) {
			
			if(buildingsPriceInit ||  unitsPriceInit || blacksmithPriceInit)
				pricesPanel.removeAll();
        	
			stablePriceInit = true; 
	        	
	        	prices[0] = new JLabel("        " + String.valueOf(Memory.horsePrice) + " G   ");
	        	prices[0].setFont(new Font(getName() ,Font.BOLD ,25));
	        	pricesPanel.add(prices[0]);
	        	        
	        
	        buildingsPriceInit = false;
	        unitsPriceInit = false;
	        blacksmithPriceInit = false;
        }
		
		//buildingName = "";
	}
		
	//------Show Map Build Locations
	private void showBuildLocation()
	{
		isBuilding = true;
		for(int j = 0 ; j<6 ; j++)
			for(int i = 0 ; i<6 ; i++)
			{
				if((i == 5 && j == 4) || (i == 4 && j == 5))
					continue;
			
				map[i][j].setVisible(true);			
			}
	}
	  
	//------show units panel
	private void showUnitsPanel()
	{  
		actionLabel.setVisible(false);
		buildingsPanel.setVisible(false);
		blacksmithPanel.setVisible(false);
		upgradesPanel.setVisible(false);
		pricesPanel.setVisible(true);
		backButton.setVisible(true);
		unitsPanel.setVisible(true);	
	}
	
	//------show upgrade panel
	private void showUpgradesPanel()
	{
		actionLabel.setVisible(false);
	    buildingsPanel.setVisible(false);
		blacksmithPanel.setVisible(false);
		unitsPanel.setVisible(false);	
		backButton.setVisible(true);
		upgradesPanel.setVisible(true);
	}
	
	//------show blacksmith panel
	private void showBlacksmithPanel()
	{
		actionLabel.setVisible(false);
		buildingsPanel.setVisible(false);
		upgradesPanel.setVisible(false);
		unitsPanel.setVisible(false);	
		backButton.setVisible(true);
		blacksmithPanel.setVisible(true);
	}

	//------show stable panel
	private void showstablePanel()
	{
		actionLabel.setVisible(false);
		buildingsPanel.setVisible(false);
		upgradesPanel.setVisible(false);
		unitsPanel.setVisible(false);	
		backButton.setVisible(true);
		blacksmithPanel.setVisible(false);
		stablePanel.setVisible(true);
	}
	
	//------Initialize units panel
   private void initUnitsPanel()
	{
		knightCharLabel.addMouseListener(this);
		soldierCharLabel.addMouseListener(this);
		archerCharLabel.addMouseListener(this);
		spearmanCharLabel.addMouseListener(this);
		warChariotLabel.addMouseListener(this);
		
		unitsPanel.add(soldierCharLabel);
		unitsPanel.add(archerCharLabel);
		unitsPanel.add(spearmanCharLabel);
		unitsPanel.add(knightCharLabel);
		unitsPanel.add(warChariotLabel);
		
	}

   //------Initialize blacksmith panel
   private void initBlacksmithPanel()
   {
	   chariotBigLabel.addMouseListener(this);
	   saddleLabel.addMouseListener(this);
	   
	   blacksmithPanel.add(chariotBigLabel);   
	   blacksmithPanel.add(saddleLabel);  
   }
   
   //------Initialize stable panel
   private void initstablePanel()
   {
	   horseLabel.addMouseListener(this);
	   
	   stablePanel.add(horseLabel);
	   
   }
   
   //------Initialize states Panel components
	private void initStatesPanel()
	{	
		menuButton.setBounds(5, 3, 200, 58);
		menuButton.setFont(new Font(getName() , Font.BOLD , 25));
		menuButton.setHorizontalTextPosition(JLabel.CENTER);
		menuButton.setForeground(Color.WHITE);
		menuButton.setText("MENU");
		menuButton.setBackground(null);
		menuButton.setBorder(null);
		menuButton.addMouseListener(this);
		
		items.setBounds(225 , 3 , 200 , 58);
		items.setFont(new Font(getName() , Font.BOLD , 25));
		items.setHorizontalTextPosition(JLabel.CENTER);
		items.setForeground(Color.WHITE);
		items.setText("Items");
		items.setBackground(null);
		items.setBorder(null);
		items.addMouseListener(this);
		
		
		timeLabel.setBounds(650 ,3 ,350 ,58);
		timeLabel.setFont(new Font(getName() , Font.BOLD , 30));
		timeLabel.setHorizontalTextPosition(JLabel.CENTER);
		timeLabel.setForeground(Color.WHITE);
		timeLabel.setText("00:00:00");
		timeLabel.setBackground(null);
		timeLabel.setBorder(null);
		
		soldierLabel.setBounds(1155 , 10 ,40 ,40);
		soldierCount.setBounds(1200 , 15 ,55 ,25);
		soldierCount.setFont(new Font(getName() , Font.BOLD , 30));
				
		archerLabel.setBounds(1275 , 10 ,40 ,40);
		archerCount.setBounds(1320 , 15 ,55 ,25);
		archerCount.setFont(new Font(getName() , Font.BOLD , 30));	
				
		spearmanLabel.setBounds(1395 , 10 ,40 ,40);
		spearmanCount.setBounds(1440 , 15 ,55 ,25);
		spearmanCount.setFont(new Font(getName() , Font.BOLD , 30));
				
		knightLabel.setBounds(1515 , 10,40 ,40);
		knightCount.setBounds(1560 , 15 ,55 ,25);
		knightCount.setFont(new Font(getName() , Font.BOLD , 30));
				
		chariotLabel.setBounds(1635, 10 ,40 ,40);
		chariotCount.setBounds(1680 , 15 ,55 ,25);
		chariotCount.setFont(new Font(getName() , Font.BOLD , 30));
						
		goldLabel.setBounds(1745 , 10 , 40 , 40);
		goldCount.setBounds(1790 , 12 ,300 ,35);
		goldCount.setFont(new Font(getName() , Font.BOLD , 30));
					
		statesPanel.add(menuButton);
		statesPanel.add(items);
		statesPanel.add(timeLabel);
					
		statesPanel.add(soldierLabel);
		statesPanel.add(archerLabel);
		statesPanel.add(spearmanLabel);
		statesPanel.add(knightLabel);
		statesPanel.add(chariotLabel);
		statesPanel.add(goldLabel);
				
		statesPanel.add(soldierCount);
		statesPanel.add(archerCount);
		statesPanel.add(spearmanCount);
		statesPanel.add(knightCount);
		statesPanel.add(chariotCount);
		statesPanel.add(goldCount);
		
		}
	
	//------Initialize Map Panel components
	private void initMapPanel()
	{
		//#########-------Map Panel-------#########	
		
		mapPanelLabel.addMouseListener(this);
		mapPanelLabel.setBackground(Color.DARK_GRAY);
		mapPanelLabel.setBounds(0 ,0 ,actionPanel.getWidth() ,800);
		
		
		mapPanelLabel.add(castleLabel);	
		mapPanelLabel.add(workerBuildingLabel);
		
		castleLabel.setBounds(1250, 175 ,250, 250);
		
		
		workerBuildingLabel.setBounds(1000 , 880 , 100 , 100);
		workerBuildingLabel.addMouseListener(new MouseAdapter(){
			
			 @Override 
			 public void mousePressed(MouseEvent e)
			 {
				 if(workerBuildingLabel.isEnabled())
				{
					 workerBuildingLabel.setEnabled(false);
					 
					 JLabel worker = new JLabel(workerIcon);
					 
					 worker.putClientProperty("isSelected", false);
					 
					 workers.add(worker);
					 
					 pos = workers.size() - 1;
					 
					 workers.get(pos).setBounds(workerBuildingLabel.getX() + 100 , workerBuildingLabel.getY() - 125 , 175, 225);
 
					 mapPanelLabel.add(workers.get(pos));
					 
					 repaint();
					 
					 workers.get(pos).addMouseListener(new MouseAdapter(){
							 
							 @Override 
							 public void mousePressed(MouseEvent e)
							 {
								 if((boolean) workers.get(pos).getClientProperty("isSelected"))
								    workers.get(pos).putClientProperty("isSelected", false);
								 else
									 worker.putClientProperty("isSelected", true);
								 
								 buildFunction();
								 showBuildLocation();
								 
								 try {
									playClick();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							 }
						 });
					 

					 try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					}			
				}
				 	
				
			 }
		});
		
		
		//#########-------Map Scroll Pane-------#########
		
		mapPane.addMouseListener(this);
		mapPane.setBackground(Color.BLACK);
		mapPane.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
			
			@Override 
	        protected void configureScrollBarColors(){
	            this.thumbColor = Color.DARK_GRAY;
	        }
			 @Override
		        protected JButton createDecreaseButton(int orientation) {
		            return createZeroButton();
		        }

		        @Override    
		        protected JButton createIncreaseButton(int orientation) {
		            return createZeroButton();
		        }

		        private JButton createZeroButton() {
		            JButton jbutton = new JButton();
		            jbutton.setPreferredSize(new Dimension(0, 0));
		            jbutton.setMinimumSize(new Dimension(0, 0));
		            jbutton.setMaximumSize(new Dimension(0, 0));
		            return jbutton;
		        }
		});
        mapPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
			
			   @Override 
	           protected void configureScrollBarColors(){
	            this.thumbColor = Color.DARK_GRAY;
	            }
			   @Override
		        protected JButton createDecreaseButton(int orientation) {
		            return createZeroButton();
		        }

		        @Override    
		        protected JButton createIncreaseButton(int orientation) {
		            return createZeroButton();
		        }
		        private JButton createZeroButton() {
		            JButton jbutton = new JButton();
		            jbutton.setPreferredSize(new Dimension(0, 0));
		            jbutton.setMinimumSize(new Dimension(0, 0));
		            jbutton.setMaximumSize(new Dimension(0, 0));
		            return jbutton;
		        }
		});
         
        
		mapPane.setBounds(0, 0, (int) width, 800);
	    mapPane.getVerticalScrollBar().setBackground(Color.BLACK);
		mapPane.getHorizontalScrollBar().setBackground(Color.BLACK);
				
		mapPanel.add(mapPane);	
	
	
	}

	//------set up map
	private void initMap()
	{
		//setting up building locations labels
				int x = 0;
				int y = 0;
				
				for(int j = 0 ; j<6 ; j++)
				{
				  for(int i = 0 ; i<6 ; i++)
				   {
					  if((i == 5 && j == 4)  || (i == 4 && j == 5))
						map[i][j] = null;
					   
					  else
					  {
						
						map[i][j] = new JLabel(squareIcon);
						map[i][j].setVisible(false);
						map[i][j].setBounds(50 - x , 360 +y , 150 ,150);
						mapPanelLabel.add(map[i][j]);
						map[i][j].addMouseListener(new MouseAdapter(){
							
							@Override
							public void mouseClicked(MouseEvent e)
							{
															
							if(workers.get(pos) != null 
									&& ((JLabel) e.getSource()).getIcon() == squareIcon 
									&& ((boolean) ( workers.get(pos).getClientProperty("isSelected"))) )
								{  
								
								target = ((JLabel) e.getSource());
								
								if(buildingName.equals("Barracks"))
								{																		
									  if(ResourcesCollector.isEnough(Memory.BarracksPrice))
										{					        
											ResourcesCollector.decrementStock(Memory.BarracksPrice);
														    
											updateGold();
															
											Barracks barracks = new Barracks();
				
											Barracks.BarracksList.add(barracks);
											
											 backAction();
											    
											 workers.get(pos).putClientProperty("isSelected", false);
											 
											 if(pos == 0)
											 work.start();
											 
											 Worker.nPosX =  ((JLabel) e.getSource()).getX();
											 Worker.nPosY = ((JLabel) e.getSource()).getY();
											 Worker.isMoving = true;
											 logs.setText("");
																					
										}						 		  
								} else if(buildingName.equals("Storage")) {

									   if(ResourcesCollector.isEnough(Memory.StoragePrice))
										{
											ResourcesCollector.decrementStock(Memory.StoragePrice);
													   
											updateGold();
														
											Storage storage = new Storage();
														
											Storage.StorageList.add(storage);												
								
											backAction();
										    
											 workers.get(pos).putClientProperty("isSelected", false);
											 
											 if(pos == 0)
											 work.start();
											 
											 Worker.nPosX =  ((JLabel) e.getSource()).getX();
											 Worker.nPosY = ((JLabel) e.getSource()).getY();
											 Worker.isMoving = true;
											 logs.setText("");
										 }

												  						
								} else if(buildingName.equals("Gold Mine")) {
  
									   if(ResourcesCollector.isEnough(Memory.MinePrice))
										{			       
											ResourcesCollector.decrementStock(Memory.MinePrice);
														   
											updateGold();

											backAction();
										    
											 workers.get(pos).putClientProperty("isSelected", false);
											 
											 if(pos == 0)
											 work.start();
											 
											 Worker.nPosX =  ((JLabel) e.getSource()).getX();
											 Worker.nPosY = ((JLabel) e.getSource()).getY();
											 Worker.isMoving = true;
											 logs.setText("");
										  }						
										   							
								} else if(buildingName.equals("Defence")) {

									 if(ResourcesCollector.isEnough(Memory.TowerPrice))
									  { 													       
										ResourcesCollector.decrementStock(Memory.TowerPrice);
														   
										updateGold();
															
										Defense tower = new Defense();
															
										Defense.TowerList.add(tower);	
										
										backAction();
									    
										 workers.get(pos).putClientProperty("isSelected", false);
										 
										 if(pos == 0)
										 work.start();
										 
										 Worker.nPosX =  ((JLabel) e.getSource()).getX();
										 Worker.nPosY = ((JLabel) e.getSource()).getY();
										 Worker.isMoving = true;
										 logs.setText("");
									   }		
										
								} else if(buildingName.equals("stable")) {
	  
									if(ResourcesCollector.isEnough(Memory.StablePrice))
									{							  		  		        
										  ResourcesCollector.decrementStock(Memory.StablePrice);
														    
										  updateGold();
															
										  Stable stable = new Stable();
															
										  Stable.StableList.add(stable);	
										  
										  backAction();
										    
											 workers.get(pos).putClientProperty("isSelected", false);
											 
											 if(pos == 0)
											 work.start();
											 
											 Worker.nPosX =  ((JLabel) e.getSource()).getX();
											 Worker.nPosY = ((JLabel) e.getSource()).getY();
											 Worker.isMoving = true;
											 logs.setText("");
									}														 
										   		
								} else if(buildingName.equals("Blacksmith")) {

									if(ResourcesCollector.isEnough(Memory.BlacksmithPrice))
									{      
										ResourcesCollector.decrementStock(Memory.BlacksmithPrice);
														    
										updateGold();
															
										Blacksmith blacksmith = new Blacksmith();
															
										Blacksmith.blackSmithList.add(blacksmith);	
										
										backAction();
									    
										 workers.get(pos).putClientProperty("isSelected", false);
										 
										 if(pos == 0)
										 work.start();
										 
										 Worker.nPosX =  ((JLabel) e.getSource()).getX();
										 Worker.nPosY = ((JLabel) e.getSource()).getY();
										 Worker.isMoving = true;
										 logs.setText("");
									}												  
								  
								} else {
									
									logs.setText("Please select a building first!");
								}
									 try {
											playClick();
										} catch (Exception e1) {
											e1.printStackTrace();
										} 
								}
			
							if(!isBuilding)
								{
									if(((JLabel)e.getSource()).getIcon() == barraksIcon)
									  {
										  try {
												playClick();
											} catch (Exception e1) {
												e1.printStackTrace();
											} 
										  showUnitsPanel();
										  
										  pricesPanel.setVisible(true);
										  if(!unitsPriceInit)
										  initPrices("Units");
										  
									  } else if(((JLabel)e.getSource()).getIcon()  == blacksmithIcon) {
										  
										  try {
												playClick();
											} catch (Exception e1) {
												e1.printStackTrace();
											} 
										  showBlacksmithPanel();

										  pricesPanel.setVisible(true);
										  if(!blacksmithPriceInit)
										  initPrices("Blacksmith");

										 
									  } else if(((JLabel)e.getSource()).getIcon()  == stableIcon) {
										  
										  try {
												playClick();
											} catch (Exception e1) {
												e1.printStackTrace();
											} 
										  showstablePanel();

										  pricesPanel.setVisible(true);
										  if(!stablePriceInit)
										  initPrices("stable");								  
									  }
								}							
							}
	
						});
						
						
						x-=140;
						y-=72;
						
						map[i][j].setForeground(Color.GREEN);
					  }	//end of inner else statement 
				    }//end of inner for loop
				
					if(j == 0)
					{
						x=-125;
						y=75;
					} else if(j == 1) {
						x=-250;
						y=140;
						
					} else if(j == 2) {
						x=-375;
						y=205;
					} else if(j == 3) {
						x=-500;
						y=270;
					} else if(j == 4) {		
						x=-625;
						y=335;
					}
					else if(j == 5) {		
						x=-750;
						y=400;
					}
				
				}//end of outer for loop
	}
	
	//------Initialize Action Panel components
	private void initActionPanel()
	{
		actionLabel.setBounds(350, 5 ,1250,200);
		
		nationLabel.setBounds(5, 5,240, (int) actionPanel.getHeight() - 90);
		nationLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
		
		logsMark.setBounds(260 , 190 , 200 , 25);
		logsMark.setFont(new Font(getName() , Font.BOLD , 20));
		logsMark.setForeground(Color.GREEN);
		actionPanel.add(logsMark);
		
		logs.setBounds(350 , 190 , 500 , 25);
		logs.setFont(new Font(getName() , Font.BOLD , 20));
		logs.setForeground(Color.GREEN);
		actionPanel.add(logs);
		
		decorationL.setBounds(1710 , 190 , 200 , 30);
		actionPanel.add(decorationL);
					
		backButton.setBounds(1650,10, 50, 50);
		backButton.setVisible(false);
		backButton.setBackground(Color.GRAY);
		backButton.setBorder(null);
		backButton.addMouseListener(this);
		actionPanel.add(backButton);
				
		attackButton.setBounds(1710, 5, 200,60);
		attackButton.setFont(new Font(getName() , Font.BOLD , 25));
		attackButton.setHorizontalTextPosition(JLabel.CENTER);
		attackButton.setForeground(Color.WHITE);
		attackButton.setText("ATTACK");
		attackButton.setBackground(null);
		attackButton.setBorder(null);
		attackButton.addMouseListener(this);
				
		buildButton.setBounds(1710 , 65 ,200 ,60);
		buildButton.setFont(new Font(getName() , Font.BOLD , 25));
		buildButton.setHorizontalTextPosition(JLabel.CENTER);
		buildButton.setForeground(Color.WHITE);
		buildButton.setText("BUILD");
		buildButton.setBackground(null);
		buildButton.setBorder(null);
		buildButton.addMouseListener(this);
				
		upgradeButton.setBounds(1710 , 130 ,200 ,60);
		upgradeButton.setFont(new Font(getName() , Font.BOLD , 25));
		upgradeButton.setHorizontalTextPosition(JLabel.CENTER);
		upgradeButton.setForeground(Color.WHITE);
		upgradeButton.setText("UPGRADE");
		upgradeButton.setBackground(null);
		upgradeButton.setBorder(null);
		upgradeButton.addMouseListener(this);

		actionPanel.add(actionLabel);
		actionPanel.add(pricesPanel);
		actionPanel.add(nationLabel);
		actionPanel.add(attackButton);
		actionPanel.add(buildButton);
		actionPanel.add(upgradeButton);		
		actionPanel.add(buildingsPanel);
		actionPanel.add(unitsPanel);
		actionPanel.add(blacksmithPanel);
		actionPanel.add(upgradesPanel);
		actionPanel.add(stablePanel);
		
		
	}
	
	//------Updates Knights Counter
	public static void updateKnightCounter()
	{
		knightCount.setText(String.valueOf(Knight.KnightList.size()));
	}
	
	//------Updates Warrior Counter
	public static void updateSoldierCounter()
	{
			soldierCount.setText(String.valueOf(Warrior.WarriorsList.size()));
	}
		
	//------Updates Archer Counter
	public static void updateArcherCounter()
	{
			archerCount.setText(String.valueOf(Archer.ArcherList.size()));
	}
		
	//------Updates Spearman Counter
	public static void updateSpearmanCounter()
	{
		spearmanCount.setText(String.valueOf(SpearMan.SpearManList.size()));
	}
	
	//------Updates Chariot Counter
	public static void updateChariotCounter()
	{
		chariotCount.setText(String.valueOf(Chariot.ChariotList.size()));
	}
	
	//------Updates Chariot car Counter
	public static void updateChariotCarCounter()
	{
		chariotCountLabel.setText("Chariots  " + String.valueOf(Blacksmith.getChariot()));
	}
		
	//------Updates Horse Counter
	public static void updateHorseCounter()
	{
		horseCountLabel.setText ("Horses    " + String.valueOf(Stable.horseNumber));
	}
	
	//------Updates saddle Counter
	public static void updateSaddleCounter()
	{
		saddleCountLabel.setText("Saddles   " + String.valueOf(Blacksmith.getSaddle()));
	}
	
	//-----Updates Gold Counter
    private void updateGold()
	{
		goldCount.setText(String.valueOf(ResourcesCollector.getStock()));
	}

    //-----Attack Action
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void attackFunction() throws IOException
    {
    	commandToSend command = new commandToSend();
    	command.commandType = "Attack";
		command.commandValue = null;
		
		try {
			Connection.output.writeObject(command);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
    	try {
			playClick();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
    	
    	WarMap war;
    	
		try {
		
			war = new WarMap("Attacker" , UserCity.this);
			Thread T = new Thread(war);
	    	T.start();
	    	
		} catch (Exception e) {

			e.printStackTrace();
		} 	
    	
    	setVisible(false);
    }
    
    //-----Upgrade Action
    private void upgradeFunction()
    {
    	unitsPanel.setVisible(false);
	    buildingsPanel.setVisible(false);	
	    blacksmithPanel.setVisible(false);
		backButton.setVisible(true);
		pricesPanel.setVisible(false);

		for(int j = 0 ; j<6 ; j++)
		   for(int i = 0 ; i<6 ; i++)
		   {
			    if(i == 5 && j == 4 || i == 4 && j == 5)
				continue;
			    
			    if(map[i][j].getIcon() != squareIcon)
			    	continue;
		
			    map[i][j].setVisible(false);		    
		   }
		showUpgradesPanel();
		
		try {
			playClick();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
    }
    
    //-----Build Action
    private void buildFunction()
    {   
    	actionLabel.setVisible(false);
    	unitsPanel.setVisible(false);
    	upgradesPanel.setVisible(false);
    	blacksmithPanel.setVisible(false);
	    buildingsPanel.setVisible(true);
	 
		backButton.setVisible(true);	
		pricesPanel.setVisible(true);
		
		
		if(!buildingsPriceInit)
		  initPrices("Buildings");
		try {
			playClick();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 

    }
  
    //-----Back Action
    private void backAction()
    {
    	actionLabel.setVisible(true);
		if(buildingsPanel.isVisible())
		isBuilding = false;
		
		buildingsPanel.setVisible(false);
		unitsPanel.setVisible(false);
		upgradesPanel.setVisible(false);
		pricesPanel.setVisible(false);
		stablePanel.setVisible(false);
		blacksmithPanel.setVisible(false);

		barraksLabel.setText("");
		storageLabel.setText("");
		blacksmithLabel.setText("");
		goldMineLabel.setText("");
		stableLabel.setText("");
		defenceLabel.setText("");
		
		
		for(int j = 0 ; j<6 ; j++)
		   for(int i = 0 ; i<6 ; i++)
		   {
			    if(i == 5 && j == 4 || i == 4 && j == 5)
				continue;
			    
			    if(map[i][j].getIcon() != squareIcon)
			    	continue;
		
			    map[i][j].setVisible(false);		
		   }
		
		backButton.setVisible(false);
		
		try {
			playClick();
		} catch (Exception e1) {
			e1.printStackTrace();
		} 
    }
	   
    //getter for time label
    public static JLabel getTimeLabel()
		{
			return timeLabel;	
		}
	
	//getter for gold label
	public static JLabel getGoldLabel()
	{
		return goldCount;
	}
	
	//set selected building
	private void select(JLabel label)
	{
		barraksLabel.setText("");
		storageLabel.setText("");
		blacksmithLabel.setText("");
		goldMineLabel.setText("");
		stableLabel.setText("");
		defenceLabel.setText("");
		
		label.setText("+");
		label.setFont(new Font(getName() , Font.BOLD , 50));
		label.setForeground(Color.green);
		label.setHorizontalTextPosition(JLabel.CENTER);
	}
	
	
	//++++++++++++++++++++++++++++++++++++++++++++++++++--Mouse Listener--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\\
	
	//------Mouse Listener
	@Override
	public void mouseClicked(MouseEvent e) 
	{		
		controls.setVisible(false);
		repaint();
		
	
		//Buttons Listener--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\\
		if(e.getSource() == menuButton)
		{	
			popup.show(e.getComponent(),menuButton.getX() - 5, menuButton.getY() +  menuButton.getHeight() - 5);
			menuButton.setIcon(buttonIcon2);
			try {
				playClick();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
		} else if (e.getSource() == attackButton) {
			
			try {
				attackFunction();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		} else if(e.getSource() == buildButton) {  
			buildFunction();
			
		} else if(e.getSource() == upgradeButton) {
			
			 upgradeFunction();
			
		} else if(e.getSource() == backButton) {
			
			backAction();
			
		} else if (e.getSource() == mainMenuButton) {
			
			popup.setVisible(false);
			try {
				playClick();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
		} else if (e.getSource() == helpButton) {
			
			popup.setVisible(false);
			
			try {
				playClick();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			controls.setBounds((int) width / 2 - 600 ,120, 1200 , 700); 
			controls.setVisible(true);
	        controls.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
			
		}  else if (e.getSource() == exitButton) {
			
			 UIManager.put("OptionPane.background",new ColorUIResource(0,0,0));
			 UIManager.put("Panel.background",new ColorUIResource(0,0,0));
			
			String ObjButtons[] = {"Yes","No"};
		    int PromptResult = JOptionPane.showOptionDialog(null, 
		        "Are you sure you want to exit?", "Guardians", 
		        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, 
		        ObjButtons,ObjButtons[1]);
		    
		    if(PromptResult==0)
		    {	    			
			  System.exit(0);
		      
		    }
			
		} 
		
		//Buildings Listener--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\\
		if(buildingsPanel.isVisible())
		{

			if(e.getSource() == barraksLabel) {
				
				select(barraksLabel);
				showBuildLocation();
				buildingName = "Barracks";
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
				
			} else if (e.getSource() == storageLabel) {
				
				select(storageLabel);
				showBuildLocation();
				buildingName = "Storage";
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
				
			} else if (e.getSource() == defenceLabel) {
				
				select(defenceLabel);
				showBuildLocation();
				buildingName = "Defence";
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
			
			} else if (e.getSource() == stableLabel) {
				
				select(stableLabel);
				showBuildLocation();
				buildingName = "stable";
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
				
			} else if (e.getSource() == goldMineLabel) {
				
				select(goldMineLabel);
				showBuildLocation();
				buildingName = "Gold Mine";
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
				
			} else if (e.getSource() == blacksmithLabel) {
				
				select(blacksmithLabel);
				showBuildLocation();
				buildingName = "Blacksmith";
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 		
			}
		}
		
		//Blacksmith Listener--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\\
		else if(blacksmithPanel.isVisible())
		{
			if(e.getSource() == chariotBigLabel) {
				
				if(ResourcesCollector.isEnough(Memory.ChariotCarPrice))
				{
					ResourcesCollector.decrementStock(Memory.ChariotCarPrice);
					
					updateGold();
						
					logs.setText(Blacksmith.makechariotCar());
					
					updateChariotCarCounter();
					try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
										
				}
			} else if(e.getSource() == saddleLabel) {
				
				if(ResourcesCollector.isEnough(Memory.SaddlePrice))
				{
					ResourcesCollector.decrementStock(Memory.SaddlePrice);
					
					updateGold();
						
					logs.setText(Blacksmith.makeSaddle());
					
					updateSaddleCounter();
											
				}	
				try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
				
			} 
		}
	
		else if(e.getSource() == horseLabel)
		{	
			if(ResourcesCollector.isEnough(Memory.horsePrice))
			{
				ResourcesCollector.decrementStock(Memory.horsePrice);
				
				updateGold();
					
			
				logs.setText(Stable.DeployHorse());
				
				updateHorseCounter();
										
			}	
			try {
				playClick();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
		} 
		
		//Units Listener--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\\
		 if (unitsPanel.isVisible()) {
		
			 if(e.getSource() == knightCharLabel) 
			 {
				 if(ResourcesCollector.isEnough(Memory.knightPrice) && Blacksmith.getSaddle() !=0  && Warrior.UpWarriors!=0 && Stable.horseNumber != 0)
					{
					  k++;
					  
					 for(int s=0 ; s<Warrior.WarriorsList.size() ; s++)
						{
							if(Warrior.WarriorsList.get(s).isUpgraded == true)
							{
								 Warrior.WarriorsList.remove(s);
								 break;
							}
								
						}
					 
					    knightCharLabel.setText(String.valueOf(k));
					    knightCharLabel.setFont(new Font(getName() , Font.BOLD , 30));
					    knightCharLabel.setForeground(Color.GREEN);
					    knightCharLabel.setHorizontalTextPosition(JLabel.CENTER);
					 
					    updateSoldierCounter();
					    
						Blacksmith.decrementSaddle();
						
						UserCity.updateSaddleCounter();
						
						Stable.horseNumber--;
						
						UserCity.updateHorseCounter();
				
						ResourcesCollector.decrementStock(Memory.knightPrice);
						
						updateGold();
						
						q.add("knight");
						
						
							try {
								playClick();
							} catch (Exception e1) {
								e1.printStackTrace();
							} 
					}
			 } else if(e.getSource() == soldierCharLabel) {
				 
					if(ResourcesCollector.isEnough(Memory.GetWarriorPrice()))
					{
						w++;
					
						soldierCharLabel.setText(String.valueOf(w));
						soldierCharLabel.setFont(new Font(getName() , Font.BOLD , 30));
						soldierCharLabel.setForeground(Color.GREEN);
						soldierCharLabel.setHorizontalTextPosition(JLabel.CENTER);
						
						ResourcesCollector.decrementStock(Memory.GetWarriorPrice());
						
						updateGold();

						q.add("worrior");
						
						repaint();
												
					}
					try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
					
				} else if(e.getSource() == archerCharLabel) {
					
					if(ResourcesCollector.isEnough(Memory.GetArcherPrice()))
					{
						a++; 
						
						ResourcesCollector.decrementStock(Memory.GetArcherPrice());
						
						updateGold();
						

						archerCharLabel.setText(String.valueOf(a));
						archerCharLabel.setFont(new Font(getName() , Font.BOLD , 30));
						archerCharLabel.setForeground(Color.GREEN);
						archerCharLabel.setHorizontalTextPosition(JLabel.CENTER);
					 					
						q.add("archer");
					
					}
					try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
					
				} else if(e.getSource() == spearmanCharLabel) {
					
						
					if(ResourcesCollector.isEnough(Memory.GetSpearManPrice()))
					{
						s++;
						ResourcesCollector.decrementStock(Memory.GetSpearManPrice());
						
						spearmanCharLabel.setText(String.valueOf(s));
						spearmanCharLabel.setFont(new Font(getName() , Font.BOLD , 30));
						spearmanCharLabel.setForeground(Color.GREEN);
						spearmanCharLabel.setHorizontalTextPosition(JLabel.CENTER);
						
						updateGold();
						
						q.add("spearman");

					}
					try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
					
				} else if(e.getSource() == warChariotLabel) {
						
					if(ResourcesCollector.isEnough(Memory.ChariotPrice) && Blacksmith.getChariot()!=0 && Archer.UpArchers != 0 && Warrior.UpWarriors != 0 && Stable.horseNumber >= 2)
					{
						ch++;
						
						warChariotLabel.setText(String.valueOf(ch));
						warChariotLabel.setFont(new Font(getName() , Font.BOLD , 30));
						warChariotLabel.setForeground(Color.GREEN);
						warChariotLabel.setHorizontalTextPosition(JLabel.CENTER);
						
						ResourcesCollector.decrementStock(Memory.ChariotPrice);
						
						updateGold();
						
						for(int s=0 ; s<Warrior.WarriorsList.size() ; s++)
						{
							if(Warrior.WarriorsList.get(s).isUpgraded == true)
							{
								 Warrior.WarriorsList.remove(s);
								 break;
							}
								
						}
						
						for(int a=0 ; a<Archer.ArcherList.size() ; a++)
						{
							if(Archer.ArcherList.get(a).isUpgraded == true)
							{
								Archer.ArcherList.remove(a);
								 break;
							}
								
						}
						
							Blacksmith.decrementChariot();
							
							updateChariotCarCounter();
							
							Stable.horseNumber = Stable.horseNumber - 2;
							
							updateHorseCounter();
							
							q.add("chariot");

							try {
								playClick();
							} catch (Exception e1) {
								e1.printStackTrace();
							} 
					}
						
				}			
			
	    } 
		
		//Upgrades Listener--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\\
		else if(upgradesPanel.isVisible())
		{			
					if(e.getSource() == upgrades[0])
					{
						
						if(ResourcesCollector.isEnough(Memory.UpgradeWarrior) && Upgrade.WarriorUpgrade == false)
						{
							try {
								audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/upgrade.wav").getAbsoluteFile());
								clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							ResourcesCollector.decrementStock(Memory.UpgradeWarrior);
							
							updateGold();
							
							logs.setText(Upgrade.upgradeSoldier());
							
							upgrades[0].setIcon(doneIcon);
							
						}
						
						prices[0] = new JLabel();
						prices[0].setText("             " + String.valueOf(Memory.GetWarriorPrice()) + " G   ");
				
					}
					else if(e.getSource() == upgrades[1] && Upgrade.ArcherUpgrade == false)
					{
						
						
						if(ResourcesCollector.isEnough(Memory.UpgradeArcher))
						{
							try {
								audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/upgrade.wav").getAbsoluteFile());
								clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
							} catch (Exception e1) {

								e1.printStackTrace();
							}
							
							ResourcesCollector.decrementStock(Memory.UpgradeArcher);
							
							updateGold();
							
							logs.setText(Upgrade.upgradeArcher());
							
							upgrades[1].setIcon(doneIcon);
						}
						
						prices[1] = new JLabel();
						prices[1].setText("                " + String.valueOf(Memory.GetArcherPrice()) + " G   ");
				
					}
					else if(e.getSource() == upgrades[2] && Upgrade.SpearmanUpgrade == false) 
					{	
										
						if(ResourcesCollector.isEnough(Memory.UpgradeSpearMan))
						{
							try {
								audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/upgrade.wav").getAbsoluteFile());
								clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							ResourcesCollector.decrementStock(Memory.UpgradeSpearMan);
							
							updateGold();
							
							logs.setText(Upgrade.upgradeSpearman());
							
							upgrades[2].setIcon(doneIcon);
						}
						
						prices[2] = new JLabel();
						prices[2].setText("                " + String.valueOf(Memory.GetSpearManPrice()) + " G   ");
									
					}
				}
	
				  
	}

	@Override
	public void mouseEntered(MouseEvent e) {	
		 //changing mouse cursor icon within the main frame (this)
			this.setCursor(c);	
			
			if(e.getSource() == items)
			{
				itemsPopup.show(e.getComponent(),items.getX() - 225, items.getY() +  items.getHeight() );
				items.setIcon(buttonIcon2);
			}else
				itemsPopup.setVisible(false);
			
			 if(e.getSource() == menuButton)
				menuButton.setIcon(buttonIcon2);
			else if(e.getSource() == attackButton)
				attackButton.setIcon(buttonIcon2);
			else if(e.getSource() == buildButton)
				buildButton.setIcon(buttonIcon2);
			else if(e.getSource() == upgradeButton)
				upgradeButton.setIcon(buttonIcon2);
			else if(e.getSource() == mainMenuButton)
				mainMenuButton.setIcon(buttonIcon2);
			else if(e.getSource() == helpButton)
				helpButton.setIcon(buttonIcon2);
			else if(e.getSource() == exitButton)
				exitButton.setIcon(buttonIcon2);
				
			
			if(ResourcesCollector.getStock() < 5)
				this.setCursor(n);
					
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
			  
		items.setIcon(buttonIcon);
	
		menuButton.setIcon(buttonIcon);

		attackButton.setIcon(buttonIcon);
	
		buildButton.setIcon(buttonIcon);
	
		upgradeButton.setIcon(buttonIcon);
		
		mainMenuButton.setIcon(buttonIcon);

		helpButton.setIcon(buttonIcon);

		exitButton.setIcon(buttonIcon);
	}

	@Override
	public void mousePressed(MouseEvent e) {	  
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}


}
