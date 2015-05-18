package com.exercises.controllers;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayInputStream;
import java.util.Date;
import java.util.List;
import java.util.SortedMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exercises.command.CommandExecutor;
import com.exercises.command.CommandExtractor;
import com.exercises.command.Instruction;
import com.exercises.user.UserPost;
import com.exercises.utilities.ConsolePrinter;

public class StreamInputControllerTest {

	@Mock
	private CommandExecutor commandExecutor;
	@Mock
	private ConsolePrinter printer;
	private CommandExtractor commandExtractor;
	private StreamInputController streamInputController;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		commandExtractor = new CommandExtractor();
		streamInputController = new StreamInputController(commandExecutor, commandExtractor, printer);
	}

	@Test
	public void postingCommandIsProcessedSuccessfully() {
		streamInputController.processUserInput(new ByteArrayInputStream("Alice -> This is Alice".getBytes()));
		verify(commandExecutor).processPost((Instruction) anyObject());
	}

	@Test
	public void followingCommandIsProcessedSuccessfully() {
		streamInputController.processUserInput(new ByteArrayInputStream("Charlie follows Alice".getBytes()));
		verify(commandExecutor).processFollowing((Instruction) anyObject());
	}

	@Test
	public void readingCommandIsProcessedSuccessfully() {
		streamInputController.processUserInput(new ByteArrayInputStream("Alice".getBytes()));
		verify(commandExecutor).processReading((Instruction) anyObject());
		verify(printer).printUserPostsToConsole((List<UserPost>) anyObject());
	}

	@Test
	public void wallCommandIsProcessedSuccessfully() {
		streamInputController.processUserInput(new ByteArrayInputStream("Alice wall".getBytes()));
		verify(commandExecutor).processWall((Instruction) anyObject());
		verify(printer).printWallToConsole((SortedMap<Date, String>) anyObject());
	}

	@Test
	public void unknownCommandIsProcessedSuccessfully() {
		streamInputController.processUserInput(new ByteArrayInputStream("Alice invalidCommand".getBytes()));
		verify(printer).printUnknownCommand();
	}

}
