package userInterface;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import server.Connection;
import server.Profile;
import server.commandToSend;

public class LogForm extends JPanel
    {
	private static final long serialVersionUID = 1L;
		int x=6;
		int y=6;
		
		boolean drawed = false;
		boolean drawRect = false;
		
    	private BufferedImage image = ImageIO.read(new File("Images & Sounds\\Images\\blurred.png"));
    	
    	private JLabel topLeftText = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\logForm.png"))));
    	private JLabel JUserName= new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\username.png")).getScaledInstance(122, 21, Image.SCALE_DEFAULT)));
    	private JLabel JPassword= new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\password.png")).getScaledInstance(122, 21, Image.SCALE_DEFAULT)));
    	private JLabel JLogin= new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\loginRect.png"))));
    	private JLabel errorMsg = new JLabel();
    	
    	public JTextField JUserText = new JTextField();
    	public JPasswordField JPassText = new JPasswordField();
    	public static Container container;
    	
    	public LogForm() throws IOException {
    
    		setPreferredSize(new Dimension(LogRegScreen.width , LogRegScreen.height));
    		setLayout(null);
    				
    		errorMsg.setForeground(Color.RED);         
    		topLeftText.setBounds(660, 300, 224, 47);
    		JUserName.setBounds(topLeftText.getBounds().x-30, topLeftText.getBounds().y+60, 224, 47);
    		JUserText.setBounds(JUserName.getBounds().x+180, JUserName.getBounds().y+10, 310, 30);
    		JPassword.setBounds(topLeftText.getBounds().x-30, topLeftText.getBounds().y+100, 224, 47);
    		JPassText.setBounds(JPassword.getBounds().x+180, JPassword.getBounds().y+10, 310, 30);
    		JLogin.setBounds(780, 650, 224, 47);
    		errorMsg.setBounds(JPassText.getBounds().x, JPassText.getBounds().y + 40, 310, 30);
    		JUserText.setBorder(null);
    		JPassText.setBorder(null);
    		
    		JPassText.getPassword();
    		
    	    add(topLeftText);
    	    add(JUserName);
    	    add(JPassword);
    	    add(JLogin);
    	    add(JUserText);
    	    add(JPassText);
    	    add(errorMsg);
    	    
    	    setFocusable(true);
    	    grabFocus();
    	    
    	    
    	   
    	    JLogin.addMouseListener(new MouseAdapter() {
    	    	@Override
    	    	public void mouseClicked(MouseEvent e)
    	    	{
    	    		login();
    	    				
    	    		super.mouseClicked(e);
    	    	}
			});
    	}
    	

		@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
		public void login()
    	{
			if(JUserText.getText().equals("") || JPassText.getText().equals(""))
    			errorMsg.setText("Username or password field are empty");
    		else{
    			
    			String st = null;
    			Profile profile = new Profile();
    			
    			profile.username = JUserText.getText();
    			profile.password = JPassText.getText();
    			
    			commandToSend command = new commandToSend();
    			command.commandType = "AccountLogin";
    			command.commandValue = profile;
    			try {
    				Connection.output.writeObject(command);
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    			try {
    				st = (String)Connection.input.readObject();
    			} catch (ClassNotFoundException | IOException e1) {
    				e1.printStackTrace();
    			}
    			if(st.equals("LoggedIn successfully"))
    			{
    				Screen.currentPanel="startForm";
    				Screen.cards.show(container, "startForm");

    			}		
    			else if(st.equals("Wrong username or password"))
    				errorMsg.setText(st);
    			
    		}
    	}
    	    
        public void paintComponent(Graphics g)
        {   
        	super.paintComponents(g);
        	g.setColor(Color.BLACK);
        	g.drawImage(image, 0, 0, LogRegScreen.width , LogRegScreen.height, topLeftText); 
       		g.fillRoundRect(650, 250, 500, 500, 40, 30);
        }

    

		}

  
       