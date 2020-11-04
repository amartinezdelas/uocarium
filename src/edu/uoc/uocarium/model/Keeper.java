package edu.uoc.uocarium.model;

import java.util.ArrayList;

public class Keeper {
	
	private String id;
	private String name;
	private String surname;
	private ArrayList<Tank> tanks;

	public Keeper(String id, String name, String surname) throws KeeperException {
		setId(id);
		setName(name);
		setSurname(surname);
		tanks = new ArrayList<Tank>(5); //5 es el m�ximo de tanques
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws KeeperException {
		if(id==null) {//id no puede estar vac�o, no empezar por G o no tener 5 caracteres
			throw new NullPointerException();
		}
		else if(id.charAt(0)!='G') {
			throw new KeeperException(KeeperException.MSG_ERR_START_LETTER);
		}
		else if(id.length()!=5) {
			throw new KeeperException(KeeperException.MSG_ERR_LENGTH_VALUE);
		}
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public ArrayList<Tank> getTanks() {
		return tanks;
	}

	public void setTanks(ArrayList<Tank> tanks) {
		this.tanks = tanks;
	}
	
	public void addTank(Tank tank) throws KeeperException {
		if(getTanks().size()==5) {//al tratar de a�adir m�s de 5 tanks, lanza excepci�n
			throw new KeeperException(KeeperException.MSG_ERR_MAX_TANKS);
		}
		else if(getTanks().contains(tank)) {
			return;//si el tanque ya est� en la arraylist, el m�todo no hace nada
		}
		getTanks().add(tank);//a�ade el tank al pasar las comprobaciones
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
	
		str.append("["+getId()+"]");
		str.append(" "+getSurname());
		str.append(", "+getName()+":");
		for(Tank tank : getTanks()) { 
			if(tank != null) str.append("\n\t"+tank.getName());
		}
		return str.toString();
	}
	
}
