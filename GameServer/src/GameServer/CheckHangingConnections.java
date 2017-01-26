package GameServer;

import java.io.IOException;

import server.PairConnection;
import server.commandToSend;

public class CheckHangingConnections implements Runnable {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void run() {
		while(true){
			for(int i = 0 ; i < GameGate.hangingConnections.size() - 1; i+=2)
			{
				commandToSend command = new commandToSend();
				command.commandType = "MatchStarted";
				command.commandValue = null;
				try {
					GameGate.hangingConnections.get(i).output.writeObject(command);
					GameGate.hangingConnections.get(i + 1).output.writeObject(command);
				} catch (IOException e) {
					e.printStackTrace();
				}
				PairConnection connection = new PairConnection();
				connection.connection1 = GameGate.hangingConnections.get(i);
				connection.connection2 = GameGate.hangingConnections.get(i + 1);
				GameGate.inGameConnections.add(connection);
				GameGate.hangingConnections.remove(i);
				GameGate.hangingConnections.remove(i);
				
				GameCommandsListner listner = null;
				try {
					listner = new GameCommandsListner(connection);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Thread t = new Thread(listner);
				t.start();
			}
		
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
