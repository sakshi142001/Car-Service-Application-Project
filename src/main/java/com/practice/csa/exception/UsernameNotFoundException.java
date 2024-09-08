package com.practice.csa.exception;

public class UsernameNotFoundException extends RuntimeException{
	
	private String message;

	public UsernameNotFoundException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "ServiceEntityNotFoundByIdException [message=" + message + "]";
	}

}
