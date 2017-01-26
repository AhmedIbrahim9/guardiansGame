package game;

public class Memory extends Units {
		
	public final static int UpgradeWarrior   = 1500;
	public final static int UpgradeArcher    = 3000;
	public final static int UpgradeSpearMan  = 5000;
	
	final static public int TowerPrice        = 600;
	final static public int BarracksPrice     = 3000;
	final static public int MinePrice         = 1200;
	final static public int StablePrice       = 1000;
	final static public int StoragePrice      = 8000;
	final static public int BlacksmithPrice   = 1500;

	final static public int horsePrice        = 600;	
	public final static int SaddlePrice     = 250;
	public final static int ChariotCarPrice = 350;
	public final static int knightPrice     = 150;
	public final static int ChariotPrice    = 200;
	
	public static int GetWarriorPrice()
	{
		if(Upgrade.WarriorUpgrade==false)
		{
			return 50;
		}
		else
		{
			return 100;
		}
	}
	
	public static int GetArcherPrice()
	{
		if(Upgrade.ArcherUpgrade==false)
		{
			return 150;
		}
		else
		{
			return 300;
		}
	}
	
	public static int GetSpearManPrice()
	{
		if(Upgrade.SpearmanUpgrade==false)
		{
			return 100;
		}
		else
		{
			return 200;
		}
	}
		
	private Memory()
	{

	}

}
