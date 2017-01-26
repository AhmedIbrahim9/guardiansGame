package game;

public class Nations {
	
	private static String Name;   //Nation's Name
	private static int ID;        //Nation's ID 
	private Units unit = new Units();    //unit is an object of class Unit containing Unit's details
	
	
	public static String getName() {
		return Name;
	}
	
	public static void setName(int id) {
		
		if(id == 1)
		{
		   Name = "Nation 1";
			
		} else if (id == 2)
		{
			 Name = "Nation 2";
			
		}
		
	}
	
	
	public int getID() {
		
		return ID;
	}
	public void setID(int id) {
		
		ID = id;
	}

	public Units getUnit() {
		return unit;
	}

	public void setUnit(Units unit) {
		
		
		this.unit = unit;
	}

}
