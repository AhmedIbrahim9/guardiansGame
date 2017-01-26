package game;

import userInterface.UserCity;

public class ActionHandler implements Runnable {

	@Override
	public void run() {
		while(true){	
			 try {
			        Thread.sleep(1); 
			    } catch (InterruptedException ignore) {
			    }
			 
			if((UserCity.q.peek() != null))
			{
				 switch (UserCity.q.peek()){	
					case"worrior":	
						for(int i=1 ; i< UserCity.w +1; i++)
						{
							try {
								Thread.sleep(1000);
								} catch (InterruptedException e) {
							
									e.printStackTrace();
								}
							
							
							UserCity.soldierCharLabel.setText(String.valueOf(UserCity.w - i));
							
							UserCity.logs.setText(Barracks.DeployWarrior());
							
							UserCity.updateSoldierCounter();	
						}
						
					UserCity.w = 0;
					
					UserCity.soldierCharLabel.setText(String.valueOf(""));
					
					UserCity.q.remove();
					
					break;
					
					case"archer":
						
						for(int i=1 ; i< UserCity.a+1 ; i++)
						{
							try {
								Thread.sleep(2000);
								} catch (InterruptedException e) {
							
									e.printStackTrace();
								}
							
							UserCity.archerCharLabel.setText(String.valueOf(UserCity.a - i));
							
							UserCity.logs.setText(Barracks.DeployArcher());
							
							UserCity.updateArcherCounter();
						
						}
						
					UserCity.a = 0;
					
					UserCity.archerCharLabel.setText(String.valueOf(""));
					
					UserCity.q.remove();
					break;
					
					case"spearman":
						
						for(int i=1 ; i< UserCity.s +1; i++)
						{
							try {
								Thread.sleep(3000);
								} catch (InterruptedException e) {
							
									e.printStackTrace();
								}
							
							UserCity.spearmanCharLabel.setText(String.valueOf(UserCity.s - i));
							
							UserCity.logs.setText(Barracks.DeploySpearman());
							
							UserCity.updateSpearmanCounter();
						
						}
						
					UserCity.s = 0;
					
					UserCity.spearmanCharLabel.setText(String.valueOf(""));
					
					UserCity.q.remove();
					
					break;
					
					case"knight":
						
						for(int i=1 ; i< UserCity.k+1 ; i++)
						{
							try {
								Thread.sleep(4000);
								} catch (InterruptedException e) {
							
									e.printStackTrace();
								}
							
							UserCity.knightCharLabel.setText(String.valueOf(UserCity.k - i));
							
							UserCity.logs.setText(Barracks.DeployKnight());
							
							UserCity.updateKnightCounter();
						
						}
						
					UserCity.k = 0;
					
					UserCity.knightCharLabel.setText(String.valueOf(""));
					
					UserCity.q.remove();
					
					break;
					
					case"chariot":
						
						for(int i=1 ; i< UserCity.ch+1 ; i++)
						{
							try {
								Thread.sleep(5000);
								} catch (InterruptedException e) {
							
									e.printStackTrace();
								}
							
							UserCity.warChariotLabel.setText(String.valueOf(UserCity.ch - i));
							
							UserCity.logs.setText(Barracks.DeployChariot());
							
							UserCity.updateChariotCounter();
						
						}
						
					UserCity.ch = 0;
					
					UserCity.warChariotLabel.setText(String.valueOf(""));
					
					UserCity.q.remove();
					
					break;
					
				}
			}
		     
			
		}
		
	}

}
