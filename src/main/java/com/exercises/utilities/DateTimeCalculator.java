package com.exercises.utilities;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

public class DateTimeCalculator {

	/**
	 * Returns the time difference between 2 dates in the following formats: (1
	 * second ago), (10 seconds ago), (1 minute ago), (5 minutes ago), (1 hour
	 * ago), (3 hours ago), (70 hours ago), etc
	 * 
	 * But will not return days, months, years, etc
	 * 
	 * @param oldest
	 *            this must be a date prior to the newest
	 * @param newest
	 *            this must be a date after the oldest
	 * @return String of time difference in above format
	 * @throws IllegalArgumentException
	 *             if the dates are null or newest is before oldest
	 */
	public static String getFormattedTimeDifference(Date oldest, Date newest) {
		if (oldest == null || newest == null || newest.before(oldest)) {
			throw new IllegalArgumentException(String.format("Invalid parameters"));
		}
		if (oldest.equals(newest)) {
			return "(0 seconds ago)";
		}
		DateTime dt1 = new DateTime(oldest);
		DateTime dt2 = new DateTime(newest);
		int hours = Hours.hoursBetween(dt1, dt2).getHours();
		if (hours > 0) {
			return (hours == 1) ? "(1 hour ago)" : String.format("(%d hours ago)", hours);
		}
		int minutes = Minutes.minutesBetween(dt1, dt2).getMinutes();
		if (minutes > 0) {
			return (minutes == 1) ? "(1 minute ago)" : String.format("(%d minutes ago)", minutes);
		}
		int seconds = Seconds.secondsBetween(dt1, dt2).getSeconds();
		if (seconds >= 0) {
			return (seconds == 1) ? "(1 second ago)" : String.format("(%d seconds ago)", seconds);
		}

		return "(Timings not available)";
	}

}
