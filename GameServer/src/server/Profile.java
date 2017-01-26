package server;


import java.io.Serializable;

public class Profile implements Serializable {
	
	private static final long serialVersionUID = -1054089910718465761L;
	public String username;	
	 public String password;
	 public int gamesWon;
	 public int gamesLost;
	
	public Profile(){}
	
	public String toString(){
		return "username = " + username + " password = " + password;
	}
}
