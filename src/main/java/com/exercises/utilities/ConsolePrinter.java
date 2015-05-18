package com.exercises.utilities;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;

import com.exercises.user.UserPost;

public class ConsolePrinter {

	public void printWallToConsole(SortedMap<Date, String> userTimelineOrderedByDate) {
		Iterator<Entry<Date, String>> iterator = userTimelineOrderedByDate.entrySet().iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getValue());
		}
	}

	public void printUserPostsToConsole(List<UserPost> userPosts) {
		Iterator<UserPost> iterator = userPosts.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getFormattedPost());
		}
	}

	public void printUnknownCommand() {
		System.out.println("Command not recognised");
	}

}
