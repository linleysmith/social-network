package com.exercises.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.exercises.user.User;
import com.exercises.user.UserPost;
import com.exercises.user.UserTimeline;
import com.exercises.user.UserTimelineEntry;

public class InstructionProcessor {

	public void addPostToUser(User user, Instruction instruction) {
		UserPost userPost = new UserPost(instruction.getTimestamp(), instruction.getMessage());
		user.addPost(userPost);
	}

	public void followUser(User user, User followingUser) {
		user.addFollowingUser(followingUser);
	}

	public List<UserPost> retrieveUserPostsInDescendingDateOrder(User user) {
		List<UserPost> listOfPosts = new ArrayList<UserPost>();
		listOfPosts.addAll(user.getUserPosts());
		Collections.reverse(listOfPosts);
		return listOfPosts;
	}

	public UserTimeline retrieveUserTimelineInDescendingDateOrder(User user) {
		UserTimeline userTimeline = new UserTimeline();
		processUserPosts(user, userTimeline);
		processFollowingUserPosts(user, userTimeline);
		return userTimeline;
	}

	private void processFollowingUserPosts(User user, UserTimeline userTimeline) {
		for (User followingUser : user.getFollowingUsers()) {
			processUserPosts(followingUser, userTimeline);
		}
	}

	private void processUserPosts(User user, UserTimeline userTimeline) {
		for (UserPost userPost : user.getUserPosts()) {
			UserTimelineEntry userTimelineEntry = new UserTimelineEntry(user, userPost);
			Map<Date, String> formattedUserPost = userTimelineEntry.getFormattedUserPost();
			userTimeline.addUserTimelineEntry(formattedUserPost);
		}
	}

}
