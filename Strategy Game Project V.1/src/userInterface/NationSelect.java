package userInterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.Nations;
import server.Connection;
import server.commandToSend;

public class NationSelect extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = ImageIO.read(new File("Images & Sounds\\Nation Select\\Nation Background.png"));
	ImageIcon nation1  = new ImageIcon(new ImageIcon("Images & Sounds/Nation Select/Diablo.png").getImage().getScaledInstance(600, 800, Image.SCALE_DEFAULT));
	ImageIcon nation2  = new ImageIcon(new ImageIcon("Images & Sounds/Nation Select/Minerva.png").getImage().getScaledInstance(620, 800, Image.SCALE_DEFAULT));


	private JLabel Diablo  = new JLabel();
	private JLabel Minerva = new JLabel();

	AudioInputStream audioInputStream ;
	Clip clip; 
	
	public NationSelect(Screen s ) throws IOException
	{
		setPreferredSize(new Dimension(LogRegScreen.width , LogRegScreen.height));
		setLayout(null);
		setVisible(true);
		
		Diablo.setBounds(85, 280, 600, 800);	
		Minerva.setBounds(1140, 280, 620, 800);
		
	    Minerva.setVisible(true);    
	    Diablo.setVisible(true);
	
	    Diablo.setIcon(nation1);
	    Minerva.setIcon(nation2);
	    
	    add(Diablo);
	    add(Minerva); 
	    
	  

	  Diablo.addMouseListener(new MouseAdapter() {
	    	@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
	    	public void mouseClicked(MouseEvent e) {
	   
	    		Nations.setName(1);
	    		
	    		 try {
						playClick();
					} catch (Exception e1) {
						e1.printStackTrace();
					} 
	    		
	    		commandToSend command1 = new commandToSend();
	    		
	    		command1.commandType = "setNation";
	    		command1.commandValue = "Nation 1";
	    		

	    		try {
	    			
	    			 Connection.output.writeObject(command1);
					@SuppressWarnings("unused")
					UserCity city = new UserCity();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
	    		
	    		
	    			
	    		Screen.musicClip.stop();
	    		s.setVisible(false);
	    		  			
	    	}   		
		});
	  Minerva.addMouseListener(new MouseAdapter() {
	    	@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
	    	public void mouseClicked(MouseEvent e) {
	   
	    		Nations.setName(2);
	    		
	    		try {
					playClick();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 

	    		commandToSend command2 = new commandToSend();
	    		
	    		command2.commandType = "setNation";
	    		command2.commandValue = "Nation 2";

	    		try {
	    			
	    			 Connection.output.writeObject(command2);
					@SuppressWarnings("unused")
					UserCity city = new UserCity();
				} catch (Exception e1) {
					e1.printStackTrace();
				} 
	    		
	    		 
	    		 
	    		Screen.musicClip.stop();
	    		s.setVisible(false);
	    	}   		
		});
	    
		repaint();	
	}
	//------Play click Function
	private void playClick() throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{	
		audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/click.wav").getAbsoluteFile());
		clip = AudioSystem.getClip();
		clip.open(audioInputStream);
		clip.start();
	}
	
	
	public void paintComponent(Graphics g)
	{
		
		g.drawImage(image, 0, 0, LogRegScreen.width , LogRegScreen.height, Diablo); 
		super.paintComponents(g);

	}
	 
}

