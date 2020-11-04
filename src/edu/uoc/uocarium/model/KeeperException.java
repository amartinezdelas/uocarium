package edu.uoc.uocarium.model;

public class KeeperException extends Exception {
	
	//mensajes propios de keeper
	public final static String MSG_ERR_START_LETTER = "[ERROR] A keeper's id must start with letter 'G'!!",
			MSG_ERR_LENGTH_VALUE = "[ERROR] A keeper's id must have 5 characters!!",
			MSG_ERR_MAX_TANKS = "[ERROR] A keeper cannot take care of more than 5 tanks!!";

	public KeeperException() {
	}

	public KeeperException(String msg) {
		super(msg);
	}

}
