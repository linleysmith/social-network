package com.exercises.user;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class UserPostTest {

	@Test
	public void orderingOfPostsIsByDescendingDate() {
		Calendar timeOfPost = Calendar.getInstance();
		Date firstTimestamp = timeOfPost.getTime();
		timeOfPost.add(Calendar.MINUTE, 1);
		Date secondTimestamp = timeOfPost.getTime();
		UserPost firstPost = new UserPost(firstTimestamp, null);
		firstPost.toString();
		UserPost secondPost = new UserPost(secondTimestamp, null);

		List<UserPost> userPosts = new ArrayList<UserPost>();
		userPosts.add(firstPost);
		userPosts.add(secondPost);

		Collections.reverse(userPosts);
		assertEquals(secondTimestamp, userPosts.get(0).getTimeOfPost());
		assertEquals(firstTimestamp, userPosts.get(1).getTimeOfPost());
	}

	@Test
	public void formatterPost() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -2);
		UserPost userPost = new UserPost(calendar.getTime(), "Hello world");
		assertEquals("Hello world (2 minutes ago)", userPost.getFormattedPost());
	}

}
