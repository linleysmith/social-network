package com.exercises.command;

import java.util.HashMap;
import java.util.Map;

public enum Commands {
	POSTING("->"), READING(null), FOLLOWING("follows"), WALL("wall");

	private String command;

	Commands(String command) {
		this.command = command;
	}

	private static final Map<String, Commands> lookup = new HashMap<String, Commands>();
	static {
		for (Commands command : Commands.values())
			lookup.put(command.getCommandValue(), command);
	}

	public String getCommandValue() {
		return command;
	}

	public static Commands getCommand(String commandValue) {
		return lookup.get(commandValue);
	}
}
