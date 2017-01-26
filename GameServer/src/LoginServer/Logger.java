package LoginServer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import GameServer.GameGate;
import server.commandToSend;
import server.Connection;

public class Logger implements Runnable{
	private Socket socket;
	private ObjectInputStream input;
	private ObjectOutputStream output;
	@SuppressWarnings("rawtypes")
	private LoginCommands command;
	private boolean connected = true;
	private int state = 1;
	
	@SuppressWarnings("rawtypes")
	Logger(Socket socket) throws ClassNotFoundException, IOException{
		this.socket = socket;
		try {
			output = new ObjectOutputStream(socket.getOutputStream());
			output.flush();
			input = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		command = new LoginCommands(socket, input, output);
	}
	
	@SuppressWarnings("rawtypes")
	public void run() {
		while(connected && state == 1){
			try{
				state = command.readCommand((commandToSend)input.readObject());
			}catch(ClassNotFoundException e){
			} catch (IOException e) {
			}
		}
		
		if(state == 2){
			Connection c = new Connection(socket, input, output);
			GameGate.hangingConnections.add(c);
		}
		
		if(state == 0 || state == -1)
			try {
				output.close();
				input.close();
				socket.close();
				connected = false;
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	public void closeConnections(){
		if(connected){
			connected = false;
			try {
				output.close();
				input.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
} 


