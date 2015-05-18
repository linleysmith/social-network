package com.exercises.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.exercises.command.Commands;
import com.exercises.command.CommandExtractor;
import com.exercises.command.Instruction;

public class CommandExtractorTest {
	
	private CommandExtractor commandExtractor;
	
	@Before
	public void setup() {
		commandExtractor = new CommandExtractor();
	}

	@Test
	public void userPostingMessage() {
		Instruction postingInstruction = commandExtractor.extractInstructionFromCommand("Alice -> I love the weather today");
		assertEquals("Alice", postingInstruction.getUsername());
		assertEquals(Commands.POSTING, postingInstruction.getCommand());
		assertEquals("I love the weather today", postingInstruction.getMessage());
		assertNotNull(postingInstruction.getTimestamp());
	}
	
	@Test
	public void userReadingTimeline() {
		Instruction readingInstruction = commandExtractor.extractInstructionFromCommand("Alice");
		assertEquals("Alice", readingInstruction.getUsername());
		assertEquals(Commands.READING, readingInstruction.getCommand());
		assertNull(readingInstruction.getMessage());
		assertNotNull(readingInstruction.getTimestamp());
	}

	@Test
	public void userFollowingAnotherUser() {
		Instruction followingInstruction = commandExtractor.extractInstructionFromCommand("Charlie follows Alice");
		assertEquals("Charlie", followingInstruction.getUsername());
		assertEquals(Commands.FOLLOWING, followingInstruction.getCommand());
		assertEquals("Alice", followingInstruction.getMessage());
		assertNotNull(followingInstruction.getTimestamp());
	}

	@Test
	public void userWall() {
		Instruction wallInstruction = commandExtractor.extractInstructionFromCommand("Charlie wall");
		assertEquals("Charlie", wallInstruction.getUsername());
		assertEquals(Commands.WALL, wallInstruction.getCommand());
		assertNull(wallInstruction.getMessage());
		assertNotNull(wallInstruction.getTimestamp());
	}

}
