package GameServer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import server.MapUnit;
import server.PairConnection;
import server.commandToSend;

public class GameCommandsListner implements Serializable , Runnable{
	private static final long serialVersionUID = 8768073132359906065L;
	
	PairConnection connection;
	
	Listener1 l1;
	Listner2 l2;

	@SuppressWarnings("rawtypes")
	public static ArrayList<MapUnit> units = new ArrayList<>();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public GameCommandsListner(PairConnection connection) throws IOException {
		
		this.connection = connection;
		commandToSend command = new commandToSend();
		command.commandType = "StartInGame";
		command.commandValue = "";
		connection.connection1.output.writeObject(command);
		connection.connection2.output.writeObject(command);
		l1 = new Listener1(connection );
		l2 = new Listner2(connection );
	}

	@Override
	public void run() {
		Thread t1 = new Thread(l1);
		t1.start();
		Thread t2 = new Thread(l2);
		t2.start();
		
		while(true){
			
			if(l1.con1Deployed == false || l2.con2Deployed == false)
			{
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else {

				FinishingDeploy();
				break;
			}
		}
		
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked"})
	private void FinishingDeploy(){
		try {
			
			commandToSend temp = new commandToSend();
			
			temp.commandType = "Deploy";
			temp.commandValue = units;
			
			connection.connection1.output.writeObject(temp);
			connection.connection2.output.writeObject(temp);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}




