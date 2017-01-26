package game;

import java.util.ArrayList;

public class Defense extends Units{
	
    private int ArmorHealth;
    public static ArrayList<Defense> TowerList = new ArrayList<Defense>();
    
    public String changeArmorHealth(int Type) //Gets the current armor health
    {
    	if(Type == 1)
		{
    		 ArmorHealth =  ArmorHealth - 5;
    		 return "Armour Health is reduced by 5";
			
		} else if(Type == 2){
			
			ArmorHealth =  ArmorHealth - 7;
			return "Armour Health is reduced by 7";
			
		} else if(Type == 3){
			
			ArmorHealth =  ArmorHealth - 10;
			return "Armour Health is reduced by 10";
		}
		else
			return "Type out range";
    }
    
    
    public String UseWeapon(ThrownWeapon A) //Defense building starts action using arrows,A is an object of class Weapon
    {
        return "TOWER IS ATTACKING WITH ARROWS";
    }
	

	public String Attack() {
		
		return "Tower is Attacking";
	}

    
}

