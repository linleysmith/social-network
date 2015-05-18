package com.exercises.controllers;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;

import com.exercises.command.CommandExecutor;
import com.exercises.command.CommandExtractor;
import com.exercises.command.Commands;
import com.exercises.command.Instruction;
import com.exercises.user.UserPost;
import com.exercises.utilities.ConsolePrinter;

public class StreamInputController implements StreamController {

	private CommandExecutor commandExecutor;
	private CommandExtractor commandExtractor;
	private ConsolePrinter printer;

	public StreamInputController(CommandExecutor commandExecutor, CommandExtractor commandExtractor,
			ConsolePrinter printer) {
		this.commandExecutor = commandExecutor;
		this.commandExtractor = commandExtractor;
		this.printer = printer;
	}

	@Override
	public void processUserInput(InputStream input) {
		Scanner inputScanner = new Scanner(input);
		while (inputScanner.hasNextLine()) {
			String inputCommand = inputScanner.nextLine();
			processCommand(commandExtractor.extractInstructionFromCommand(inputCommand));
		}
		inputScanner.close();
	}

	private void processCommand(Instruction instruction) {
		Commands userCommand = instruction.getCommand();
		if (Commands.POSTING == userCommand) {
			commandExecutor.processPost(instruction);
		} else if (Commands.FOLLOWING == userCommand) {
			commandExecutor.processFollowing(instruction);
		} else if (Commands.READING == userCommand) {
			List<UserPost> userPosts = commandExecutor.processReading(instruction);
			printer.printUserPostsToConsole(userPosts);
		} else if (Commands.WALL == userCommand) {
			SortedMap<Date, String> userTimeline = commandExecutor.processWall(instruction);
			printer.printWallToConsole(userTimeline);
		} else {
			printer.printUnknownCommand();
		}
	}

}
