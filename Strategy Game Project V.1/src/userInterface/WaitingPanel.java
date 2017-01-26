package userInterface;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class WaitingPanel extends JPanel {
	private static final long serialVersionUID = -6532309641557056827L;

	private BufferedImage image ; 
	
	public static Container container;
	
	public WaitingPanel() throws IOException
	{

		setVisible(true);
		
		setPreferredSize(new Dimension(LogRegScreen.width , LogRegScreen.height));
		
		image = ImageIO.read(new File("Images & Sounds\\waiting.png"));
		repaint();
		
	}
	
	
	public void paintComponent(Graphics g)
    {     	
    	super.paintComponents(g);
    	g.drawImage(image, 0, 0,LogRegScreen.width , LogRegScreen.height, this);    
    }
	
}
