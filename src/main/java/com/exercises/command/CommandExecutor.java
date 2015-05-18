package com.exercises.command;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import com.exercises.user.User;
import com.exercises.user.UserPost;
import com.exercises.user.UserTimeline;

public class CommandExecutor {

	private InstructionProcessor instructionProcessor;
	private Map<String, User> activeUsers = Collections.synchronizedMap(new LinkedHashMap<String, User>());

	public CommandExecutor(InstructionProcessor instructionProcessor) {
		this.instructionProcessor = instructionProcessor;
	}

	public void processPost(Instruction instruction) {
		User user = addToActiveUsers(instruction.getUsername());
		instructionProcessor.addPostToUser(user, instruction);
	}

	public void processFollowing(Instruction instruction) {
		User user = getActiveUser(instruction.getUsername());
		User followingUser = getActiveUser(instruction.getMessage());
		instructionProcessor.followUser(user, followingUser);
	}

	public List<UserPost> processReading(Instruction instruction) {
		User user = getActiveUser(instruction.getUsername());
		List<UserPost> userPosts = instructionProcessor.retrieveUserPostsInDescendingDateOrder(user);
		return userPosts;
	}

	public SortedMap<Date, String> processWall(Instruction instruction) {
		User user = addToActiveUsers(instruction.getUsername());
		UserTimeline userTimeline = instructionProcessor.retrieveUserTimelineInDescendingDateOrder(user);
		SortedMap<Date, String> userTimelineOrderedByDate = userTimeline.getTimeLineOrderedByDate();
		return userTimelineOrderedByDate;
	}

	private User addToActiveUsers(String username) {
		if (activeUsers.containsKey(username)) {
			return activeUsers.get(username);
		} else {
			User user = new User(username);
			activeUsers.put(username, user);
			return user;
		}
	}

	protected Map<String, User> getActiveUsers() {
		if (activeUsers.isEmpty()) {
			return Collections.emptyMap();
		} else {
			Map<String, User> copy = new LinkedHashMap<String, User>();
			copy.putAll(activeUsers);
			return copy;
		}
	}

	private User getActiveUser(String username) {
		return activeUsers.containsKey(username) ? activeUsers.get(username) : new User(username);
	}

	@Override
	public String toString() {
		return "CommandExecutor [activeUsers=" + activeUsers + "]";
	}

}
