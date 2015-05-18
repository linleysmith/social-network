package com.exercises.user;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.exercises.user.User;

public class UserTest {
	
	private User charlie;
	
	@Before
	public void setup() {
		charlie = new User("Charlie");
		charlie.toString();
	}

	@Test
	public void noFollowingUsers() {
		assertTrue(charlie.getFollowingUsers().isEmpty());
	}
	
	@Test
	public void addFollowingUser() {
		User alice = new User("Alice");
		User bob = new User("Bob");
		charlie.addFollowingUser(alice);
		assertFalse(charlie.getFollowingUsers().isEmpty());
		assertEquals(1, charlie.getFollowingUsers().size());
		charlie.addFollowingUser(bob);
		assertEquals(2, charlie.getFollowingUsers().size());
		assertTrue(charlie.getFollowingUsers().contains(alice));
		assertTrue(charlie.getFollowingUsers().contains(bob));
	}
	
	@Test
	public void twoUsersTheSameAreEqual() {
		User alice1 = new User("Alice");
		User alice2 = new User("Alice");
		assertEquals(alice1, alice2);
		assertEquals("User [name=Alice, followingUsers=[], userPosts=[]]", alice1.toString());
	}
	
	@Test
	public void addPostToUser() {
		User alice = new User("Alice");
		assertTrue(alice.getUserPosts().isEmpty());
		Date currentTime = Calendar.getInstance().getTime();
		String post = "I love the weathertoday";
		UserPost userPost = new UserPost(currentTime, post);
		alice.addPost(userPost);
		assertEquals(1,alice.getUserPosts().size());
		assertTrue(alice.getUserPosts().contains(userPost));
	}
}
