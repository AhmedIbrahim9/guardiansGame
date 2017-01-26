package userInterface;


import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Defeat  extends JPanel{

	private static final long serialVersionUID = -7860734592519316923L;
	private int x=245;
	@SuppressWarnings("unused")
	private int	y=550;
	
	private int youSoldierKills=0;   int sk = 0;
    private int youSoldierLosses=0;  int sl = 0;
    private int youKnightKills=0;	 int kk = 0;
    private int youKnightLosses=0;   int kl = 0;
    private int youChariotKills=0;   int ck = 0;
    private int youChariotLosses=0;  int cl = 0;
    private int youSpearManKills=0;  int smk = 0;
    private int youSpearManLosses=0; int sml = 0;
    private int youArcherKills=0;    int ak = 0;
    private int youArcherLosses=0;   int al = 0;
    
    private BufferedImage imageBG;
    JLabel JEndGame=new JLabel();
	
	public Defeat(int a ,int b ,int c ,int d,int e,int f ,int g ,int h ,int i ,int j )throws IOException, UnsupportedAudioFileException, LineUnavailableException
	{
		youSoldierKills   = a * 12;
	    youSoldierLosses  = b * 12;
	    youKnightKills    = c * 12;
	    youKnightLosses	  = d * 12;
	    youChariotKills   = e * 12;
	    youChariotLosses  = f * 12;
	    youSpearManKills  = g * 12;
	    youSpearManLosses = h * 12;
	    youArcherKills    = i * 12;
	    youArcherLosses   = j * 12;
		 
		imageBG = ImageIO.read(new File("Images & Sounds\\defeat.jpg"));

		setBounds(0 , 0 , LogRegScreen.width,LogRegScreen.height);
		setVisible(true);
		setLayout(null);
            
		
		Draw();
		repaint();
		
		JEndGame.setBounds(LogRegScreen.width/2-200,LogRegScreen.height-125,380,95);
		add(JEndGame);
		
		JEndGame.addMouseListener(new MouseAdapter(){
			
		@Override
		public void mousePressed(MouseEvent e)
		{
			try {
				WarMap.playClick();
			} catch (Exception e1) {
				e1.printStackTrace();
			} 
			
			try {
				Credits credit = new Credits();
				WarMap.getWar().dispose();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		 
	 });
	}
	
	public void Draw(){
		 
		 
		 new Thread(new  Runnable() {
			public void run() {
				 for(int i=0;i<youSoldierKills;i++)
				 {
					 sk++;
					 
			        	repaint();
			        	try {
							Thread.sleep(10);
							
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				 }
			}
		}).start();
		 		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youSoldierLosses;i++)
					 {
						 sl++;
				       
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 		 
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youKnightKills;i++)
					 {
						 kk++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 
		 
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youKnightLosses;i++)
					 {
						 kl++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 		 
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youChariotKills;i++)
					 {
						 ck++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youChariotLosses;i++)
					 {
						 cl++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 
		 		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youSpearManKills;i++)
					 {
						 smk++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youSpearManLosses;i++)
					 {
						 sml++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 	
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youArcherKills;i++)
					 {
						 ak++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
		 
		 new Thread(new  Runnable() {
				public void run() {
					 for(int i=0;i<youArcherLosses;i++)
					 {
						 al++;
				        
				        	repaint();
				        	try {
								Thread.sleep(10);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					 }
				}
			}).start();
			
	 }
	    public void paintComponent(Graphics g) {
	    	super.paintComponents(g);
	    	g.drawImage(imageBG, 0, 0, LogRegScreen.width , LogRegScreen.height ,this);
	    	
	    	
	
	
	        g.setColor(Color.GREEN);
	        
	        g.fillRect(x, 140 ,sk,30);
	        g.fillRect(x, 320,kk,30);
	        g.fillRect(x, 490,ck,30);
	        g.fillRect(x, 660,smk,30);
	        g.fillRect(x, 830,ak,30);
	        
	       
	        
	        g.setColor(Color.RED);
	        
	        g.fillRect(x, 170,sl,30);
	        g.fillRect(x, 350,kl,30);
	        g.fillRect(x, 520,cl,30);
	        g.fillRect(x, 690,sml,30);
	        g.fillRect(x, 860,al,30);
	        
	        
	      
	       
	    }
	    
}
