package game;

public class Upgrade extends Units{
	

	public static boolean WarriorUpgrade  = false;
	public static boolean ArcherUpgrade   = false;
	public static boolean SpearmanUpgrade = false;

public static String upgradeSoldier()
{
	if(WarriorUpgrade == true)
		return "Warrior is already upgraded";
	else
	{
		WarriorUpgrade = true;
	  return "Warrior Upgraded";	
	}
}

public static String upgradeArcher()
{
	if(ArcherUpgrade==true)
		return "Archer is already upgraded";
	else
	{
	  ArcherUpgrade=true;
	  return "Archer Upgraded";	
	}
}

public static String upgradeSpearman()
{
	if(SpearmanUpgrade == true)
		return "SpearMan is upgraded";
	else
	{
      SpearmanUpgrade = true;
      return "Spear Man Upgraded";
	}
}
		

}
