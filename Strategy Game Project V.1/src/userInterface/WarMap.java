package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ToolTipManager;

import server.Connection;
import server.MapUnit;
import server.commandToSend;


public class WarMap extends JFrame implements Runnable {
private static final long serialVersionUID = 4954849721314145851L;
	
	
	//*****************************************Variables*****************************************\
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screenSize.getWidth();
		int height = (int) screenSize.getHeight();
		
		public static String identity;
		
		public static String player2Nation = new String();
			
		@SuppressWarnings("rawtypes")
		public static ArrayList<MapUnit> units = new ArrayList<MapUnit>();
			
		UnitsNumberSelecter unitsSelect;

		public static int SoldierNumber =  0; 
		public static int ArcherNumber = 0 ;
		public static int SpearmanNumber = 0;
		public static int KnightNumber =  0;
		public static int ChariotNumber = 0; 
		
		public static int player1 = 0;
		public static int player2 = 0;

		public static int Units1;
		public static int Units2;
		
		static CardLayout cards;
		
		public static int player1StatsB[] = new int [5];
		public static int player2StatsB[] = new int [5];
		public static int player1StatsE[] = new int [5];
		public static int player2StatsE[] = new int [5];
		
		
	//*****************************************Panels*****************************************\
	JPanel statesPanel = new JPanel();	
	JPanel mapPanel    = new JPanel();
	
	//*****************************************Icons*****************************************\
	public static ImageIcon mapIcon  = new ImageIcon(new ImageIcon("Images & Sounds/map/sqr.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
	ImageIcon image    = new ImageIcon(new ImageIcon("Images & Sounds\\map\\warMapFinal.png").getImage().getScaledInstance(width, height , Image.SCALE_DEFAULT));
	
	ImageIcon buttonIcon  = new ImageIcon(new ImageIcon("Images & Sounds/Images/button1.png").getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT));
	ImageIcon buttonIcon2 = new ImageIcon(new ImageIcon("Images & Sounds/Images/button2.png").getImage().getScaledInstance(200, 130, Image.SCALE_DEFAULT));
	
	ImageIcon soldierIcon = new ImageIcon(new ImageIcon("Images & Sounds/symbols/sword.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon archerIcon  = new ImageIcon(new ImageIcon("Images & Sounds/symbols/bow.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon knightIcon  = new ImageIcon(new ImageIcon("Images & Sounds/symbols/knight.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon chariotIcon = new ImageIcon(new ImageIcon("Images & Sounds/symbols/chariot.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	ImageIcon spearIcon   = new ImageIcon(new ImageIcon("Images & Sounds/symbols/spear.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	
	public static ImageIcon Worrior  = new ImageIcon(new ImageIcon("Images & Sounds/Attacker/warriors.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
	public static ImageIcon  Archer  = new ImageIcon(new ImageIcon("Images & Sounds/Attacker/archers.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon Knight   = new ImageIcon(new ImageIcon("Images & Sounds/Attacker/knights.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon  Chariot = new ImageIcon(new ImageIcon("Images & Sounds/Attacker/chariot.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon Spear    = new ImageIcon(new ImageIcon("Images & Sounds/Attacker/spearman.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

	public static ImageIcon WorriorD  = new ImageIcon(new ImageIcon("Images & Sounds/Deffender/warriors.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));
	public static ImageIcon  ArcherD  = new ImageIcon(new ImageIcon("Images & Sounds/Deffender/archers.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon KnightD   = new ImageIcon(new ImageIcon("Images & Sounds/Deffender/knights.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon  ChariotD = new ImageIcon(new ImageIcon("Images & Sounds/Deffender/chariot.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
	public static ImageIcon SpearD    = new ImageIcon(new ImageIcon("Images & Sounds/Deffender/spearman.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));

	ImageIcon flagIcon   = new ImageIcon(new ImageIcon("Images & Sounds/symbols/flag.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
	
	ImageIcon nationIcon = new ImageIcon(new ImageIcon("Nation 1/nation.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
	ImageIcon nationIcon2 = new ImageIcon(new ImageIcon("Nation 2/nation.png").getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT));
	
	static ImageIcon HPIcon   = new ImageIcon(new ImageIcon("Images & Sounds/HP/1.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon2  = new ImageIcon(new ImageIcon("Images & Sounds/HP/2.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon3   = new ImageIcon(new ImageIcon("Images & Sounds/HP/3.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon4  = new ImageIcon(new ImageIcon("Images & Sounds/HP/4.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon5   = new ImageIcon(new ImageIcon("Images & Sounds/HP/5.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon6  = new ImageIcon(new ImageIcon("Images & Sounds/HP/6.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon7   = new ImageIcon(new ImageIcon("Images & Sounds/HP/7.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon8   = new ImageIcon(new ImageIcon("Images & Sounds/HP/8.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon9   = new ImageIcon(new ImageIcon("Images & Sounds/HP/9.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	static ImageIcon HPIcon10   = new ImageIcon(new ImageIcon("Images & Sounds/HP/10.png").getImage().getScaledInstance(250, 15, Image.SCALE_DEFAULT));
	
	public static ImageIcon gate = new ImageIcon(new ImageIcon("Images & Sounds/deffBuilding.png").getImage().getScaledInstance(135, 135, Image.SCALE_DEFAULT));
	
	//*****************************************Labels*****************************************\
	public static JLabel map[][] = new JLabel[12][12];
	
	JLabel background = new JLabel(image);
	
	JLabel soldierLabel   = new JLabel(soldierIcon);
	JLabel archerLabel    = new JLabel(archerIcon);
	JLabel knightLabel    = new JLabel(knightIcon);
	JLabel chariotLabel   = new JLabel(chariotIcon);
	JLabel spearmanLabel  = new JLabel(spearIcon);
	
	JLabel flagLabel  = new JLabel(flagIcon);
	
	public static JLabel soldierCount  = new JLabel("0");
	public static JLabel archerCount   = new JLabel("0");
	public static JLabel knightCount   = new JLabel("0");
	public static JLabel chariotCount  = new JLabel("0");
	public static JLabel spearmanCount = new JLabel("0");

	
	JLabel nationLabel   = new JLabel();
	JLabel nationLabel2   = new JLabel();
	
	private static JLabel HP = new JLabel(HPIcon10);
	private static JLabel HP2 = new JLabel(HPIcon10);
	
	public static JLabel NumOfTurns = new JLabel("Number of Turns : ");
	public static JLabel Turn=new JLabel("Deploying");
	
	JLabel selectedLabel = new JLabel();
	
	
	//*****************************************Buttons*****************************************\
	
	JButton endTurn   = new JButton(buttonIcon);
	JButton showHide   = new JButton(buttonIcon);
	JButton Deploy   = new JButton(buttonIcon);
	
	 //*****************************************Clips*****************************************\
	static AudioInputStream audioInputStream ;
	static Clip clip; 
	
	 
	
	static boolean visible = true;
	public static boolean isDeployed = false;
	static boolean isSelected = false;
	public static boolean isTurn = false;
	public static boolean isEnemyDeployed = false;
	
	static String[] str;
	
	public static int numOfTurns = 0;
	
	UserCity c;
	static WarMap warmap;
	
	int[] soldierMovesX = {-1 , -1 , -1 , 0 ,0 ,1 , 1 , 1};
	int[] soldierMovesY = {-1 , 0 , 1 , -1 ,1 ,-1 , 0 , 1};
	
	int[] archerMovesX = {-2 , -2 , -2 , -2 , -2 , 2 , 2  , 2 , 2  , 2 , 0 , 0 , -1 , -1 , 1 , 1 };
	int[] archerMovesY = { 0 , -1 , 1  , -2 ,  2 , 0 , -1 , 1 , -2 , 2 , -2, 2 , -2 , 2 , -2 , 2};
	
	int[] spearmanAMovesX = {-2 , 2 , 1 , -1 , 1 , 2 , -2 , -1 , -1 , -2 };
	int[] spearmanAMovesY = {-2 , -2 , 1 , -1 , -1 , 2 , 0 , 0 , 1 , 2};
	
	int[] spearmanDMovesX = {-2 , -1 , -1 , -2 , 1 , 2 , 1 , 2 , 1, 2};
	int[] spearmanDMovesY = {-2 , -1 , 1 , 2 , 1 , 2 , -1 , -2 , 0 ,0};
	
	int[] knightMovesX = { 0 , 0 , -1 , -1 , 1 , 1};
	int[] knightMovesY = { 1 , -1 , 1 , -1 ,1 , -1};
	
	int[] chariotMovesX = { 0 , 0 , 0 , 0 , 0 , 0 , 1 , 2 , 3 , -1, -2 , -3 };
	int[] chariotMovesY = { -1 , -2 , -3 ,1 , 2 , 3 , 0 , 0 ,0 , 0 , 0 , 0 };
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image imageC     = toolkit.getImage("Images & Sounds/mouse/glove.png");
	Cursor cr        = toolkit.createCustomCursor(imageC , new Point(this.getX(), this.getY()), "img");
	

	@SuppressWarnings("static-access")
	public WarMap(String type , UserCity s) throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{ 
		try {
			audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/inWar.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		ToolTipManager.sharedInstance().setDismissDelay(15000);
		
		identity = type;

		c = s;
		warmap = this;
		
		SoldierNumber  = Integer.parseInt(c.soldierCount.getText());
		ArcherNumber   = Integer.parseInt(c.archerCount.getText());
		SpearmanNumber = Integer.parseInt(c.spearmanCount.getText());
		KnightNumber   = Integer.parseInt(c.knightCount.getText());
		ChariotNumber  = Integer.parseInt(c.chariotCount.getText());
		
		soldierCount.setText(String.valueOf(SoldierNumber));
		archerCount.setText(String.valueOf(ArcherNumber));
		spearmanCount.setText(String.valueOf(SpearmanNumber));
		knightCount.setText(String.valueOf(KnightNumber));
		chariotCount.setText(String.valueOf(ChariotNumber));
		

		//Main frame settings
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);
		setLayout(null);
		setBackground(Color.BLACK);
		

		//Panels Layouts
		statesPanel.setLayout(null);
		statesPanel.setBounds(0, 0, 58, height);
		statesPanel.setBackground(Color.GRAY);
		add(statesPanel);
		
	
		mapPanel.setLayout(null);
		mapPanel.setBounds(59, 0 , width - 59, height);
		mapPanel.setBackground(Color.GREEN);
		add(mapPanel);
		
		
		initMap();
	    initStatesPanel();
	    
	 	repaint();	 			
	}

	
	  //------Initialize states Panel components
		private void initStatesPanel()
		{	
			int y = 75;
		
			flagLabel.setBounds(10 , 15 ,50 ,50);
			flagLabel.addMouseListener(new MouseAdapter(){
				
				@SuppressWarnings({ "rawtypes", "unchecked"})
				@Override
				public void mouseClicked(MouseEvent e)
				{
					commandToSend command = new commandToSend();
					command.commandType = "surrender";
					
					try {
						Connection.output.writeObject(command);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					dispose();	
					c.setVisible(true);
										
				}
				
			});
		
			
			soldierLabel.setBounds(10 , 60 + y ,40 ,40);
			soldierCount.setBounds(10 , 110 + y,55 ,25);
			soldierCount.setFont(new Font(getName() , Font.BOLD , 15));
					
			archerLabel.setBounds(10 , 160 + y,40 ,40);
			archerCount.setBounds(10 , 210 + y,55 ,25);
			archerCount.setFont(new Font(getName() , Font.BOLD , 15));	
					
			spearmanLabel.setBounds(10 , 260 + y ,40 ,40);
			spearmanCount.setBounds(10 , 310 + y,55 ,25);
			spearmanCount.setFont(new Font(getName() , Font.BOLD , 15));
					
			knightLabel.setBounds(10 , 360 + y,40 ,40);
			knightCount.setBounds(10 , 410 + y ,55 ,25);
			knightCount.setFont(new Font(getName() , Font.BOLD , 15));
					
			chariotLabel.setBounds(10, 460 + y ,40 ,40);
			chariotCount.setBounds(10 , 510 + y ,55 ,25);
			chariotCount.setFont(new Font(getName() , Font.BOLD , 15));

		
			statesPanel.add(flagLabel);
					
			statesPanel.add(soldierLabel);
			statesPanel.add(archerLabel);
			statesPanel.add(spearmanLabel);
			statesPanel.add(knightLabel);
			statesPanel.add(chariotLabel);

					
			statesPanel.add(soldierCount);
			statesPanel.add(archerCount);
			statesPanel.add(spearmanCount);
			statesPanel.add(knightCount);
			statesPanel.add(chariotCount);
			
			repaint();
				
			}
		
		//------Initialize map
		private void initMap()
		{
			background.setBounds(0,0,mapPanel.getWidth(),mapPanel.getHeight());
			mapPanel.add(background);
			
			this.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					setCursor(cr);	
				}
			});
			
			
			if(identity.equals("Attacker"))
			{
				if(UserCity.folder.equals("Nation 1"))
				{
					nationLabel.setIcon(nationIcon);
					
				} else if(UserCity.folder.equals("Nation 2")){
					
					nationLabel.setIcon(nationIcon2);
				}
				
				if(player2Nation.equals("Nation 1"))
					nationLabel2.setIcon(nationIcon);
				else if(player2Nation.equals("Nation 2"))
					nationLabel2.setIcon(nationIcon2);
							
			} else if(identity.equals("Deffender")) {
				
				if(UserCity.folder.equals("Nation 1"))
				{
					nationLabel2.setIcon(nationIcon);
						
				} else if(UserCity.folder.equals("Nation 2")){
					
					nationLabel2.setIcon(nationIcon2);
				}	
				
				if(player2Nation.equals("Nation 1"))
					nationLabel.setIcon(nationIcon);
				else if (player2Nation.equals("Nation 2"))
					nationLabel.setIcon(nationIcon2);
			}
			
			repaint();
			
			
			nationLabel.setBounds(1600 , 10 , 250 , 250);
			background.add(nationLabel);
			
			HP.setBounds(1600 , 265 , 250 , 15);
			background.add(HP);
			
			nationLabel2.setBounds( 5 , 10 , 250 , 250);
			background.add(nationLabel2);	
			
			HP2.setBounds(5 , 265 , 250 , 15);
			background.add(HP2);

			NumOfTurns.setBounds(nationLabel2.getWidth() + 50 , 90 , 400 , 25);
			NumOfTurns.setFont(new Font(getName() , Font.BOLD , 25));
			background.add(NumOfTurns);
			
			Turn.setBounds(nationLabel2.getWidth() + 50 , 30 , 400 , 25);
			Turn.setFont(new Font(getName() , Font.BOLD , 25));
			background.add(Turn);
			
			showHide.setBounds(1650, 930, 200, 60);
			showHide.setFont(new Font(getName() , Font.BOLD , 25));
			showHide.setHorizontalTextPosition(JLabel.CENTER);
			showHide.setForeground(Color.WHITE);
			showHide.setText("Show/Hide");
			showHide.setBackground(Color.GRAY);
			showHide.setBorder(null);
			showHide.addMouseListener(new MouseAdapter(){
				
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(visible)
					{
						showHide(0);
						visible = false;
					} else {
						
						showHide(1);
						visible = true;
					}
					
					try {
						 playClick();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					showHide.setIcon(buttonIcon2);
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					showHide.setIcon(buttonIcon);
				}
			});
			background.add(showHide);
			
			Deploy.setBounds(1650, 860, 200, 60);
			Deploy.setFont(new Font(getName() , Font.BOLD , 25));
			Deploy.setHorizontalTextPosition(JLabel.CENTER);
			Deploy.setForeground(Color.WHITE);
			Deploy.setText("Deploy");
			Deploy.setBackground(Color.GRAY);
			Deploy.setBorder(null);
			Deploy.addMouseListener(new MouseAdapter(){
				
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(allowDeployment())
					{
						showHide(0);
						visible = false;
						
						Deploy.setEnabled(false);
						isDeployed = true;
						if(identity == "Attacker")
							isTurn = true;
						commandToSend command = new commandToSend();
						command.commandType = "Deploy";
						
						commandToSend command2 = new commandToSend();
						commandToSend command3 = new commandToSend();
						
						command3.commandType = "setUnitsArray";
						
						if(identity.equals("Attacker"))
						{
							command2.commandType = "setAttackerUnits";
							command2.commandValue = player1;
							Units1 = player1;
									
							command3.commandValue = player1StatsB;
							
						} else {
							
							command2.commandType = "setDeffenderUnits";
							command2.commandValue = player2;
							Units2 = player2;
							
							command3.commandValue = player2StatsB;
						}
							
						
						

						try {
							Connection.output.writeObject(command);
							Connection.output.writeObject(command2);
							Connection.output.writeObject(command3);
						} catch (IOException e2) {
							e2.printStackTrace();
						}
			
					try {
						 playClick();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
											
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					Deploy.setIcon(buttonIcon2);
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					Deploy.setIcon(buttonIcon);
				}
			});
			background.add(Deploy);
			
			
			endTurn.setBounds(1650, 1000, 200, 60);
			endTurn.setFont(new Font(getName() , Font.BOLD , 25));
			endTurn.setHorizontalTextPosition(JLabel.CENTER);
			endTurn.setForeground(Color.WHITE);
			endTurn.setText("End Turn");
			endTurn.setBackground(Color.GRAY);
			endTurn.setBorder(null);
			endTurn.addMouseListener(new MouseAdapter(){
				
				@SuppressWarnings({ "rawtypes", "unchecked" })
				@Override
				public void mouseClicked(MouseEvent e)
				{
					if(isTurn)
					{
						
							NumOfTurns.setText("Out of turns");	
							
							numOfTurns = 0;
							
							isTurn = false;
							
							showHide(0);
							
							Turn.setText("Enemy's Turn");
							
							commandToSend command = new commandToSend();
							
							command.commandType = "Turn";
							
							try {
								Connection.output.writeObject(command);
							} catch (IOException e1) {
								
								e1.printStackTrace();
							}
						}
						try {
							 playClick();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
														
				}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					endTurn.setIcon(buttonIcon2);
				}
				
				@Override
				public void mouseExited(MouseEvent e)
				{
					endTurn.setIcon(buttonIcon);
				}
			});
			background.add(endTurn);
			
	 
			int x = 0;
			int y = 0;
			
			for(int i=0 ; i<12; i++)
			{	
				if(i == 0)
				{
					x=10;
					y=490;
				} else if(i == 1) {
					x=80;
					y=530;
					
				} else if(i == 2) {
					x=160;
					y=575;
					
				} else if(i == 3) {
					x=240;
					y=620;
					
				} else if(i == 4) {
					x=320;
					y=665;
				} else if(i == 5) {
					x=400;
					y=710;
					
				} else if(i == 6) {
					x=480;
					y=755;
				} else if(i == 7) {
					x=560;
					y=800;
					
				} else if(i == 8) {		
					x=640;
					y=845;
				} else if(i == 9) {
					x=720;
					y=890;
				} else if(i == 10) {		
					x=800;
					y=935;
				} else if(i == 11) {
					x=880;
					y=980;		
				}
				
				for(int j=0 ; j<12 ; j++)
				{
					
					
					if(i == 0 && j == 6)
					{
						map[i][j] = new JLabel(gate);
						map[i][j].setBounds(x - 30,y - 40 ,135 , 135);	
						map[i][j].putClientProperty("Health", 5000 + ( game.Defense.TowerList.size() * 250));
						map[i][j].putClientProperty("Damage", 200);
						map[i][j].putClientProperty("Identity", "Deffender");
						map[i][j].putClientProperty("Number", 1);
						
						map[i][j].putClientProperty("deployed", true);
						
						
						if(identity.equals("Deffender"))
						  player2 = 1;
						
					} else {
						
						
						map[i][j] = new JLabel(mapIcon);
						map[i][j].setBounds(x ,y ,90 , 90);	
						map[i][j].putClientProperty("deployed", false);
						map[i][j].putClientProperty("Damage", 1);
						map[i][j].putClientProperty("Health", 1);
						map[i][j].putClientProperty("Number", 1);
					}
					
					
					if(identity == "Attacker")
					{
						if(i == 10 || i == 11)
							map[i][j].setVisible(true);	
						else
							map[i][j].setVisible(false);	
						
					} else if(identity == "Deffender") {
						
						if(i == 0 || i == 1)
							map[i][j].setVisible(true);	
						else
							map[i][j].setVisible(false);	
												
					}
					
					
					map[i][j].setText(String.valueOf(i) + " " + String.valueOf(j));
					map[i][j].setFont(new Font(getName() , Font.PLAIN ,0));
					map[i][j].setHorizontalTextPosition(JLabel.CENTER);
					background.add(map[i][j]);
					
		map[i][j].addMouseListener(new MouseAdapter(){
		@SuppressWarnings({ "rawtypes", "unchecked" })
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
			
			if(!isDeployed &&  !((boolean) ((JLabel)e.getSource()).getClientProperty("deployed")) && ((JLabel)e.getSource()).getIcon() != gate)
			{
				unitsSelection((JLabel)e.getSource());
							
			} else if (isDeployed && numOfTurns < 5 && isEnemyDeployed) {
							
					if(player2 == 1 && map[0][6].getIcon() == gate)
					{
						if(identity.equals("Attacker"))
							numOfTurns = - 99;
					}
					
					if(isTurn)
					{
																			
						if(!isSelected)
						{
							if(((JLabel)e.getSource()).getIcon() != mapIcon && ((JLabel)e.getSource()).getClientProperty("Identity").equals(identity))  //to show the movments
							{					
								showHide(0);
			
								ImageIcon icon =  (ImageIcon) (((JLabel)e.getSource()).getIcon());
	
								str = (((JLabel)e.getSource()).getText()).split(" ");
									
								if(identity.equals("Attacker"))
								{
									if(icon == Worrior)							
											showMovments(1 , Integer.parseInt(str[0]) , Integer.parseInt(str[1]));							
									else if ((icon == Archer)) 							
											showMovments( 2, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));								
									else if ((icon == Spear)) 
											 showMovments( 3, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));		
									else if ((icon == Knight)) 
											 showMovments( 4, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));		
									else if ((icon == Chariot)) 
											 showMovments( 5, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));	
									
									} else {
										
									if(icon == WorriorD)							
											showMovments(1 , Integer.parseInt(str[0]) , Integer.parseInt(str[1]));							
									else if ((icon == ArcherD)) 							
											showMovments( 2, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));								
									else if ((icon == SpearD)) 
											 showMovments( 3, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));		
									else if ((icon == KnightD)) 
											 showMovments( 4, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));		
									else if ((icon == ChariotD)) 
											 showMovments( 5, Integer.parseInt(str[0]) , Integer.parseInt(str[1]));	
										
										
									}
																
										isSelected = true;
										selectedLabel = (JLabel)e.getSource();
										
										try {
											 playClick();
											} catch (Exception e1) {
												e1.printStackTrace();
											}
							
							}
							
						} else {
								
								
						String next[] = ((JLabel)e.getSource()).getText().split(" ");  //next position
												
						ImageIcon icon = (ImageIcon) map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].getIcon(); //selected unit icon
								
						if(map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].getIcon() == mapIcon) //to prevent movment to occupied locations
						{	
						    map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].setIcon(icon); //setting the next location icon to the selected unit icon
									
							map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].setIcon(mapIcon); //setting the previous locatin to a map icon
									
  map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].putClientProperty("Identity", map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].getClientProperty("Identity"));
  map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].putClientProperty("Health", map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].getClientProperty("Health"));
  map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].putClientProperty("Damage", map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].getClientProperty("Damage"));
  map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].putClientProperty("Number", map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].getClientProperty("Number"));					
				
  map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].putClientProperty("Identity", "");
  map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].putClientProperty("Health", "");
  map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].putClientProperty("Damage", "");
  map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].putClientProperty("Number", "");
											
								String location = map[Integer.parseInt(str[0])][Integer.parseInt(str[1])].getText() ;
													
								String newLocation = map[Integer.parseInt(next[0])][Integer.parseInt(next[1])].getText();

								String type = "";
									
									if(icon == Worrior || icon ==WorriorD)
										type = "Soldier";
									else if (icon == Archer|| icon ==ArcherD)
										type = "Archer";
									else if (icon == Spear|| icon ==SpearD)
										type = "Spearman";
									else if (icon == Knight|| icon ==KnightD)
										type = "Knight";
									else if (icon == Chariot|| icon ==ChariotD)
										type = "Chariot";
										
									commandToSend command1 = new commandToSend();
									MapUnit unit = new MapUnit();
									
									
									
									unit.Damage = (int) ((JLabel)e.getSource()).getClientProperty("Damage");
									unit.Health = (int) ((JLabel)e.getSource()).getClientProperty("Health");
									unit.position = location;
									unit.type = type;
									unit.identitiy = (String) ((JLabel)e.getSource()).getClientProperty("Identity");
									unit.NumberOfUnits = (int)((JLabel)e.getSource()).getClientProperty("Number");
									unit.additionData = newLocation;
									
									command1.commandType = "MoveUnit";
									command1.commandValue = unit ;
									
									try {
										Connection.output.writeObject(command1);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									
									isSelected = false;
									
									showHide(0);
									
									numOfTurns++;
									NumOfTurns.setText("Number of Turns : " + (5 - numOfTurns));
									if(numOfTurns == 5)
									{
										NumOfTurns.setText("Out of turns");	
										
										numOfTurns = 0;
										
										isTurn = false;
										
										showHide(0);
										
										Turn.setText("Enemy's Turn");
										
										commandToSend command = new commandToSend();
										
										command.commandType = "Turn";
										
										try {
											Connection.output.writeObject(command);
										} catch (IOException e1) {
											
											e1.printStackTrace();
										}
									}
									try {
										 playClick();
										} catch (Exception e1) {
											e1.printStackTrace();
										}
									
								
								 } else	if  (((((JLabel)(e.getSource())).getClientProperty("Identity").equals("Attacker")) && selectedLabel.getClientProperty("Identity").equals("Deffender")) 
											|| ((((JLabel)(e.getSource())).getClientProperty("Identity").equals("Deffender")) && selectedLabel.getClientProperty("Identity").equals("Attacker"))) 
								 {
									 if(checkRange(selectedLabel ,((JLabel)(e.getSource()))))
									 {
								
											commandToSend command = new commandToSend();
											command.commandType = "AttackingUnit";
											MapUnit attacker = new MapUnit();
											MapUnit deffender = new MapUnit();
											
											attacker.Damage 		= (int)selectedLabel.getClientProperty("Damage");
											attacker.Health 		= (int)selectedLabel.getClientProperty("Health");
											attacker.NumberOfUnits  = (int)selectedLabel.getClientProperty("Number");
											attacker.position 		= selectedLabel.getText();
											
											
											deffender.Damage 		= (int)((JLabel)e.getSource()).getClientProperty("Damage");
											deffender.Health 		= (int)((JLabel)e.getSource()).getClientProperty("Health");
											deffender.NumberOfUnits = (int)((JLabel)e.getSource()).getClientProperty("Number");
											deffender.position 		= ((JLabel)e.getSource()).getText();
											
											attacker.additionData = deffender;
											
											command.commandValue = attacker;
											
											try {
												Connection.output.writeObject(command);
											} catch (IOException e1) {
												e1.printStackTrace();
											}	
											
											numOfTurns++;
											NumOfTurns.setText("Number of Turns : " + (5 - numOfTurns));
											if(numOfTurns == 5)
											{
												NumOfTurns.setText("Out of turns");	
												
												numOfTurns = 0;
												
												isTurn = false;
												
												Turn.setText("Enemy's Turn");
												
												commandToSend command2 = new commandToSend();
												
												command2.commandType = "Turn";
												

												try {
													Connection.output.writeObject(command2);
												} catch (IOException e1) {
													
													e1.printStackTrace();
												}
											
												showHide(0);
												
											} else {
												
												NumOfTurns.setText("Number of Turns : " +String.valueOf(5 - numOfTurns));
											}
											
									 if(selectedLabel.getIcon() == Chariot || selectedLabel.getIcon() == ChariotD){
												
												try {
													audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/stable.wav").getAbsoluteFile());
													clip = AudioSystem.getClip();
													clip.open(audioInputStream);
													clip.start();
												} catch (Exception e1) {
													e1.printStackTrace();
												}
				
										} else {
											
											try {
												 playClick();
												} catch (Exception e1) {
													e1.printStackTrace();
												}
										}
											
											
									 }
											
								
								} else {
									
									showHide(0);
									isSelected = false;
								}
								
							}
						}
						
						
						}
					
					}
				
				@Override
				public void mouseEntered(MouseEvent e)
				{
					if( ((JLabel)(e.getSource())).getIcon() != mapIcon )
					{
						((JLabel)(e.getSource())).setToolTipText(
								"Damage : " 
								+ (int)((JLabel)e.getSource()).getClientProperty("Damage")
								+ " , Health : " 
								+ (int)((JLabel)e.getSource()).getClientProperty("Health"));
					}
					
				}
				
				});
				
								
					x+= 80;
					y-= 45;
					
				}//inner for loop
				
			}//outer for loop
			
			repaint();			
			
		}
		
		//------Play click Function
		public static void playClick() throws UnsupportedAudioFileException, IOException, LineUnavailableException
		{
			audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/click.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		

		//------Hide/show 
		public void showHide(int x)
		{
			for(int i=0 ; i<12 ; i++)
				for(int j=0 ; j<12 ; j++)
					if(identity.equals("Attacker"))
					{
						if((i == 10 || i == 11) && x == 1 )
						  map[i][j].setVisible(true);
						else if((i == 10 || i == 11) && x == 0 && map[i][j].getIcon() == mapIcon)
							map[i][j].setVisible(false);
						else if (isDeployed && map[i][j].getIcon() == mapIcon)
							map[i][j].setVisible(false);
									
					} else if (identity.equals("Deffender")) {
						
						if((i == 0 || i == 1) && x == 1)
						  map[i][j].setVisible(true);
						else if(!isDeployed && (i == 0 || i == 1) && x == 0 && map[i][j].getIcon() == mapIcon)
							map[i][j].setVisible(false);
						else if (isDeployed && map[i][j].getIcon() == mapIcon)
							map[i][j].setVisible(false);
					}
			
			repaint();
						
		}

		//-----check if any units is deployed
		private boolean allowDeployment()
		{
			for(int i=0 ; i<12 ; i++)
				for(int j=0 ; j<12 ; j++)
					if(map[i][j].getIcon() != mapIcon)
						return true;
			
			return false;
		}
	
		//------Hide/show movments
		private void showMovments(int type , int x ,int y)
		{

				switch(type)
				{
				   case 1:
					   
					  for(int i=0 ; i<12 ; i++)
					     for(int j=0 ; j<12 ; j++)
					    	 if( (i == (x-1) && j == (y-1)) || (i == (x) && j == (y-1)) || (i == (x+1) && j == (y-1)) || (i == (x-1) && j == (y)) ||
					    	     (i == (x+1) && j == (y)) || (i == (x-1) && j == (y+1)) || (i == (x) && j == (y+1)) || (i == (x+1) && j == (y+1)) )
					    		   map[i][j].setVisible(true);
					  break; 		 
		
				   case 2:				   
						  for(int i=0 ; i<12 ; i++)
						     for(int j=0 ; j<12 ; j++)
						    	 if( (i == (x-2) && j == (y-2)) || (i == (x-2) && j == (y-1)) || (i == (x-2) && j == (y))   || (i == (x-2) && j == (y+1)) ||
						    	     (i == (x-2) && j == (y+2)) || (i == (x-1) && j == (y-2)) || (i == (x-1) && j == (y+2)) || (i == (x) && j == (y-2))   ||
						    	     (i == (x) && j == (y+2))   || (i == (x+1) && j == (y-2)) || (i == (x+1) && j == (y+2)) || (i == (x+2) && j == (y-2)) ||
						    	     (i == (x+2) && j == (y-1)) || (i == (x+2) && j == (y))   || (i == (x+2) && j == (y+1)) || (i == (x+2) && j == (y+2) ))
						    		   map[i][j].setVisible(true);
						  break; 		   
					   
				   case 3:
					   
					   if(identity.equals("Attacker"))
					   {
						   for(int i=0 ; i<12 ; i++)
							     for(int j=0 ; j<12 ; j++)
							    	 if( (i == (x-2) && j == (y-2)) || (i == (x-1) && j == (y-1)) || (i == (x-2) && j == (y)) || (i == (x-1) && j == (y)) ||
							    	     (i == (x+2) && j == (y-2)) || (i == (x+1) && j == (y-1)) || (i == (x-1) && j == (y+1)) || (i == (x-2) && j == (y+2)) ||
							    	     (i == (x+1) && j == (y+1)) || (i == (x+2) && j == (y+2)) )
							    		   map[i][j].setVisible(true);
					   } else {
						   
						   for(int i=0 ; i<12 ; i++)
							     for(int j=0 ; j<12 ; j++)
							    	 if( (i == (x-2) && j == (y-2)) || (i == (x-1) && j == (y-1)) || (i == (x-1) && j == (y+1)) || (i == (x-2) && j == (y+2)) ||
							    	     (i == (x+1) && j == (y+1)) || (i == (x+2) && j == (y+2)) || (i == (x+1) && j == (y-1)) || (i == (x+2) && j == (y-2)) ||
							    	     (i == (x+1) && j == (y)) || (i == (x+2) && j == (y)) )
							    		   map[i][j].setVisible(true);
					   }
						  
						 					 
						  break; 	
						  
				   case 4:		   
						  for(int i=0 ; i<12 ; i++)
						     for(int j=0 ; j<12 ; j++)
						    	 if( (i == (x-1) && j == (y-1)) || (i == (x) && j == (y-1)) || (i == (x-1) && j == (y+1)) || (i == (x+1) && j == (y-1)) ||
						    	     (i == (x) && j == (y+1)) || (i == (x+1) && j == (y+1))  )
						    		   map[i][j].setVisible(true);
						  break; 		
				   case 5:
					   
						  for(int i=0 ; i<12 ; i++)
						     for(int j=0 ; j<12 ; j++)
						    	 if( (i == (x) && j == (y+1)) || (i == (x) && j == (y+2)) || (i == (x) && j == (y+3)) || (i == (x) && j == (y-1)) ||
						    	     (i == (x) && j == (y-2)) || (i == (x) && j == (y-3)) || (i == (x+1) && j == (y)) || (i == (x+2) && j == (y)) ||
						    	     (i == (x+3) && j == (y)) || (i == (x-1) && j == (y)) || (i == (x-2) && j == (y)) || (i == (x-3) && j == (y)) )
						    		   map[i][j].setVisible(true);
						  break; 						
					
				}				
			
			repaint();
		}
		
		
		//-----Deploy Units to selected location
		@SuppressWarnings("rawtypes")
		public void unitsSelection(JLabel label){
			
			MapUnit unit = new MapUnit();
			String curr = label.getText();
			
			try {
				showHide(0);	
				showHide.setEnabled(false);
				
				unitsSelect = new UnitsNumberSelecter();
				unitsSelect.setBounds(550 , 250 , 534,465);
				unitsSelect.setVisible(true);
				background.add(unitsSelect);
				
				repaint();
			
				unitsSelect.getJConfim().addMouseListener(new MouseAdapter(){
					
					@SuppressWarnings({ "unchecked", "static-access" })
					@Override
					public void mouseClicked(MouseEvent e)
					{									
							
						showHide(1);	
						showHide.setEnabled(true);

							try {
								 playClick();
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							
							if(unitsSelect.getNumber() != 0)
							{
								label.putClientProperty("deployed", true);
								label.putClientProperty("Identity", identity);
								

								unit.identitiy = identity;
								unit.type = unitsSelect.getType();
								unit.position = curr;
								
								
								switch(unit.type)
								{
								
								case "Soldier":
									
									game.Warrior w = new game.Warrior();
									
									
									label.putClientProperty("Health", w.Health * unitsSelect.getNumber());
									label.putClientProperty("Number", unitsSelect.getNumber());
									label.putClientProperty("Damage", w.Damage * unitsSelect.getNumber());
									
									unit.Damage =  w.Damage * unitsSelect.getNumber();
									unit.Health =  w.Health * unitsSelect.getNumber();
									
									if(identity.equals("Attacker"))
									{
										player1StatsB[0] += unitsSelect.getNumber();
										label.setIcon(Worrior);
									} else	{
										player2StatsB[0] += unitsSelect.getNumber();
										label.setIcon(WorriorD);
									}
										
									
									break;
									
								case "Archer":
									
									game.Archer ar = new game.Archer();
									
									
									label.putClientProperty("Damage", ar.Damage * unitsSelect.getNumber());
									label.putClientProperty("Health", ar.Health * unitsSelect.getNumber());
									label.putClientProperty("Number", unitsSelect.getNumber());
									
									unit.Damage = ar.Damage * unitsSelect.getNumber();
									unit.Health = ar.Health * unitsSelect.getNumber();
									
									if(identity.equals("Attacker"))
									{
										player1StatsB[1] += unitsSelect.getNumber();
										label.setIcon(Archer);
									} else {

										player2StatsB[1] += unitsSelect.getNumber();
										label.setIcon(ArcherD);
									}
									
									break;
									
								case "Spearman":
									
									game.SpearMan sp = new game.SpearMan();
									
								
									label.putClientProperty("Damage", sp.Damage * unitsSelect.getNumber());
									label.putClientProperty("Health", sp.Health * unitsSelect.getNumber());
									label.putClientProperty("Number", unitsSelect.getNumber());
									
									unit.Damage = sp.Damage * unitsSelect.getNumber();
									unit.Health = sp.Health * unitsSelect.getNumber();
									
									if(identity.equals("Attacker"))
									{
										player1StatsB[2] += unitsSelect.getNumber();
										label.setIcon(Spear);
									} else {
										
										player2StatsB[2] += unitsSelect.getNumber();
										label.setIcon(SpearD);
									}
										
															
									break;
									
								case "Knight":
									
									game.Knight kn = new game.Knight();
									
									
									label.putClientProperty("Damage", kn.Damage * unitsSelect.getNumber());
									label.putClientProperty("Health", kn.Health * unitsSelect.getNumber());
									label.putClientProperty("Number", unitsSelect.getNumber());
									
									unit.Damage = kn.Damage * unitsSelect.getNumber();
									unit.Health = kn.Health * unitsSelect.getNumber();
									
									if(identity.equals("Attacker"))
									{
										player1StatsB[3] += unitsSelect.getNumber();
										label.setIcon(Knight);
									} else {
										player2StatsB[3] += unitsSelect.getNumber();
										label.setIcon(KnightD);
									}
										
									
									break;
									
								case "Chariot":
									
									game.Chariot ch = new game.Chariot();
									
									label.putClientProperty("Damage", ch.Damage * unitsSelect.getNumber());
									label.putClientProperty("Health", ch.Health * unitsSelect.getNumber());
									label.putClientProperty("Number", unitsSelect.getNumber());
																									
									unit.Damage = ch.Damage * unitsSelect.getNumber();
									unit.Health = ch.Health * unitsSelect.getNumber();
									
									if(identity.equals("Attacker"))
									{
										player1StatsB[4] += unitsSelect.getNumber();
										label.setIcon(Chariot);
									} else {

										player2StatsB[4] += unitsSelect.getNumber();
										label.setIcon(ChariotD);
									}
									
									break;
								}	
								
								if(unitsSelect.getNumber() != 0)
								{
									if(identity.equals("Attacker"))
									   player1++;
									else
										   player2++;
								}
								
								commandToSend command = new commandToSend();
								command.commandType = "SendUnit";
								command.commandValue = unit;
								
								try {
									Connection.output.writeObject(command);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								
							} else {
								label.setIcon(mapIcon);

							
							}
							
							unitsSelect.setVisible(false);
							unitsSelect = null;
					}
				});
				
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			
			  repaint();
						
		}
		
		//----returns current warmap
		public static WarMap getWar()
		{
			
			return warmap;
		}

		//----Update map
		@SuppressWarnings("rawtypes")
		public static void updateMap()
		{
					
					for(MapUnit unit : units)
					{
						String pos1 = unit.position;
						
						String id = unit.identitiy;
						
						String posSplit[] = new String[2];

						posSplit[0] = new String();
						posSplit[1] = new String();
								
						posSplit = pos1.split(" ");
								
						String type = unit.type;
						
						if(id.equals("Attacker"))
						{
							switch(type)
							{
								case "Soldier" :								  
									     map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(Worrior);	
									     break;								  
								case "Archer" :							  
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(Archer);
										  break;
								case "Spearman" :
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(Spear);
										  break;
								case "Knight" :
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(Knight);
										  break;
								case "Chariot" :
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(Chariot);
										  break;
												
							}
							
						} else {
							switch(type)
							{
								case "Soldier" :								  
									     map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(WorriorD);	
									     break;								  
								case "Archer" :							  
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(ArcherD);
										  break;
								case "Spearman" :
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(SpearD);
										  break;
								case "Knight" :
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(KnightD);
										  break;
								case "Chariot" :
										  map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setIcon(ChariotD);
										  break;
												
							}
							
						}
						
								
						 map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].putClientProperty("Health", unit.Health);
						 map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].putClientProperty("Number", unit.NumberOfUnits);
						 map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].putClientProperty("Damage", unit.Damage);
						 map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].putClientProperty("Identity", id);
						 map[Integer.parseInt(posSplit[0])][Integer.parseInt(posSplit[1])].setVisible(true);
								
							}
					
					map[0][6].setIcon(gate);
					map[0][6].setBounds(460,155 ,150 , 150);	
					map[0][6].putClientProperty("Health", 5000);
					map[0][6].setVisible(true);
					
					 getWar().repaint();
		}
		
		//---checks if the target is within the attacker's range
		private boolean checkRange(JLabel point , JLabel target)
		{
			ImageIcon icon =  (ImageIcon) (point.getIcon());
			
			int type = 0;
			
			String string [] = (point.getText()).split(" ");
			String string2 []= (target.getText()).split(" ");
			
			int pointX = Integer.parseInt(string[0]);
			int pointY = Integer.parseInt(string[1]);
			
			int targetX = Integer.parseInt(string2[0]);
			int targetY = Integer.parseInt(string2[1]);
			
		
			if(icon == Worrior || icon == WorriorD)							
				type = 1;						
			else if ((icon == Archer) || icon == ArcherD) 							
				type = 2;							
			 else if ((icon == Spear) || icon == SpearD) 
				 type = 3;
			 else if ((icon == Knight) || icon == KnightD) 
				 type = 4;	
			 else if ((icon == Chariot) || icon == ChariotD) 
				 type = 5;
			
			switch(type)
			{
			   case 1:
				   
				  for(int i=0 ; i<8 ; i++)
				     if(pointX + soldierMovesX[i] == targetX && pointY + soldierMovesY[i] == targetY)
				    	 return true;
				  
				  break; 		 
	
			   case 2:				   
				   for(int i=0 ; i<16 ; i++)
					     if(pointX + archerMovesX[i] == targetX && pointY + archerMovesY[i] == targetY)
					    	 return true;
					  break; 		   
				   
			   case 3:
				   
				   if(identity.equals("Attacker"))
					{
				    	   for(int i=0 ; i<10 ; i++)
						     if(pointX + spearmanAMovesX[i] == targetX && pointY + spearmanAMovesY[i] == targetY)
						    	 return true;
						  	
											
					} else if(identity.equals("Deffender")) {
						
						for(int i=0 ; i<10 ; i++)
						     if(pointX + spearmanDMovesX[i] == targetX && pointY + spearmanDMovesY[i] == targetY)
						    	 return true;
					}
				   
				   break; 
			   case 4:		   
				   for(int i=0 ; i<6 ; i++)
					     if(pointX + knightMovesX[i] == targetX && pointY + knightMovesY[i] == targetY)
					    	 return true;
					  break; 		
			   case 5:   
				   for(int i=0 ; i<12 ; i++)
					     if(pointX + chariotMovesX[i] == targetX && pointY + chariotMovesY[i] == targetY)
					    	 return true;
					  break; 
			}
			
			return false;
				
		}
		
		//---updates the health
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void UpdateHealthBar() throws IOException, UnsupportedAudioFileException, LineUnavailableException
		{
			
			if((double)player1 / (double)Units1 <= 0.1)
				HP.setIcon(HPIcon);
			else if((double)player1 / (double)Units1 <= 0.2)
				HP.setIcon(HPIcon2);
			else if((double)player1 / (double)Units1 <= 0.3)
				HP.setIcon(HPIcon3);
			else if((double)player1 / (double)Units1 <= 0.4)
				HP.setIcon(HPIcon4);
			else if((double)player1 / (double)Units1 <= 0.5)
				HP.setIcon(HPIcon5);
			else if((double)player1 / (double)Units1 <= 0.6)
				HP.setIcon(HPIcon6);
			else if((double)player1 / (double)Units1 <= 0.7)
				HP.setIcon(HPIcon7);
			else if((double)player1 / (double)Units1 <= 0.8)
				HP.setIcon(HPIcon8);
			else if((double)player1 / (double)Units1 <= 0.9)
				HP.setIcon(HPIcon9);
			
			if((double)player2 / (double)Units2 <= 0.1)
				HP2.setIcon(HPIcon);
			else if((double)player2 / (double)Units2 <= 0.2)
				HP2.setIcon(HPIcon2);
			else if((double)player2 / (double)Units2 <= 0.3)
				HP2.setIcon(HPIcon3);
			else if((double)player2 / (double)Units2 <= 0.4)
				HP2.setIcon(HPIcon4);
			else if((double)player2 / (double)Units2 <= 0.5)
				HP2.setIcon(HPIcon5);
			else if((double)player2 / (double)Units2 <= 0.6)
				HP2.setIcon(HPIcon6);
			else if((double)player2 / (double)Units2 <= 0.7)
				HP2.setIcon(HPIcon7);
			else if((double)player2 / (double)Units2 <= 0.8)
				HP2.setIcon(HPIcon8);
			else if((double)player2 / (double)Units2 <= 0.9)
				HP2.setIcon(HPIcon9);
			
			if(player2 == 1 && map[0][6].getIcon() == gate)
			{
				if(identity.equals("Attacker"))
				{
					numOfTurns = 9999;
					isTurn = true;
				} else {
					
					numOfTurns = 0;
					isTurn = false;
					
					commandToSend command = new commandToSend();
					command.commandType = "Turn";
					try {
						Connection.output.writeObject(command);
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				
				
			}
			if(player1 == 0 || player2 == 0)
			{
				WarMap.getWar().EndOfWar();
				
				int sk  = player2StatsB[0] - player2StatsE[0];
				int sl  = player1StatsB[0] - player1StatsE[0];
				int ak  = player2StatsB[1] - player2StatsE[1];
				int al  = player1StatsB[1] - player1StatsE[1];
				int smk = player2StatsB[2] - player2StatsE[2];
				int sml = player1StatsB[2] - player1StatsE[2];
				int kk  = player2StatsB[3] - player2StatsE[3];
				int kl  = player1StatsB[3] - player1StatsE[3];
				int ck  = player2StatsB[4] - player2StatsE[4];
				int cl  = player1StatsB[4] - player1StatsE[4];
				
				if(player1 == 0)
				{
					if(identity.equals("Attacker"))
					{
						
						Defeat defeat = new Defeat(sk , sl , kk , kl , ck , cl , smk , sml ,ak ,al);
						getWar().add(defeat);
						defeat.setVisible(true);
		
						
						try {
							audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/lose.wav").getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioInputStream);
							clip.start();
						} catch (Exception e1) {
							e1.printStackTrace();
						}		
						
					
					} else {
						
						Victory victory = new Victory(sk , sl , kk , kl , ck , cl , smk , sml ,ak ,al);				
						getWar().add(victory);	
						victory.setVisible(true);
						
				
						try {
							audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/winMusicFinal.wav").getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioInputStream);
							clip.start();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						
					}
					
					
					
				} else if (player2 == 0) {
					
					if(identity.equals("Deffender"))
					{
						Defeat defeat = new Defeat(sk , sl , kk , kl , ck , cl , smk , sml ,ak ,al);
						defeat.setVisible(true);
						getWar().add(defeat);
		
						try {
							audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/lose.wav").getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioInputStream);
							clip.start();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						
					} else {
						
						Victory victory = new Victory(sk , sl , kk , kl , ck , cl , smk , sml ,ak ,al);				
						getWar().add(victory);	
						victory.setVisible(true);
				
						
						try {
							audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/winMusicFinal.wav").getAbsoluteFile());
							clip = AudioSystem.getClip();
							clip.open(audioInputStream);
							clip.start();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						
						
					}
				}
				
				getWar().showHide.setVisible(false);
				getWar().Deploy.setVisible(false);
				getWar().endTurn.setVisible(false);
				getWar().repaint();
			}		
		
		}
		
		
		@Override
 		public void run() 
		{
			while(true)
			{
								
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		
		}
		
		public void EndOfWar()
		{
			for(int i=0;i<12;i++)
				for(int j=0;j<12;j++)
				{
					if(WarMap.map[i][j].getIcon().equals(Worrior))
					{
						if(identity.equals("Attacker"))
							player1StatsE[0] += (int) WarMap.map[i][j].getClientProperty("Number") ;
						else
							player2StatsE[0] += (int) WarMap.map[i][j].getClientProperty("Number") ;
					}
					else if(WarMap.map[i][j].getIcon().equals(Archer))
					{
						if(identity.equals("Attacker"))
							player1StatsE[1] += (int) WarMap.map[i][j].getClientProperty("Number") ;
						else
							player2StatsE[1] += (int) WarMap.map[i][j].getClientProperty("Number") ;
					}
					else if(WarMap.map[i][j].getIcon().equals(Spear))
					{
						if(identity.equals("Attacker"))
							player1StatsE[2] += (int) WarMap.map[i][j].getClientProperty("Number") ;
						else
							player2StatsE[2] += (int) WarMap.map[i][j].getClientProperty("Number") ;
					}
					else if(WarMap.map[i][j].getIcon().equals(Knight))
					{
						if(identity.equals("Attacker"))
							player1StatsE[3] += (int) WarMap.map[i][j].getClientProperty("Number") ;
						else
							player2StatsE[3] += (int) WarMap.map[i][j].getClientProperty("Number") ;
					}
					else if(WarMap.map[i][j].getIcon().equals(Chariot))
					{
						if(identity.equals("Attacker"))
							player1StatsE[4] += (int) WarMap.map[i][j].getClientProperty("Number") ;
						else
							player2StatsE[4] += (int) WarMap.map[i][j].getClientProperty("Number") ;
					}
				}
		}

}
