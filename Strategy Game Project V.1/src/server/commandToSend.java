package server;

import java.io.Serializable;

public class commandToSend <e> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -692528034417425243L;
	public String commandType;
	public e commandValue;
}
