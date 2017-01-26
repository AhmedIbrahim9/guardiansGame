package LoginServer;

import GameServer.GameGate;

public class Main {

	public static void main(String[] args) {
		LoginGate Lgate = new LoginGate();
		Thread thread = new Thread(Lgate);
		thread.start();
		
		@SuppressWarnings("unused")
		GameGate Ggate = new GameGate();
	}

}
