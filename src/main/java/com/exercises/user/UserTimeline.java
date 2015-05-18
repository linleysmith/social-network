package com.exercises.user;

import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public final class UserTimeline {

	private final SortedMap<Date, String> userTimelineEntries = new TreeMap<Date, String>();

	public void addUserTimelineEntry(Map<Date, String> formattedUserPost) {
		userTimelineEntries.putAll(formattedUserPost);
	}

	public SortedMap<Date, String> getTimeLineOrderedByDate() {
		if (userTimelineEntries.isEmpty()) {
			return Collections.emptySortedMap();
		}
		SortedMap<Date, String> reversedTimeline = new TreeMap<Date, String>(Collections.reverseOrder());
		reversedTimeline.putAll(userTimelineEntries);
		return reversedTimeline;
	}

}
