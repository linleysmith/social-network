package com.exercises.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Before;
import org.junit.Test;

public class UserTimelineTest {

	private UserTimeline userTimeline;
	private Calendar calendar;

	@Before
	public void setup() {
		userTimeline = new UserTimeline();
		calendar = Calendar.getInstance();
	}

	@Test
	public void userTimelineEntryAdded() {

		assertTrue(userTimeline.getTimeLineOrderedByDate().isEmpty());

		User alice = new User("Alice");
		User bob = new User("Bob");
		User charlie = new User("Charlie");
		charlie.addFollowingUser(alice);
		charlie.addFollowingUser(bob);

		calendar.add(Calendar.MINUTE, -5);
		Date alicePostTimeOne = calendar.getTime();
		UserPost aliceUserPostOne = new UserPost(alicePostTimeOne, "I love the weather today");
		UserTimelineEntry aliceTimelineEntryOne = new UserTimelineEntry(alice, aliceUserPostOne);
		userTimeline.addUserTimelineEntry(aliceTimelineEntryOne.getFormattedUserPost());

		calendar.add(Calendar.MINUTE, 3);
		Date bobPostTimeOne = calendar.getTime();
		UserPost bobUserPostOne = new UserPost(bobPostTimeOne, "Damn! We lost!");
		UserTimelineEntry bobTimelineEntryOne = new UserTimelineEntry(bob, bobUserPostOne);
		userTimeline.addUserTimelineEntry(bobTimelineEntryOne.getFormattedUserPost());

		calendar.add(Calendar.MINUTE, 1);
		Date bobPostTimeTwo = calendar.getTime();
		UserPost bobUserPostTwo = new UserPost(bobPostTimeTwo, "Good game though.");
		UserTimelineEntry bobTimelineEntryTwo = new UserTimelineEntry(bob, bobUserPostTwo);
		userTimeline.addUserTimelineEntry(bobTimelineEntryTwo.getFormattedUserPost());

		calendar.add(Calendar.SECOND, 45);
		Date charliePostTimeOne = calendar.getTime();
		UserPost charlieUserPostTwo = new UserPost(charliePostTimeOne,
				"I am in New York today! Anyone want to have coffee?");
		UserTimelineEntry charlieTimelineEntryTwo = new UserTimelineEntry(charlie, charlieUserPostTwo);
		userTimeline.addUserTimelineEntry(charlieTimelineEntryTwo.getFormattedUserPost());

		SortedMap<Date, String> orderedTimeline = userTimeline.getTimeLineOrderedByDate();
		assertFalse(orderedTimeline.isEmpty());
		Set<Date> keySet = orderedTimeline.keySet();
		Iterator<Date> iterator = keySet.iterator();
		assertEquals(charliePostTimeOne, iterator.next());
		assertEquals(bobPostTimeTwo, iterator.next());
		assertEquals(bobPostTimeOne, iterator.next());
		assertEquals(alicePostTimeOne, iterator.next());

	}

}
