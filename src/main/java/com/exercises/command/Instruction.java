package com.exercises.command;

import java.util.Date;

public final class Instruction {
	private final String username;
	private final Commands command;
	private final String message;
	private final Date timestamp;

	public Instruction(String username, Commands command, String message,
			Date timestamp) {
		this.username = username;
		this.command = command;
		this.message = message;
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public Commands getCommand() {
		return command;
	}

	public String getMessage() {
		return message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "Instruction [username=" + username + ", command=" + command
				+ ", message=" + message + ", timestamp=" + timestamp + "]";
	}

}
