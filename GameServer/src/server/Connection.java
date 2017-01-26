package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
	public Socket socket;
	public ObjectInputStream input;
	public ObjectOutputStream output;
	
	public Connection(Socket socket, ObjectInputStream input, ObjectOutputStream output) {
		this.socket = socket;
		this.input = input;
		this.output = output;
	}
}
