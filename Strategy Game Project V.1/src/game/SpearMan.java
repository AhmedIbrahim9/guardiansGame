package game;

import java.util.ArrayList;

public class SpearMan extends Warrior implements IArmy{
	
	private int ShieldHealth;
	public boolean isUpgraded = false;
	ThrownWeapon Spear = new ThrownWeapon();
	public static ArrayList<SpearMan> SpearManList = new ArrayList<SpearMan>(); 
	public static int UpSpearMen=0;
	
        
        public SpearMan(){
            Health=1000;
            Damage=350;
        }
        
public void setUpgrade(boolean value){
		
		isUpgraded = value;
	}
	
	public String changeShieldHealth(int Type) //Gets the current armor health
    {
		if(Upgrade.SpearmanUpgrade==false)
		{
    	if(Type == 1)
		{
    		ShieldHealth =  ShieldHealth - 5;
    		return "Shield Health is reduced by 5";
			
		} else if(Type == 2){
			
			ShieldHealth =  ShieldHealth - 7;
			return "Shield Health is reduced by 7";
			
		} else if(Type == 3){
			
			ShieldHealth =  ShieldHealth - 10;
			return "Shield Health is reduced by 10";
		}
		else
			return "Type out range";
		}
		else
		{
			if(Type == 1)
			{
	    		ShieldHealth =  ShieldHealth - 3;
	    		return "Shield Health is reduced by 3";
				
			} else if(Type == 2){
				
				ShieldHealth =  ShieldHealth - 5;
				return "Shield Health is reduced by 5";
				
			} else if(Type == 3){
				
				ShieldHealth =  ShieldHealth - 8;
				return "Shield Health is reduced by 8";
			}
			else
				return "Type out range";
		}
    }
	@Override 
	 public String Relocate(float X, float Y)  //Change the location of the unit
	 {
		if(Upgrade.SpearmanUpgrade==true)
 			return "Upgraded SpearMan Has Been Moved";
 		else
 			return "SpearMan Has Been Moved";
	 }
	   
	@Override 
	 public String Attack () //for attacking
	 {
		if(isUpgraded==true)
 			return "Upgraded SpearMan "+ UseWeapon(Spear);
 		else
		 return " SpearMan"+ UseWeapon(Spear);
	} 
	
	public String UseWeapon(ThrownWeapon S)
	{
		
		return " has thrown a spear";
		
	}

	
}
