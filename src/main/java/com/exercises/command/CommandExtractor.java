package com.exercises.command;

import java.util.Calendar;
import java.util.Date;

public class CommandExtractor {

	private static final int USER_COMMAND_POSITION = 2;
	private static final String WHITESPACE = " ";

	public Instruction extractInstructionFromCommand(String inputCommand) {
		String input[] = inputCommand.split(WHITESPACE);
		String username = input[0];
		Commands command = null;
		String message = null;
		Date timestamp = Calendar.getInstance().getTime();
		int commandLength = input.length;
		if (commandLength >= USER_COMMAND_POSITION) {
			command = Commands.getCommand(input[1]);
			if (commandLength > USER_COMMAND_POSITION) {
				StringBuilder post = new StringBuilder();
				for (int i = USER_COMMAND_POSITION; i < commandLength; i++) {
					String text = input[i];
					post.append(text);
					if (i != commandLength - 1) {
						post.append(WHITESPACE);
					}
				}
				message = post.toString();
			}
		} else {
			command = Commands.READING;
		}
		return new Instruction(username, command, message, timestamp);
	}

}
