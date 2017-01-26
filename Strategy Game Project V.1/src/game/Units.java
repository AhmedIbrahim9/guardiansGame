package game;

public class Units {
	
	protected String Name;
	protected int ID;
	public static int Health;	        
	protected float LocationX;
	protected float LocationY;		 
	protected int AttackSpeed;
	public static int Damage;
	protected boolean isAttacking;

	

	   /*-----------------------------------------------------------------------
		Precondition:  None.
		Postcondition: Unit health is reduced
		-----------------------------------------------------------------------*/ 
	 public String Reducehealth(int type)
	   {   
		   if(type == 1)  //indicates that the unit has been hit by a Soldier
		   {
			   Health = Health - 5;
			   return "Your Health is reduced by 5";
		   } 
		   else if (type == 2)   //indicates that the unit has been hit by an Archer
		   {  
			   Health = Health - 7;
			   return "Your Health is reduced by 7";
		   }
		   else if (type == 3)  //indicates that the unit has been hit by a Spear Man
		   {
			   Health = Health - 10;
			   return "Your Health is reduced by 10";
			   
		   }
		   else
			   return "Type out range";
		   
	   }
}
