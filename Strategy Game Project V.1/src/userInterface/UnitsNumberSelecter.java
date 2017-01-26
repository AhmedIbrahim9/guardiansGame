package userInterface;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UnitsNumberSelecter extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	public String type;
	public int number;
	
	private ImageIcon JFramI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\frame.png").getImage().getScaledInstance(534, 465, Image.SCALE_DEFAULT));
	private ImageIcon JBPluseI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\buttonPlus.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
	private ImageIcon JBMinusI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\buttonNegative.png").getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT));
	private ImageIcon JSoldiorI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\solider.png").getImage().getScaledInstance(180, 35, Image.SCALE_DEFAULT));
	private ImageIcon JArcherI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\archer.png").getImage().getScaledInstance(180, 35, Image.SCALE_DEFAULT));
	private ImageIcon JSpearI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\spearman.png").getImage().getScaledInstance(180, 35, Image.SCALE_DEFAULT));
	private ImageIcon JKnightI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\knight.png").getImage().getScaledInstance(180, 35, Image.SCALE_DEFAULT));
	private ImageIcon JChariotI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\chariot.png").getImage().getScaledInstance(180, 35, Image.SCALE_DEFAULT));
	private static ImageIcon JConfimI=new ImageIcon(new ImageIcon("Images & Sounds\\units select\\confirm.png").getImage().getScaledInstance(236, 75, Image.SCALE_DEFAULT));

	private JLabel JFrame= new JLabel(JFramI);
	private JLabel JBPluse1 = new JLabel(JBPluseI);
	private JLabel JBPluse2= new JLabel(JBPluseI);
	private JLabel JBPluse3 = new JLabel(JBPluseI);
	private JLabel JBPluse4 = new JLabel(JBPluseI);
	private JLabel JBPluse5 = new JLabel(JBPluseI);
	private JLabel JBMinus1 = new JLabel(JBMinusI);
	private JLabel JBMinus2= new JLabel(JBMinusI);
	private JLabel JBMinus3 = new JLabel(JBMinusI);
	private JLabel JBMinus4 = new JLabel(JBMinusI);
	private JLabel JBMinus5 = new JLabel(JBMinusI);
	private JLabel JSoldier = new JLabel(JSoldiorI);
	private JLabel JArcher= new JLabel(JArcherI);
	private JLabel JSpearman = new JLabel(JSpearI);
	private JLabel JKnight = new JLabel(JKnightI);
	private JLabel JChariot = new JLabel(JChariotI);
	private JLabel JConfim = new JLabel(JConfimI);
	private int SoldierNumber=0;
	private int ArhcerNumber=0;
	private int SpearmanNumber=0;
	private int KnightNumber=0;
	private int ChariotNumber=0;
	private JTextField JTextS= new JTextField(Integer.toString(SoldierNumber));
	private JTextField JTextA= new JTextField(Integer.toString(ArhcerNumber));
	private JTextField JTextSP= new JTextField(Integer.toString(SpearmanNumber));
	private JTextField JTextK= new JTextField(Integer.toString(KnightNumber));
	private JTextField JTextC= new JTextField(Integer.toString(ChariotNumber));
	
	AudioInputStream audioInputStream ;
	Clip clip; 
	
	UnitsNumberSelecter() throws IOException {
	
		setVisible(false);
		setLayout(null);
		JFrame.setBounds(0,0,534,465);
		add(JFrame);
		JSoldier.setBounds(10,10,224,74);
		JArcher.setBounds(JSoldier.getBounds().x,JSoldier.getBounds().y+70,224,74);
		JSpearman.setBounds(JSoldier.getBounds().x,JSoldier.getBounds().y+140,224,74);
		JKnight.setBounds(JSoldier.getBounds().x,JSoldier.getBounds().y+210,224,74);
		JChariot.setBounds(JSoldier.getBounds().x,JSoldier.getBounds().y+280,224,74);
		JBPluse1.setBounds(JSoldier.getBounds().x+220,JSoldier.getBounds().y+10,60,60);
		JBPluse2.setBounds(JArcher.getBounds().x+220,JArcher.getBounds().y+10,60,60);
		JBPluse3.setBounds(JSpearman.getBounds().x+220,JSpearman.getBounds().y+10,60,60);
		JBPluse4.setBounds(JKnight.getBounds().x+220,JKnight.getBounds().y+10,60,60);
		JBPluse5.setBounds(JChariot.getBounds().x+220,JChariot.getBounds().y+10,60,60);
		JBMinus1.setBounds(JSoldier.getBounds().x+280,JSoldier.getBounds().y+10,60,60);
		JBMinus2.setBounds(JArcher.getBounds().x+280,JArcher.getBounds().y+10,60,60);
		JBMinus3.setBounds(JSpearman.getBounds().x+280,JSpearman.getBounds().y+10,60,60);
		JBMinus4.setBounds(JKnight.getBounds().x+280,JKnight.getBounds().y+10,60,60);
		JBMinus5.setBounds(JChariot.getBounds().x+280,JChariot.getBounds().y+10,60,60);
		JTextS.setBounds(JBMinus1.getBounds().x+140,JSoldier.getBounds().y+13,45,45);
		JTextA.setBounds(JBMinus1.getBounds().x+140,JArcher.getBounds().y+13,45,45);
		JTextSP.setBounds(JBMinus1.getBounds().x+140,JSpearman.getBounds().y+13,45,45);
		JTextK.setBounds(JBMinus1.getBounds().x+140,JKnight.getBounds().y+13,45,45);
		JTextC.setBounds(JBMinus1.getBounds().x+140,JChariot.getBounds().y+13,45,45);
		getJConfim().setBounds(JTextC.getBounds().x-275, JTextC.getBounds().y+65, 237, 75);
		
		JTextS.setBorder(null);
		JTextS.setEditable(false);
		JTextS.setHorizontalAlignment(SwingConstants.CENTER);
		JTextS.setFont(new Font("SansSerif", Font.BOLD, 20));
		JTextS.setOpaque(false);
		JTextS.setForeground(Color.WHITE);
		JTextA.setBorder(null);
		JTextA.setEditable(false);
		JTextA.setHorizontalAlignment(SwingConstants.CENTER);
		JTextA.setFont(new Font("SansSerif", Font.BOLD, 20));
		JTextA.setOpaque(false);
		JTextA.setForeground(Color.WHITE);
		JTextSP.setBorder(null);
		JTextSP.setEditable(false);
		JTextSP.setHorizontalAlignment(SwingConstants.CENTER);
		JTextSP.setFont(new Font("SansSerif", Font.BOLD, 20));
		JTextSP.setOpaque(false);
		JTextSP.setForeground(Color.WHITE);
		JTextK.setBorder(null);
		JTextK.setEditable(false);
		JTextK.setHorizontalAlignment(SwingConstants.CENTER);
		JTextK.setFont(new Font("SansSerif", Font.BOLD, 20));
		JTextK.setOpaque(false);
		JTextK.setForeground(Color.WHITE);
		JTextC.setBorder(null);
		JTextC.setEditable(false);
		JTextC.setHorizontalAlignment(SwingConstants.CENTER);
		JTextC.setFont(new Font("SansSerif", Font.BOLD, 20));
		JTextC.setOpaque(false);
		JTextC.setForeground(Color.WHITE);
		
	    JFrame.setVisible(true);
	    JBPluse1.setVisible(true);
	    JBPluse2.setVisible(true);
	    JBPluse3.setVisible(true);
	    JBPluse4.setVisible(true);
	    JBPluse5.setVisible(true);
	    JBMinus1.setVisible(true);
	    JBMinus2.setVisible(true);
	    JBMinus3.setVisible(true);
	    JBMinus4.setVisible(true);
	    JBMinus5.setVisible(true);
	    JSoldier.setVisible(true);
	    JArcher.setVisible(true);
	    JSpearman.setVisible(true);
	    JKnight.setVisible(true);
	    JChariot.setVisible(true);
	    JTextS.setVisible(true);
	    JTextA.setVisible(true);
	    JTextSP.setVisible(true);
	    JTextK.setVisible(true);
	    JTextC.setVisible(true);
	    getJConfim().setVisible(true);
	 

		JFrame.add(JBPluse1);
		JFrame.add(JBPluse2);
		JFrame.add(JBPluse3);
		JFrame.add(JBPluse4);
		JFrame.add(JBPluse5);
		JFrame.add(JBMinus1);
		JFrame.add(JBMinus2);
		JFrame.add(JBMinus3);
		JFrame.add(JBMinus4);
		JFrame.add(JBMinus5);
		JFrame.add(JBMinus5);
		JFrame.add(JSoldier);
		JFrame.add(JArcher);
		JFrame.add(JSpearman);
		JFrame.add(JKnight);
		JFrame.add(JChariot);
		JFrame.add(JTextS);
		JFrame.add(JTextA);
		JFrame.add(JTextSP);
		JFrame.add(JTextK);
		JFrame.add(JTextC);
		JFrame.add(getJConfim());
		
	
		
		
		JBPluse1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(WarMap.SoldierNumber == 0)
					return;
				if(JBPluse1.isEnabled())
				{
					SoldierNumber++;
					WarMap.SoldierNumber--;
					WarMap.soldierCount.setText(String.valueOf(WarMap.SoldierNumber));
					JTextS.setText(String.valueOf(SoldierNumber));
					JBPluse2.setEnabled(false);
					JBPluse3.setEnabled(false);
					JBPluse4.setEnabled(false);
					JBPluse5.setEnabled(false);
					JBMinus2.setEnabled(false);
					JBMinus3.setEnabled(false);
					JBMinus4.setEnabled(false);
					JBMinus5.setEnabled(false);
					
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				}
				
				
				super.mouseClicked(e);
			}
		});
		
		JBMinus1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(JBMinus1.isEnabled())
				{
					if(SoldierNumber == 0)
						return;
					if(SoldierNumber == 1){
						JBPluse2.setEnabled(true);
						JBPluse3.setEnabled(true);
						JBPluse4.setEnabled(true);
						JBPluse5.setEnabled(true);
						JBMinus2.setEnabled(true);
						JBMinus3.setEnabled(true);
						JBMinus4.setEnabled(true);
						JBMinus5.setEnabled(true);
					}
					
					SoldierNumber--;
					WarMap.SoldierNumber++;
					WarMap.soldierCount.setText(String.valueOf(WarMap.SoldierNumber));
					JTextS.setText(String.valueOf(SoldierNumber));
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				}
				
				
				super.mouseClicked(e);
			}
		});
		
		JBPluse2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(WarMap.ArcherNumber == 0)
					return;
				if(JBPluse2.isEnabled())
				{
					ArhcerNumber++;
					WarMap.ArcherNumber--;
					WarMap.archerCount.setText(String.valueOf(WarMap.ArcherNumber));
					JTextA.setText(String.valueOf(ArhcerNumber));
					JBPluse1.setEnabled(false);
					JBPluse3.setEnabled(false);
					JBPluse4.setEnabled(false);
					JBPluse5.setEnabled(false);
					JBMinus1.setEnabled(false);
					JBMinus3.setEnabled(false);
					JBMinus4.setEnabled(false);
					JBMinus5.setEnabled(false);
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
					
				}
				super.mouseClicked(e);
				
			}
		});
		
		JBMinus2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(JBMinus2.isEnabled())
				{
					if(ArhcerNumber == 0)
						return;
					if(ArhcerNumber == 1){
						JBPluse1.setEnabled(true);
						JBPluse3.setEnabled(true);
						JBPluse4.setEnabled(true);
						JBPluse5.setEnabled(true);
						JBMinus1.setEnabled(true);
						JBMinus3.setEnabled(true);
						JBMinus4.setEnabled(true);
						JBMinus5.setEnabled(true);
					
					}
					ArhcerNumber--;
					WarMap.ArcherNumber++;
					WarMap.archerCount.setText(String.valueOf(WarMap.ArcherNumber));

					JTextA.setText(String.valueOf(ArhcerNumber));
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				}
				
				
				super.mouseClicked(e);
			}
		});
		
		JBPluse3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(WarMap.SpearmanNumber == 0)
					return;
				if(JBPluse3.isEnabled())
				{
					SpearmanNumber++;
					WarMap.SpearmanNumber--;
					WarMap.spearmanCount.setText(String.valueOf(WarMap.SpearmanNumber));

					JTextSP.setText(String.valueOf(SpearmanNumber));
					JBPluse2.setEnabled(false);
					JBPluse1.setEnabled(false);
					JBPluse4.setEnabled(false);
					JBPluse5.setEnabled(false);
					JBMinus2.setEnabled(false);
					JBMinus1.setEnabled(false);
					JBMinus4.setEnabled(false);
					JBMinus5.setEnabled(false);
					
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
					
				}
				super.mouseClicked(e);
			}
		});
		
		JBMinus3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(JBMinus3.isEnabled())
				{				
					if(SpearmanNumber == 0)
						return;
					if(SpearmanNumber == 1){
						JBPluse2.setEnabled(true);
						JBPluse1.setEnabled(true);
						JBPluse4.setEnabled(true);
						JBPluse5.setEnabled(true);
						JBMinus2.setEnabled(true);
						JBMinus1.setEnabled(true);
						JBMinus4.setEnabled(true);
						JBMinus5.setEnabled(true);
						
						try {
							playClick();
						} catch (Exception e1) {
						
							e1.printStackTrace();
						}
					}
					SpearmanNumber--;
					WarMap.SpearmanNumber++;
					WarMap.spearmanCount.setText(String.valueOf(WarMap.SpearmanNumber));
					JTextSP.setText(String.valueOf(SpearmanNumber));
				}
				
				
				super.mouseClicked(e);
			}
		});
		
		JBPluse4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(WarMap.KnightNumber == 0)
					return;
				if(JBPluse4.isEnabled())
				{
					
					KnightNumber++;
					WarMap.KnightNumber--;
					WarMap.knightCount.setText(String.valueOf(WarMap.KnightNumber));
					JTextK.setText(String.valueOf(KnightNumber));
					JBPluse2.setEnabled(false);
					JBPluse3.setEnabled(false);
					JBPluse1.setEnabled(false);
					JBPluse5.setEnabled(false);
					JBMinus2.setEnabled(false);
					JBMinus3.setEnabled(false);
					JBMinus1.setEnabled(false);
					JBMinus5.setEnabled(false);
					
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				}
				
				super.mouseClicked(e);
			}
		});
		
		JBMinus4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(JBMinus4.isEnabled())
				{		
					if(KnightNumber == 0)
						return;
					if(KnightNumber == 1){
						JBPluse2.setEnabled(true);
						JBPluse3.setEnabled(true);
						JBPluse1.setEnabled(true);
						JBPluse5.setEnabled(true);
						JBMinus2.setEnabled(true);
						JBMinus3.setEnabled(true);
						JBMinus1.setEnabled(true);
						JBMinus5.setEnabled(true);
						
						try {
							playClick();
						} catch (Exception e1) {
						
							e1.printStackTrace();
						}
					}
					KnightNumber--;
					WarMap.KnightNumber++;
					WarMap.knightCount.setText(String.valueOf(WarMap.KnightNumber));
					JTextK.setText(String.valueOf(KnightNumber));
				}
				
				super.mouseClicked(e);
			}
		});
		
		JBPluse5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(WarMap.ChariotNumber == 0)
					return;
				if(JBPluse5.isEnabled())
				{
					ChariotNumber++;
					WarMap.ChariotNumber--;
					WarMap.chariotCount.setText(String.valueOf(WarMap.ChariotNumber));
					JTextC.setText(String.valueOf(ChariotNumber));
					JBPluse2.setEnabled(false);
					JBPluse3.setEnabled(false);
					JBPluse4.setEnabled(false);
					JBPluse1.setEnabled(false);
					JBMinus2.setEnabled(false);
					JBMinus3.setEnabled(false);
					JBMinus4.setEnabled(false);
					JBMinus1.setEnabled(false);
					
					try {
						playClick();
					} catch (Exception e1) {
					
						e1.printStackTrace();
					}
				}
				
				super.mouseClicked(e);
			}
			
			
		});
		
		JBMinus5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if(JBMinus5.isEnabled())
				{
					if(ChariotNumber == 0)
						return;
					if(ChariotNumber == 1){
						JBPluse2.setEnabled(true);
						JBPluse3.setEnabled(true);
						JBPluse4.setEnabled(true);
						JBPluse1.setEnabled(true);
						JBMinus2.setEnabled(true);
						JBMinus3.setEnabled(true);
						JBMinus4.setEnabled(true);
						JBMinus1.setEnabled(true);
					}
					ChariotNumber--;
					WarMap.ChariotNumber++;
					WarMap.chariotCount.setText(String.valueOf(WarMap.ChariotNumber));
					JTextC.setText(String.valueOf(ChariotNumber));
					
					 
						try {
							playClick();
						} catch (Exception e1) {
						
							e1.printStackTrace();
						}
				
				}
				
				
				super.mouseClicked(e);
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
	
	public JLabel getJConfim() {
		return JConfim;
	}



	public void setJConfim(JLabel jConfim) {
		JConfim = jConfim;
	}
	
	public int getNumber(){
		
		if(JBPluse1.isEnabled())
			return SoldierNumber;
		else if(JBPluse2.isEnabled())
			return ArhcerNumber;
		else if(JBPluse3.isEnabled())
			return SpearmanNumber;
		else if(JBPluse4.isEnabled())
			return KnightNumber;
		else if(JBPluse5.isEnabled())
			return ChariotNumber;
		else
			return 0;
	}
	
	public String getType(){
		if(JBPluse1.isEnabled())
			return "Soldier";
		else if(JBPluse2.isEnabled())
			return "Archer";
		else if(JBPluse3.isEnabled())
			return "Spearman";
		else if(JBPluse4.isEnabled())
			return "Knight";
		else if(JBPluse5.isEnabled())
			return "Chariot";
		else
			return null;
	}
	
}
