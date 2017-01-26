package server;


import java.io.Serializable;

@SuppressWarnings("serial")
public class Profile implements Serializable {
	
	 public String username;	
	 public String password;
	 public int gamesWon;
	 public int gamesLost;
	
	public Profile(){}
	
	public String toString(){
		return "username = " + username + " password = " + password;
	}
}
