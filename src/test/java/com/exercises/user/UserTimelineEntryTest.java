package com.exercises.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

public class UserTimelineEntryTest {

	@Test
	public void userPostInCorrectFormat() {
		Date now = Calendar.getInstance().getTime();
		User user = new User("John");
		UserPost userPost = new UserPost(now, "Hello world!!");
		user.addPost(userPost);
		UserTimelineEntry entry = new UserTimelineEntry(user, userPost);
		Map<Date, String> formattedUserPost = entry.getFormattedUserPost();
		assertTrue(formattedUserPost.containsKey(now));
		String post = formattedUserPost.get(now);
		assertEquals("John - Hello world!! (0 seconds ago)", post);
	}

}
