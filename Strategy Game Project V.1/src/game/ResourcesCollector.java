package game;

import java.util.ArrayList;

import userInterface.UserCity;


public class ResourcesCollector extends Units implements Runnable {
	
	private long Rate = 25; // Number of mill seconds to wait before increasing stock
	private int numberOfMines;
	public static boolean isCollecting = false; //stock increase while true ,else stops
	private static int Stock = 10000;  //player starts game with 5000 coins
	public static ArrayList<ResourcesCollector> ResourcesList = new ArrayList<ResourcesCollector>();
	
	private Thread T;
	private String threadName = "collector";
	
	@Override
	public void run() {
		
		while(isCollecting)
		{
			numberOfMines = ResourcesList.size();
			
			int capacity = 10000 + (Storage.Capacity * Storage.StorageList.size());
				
			
			if(Stock == capacity)
			{
				try {
					
					Thread.sleep(Rate);
				
					incrementStock(0); 
				
				
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
			else
			{
			
			try {
	
					Thread.sleep(Rate);
				
					for(int i=0 ; i< numberOfMines ; i++)
					{
						if(Stock == capacity)
							continue;
						else	
							incrementStock(1); 	
					}
					
					UserCity.getGoldLabel().setText(String.valueOf(Stock));
					  		
				
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
			}
			
		}	
		
	}
	
	public void start () 
	{      
	    if (T == null)
	    {
	       T = new Thread (this, threadName);
	       T.start ();
	       isCollecting = true;
	    }
	}
	
	
	public static int getStock()
	{		
		return Stock;
	}
	
	public static void incrementStock(int amount)
	{		
		Stock = Stock + amount;
		
	}
	
	public static void decrementStock(int amount)
	{		
		Stock = Stock - amount;
		
	}
	
	public static boolean isEnough(int price)
	{
		if(Stock >= price)
		{
			return true;
			
		}else{
			
			return false;
			
		}	
		
	}

}
