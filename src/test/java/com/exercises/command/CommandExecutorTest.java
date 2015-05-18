package com.exercises.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.exercises.user.User;
import com.exercises.user.UserPost;
import com.exercises.user.UserTimeline;

public class CommandExecutorTest {

	@Mock
	private InstructionProcessor instructionProcessor;
	private CommandExecutor commandExecutor;
	private User alice;
	private User bob;
	private User charlie;
	private static final Instruction ALICE_POST = buildInstruction("Alice", Commands.POSTING,
			"I love the weather today!!");
	private static final Instruction BOB_FIRST_POST = buildInstruction("Bob", Commands.POSTING, "Damn! We lost!");
	private static final Instruction BOB_SECOND_POST = buildInstruction("Bob", Commands.POSTING, "Good game though.");
	private static final Instruction ALICE_READING = buildInstruction("Alice", Commands.READING, null);
	private static final Instruction ALICE_WALL = buildInstruction("Alice", Commands.WALL, null);
	private static final Instruction CHARLIE_POST = buildInstruction("Charlie", Commands.POSTING,
			"I'm in New York today! Anyone want to have a coffee?");
	private static final Instruction CHARLIE_FOLLOWS_ALICE = buildInstruction("Charlie", Commands.FOLLOWING, "Alice");

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		commandExecutor = new CommandExecutor(instructionProcessor);
		alice = new User("Alice");
		bob = new User("Bob");
	}

	@Test
	public void userAddedToEmptyActiveUsers() {
		Map<String, User> activeUsers = commandExecutor.getActiveUsers();
		assertTrue(activeUsers.isEmpty());
		assertEquals("CommandExecutor [activeUsers={}]", commandExecutor.toString());

		commandExecutor.processPost(ALICE_POST);
		activeUsers = commandExecutor.getActiveUsers();
		assertFalse(activeUsers.isEmpty());
		assertEquals(1, activeUsers.size());
		assertTrue(activeUsers.containsValue(alice));

		commandExecutor.processPost(BOB_FIRST_POST);
		activeUsers = commandExecutor.getActiveUsers();
		assertFalse(activeUsers.isEmpty());
		assertEquals(2, activeUsers.size());
		assertTrue(activeUsers.containsValue(bob));
	}

	@Test
	public void activeUsersUpdatedIfAlreadyExist() {
		Map<String, User> activeUsers = commandExecutor.getActiveUsers();
		assertTrue(activeUsers.isEmpty());

		commandExecutor.processPost(BOB_FIRST_POST);
		activeUsers = commandExecutor.getActiveUsers();
		assertFalse(activeUsers.isEmpty());
		assertEquals(1, activeUsers.size());
		assertTrue(activeUsers.containsValue(bob));

		commandExecutor.processPost(BOB_SECOND_POST);
		activeUsers = commandExecutor.getActiveUsers();
		assertFalse(activeUsers.isEmpty());
		assertEquals(1, activeUsers.size());
		assertTrue(activeUsers.containsValue(bob));
	}

	@Test
	public void processUserPost() {
		doNothing().when(instructionProcessor).addPostToUser(alice, ALICE_POST);
		commandExecutor.processPost(ALICE_POST);
		verify(instructionProcessor).addPostToUser(alice, ALICE_POST);
	}

	@Test
	public void processUserFollowing() {
		doNothing().when(instructionProcessor).followUser(charlie, alice);
		commandExecutor.processPost(ALICE_POST);
		commandExecutor.processPost(CHARLIE_POST);
		commandExecutor.processFollowing(CHARLIE_FOLLOWS_ALICE);
		verify(instructionProcessor).followUser((User) anyObject(), (User) anyObject());
	}

	@Test
	public void processUserReadinging() {
		when(instructionProcessor.retrieveUserPostsInDescendingDateOrder((User) anyObject())).thenReturn(
				(List<UserPost>) anyObject());
		commandExecutor.processReading(ALICE_READING);
		verify(instructionProcessor).retrieveUserPostsInDescendingDateOrder((User) anyObject());
	}

	@Test
	public void processUserWall() {
		when(instructionProcessor.retrieveUserTimelineInDescendingDateOrder((User) anyObject())).thenReturn(
				new UserTimeline());
		commandExecutor.processWall(ALICE_WALL);
		verify(instructionProcessor).retrieveUserTimelineInDescendingDateOrder((User) anyObject());
	}

	private static Instruction buildInstruction(String username, Commands command, String message) {
		return new Instruction(username, command, message, Calendar.getInstance().getTime());
	}

}
