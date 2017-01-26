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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import server.Connection;
import server.Profile;
import server.commandToSend;

public class RegForm extends JPanel
    {
	private static final long serialVersionUID = 1L;
	
    	private BufferedImage image = ImageIO.read(new File("Images & Sounds\\Images\\blurred.png"));
    	
    	private JLabel topLeftText = new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\regForm.png"))));
    	private JLabel JUserName= new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\username.png")).getScaledInstance(122, 35, Image.SCALE_DEFAULT)));
    	private JLabel JConfirmPassword=new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\confirmpassword.png")).getScaledInstance(122, 35, Image.SCALE_DEFAULT))); 
    	private JLabel JPassword= new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\password.png")).getScaledInstance(122, 35, Image.SCALE_DEFAULT)));
    	private JLabel JRegister= new JLabel(new ImageIcon(ImageIO.read(new File("Images & Sounds\\Images\\regRect.png"))));
    	
    	private JLabel JEightChar=new JLabel("*Your Password Must Be at Least 4 Characters");
    	private JLabel JPutAgain=new JLabel("*Put your Password Again to Confirm your Password");
    
    	public JTextField JUserText=new JTextField();
    	public JPasswordField JPassText=new JPasswordField();
    	private JPasswordField JConfirmPassText=new JPasswordField();
    	
    	public static Container container;

    	
    	public RegForm() throws IOException 
    	{
    		Screen.currentPanel="startForm";
    		
    		
    		setPreferredSize(new Dimension(LogRegScreen.width , LogRegScreen.height));
    		setLayout(null);
    
    		
    		topLeftText.setBounds(660, 300, 224, 47);
    		JUserName.setBounds(topLeftText.getBounds().x-30, topLeftText.getBounds().y+60, 224, 35);
    		JUserText.setBounds(JUserName.getBounds().x+180, JUserName.getBounds().y+10, 310, 30);
    		JPassword.setBounds(topLeftText.getBounds().x-30, topLeftText.getBounds().y+120, 224, 35);
    		JPassText.setBounds(JPassword.getBounds().x+180, JPassword.getBounds().y+10, 310, 30);
    		JConfirmPassword.setBounds(topLeftText.getBounds().x-30, topLeftText.getBounds().y+180, 224, 35);
    		JConfirmPassText.setBounds(JConfirmPassword.getBounds().x+180,JConfirmPassword.getBounds().y+10,310,30);
    		JEightChar.setBounds(JPassText.getBounds().x, JPassword.getBounds().y+35,310,30);
    		JPutAgain.setBounds(JConfirmPassText.getBounds().x,JConfirmPassword.getBounds().y+35,310,30);
    		
    		JRegister.setBounds(780, 650, 224, 47);
    		JUserText.setBorder(null);
    		JPassText.setBorder(null);
    		JConfirmPassText.setBorder(null);
    		
    		JConfirmPassText.getPassword();
    		JPassText.getPassword();
    		
    		JEightChar.setForeground(Color.WHITE);
    		JPutAgain.setForeground(Color.WHITE);
    		
    	    add(topLeftText);
    	    add(JConfirmPassword);
    	    add(JUserName);
    	    add(JPassword);
    	    add(JRegister);
    	    add(JUserText);
    	    add(JPassText);
    	    add(JConfirmPassText);
    	    add(JEightChar);
    	    add(JPutAgain);
    	    
    	    JRegister.addMouseListener(new MouseAdapter() {
    	    	@Override
    	    	public void mouseClicked(MouseEvent e){
    	    		
    	    			reg();
    	    		super.mouseClicked(e);
    	    	}
			});
    	    
    	}
    	
    	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
		public void reg()
    	{
    		if(JUserText.getText().equals("") || JPassText.getText().equals(""))
    			JOptionPane.showMessageDialog(null, "Username or password field are empty", "Login Error",
    				    JOptionPane.ERROR_MESSAGE);
    		else{
    			
    			String st = null;
    			Profile profile = new Profile();
    			profile.username = JUserText.getText();
    			profile.password = JPassText.getText();
    			commandToSend command = new commandToSend();
    			command.commandType = "AccountRegister";
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
    			if(st.equals("Registerd successfully"))
    			{
    				Screen.currentPanel="startForm";
    				Screen.cards.show(container, "startForm");
    			
    			}			
    			else if(st.equals("duplicated username"))
    				System.out.println("duplicated username");
    			else
    				System.out.println(st);
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