package com.exercises.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class DateTimeCalculatorTest {

	private Calendar now;
	private Date oldest;
	private Date newest;

	@Before
	public void setup() {
		now = Calendar.getInstance();
		oldest = now.getTime();
	}

	@Test(expected = IllegalArgumentException.class)
	public void exceptionThrownWhenOldestIsNull() {
		DateTimeCalculator.getFormattedTimeDifference(null, now.getTime());
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void exceptionThrownWhenNewestIsNull() {
		DateTimeCalculator.getFormattedTimeDifference(now.getTime(), null);
		fail();
	}

	@Test(expected = IllegalArgumentException.class)
	public void exceptionThrownWhenNewestDateIsBeforeOldest() {
		now.add(Calendar.SECOND, -10);
		newest = now.getTime();
		DateTimeCalculator.getFormattedTimeDifference(oldest, newest);
		fail();
	}

	@Test
	public void formattedTimeDifferenceInHours() {
		now.add(Calendar.HOUR, 1);
		newest = now.getTime();
		assertEquals("(1 hour ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
		now.add(Calendar.HOUR, 2);
		newest = now.getTime();
		assertEquals("(3 hours ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
		now.add(Calendar.HOUR, 30);
		newest = now.getTime();
		assertEquals("(33 hours ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));

	}

	@Test
	public void formattedTimeDifferenceInMinutes() {
		now.add(Calendar.MINUTE, 1);
		newest = now.getTime();
		assertEquals("(1 minute ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
		now.add(Calendar.MINUTE, 5);
		newest = now.getTime();
		assertEquals("(6 minutes ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
	}

	@Test
	public void formattedTimeDifferenceInSeconds() {
		now.add(Calendar.MILLISECOND, 1);
		newest = now.getTime();
		assertEquals("(0 seconds ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
		now.add(Calendar.SECOND, 1);
		newest = now.getTime();
		assertEquals("(1 second ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
		now.add(Calendar.SECOND, 20);
		newest = now.getTime();
		assertEquals("(21 seconds ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
	}

	@Test
	public void formattedTimeDifferenceIfNoDifference() {
		assertEquals("(0 seconds ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, oldest));
	}

	@Test
	public void formattedTimeDifferenceInGreatestTimeMeasure() {
		now.add(Calendar.SECOND, 1);
		now.add(Calendar.MINUTE, 59);
		now.add(Calendar.HOUR, 4);
		newest = now.getTime();
		assertEquals("(4 hours ago)", DateTimeCalculator.getFormattedTimeDifference(oldest, newest));
	}

}
