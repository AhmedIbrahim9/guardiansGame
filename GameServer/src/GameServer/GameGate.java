package GameServer;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import server.Connection;
import server.PairConnection;

@SuppressWarnings("serial")
public class GameGate extends JFrame{
	
	public static ArrayList<Connection> hangingConnections;
	public static ArrayList<PairConnection> inGameConnections;
	private static JTextArea displayInfo;
	@SuppressWarnings("unused")
	private static String displayedText;
	CheckHangingConnections hangingConnectionsClass;
	updateConnectedText connectedText;
	
	public GameGate() {
		hangingConnections = new ArrayList<>();
		inGameConnections = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GameGate");
		setSize(700, 700);
		setLayout(null);
		setVisible(true);
		displayInfo = new JTextArea();
		displayedText = "";
		add(displayInfo);
		displayInfo.setEditable(false);
		displayInfo.setLineWrap(true);
		displayInfo.setBorder(null);
		displayInfo.setBounds(0, 0, 700, 700);
		displayInfo.setVisible(true);
		hangingConnectionsClass = new CheckHangingConnections();
		connectedText = new updateConnectedText();
		
		Thread t = new Thread(hangingConnectionsClass);
		t.start();
		Thread t2 = new Thread(connectedText);
		t2.start();
	}
	
	class updateConnectedText implements Runnable{

		@SuppressWarnings("unused")
		@Override
		public void run() {
			while(true){
				String st = "";
				for(int i = 0 ; i < inGameConnections.size() ; i++)
					st += inGameConnections.get(i).connection1.socket.getInetAddress().getHostAddress() + " : " 
							+ inGameConnections.get(i).connection2.socket.getInetAddress().getHostAddress() + "\n";
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}
