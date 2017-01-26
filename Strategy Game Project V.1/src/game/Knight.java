package game;

import java.util.ArrayList;

public class Knight extends Units implements IArmy {
	
	protected double speed; 
	protected boolean isAlive;
	protected boolean isMoving;
	
	public static ArrayList<Knight> KnightList = new ArrayList<Knight>();
	
        public Knight()
        {
            Health=1500;
            Damage=700;
        }
	
	@Override 
    public String Relocate(float X, float Y) //Change the location of the unit
    {
 			return "Knight Has Been Moved";
    }
	
	@Override   
    public String Attack () //for attacking
    {
 			return "Knight is attacking";
    }
 
}

