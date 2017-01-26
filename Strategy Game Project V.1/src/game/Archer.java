package game;

import java.util.ArrayList;

public class Archer extends Warrior implements IArmy{
	
	public boolean isUpgraded = false;
	ThrownWeapon Arrow=new ThrownWeapon();
	public static ArrayList<Archer> ArcherList = new ArrayList<Archer>();
	public static int UpArchers=0;
	
        public Archer(){
            Health=500;
            Damage=600;
        }
        
    public void setUpgrade(boolean value)
    {
		isUpgraded = value;
    }
	
	
	 @Override    
	 public String Relocate(float X, float Y) //Change the location of the unit
	    {
	 		if(Upgrade.ArcherUpgrade==true)
	 			return "Upgraded Archer Has Been Moved";
	 		else
	 			return "Archer Has Been Moved";
	    }
	 
	 @Override    
	 public String Attack () //for attacking
	    {
	 		if(isUpgraded==true)
	 			return "Upgraded Archer "+ useWeapon(Arrow);
	 		else
	 			return "Archer Archer "+ useWeapon(Arrow);
	    }
	 	
	 public String useWeapon(ThrownWeapon A) //for throwing arrows
        {
		         return " has thrown an arrow";
	    }
	    
	   


}
