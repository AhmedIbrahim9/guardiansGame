package LoginServer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import server.ManageAccounts;
import server.Profile;
import server.commandToSend;

public class LoginCommands <E>{
	private String commandType;
	@SuppressWarnings("unused")
	private E CommandValue;
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	private ManageAccounts accountManager;

	public LoginCommands(Socket socket, ObjectInputStream input, ObjectOutputStream output) throws ClassNotFoundException, IOException {
		this.socket = socket;
		this.input = input;
		this.output = output;
		commandType = new String();
		accountManager = new ManageAccounts();
	}
	
	@SuppressWarnings("rawtypes")
	public int readCommand(commandToSend command) throws FileNotFoundException, ClassNotFoundException, IOException{
		commandType = command.commandType;
		
		switch(commandType){
		case "CloseConnection":
			closeConnections();
			LoginGate.updateDisplayedText(socket.getInetAddress().getHostAddress() + " Closed connection");
			return 0;
		case "AccountLogin":
			Profile profileLog = (Profile) command.commandValue;
			LoginGate.updateDisplayedText(socket.getInetAddress().getHostAddress() + " commanded : " + command.commandType +
					" with value " + profileLog.toString());
			if(accountManager.login(profileLog)){
				output.writeObject("LoggedIn successfully");
				LoginGate.updateDisplayedText(socket.getInetAddress().getHostAddress() + " : successfully logged in");
			}
			else{
				output.writeObject("Wrong username or password");
			}
			return 1;
		case "AccountRegister":
			Profile profileReg = (Profile) command.commandValue;
			LoginGate.updateDisplayedText(socket.getInetAddress().getHostAddress() + " commanded : " + command.commandType +
					" with value " + profileReg.toString());
			if(accountManager.reigster(profileReg)){
				output.writeObject("Registerd successfully");
				LoginGate.updateDisplayedText(socket.getInetAddress().getHostAddress() + " : successfully Registred");
			}
			else{
				output.writeObject("duplicated username");
			}
			return 1;
		case "StartGame":
			if(ManageAccounts.loggedIn)
				return 2;
			else
				return 1;
		default:
			return -1;
		}
		
	}
	
	private void closeConnections(){
		try {
			output.close();
			input.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
