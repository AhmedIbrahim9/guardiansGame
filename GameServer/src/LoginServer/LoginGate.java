package LoginServer;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class LoginGate extends JFrame implements Runnable{

	private static final long serialVersionUID = -3188233999584515315L;
	private static JTextArea displayInfo;
	private static String displayedText;
	
	private ServerSocket serverSocket;
	private Socket socket = null;
	
	public LoginGate(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("LoginGate");
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
		
	}
	
	public void run(){
		try {
			serverSocket = new ServerSocket(6500);
			while(true){
				try{
					socket = serverSocket.accept();
					displayedText += socket.getInetAddress().getHostAddress() + " : Connected" + "\n";
					displayInfo.setText(displayedText);
					Logger logger = new Logger(socket);
					Thread t = new Thread(logger);
					t.start();
					
				}catch(EOFException eofException){
					eofException.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}finally {
					
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void updateDisplayedText(String st){
		displayedText += st + "\n";
		displayInfo.setText(displayedText);
	}
}
