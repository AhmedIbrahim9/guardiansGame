package userInterface;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Credits extends JFrame{
	private static final long serialVersionUID = -3266787983930175572L;
	
	 ImageIcon icon = new ImageIcon(new ImageIcon("Images & Sounds\\credits.jpg").getImage().getScaledInstance(LogRegScreen.width, LogRegScreen.height, Image.SCALE_DEFAULT));
	 
	 JLabel label = new JLabel(icon);
	 
	 	Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image imageC     = toolkit.getImage("Images & Sounds/mouse/glove.png");
		Cursor cr        = toolkit.createCustomCursor(imageC , new Point(this.getX(), this.getY()), "img");
	 
	 public Credits() throws IOException
	 {
		
		 setBounds(0 , 0 , LogRegScreen.width,LogRegScreen.height);
		 setExtendedState(JFrame.MAXIMIZED_BOTH); 
		 setUndecorated(true);
		 setVisible(true);
		 setLayout(null);
		 
		 label.setBounds(0 , 0 ,LogRegScreen.width, LogRegScreen.height);
		 add(label);
		 
		 this.addMouseListener(new MouseAdapter(){
			 
			 @Override
			 public void mouseEntered(MouseEvent e){
				 
				setCursor(cr);	
			 }
		 });
		 
		 this.addKeyListener(new KeyAdapter(){
			 
			 @Override
			 public void keyPressed(KeyEvent e) {
				 
				 if(e.getKeyCode() == KeyEvent.VK_ENTER)
				    System.exit(0);
			 }
		 });

	 }

}
