package com.exercises.user;

import java.util.Calendar;
import java.util.Date;

import com.exercises.utilities.DateTimeCalculator;

public final class UserPost {

	private final Date timeOfPost;
	private final String post;

	public UserPost(Date timeOfPost, String post) {
		this.timeOfPost = timeOfPost;
		this.post = post;
	}

	public Date getTimeOfPost() {
		return timeOfPost;
	}

	public String getPost() {
		return post;
	}

	public String getFormattedPost() {
		StringBuilder post = new StringBuilder();
		post.append(this.post);
		post.append(" ");
		post.append(DateTimeCalculator.getFormattedTimeDifference(this.timeOfPost, Calendar.getInstance().getTime()));
		return post.toString();
	}

	@Override
	public String toString() {
		return "UserPost [timeOfPost=" + timeOfPost + ", post=" + post + "]";
	}

}
