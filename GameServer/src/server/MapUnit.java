package server;

import java.io.Serializable;

public class MapUnit<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	

	public String type;
	public String position;
	public String identitiy;
	public int Health;
	public int Damage;
	public int NumberOfUnits;
	public E additionData;
}
