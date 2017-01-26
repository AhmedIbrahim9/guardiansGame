package game;

import java.util.ArrayList;

import userInterface.UserCity;

public class Barracks extends Units{
	
	static public ArrayList<Barracks> BarracksList = new ArrayList<Barracks>();
	
	public static  String DeployWarrior()
	{
		if(Upgrade.WarriorUpgrade==true)
		{
		  Warrior warrior = new Warrior();
		
		  warrior.setUpgrade(true);

		  Warrior.WarriorsList.add(warrior);
		  Warrior.UpWarriors++;
		  
		  return "Upgraded Warrior deployed";
		  
		}else{
					
			Warrior warrior = new Warrior();

			Warrior.WarriorsList.add(warrior);
			
			
			return "Warrior deployed";
		}
	
	}
	
	public static String DeployArcher()
	{
		if(Upgrade.ArcherUpgrade==true)
		{
		 Archer archer = new Archer();
		 archer.setUpgrade(true);
		
		 Archer.ArcherList.add(archer);
		 Archer.UpArchers++;
		 
		return "Upgraded Archer deployed";
		
		}else{
			Archer archer= new Archer();
			
			Archer.ArcherList.add(archer);
			
			return "Archer deployed";
		}
	
	}
	
	public static String DeploySpearman()
	{
		if(Upgrade.SpearmanUpgrade==true)
		{
		SpearMan spearman = new SpearMan();
		
		spearman.setUpgrade(true);
		
		SpearMan.SpearManList.add(spearman);
		SpearMan.UpSpearMen++;
		
		return "Upgraded Spear Man deployed";
		
		}else{
			
			SpearMan spearman = new SpearMan();
			
			SpearMan.SpearManList.add(spearman);
			
			return "Spear Man deployed";
		}	
	}
	
	public static String DeployKnight()
	{	
	
		UserCity.updateKnightCounter();
					
		Knight knight = new Knight();
		
		Knight.KnightList.add(knight);
		
		return "Knight deployed";
		
	}
	
	public static String DeployChariot()
	{
		
		Chariot chariot = new Chariot();
		
		Chariot.ChariotList.add(chariot);
		
		return "Chariot deployed";
	}

	
}
