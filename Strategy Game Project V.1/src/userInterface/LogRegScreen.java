package userInterface;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
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

public class LogRegScreen extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private BufferedImage image = ImageIO.read(new File("Images & Sounds\\Images\\startBackground.jpg"));
	
	public JLabel choiceSelector = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\choice.png"))));
	JLabel choice1 = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\login.png"))));
	JLabel choice2 = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\register.png"))));
	JLabel choice3 = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\exit.png"))));
		
	AudioInputStream audio = AudioSystem.getAudioInputStream(new File("Images & Sounds\\Sounds\\click.wav"));;
	Clip clip = AudioSystem.getClip();
	
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int width = (int) screenSize.getWidth();
	public static int height = (int) screenSize.getHeight();
		

	public LogRegScreen() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		
		setPreferredSize(new Dimension(width , height));	
		setLayout(null);
		
		clip.open(audio);
			
		choice1.setBounds(100, 300, 224, 47);
		choice2.setBounds(100, 360, 224, 47);
		choice3.setBounds(100, 420, 224, 47);
		choiceSelector.setBounds(20, 280, 83, 83);
		
		choice1.setVisible(true);
		choice2.setVisible(true);
		choice3.setVisible(true);
		choiceSelector.setVisible(true);
		
		add(choice1);
		add(choice2);
		add(choice3);
		add(choiceSelector);
		
		repaint();			
		
	}
	
	 public void paintComponent(Graphics g)
     {     	
     	super.paintComponents(g);
     	g.drawImage(image, 0, 0, width,height, choiceSelector);    
     }
}