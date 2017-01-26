package game;

import java.util.ArrayList;

public class Stable extends Units{

		public static ArrayList<Stable> StableList=new ArrayList<Stable>();
		
		public static int horseNumber = 0;

		public static String DeployHorse()
		{	
			horseNumber++;
			
			return "Horse deployed";		
		}
	

	
	}
