package game;

import java.util.ArrayList;

public class Chariot extends Units implements IArmy {
	
	protected double speed; 
	protected boolean isAlive;
	protected boolean isMoving;
	
	public static ArrayList<Chariot> ChariotList = new ArrayList<Chariot>();
	
	public Chariot(){
            Health=2000;
            Damage=1000;
        }
        
	@Override 
    public String Relocate(float X, float Y) //Change the location of the unit
    {
 			return "Chariot Has Been Moved";
    }
	
	@Override   
    public String Attack () //for attacking
    {
 			return "Chariot is attacking";
    }
 
}

