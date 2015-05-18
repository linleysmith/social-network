package com.exercises.command;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.exercises.user.User;
import com.exercises.user.UserPost;
import com.exercises.user.UserTimeline;

public class InstructionProcessorTest {

	private InstructionProcessor instructionProcessor;

	@Before
	public void setup() {
		instructionProcessor = new InstructionProcessor();
	}

	@Test
	public void addPostsToUser() {
		Date currentTime = Calendar.getInstance().getTime();
		String message = "Hello World!";
		Instruction instruction = new Instruction("Alice", Commands.POSTING, message, currentTime);
		instruction.toString();
		User user = new User("Alice");

		assertTrue(user.getUserPosts().isEmpty());
		instructionProcessor.addPostToUser(user, instruction);
		assertFalse(user.getUserPosts().isEmpty());
		assertEquals(1, user.getUserPosts().size());
		for (UserPost userPost : user.getUserPosts()) {
			assertEquals(currentTime, userPost.getTimeOfPost());
			assertEquals(message, userPost.getPost());
		}
	}

	@Test
	public void followUsers() {
		User user = new User("Charlie");
		assertTrue(user.getFollowingUsers().isEmpty());
		instructionProcessor.followUser(user, new User("Alice"));
		assertFalse(user.getFollowingUsers().isEmpty());
		assertEquals(1, user.getFollowingUsers().size());
		for (User userFollowed : user.getFollowingUsers()) {
			assertEquals("Alice", userFollowed.getName());
		}
		instructionProcessor.followUser(user, new User("Bob"));
		assertEquals(2, user.getFollowingUsers().size());
	}

	@Test
	public void readUserPostsInReverseTimeOrder() {
		User user = new User("Bob");
		Calendar timeOfPost = Calendar.getInstance();
		Date firstTimestamp = timeOfPost.getTime();
		timeOfPost.add(Calendar.MINUTE, 1);
		Date secondTimestamp = timeOfPost.getTime();
		String firstMessage = "Damn! We lost!";
		String secondMessage = "Good game though.";

		Instruction firstPostInstruction = new Instruction("Bob", Commands.POSTING, firstMessage, firstTimestamp);
		Instruction secondPostInstruction = new Instruction("Bob", Commands.POSTING, secondMessage, secondTimestamp);

		instructionProcessor.addPostToUser(user, firstPostInstruction);
		instructionProcessor.addPostToUser(user, secondPostInstruction);

		List<UserPost> userPosts = instructionProcessor.retrieveUserPostsInDescendingDateOrder(user);
		assertEquals(2, userPosts.size());
		assertEquals(secondTimestamp, userPosts.get(0).getTimeOfPost());
		assertEquals(secondMessage, userPosts.get(0).getPost());
		assertEquals(firstTimestamp, userPosts.get(1).getTimeOfPost());
		assertEquals(firstMessage, userPosts.get(1).getPost());
	}

	@Test
	public void readUserTimelineInReverseTimeOrder() {
		Calendar timeOfPost = Calendar.getInstance();
		timeOfPost.add(Calendar.MINUTE, -5);
		Date firstTimestamp = timeOfPost.getTime();
		timeOfPost.add(Calendar.MINUTE, 3);
		Date secondTimestamp = timeOfPost.getTime();

		User alice = new User("Alice");
		UserPost alicePost = new UserPost(firstTimestamp, "This is Alice");
		alice.addPost(alicePost);

		User charlie = new User("Charlie");
		UserPost charliePost = new UserPost(secondTimestamp, "This is Charlie");
		charlie.addPost(charliePost);
		charlie.addFollowingUser(alice);

		UserTimeline timeline = instructionProcessor.retrieveUserTimelineInDescendingDateOrder(charlie);
		Map<Date, String> orderedTimeline = timeline.getTimeLineOrderedByDate();
		assertTrue(orderedTimeline.containsKey(firstTimestamp));
		assertTrue(orderedTimeline.containsKey(secondTimestamp));

		Set<Entry<Date, String>> entrySet = orderedTimeline.entrySet();
		Iterator<Entry<Date, String>> iterator = entrySet.iterator();
		Entry<Date, String> firstEntry = iterator.next();
		assertEquals(secondTimestamp, firstEntry.getKey());
		assertEquals("Charlie - This is Charlie (2 minutes ago)", firstEntry.getValue());
		Entry<Date, String> secondEntry = iterator.next();
		assertEquals(firstTimestamp, secondEntry.getKey());
		assertEquals("Alice - This is Alice (5 minutes ago)", secondEntry.getValue());
	}

}
