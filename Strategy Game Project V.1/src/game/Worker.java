package game;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import userInterface.UserCity;

public class Worker implements Runnable{
	
	String folder = UserCity.folder;

	public static int posX = 1100;
	public static int posY =  755;
	
	public static int nPosX = 800;
	public static int nPosY =  450;
	
	static public boolean isMoving = true;
	static public boolean done = false;
	
	AudioInputStream audioInputStream ;
	Clip clip; 
	
	@Override
	public void run() {	
		
		while(true)
		{
			try {
	        Thread.sleep(1); 
	    } catch (InterruptedException ignore) {
	    }
			
			posX = UserCity.getWorker().getX();
			posY = UserCity.getWorker().getY(); 
			
			if(isMoving)
			{
				while(!done)
				{
					if( posY != 580)
					  posY--;

					
					if( posX != 930)
						  posX--;
					
					UserCity.getWorker().setBounds(posX, posY, 175, 225);
					
					
					if( posY == 580)
					{
						done = true;
					}
					
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}			
				}
				
				if(nPosY == posY && nPosX == posX)
				{
					
					isMoving = false;
					done = false;
					UserCity.workerBuildingLabel.setEnabled(true);
					
					UserCity.getWorker().setVisible(false);
					
					switch(UserCity.buildingName)
					{
						case "Barracks":
							UserCity.target.setIcon(UserCity.barraksIcon);
							
							UserCity.logs.setText("Barracks Built");
							try {
								audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/baracksBuilt.wav").getAbsoluteFile());
								clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
							} catch (Exception e1) {	
								e1.printStackTrace();
							}		
							break;
							
							
						case "Storage":
							UserCity.target.setIcon(UserCity.storageIcon);
							UserCity.logs.setText("Storage Built");
							ResourcesCollector.isCollecting = true;
							break;
							
							
						case "Defence":
							UserCity.target.setIcon(UserCity.defenceIcon);
							UserCity.logs.setText("Defence Tower Built");
							break;
							
						case "stable":
							UserCity.target.setIcon(UserCity.stableIcon);
							try {
								audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/stable.wav").getAbsoluteFile());
								clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
							 } catch (Exception e1) {
								e1.printStackTrace();
							 }
							UserCity.logs.setText("Stable Built");
							break;
							
						case "Gold Mine":
							UserCity.target.setIcon(UserCity.goldMineIcon);
							try {
								 audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/mine.wav").getAbsoluteFile());
								 clip = AudioSystem.getClip();
								 clip.open(audioInputStream);
								 clip.start();
							   } catch (Exception e1) {
								 e1.printStackTrace();
								}
							ResourcesCollector mine = new ResourcesCollector();	
							
							if(ResourcesCollector.ResourcesList.size() == 0)
								mine.start();
							
							ResourcesCollector.ResourcesList.add(mine);	
							
							if(ResourcesCollector.ResourcesList.size() == 0)			
								mine.start();
							
							UserCity.logs.setText("Mine Built");
							break;
							
						case "Blacksmith":
							UserCity.target.setIcon(UserCity.blacksmithIcon);
							try {
								audioInputStream = AudioSystem.getAudioInputStream(new File("Images & Sounds/Sounds/smith.wav").getAbsoluteFile());
								clip = AudioSystem.getClip();
								clip.open(audioInputStream);
								clip.start();
							} catch (Exception e1) {
								e1.printStackTrace();
							}						
							UserCity.logs.setText("Blacksmith Built");
							break;
					}
					
					UserCity.target.setVisible(true);
					UserCity.buildingName = "";
									
				}
					
				
				if(nPosY > posY && nPosX > posX)
				{
					posX++;
					
				} else {
					
					 if(nPosX < posX )
						 posX--;
					 else if (nPosX > posX)
						posX++;
					
					 if(nPosY < posY )
						   posY--;
					 else if(nPosY > posY )
							posY++;
				}
		
				UserCity.getWorker().setBounds(posX, posY, 175, 225);
			
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				
			}
			
		}
		
	}

}
