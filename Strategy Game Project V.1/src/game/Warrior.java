package game;

import java.util.ArrayList;

public class Warrior extends Units implements IArmy {
	
	protected double speed; 
	protected boolean isAlive;
	protected boolean isMoving;
	public boolean isUpgraded = false;
	public static int UpWarriors=0;
	
	public static ArrayList<Warrior> WarriorsList = new ArrayList<Warrior>();
        
        public Warrior(){
            Health=800;
            Damage=200;
        }
	
	public void setUpgrade(boolean value){
		
		isUpgraded = value;
	}
	
	@Override 
    public String Relocate(float X, float Y) //Change the location of the unit
    {
		if(isUpgraded==true)
 			return "Upgraded Warrior Has Been Moved";
 		else
 			return "Warrior Has Been Moved";
    }
	
	@Override   
    public String Attack () //for attacking
    {
		if(isUpgraded==true)
 			return "Upgraded Warrior is attacking";
 		else
 			return "Warrior is attacking";
    }
 
}
