package server;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ManageAccounts {
	
	static private ArrayList<Profile> profiles = new ArrayList<>();
	static public boolean loggedIn;
		
	public ManageAccounts() throws IOException, ClassNotFoundException {
		
		if(!new File("accounts.bin").exists())
			new File("accounts.bin").createNewFile();
		
		if(new File("accounts.bin").length() != 0){
			ObjectInputStream ips =	new ObjectInputStream(new FileInputStream("accounts.bin"));
			Profile[] p = (Profile[])ips.readObject();
			for(int i = 0 ; i < p.length ; i++){
				profiles.add(p[i]);
			}
			ips.close();
		}
	}
		
	
	public boolean login(Profile sentProfile) throws FileNotFoundException, IOException, ClassNotFoundException
	{	
			String username = sentProfile.username;
			String password = sentProfile.password;
			
			for(int i = 0; i < profiles.size(); i++){
				if(profiles.get(i).username.equals(username) && profiles.get(i).password.equals(password)){
					loggedIn = true;
					return true;
				}
			}
			return false;
	}
	
	public boolean reigster(Profile sentProfile) throws FileNotFoundException, IOException, ClassNotFoundException{
	 		
		
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accounts.bin"));
		
			boolean usernameAva = true;
			for(int i = 0; i < profiles.size(); i++)
				if(profiles.get(i).username.equals(sentProfile.username)){
					usernameAva = false;
					break;
				}
		
			if(!usernameAva)
			{
				out.close();
				return false;
			}
		
		loggedIn = true;
		Profile p = new Profile();
		p.username = sentProfile.username;
		p.password = sentProfile.password;
		p.gamesWon = 0;
		p.gamesLost = 0;
		profiles.add(p);
		Profile []arp = new Profile[profiles.size()];
		
		for(int i = 0 ; i < profiles.size() ; i++)
			arp[i] = profiles.get(i);
		out.writeObject(arp);
		out.close();
		
		return true;
	}
	
}
