package userInterface;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import server.Connection;
import server.ListenToServer;
import server.commandToSend;


public class Screen extends JFrame {

	private static final long serialVersionUID = 1L;
	
	AudioInputStream music;
	public static Clip musicClip;
	
	Clip click;
	
	public static CardLayout cards;
	static public String currentPanel;
	RegForm regForm;
	LogForm logForm;
	LogRegScreen logRegScreen;
	StartExit startExit;
	NationSelect nationForm;
	int choice = 1;
	WaitingPanel panel;
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Image image     = toolkit.getImage("Images & Sounds/mouse/glove.png");
	Cursor c        = toolkit.createCustomCursor(image , new Point(this.getX(), this.getY()), "img");
	
	AudioInputStream audioInputStream;
	Clip clip;
	
	public Screen() throws IOException, UnsupportedAudioFileException, LineUnavailableException{
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH); 
		setUndecorated(true);
		setVisible(true);

		
		cards=new CardLayout();
		setLayout(cards);
		
		
		regForm = new RegForm();
		logForm = new LogForm();
		logRegScreen = new LogRegScreen();
		startExit = new StartExit();
		nationForm =new NationSelect(this);
		panel = new WaitingPanel();
		
		add(logRegScreen, "logAndReg");	
		add(startExit ,"startForm");	
		add(regForm,"regForm");
		add(logForm,"logForm");
		add(nationForm,"nationform");
		add(panel,"waitingForm");
		
		LogForm.container = this.getContentPane();
		RegForm.container = this.getContentPane();
		StartExit.container = this.getContentPane();
		ListenToServer.container = this.getContentPane();
		WaitingPanel.container = this.getContentPane();
		
		currentPanel = "LogRegScreen";
		
		logForm.JPassText.addKeyListener(new KeyAdapter(){ 	 
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER)		
					logForm.login();
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)		
					goBack();
			}
	    });
		
		logForm.JUserText.addKeyListener(new KeyAdapter(){ 	 
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER)		
					logForm.login();
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)		
					goBack();
			}
	    });
		
		regForm.JPassText.addKeyListener(new KeyAdapter(){ 	 
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER)		
					regForm.reg();
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)		
					goBack();
			}
	    });
		
		regForm.JUserText.addKeyListener(new KeyAdapter(){ 	 
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode() == KeyEvent.VK_ENTER)		
					regForm.reg();
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE)		
					goBack();
			}
	    });
		
		this.addMouseListener(new MouseAdapter(){
			
			@Override
			public void mouseEntered(MouseEvent e)
			{
				setCursor(c);	
			}
		});
		
		this.addKeyListener(new KeyAdapter() {
			
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
        public void keyPressed(KeyEvent e) {
			
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/click.wav").getAbsoluteFile());
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception e1) {	
				e1.printStackTrace();
			}		
									
					if(e.getKeyCode() == KeyEvent.VK_UP &&  choice > 1)
						choice--;
					else if(e.getKeyCode() == KeyEvent.VK_DOWN && choice < 3)
						choice++;
					else if(e.getKeyCode() == KeyEvent.VK_ENTER)
					{
						if(choice == 1)
						{
							if(currentPanel == "LogRegScreen")
							{
								cards.show(Screen.this.getContentPane(), "logForm");	
								currentPanel = "logForm";
							} else 	{
								commandToSend command = new commandToSend();
		    	    			command.commandType = "StartGame";
		    	    			command.commandValue = null;
		    	    			try {
		    	    				Connection.output.writeObject(command);
		    	    				
								} catch (IOException e1) {
									e1.printStackTrace();
								}
		    	    			ListenToServer toServer = new ListenToServer();
		    	    			Thread t = new Thread(toServer);
		    	    			t.start();	
		    	    			
		    	    			cards.show(Screen.this.getContentPane(), "waitingForm");
			
							}
							
						} else if(choice == 2) {
							
							if(currentPanel == "LogRegScreen")
							{
								cards.show(Screen.this.getContentPane(), "regForm");
								currentPanel = "logForm";
							} else
								dispose();
							
						} else if(choice == 3) {
							
							dispose();
						} 
						
							
					} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
						
						if((currentPanel == "logForm") || (currentPanel == "regForm"))
						{
							cards.show(Screen.this.getContentPane(), "LogRegScreen");
							logForm.setVisible(false);
							regForm.setVisible(false);
							currentPanel = "LogRegScreen";
						}
					}
						
					if(currentPanel == "LogRegScreen")
					{
						if(choice == 1)
							logRegScreen.choiceSelector.setLocation(20, 280);
						else if(choice == 2)
							logRegScreen.choiceSelector.setLocation(20, 340);
						else if(choice == 3)
							logRegScreen.choiceSelector.setLocation(20, 400);
				
					} else {
						if(choice == 1)
							startExit.choiceSelector.setLocation(20, 280);
						else if(choice == 2)
							startExit.choiceSelector.setLocation(20, 340);						

					}
					
					setFocusable(true);
					repaint();
					super.keyPressed(e);
				}
			
		});
		
				
		/*------------------------------Audio Usage---------------------------------*/
		music = AudioSystem.getAudioInputStream(new File("Images & Sounds\\Sounds\\music.wav"));
		musicClip = AudioSystem.getClip();
		musicClip.open(music);
		musicClip.start();
		
	}
	
	public void goBack()
	{
		cards.show(Screen.this.getContentPane(), "LogRegScreen");
		logForm.setVisible(false);
		regForm.setVisible(false);
		currentPanel = "LogRegScreen";
	}
	
}
