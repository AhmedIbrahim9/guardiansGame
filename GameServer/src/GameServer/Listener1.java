package GameServer;

import java.io.IOException;
import java.io.Serializable;

import server.PairConnection;
import server.MapUnit;
import server.commandToSend;

public class Listener1 implements Runnable , Serializable{
	private static final long serialVersionUID = 2542063890530813537L;
	
	@SuppressWarnings("rawtypes")
	commandToSend command1;
	@SuppressWarnings("rawtypes")
	public MapUnit con1Units;
	
		
	PairConnection connection;
	
	public boolean con1Deployed = false;

	public Listener1(PairConnection connection ) {
		this.connection = connection;
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void run() {
		
		while(true){
			
			command1 = new commandToSend();
			commandToSend temp = new commandToSend();

			try {
				command1 = (commandToSend)connection.connection1.input.readObject();
			} catch (ClassNotFoundException | IOException e) {
				System.out.println(e.getMessage());
			}
								
				switch(command1.commandType){
	
				case "Turn":
					temp.commandType = "Turn";
					try {
						connection.connection2.output.writeObject(temp);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					break;
					
				case "Attack":
					command1.commandType = "Attack";

					try {
						connection.connection2.output.writeObject(command1);
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;

				case "Deploy":
					con1Deployed = true;
					break;
					
				case "SendUnit":
					GameCommandsListner.units.add( ((MapUnit)(command1.commandValue)) );					
					break;
					
					
				case "MoveUnit":
									
				commandToSend temp2 = new commandToSend();
				temp2.commandType = "UpdateMap";
				temp2.commandValue = command1.commandValue;
					
					try {
						connection.connection2.output.writeObject(temp2);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					break;
					
				case "AttackingUnit":
					
					MapUnit attacker = (MapUnit)command1.commandValue;
					
					MapUnit attacked = (MapUnit)attacker.additionData;
					
					
					attacked.Health -= attacker.Damage;
					attacker.Health -= attacked.Damage * 0.5;
					
					commandToSend temp3 = new commandToSend();
					if(attacked.Health <= 0){
						temp3.commandType = "KillUnit";
						temp3.commandValue = attacked.position;
					}
					else{
						temp3.commandType = "UpdateHealth";
						temp3.commandValue = attacked.position + "," + attacked.Health;
					}
					try {
						connection.connection1.output.writeObject(temp3);
						connection.connection2.output.writeObject(temp3);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					commandToSend temp4 = new commandToSend();
					if(attacker.Health <= 0){
						temp4.commandType = "KillUnit";
						temp4.commandValue = attacker.position;
					}
					else{
						temp4.commandType = "UpdateHealth";
						temp4.commandValue = attacker.position + "," + attacker.Health;
					}
					try {
						connection.connection2.output.writeObject(temp4);
		
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					break;
				case "surrender":
					
					commandToSend command = new commandToSend();
					command.commandType = "surrenderNow";
					
					try {
						connection.connection2.output.writeObject(command);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				   break;
				   
				case "setNation":
					
					commandToSend nationCommand = new commandToSend();
					
					nationCommand.commandType = "Nation2";
					nationCommand.commandValue = (String) (command1.commandValue);
					
					try {
						connection.connection2.output.writeObject(nationCommand);
					} catch (IOException e) {

						e.printStackTrace();
					}
					break;
					
				case "setAttackerUnits":
					
					commandToSend forward = new commandToSend();
					
					forward.commandType = "setAttackerCount";
					forward.commandValue = (int) (command1.commandValue);
					
					try {
						connection.connection2.output.writeObject(forward);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case "setDeffenderUnits":
					
					commandToSend forward2 = new commandToSend();
					
					forward2.commandType = "setDeffenderCount";
					forward2.commandValue = (int) (command1.commandValue);
					
					try {
						connection.connection2.output.writeObject(forward2);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				   
				case "setUnitsArray" :
					
					commandToSend forward3 = new commandToSend();
					
					forward3.commandType = "setArrayCount";
					forward3.commandValue = (int[]) (command1.commandValue);
					
					try {
						connection.connection2.output.writeObject(forward3);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;

				}
			
		}
	}
		
}


