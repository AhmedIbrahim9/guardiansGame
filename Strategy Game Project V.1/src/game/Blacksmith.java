package game;

import java.util.ArrayList;

public class Blacksmith {

	public static ArrayList<Blacksmith> blackSmithList = new ArrayList<Blacksmith>();
	static int saddleNumber = 0;
	static int chariotVehicleNumber = 0;

	public static String makeSaddle()
	{
		   saddleNumber++;
			
			return "Saddle made";		
	}
	
	public static String makechariotCar()
	{ 
			
            chariotVehicleNumber++;
			
			return "Chariot \"vehicle only\" deployed";		
	}
	
	public static void decrementSaddle()
	{
		saddleNumber--;
	}
	
	public static void decrementChariot()
	{
        chariotVehicleNumber--;
	}
	
	public static int getSaddle()
	{
		return saddleNumber;
	}
	
	public static int getChariot()
	{
		return chariotVehicleNumber;
	}
	
}
