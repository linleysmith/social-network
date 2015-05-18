package com.exercises.consoles;

import com.exercises.command.CommandExecutor;
import com.exercises.command.CommandExtractor;
import com.exercises.command.InstructionProcessor;
import com.exercises.controllers.StreamController;
import com.exercises.controllers.StreamInputController;
import com.exercises.utilities.ConsolePrinter;

public class SystemConsole {

	private CommandExecutor commandExecutor = new CommandExecutor(new InstructionProcessor());
	private CommandExtractor commandExtractor = new CommandExtractor();
	private ConsolePrinter consolePrinter = new ConsolePrinter();

	public static void main(String[] args) {
		System.out.println("Please enter your command:");
		SystemConsole console = new SystemConsole();
		console.executeCommand();
	}

	private void executeCommand() {
		StreamController inputController = new StreamInputController(commandExecutor, commandExtractor, consolePrinter);
		inputController.processUserInput(System.in);
	}
}
