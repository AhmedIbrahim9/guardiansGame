package server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import userInterface.Credits;
import userInterface.Screen;
import userInterface.Victory;


public class mainClass {
	
	  public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		  
	
try {
				Connection.socket = new Socket("IP goes here", 6500);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Couldn't Connect, please return to server admin", "Couldn't Connect",
					    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
			try {
				Connection.output =  new ObjectOutputStream(Connection.socket.getOutputStream());
				Connection.output.flush();
				Connection.input = new ObjectInputStream(Connection.socket.getInputStream());
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Connection error, please return to server admin", "Couldn't Connect",
					    JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
			
		Screen s = new Screen();
		s.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				commandToSend<Object> command = new commandToSend<Object>();
    			command.commandType = "CloseConnection";
    			command.commandValue = null;
    			try {
    				Connection.output.writeObject(command);
    				Connection.output.close();
    				Connection.input.close();
    				Connection.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				s.dispose();
				super.windowClosing(e);
			}
		});
	}
}
