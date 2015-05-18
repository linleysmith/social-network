package com.exercises.user;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.exercises.utilities.DateTimeCalculator;

public class UserTimelineEntry {
	private final String username;
	private final Date timeOfPost;
	private final String post;

	public UserTimelineEntry(User user, UserPost userPost) {
		this.username = user.getName();
		this.timeOfPost = userPost.getTimeOfPost();
		this.post = userPost.getPost();
	}

	public Map<Date, String> getFormattedUserPost() {
		Map<Date, String> entry = new LinkedHashMap<Date, String>();
		entry.put(this.timeOfPost, buildPost());
		return entry;
	}

	private String buildPost() {
		StringBuilder post = new StringBuilder();
		post.append(this.username);
		post.append(" - ");
		post.append(this.post);
		post.append(" ");
		post.append(DateTimeCalculator.getFormattedTimeDifference(this.timeOfPost, Calendar.getInstance().getTime()));
		return post.toString();
	}

}
