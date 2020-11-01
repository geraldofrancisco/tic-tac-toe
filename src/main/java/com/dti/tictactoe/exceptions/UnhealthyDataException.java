package com.dti.tictactoe.exceptions;

import java.util.ArrayList;
import java.util.List;

public class UnhealthyDataException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private String error = null;
	
	public void addMessage(String msg) {
		error = msg;
	}

	public String getError() {
		return error;
	}
}
