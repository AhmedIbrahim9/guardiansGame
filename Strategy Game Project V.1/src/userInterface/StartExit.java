package userInterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartExit extends JPanel  {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = ImageIO.read(new File("Images & Sounds\\Images\\startBackground.jpg"));
	
	JLabel choice1 = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\start.png"))));
	JLabel choice2 = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\exit.png"))));
	
	public JLabel choiceSelector = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\choice.png"))));

	int choice=1;
	public static Container container;
	
	public StartExit() throws IOException, UnsupportedAudioFileException, LineUnavailableException 
	{
		setPreferredSize(new Dimension(LogRegScreen.width , LogRegScreen.height));
		setLayout(null);
		
		choice1.setBounds(100, 300, 224, 47);
		choice2.setBounds(100, 360, 224, 47);
		choiceSelector.setBounds(20, 280, 83, 83);
		choice1.setVisible(true);
		choice2.setVisible(true);
		choiceSelector.setVisible(true);
		add(choice1);
		add(choice2);
		add(choiceSelector);
		repaint();	
		Screen.currentPanel="startForm";
	}
	
	 public void paintComponent(Graphics g)
     {     	
     	super.paintComponents(g);
     	g.drawImage(image, 0, 0,LogRegScreen.width , LogRegScreen.height, choiceSelector);    
     }

}
	

