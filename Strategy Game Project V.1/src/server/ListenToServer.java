package server;

import java.awt.Container;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JLabel;

import userInterface.Screen;
import userInterface.UserCity;
import userInterface.WarMap;

public class ListenToServer implements Runnable  {

	public static Container container;
	@SuppressWarnings("rawtypes")
	commandToSend command;

	Screen s;
	UserCity c;
	
		
	public ListenToServer() {
	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void run() {
		
		while(true)
		{
			command = new commandToSend();
			
			try {
				command = (commandToSend)Connection.input.readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			
		switch(command.commandType)
		{
			case "StartInGame":
					Screen.cards.show(container, "nationform");	
					break;
					
			case "Attack":  
								  
					  c = UserCity.getCity();	
					  
					  WarMap war;
					try {
						war = new WarMap("Deffender" , c);
						 c.setVisible(false);
						  Thread T = new Thread(war);
						  T.start();
					} catch (Exception e) {
			
						e.printStackTrace();
					} 
					 
					  	
				  	break;
		
			case "Turn":
				
				WarMap.isTurn = true;
				WarMap.Turn.setText("Your Turn");
				WarMap.numOfTurns = 0;
				WarMap.NumOfTurns.setText("Number of Turns : 5");
				WarMap.getWar().repaint();
				
				break;
				
			case "Deploy":
				WarMap.units.addAll((ArrayList<MapUnit>)command.commandValue);
				WarMap.updateMap();
				WarMap.isEnemyDeployed = true;
				if(WarMap.identity == "Attacker")
					WarMap.Turn.setText("Your Turn");
				else
					WarMap.Turn.setText("Enemy's Turn");
				WarMap.getWar().repaint();
				break;
			
			case "UpdateMap" :
				
				String identity = ((MapUnit)command.commandValue).identitiy;

				for(int i=0 ; i<12 ; i++)
					for(int j=0 ; j<12 ; j++)
					{
						
						if(WarMap.map[i][j].getText().equals(((MapUnit)command.commandValue).position))
						{	
							WarMap.map[i][j].setIcon(WarMap.mapIcon);
							WarMap.map[i][j].setVisible(false);
							WarMap.map[i][j].putClientProperty("Identity", "");
							WarMap.map[i][j].putClientProperty("Health", "");
							WarMap.map[i][j].putClientProperty("Damage", "");
							WarMap.map[i][j].putClientProperty("Number", "");
						}
							
						
						if(WarMap.map[i][j].getText().equals((String)(((MapUnit)command.commandValue).additionData)))
						{
							switch(((MapUnit)command.commandValue).type){
							case "Soldier":
								
								if(identity.equals("Attacker"))
									WarMap.map[i][j].setIcon(WarMap.Worrior);
								else
									WarMap.map[i][j].setIcon(WarMap.WorriorD);
								
								WarMap.map[i][j].setText((String)(((MapUnit)command.commandValue).additionData));
								WarMap.map[i][j].putClientProperty("Damage", ((MapUnit)command.commandValue).Damage);
								WarMap.map[i][j].putClientProperty("Health", ((MapUnit)command.commandValue).Health);
								WarMap.map[i][j].putClientProperty("Number", ((MapUnit)command.commandValue).NumberOfUnits);
	
								break;
							case "Archer":
								
								if(identity.equals("Attacker"))
									WarMap.map[i][j].setIcon(WarMap.Archer);
								else
									WarMap.map[i][j].setIcon(WarMap.ArcherD);
						
								WarMap.map[i][j].setText((String)(((MapUnit)command.commandValue).additionData));
								WarMap.map[i][j].putClientProperty("Damage", ((MapUnit)command.commandValue).Damage);
								WarMap.map[i][j].putClientProperty("Health", ((MapUnit)command.commandValue).Health);
								WarMap.map[i][j].putClientProperty("Number", ((MapUnit)command.commandValue).Health);
								break;
							case "Spearman":
								
								if(identity.equals("Attacker"))
									WarMap.map[i][j].setIcon(WarMap.Spear);
								else
									WarMap.map[i][j].setIcon(WarMap.SpearD);
		
								WarMap.map[i][j].setText((String)(((MapUnit)command.commandValue).additionData));
								WarMap.map[i][j].putClientProperty("Damage", ((MapUnit)command.commandValue).Damage);
								WarMap.map[i][j].putClientProperty("Health", ((MapUnit)command.commandValue).Health);
								WarMap.map[i][j].putClientProperty("Number", ((MapUnit)command.commandValue).NumberOfUnits);
								break;
							case "Knight":
								
								if(identity.equals("Attacker"))
									WarMap.map[i][j].setIcon(WarMap.Knight);
								else
									WarMap.map[i][j].setIcon(WarMap.KnightD);
			
								WarMap.map[i][j].setText((String)(((MapUnit)command.commandValue).additionData));
								WarMap.map[i][j].putClientProperty("Damage", ((MapUnit)command.commandValue).Damage);
								WarMap.map[i][j].putClientProperty("Health", ((MapUnit)command.commandValue).Health);
								WarMap.map[i][j].putClientProperty("Number", ((MapUnit)command.commandValue).NumberOfUnits);
								break;
							case "Chariot":
								
								if(identity.equals("Attacker"))
									WarMap.map[i][j].setIcon(WarMap.Chariot);
								else
									WarMap.map[i][j].setIcon(WarMap.ChariotD);
					
								WarMap.map[i][j].setText((String)(((MapUnit)command.commandValue).additionData));
								WarMap.map[i][j].putClientProperty("Damage", ((MapUnit)command.commandValue).Damage);
								WarMap.map[i][j].putClientProperty("Health", ((MapUnit)command.commandValue).Health);
								WarMap.map[i][j].putClientProperty("Number", ((MapUnit)command.commandValue).NumberOfUnits);
								break;
							}	
							
							WarMap.map[i][j].putClientProperty("Identity", identity);
							WarMap.map[i][j].setVisible(true);
						}
					}
				
				break;
				
			case "KillUnit":			
				
				 JLabel label = new JLabel();
				for(int i=0 ; i<12 ; i++)
					for(int j=0 ; j<12 ; j++)
					{
						if(WarMap.map[i][j].getText().equals((String)command.commandValue))
						{
							WarMap.map[i][j].setIcon(WarMap.mapIcon);
							 WarMap.map[i][j].setVisible(false);
							 
							 label =  WarMap.map[i][j];						
						}
						
					}
				
				WarMap.getWar().showHide(0);
			
		
				if (((String)(label.getClientProperty("Identity"))).equals("Attacker"))
					WarMap.player1--;
				else if (((String)(label.getClientProperty("Identity"))).equals("Deffender"))	
					WarMap.player2--;	

			try {
				WarMap.UpdateHealthBar();
			} catch (Exception e) {
				e.printStackTrace();
			} 
				break;
				
			case "UpdateHealth":
				
				String st2 = (String)(command.commandValue);
				
				String[] split2 = st2.split(",");
				
				String position = split2[0];
				
				int newHealth = Integer.parseInt(split2[1]);
				
				for(int i=0 ; i<12 ; i++)
					for(int j=0 ; j<12 ; j++)
					{
						if(WarMap.map[i][j].getText().equals(position))
							WarMap.map[i][j].putClientProperty("Health", newHealth);
					}
				
				break;
				
			case "surrenderNow" :		
				
				WarMap.getWar().dispose();
				UserCity.getCity().setVisible(true);
		
				break;
				
			case "Nation2" :			
				WarMap.player2Nation = (String) (command.commandValue);
				break;
				
			case "setAttackerCount" :			
				WarMap.player1 = (int) (command.commandValue);
				
				WarMap.Units1 = WarMap.player1;
				
				break;
				
			case "setDeffenderCount" :			
				WarMap.player2 = (int) (command.commandValue);
				
				WarMap.Units2 = WarMap.player2;
				
				break;
				
			case "setArrayCount":
						
				if(WarMap.identity.equals("Attacker"))
					WarMap.player2StatsB =  (int[]) (command.commandValue);
				else
					WarMap.player1StatsB =  (int[]) (command.commandValue);
					
				break;

			
		}

	  }
		
	}

}
