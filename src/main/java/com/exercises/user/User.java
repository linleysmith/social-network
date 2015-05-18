package com.exercises.user;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class User {

	private final String name;
	private final Set<User> followingUsers = Collections
			.synchronizedSet(new LinkedHashSet<User>());
	private final Set<UserPost> userPosts = Collections
			.synchronizedSet(new LinkedHashSet<UserPost>());

	public User(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addFollowingUser(User followingUser) {
		followingUsers.add(followingUser);
	}

	public Set<User> getFollowingUsers() {
		if (followingUsers.isEmpty()) {
			return Collections.emptySet();
		}
		return new LinkedHashSet<User>(followingUsers);
	}

	public void addPost(UserPost userPost) {
		userPosts.add(userPost);
	}

	public Set<UserPost> getUserPosts() {
		if (userPosts.isEmpty()) {
			return Collections.emptySet();
		}
		return new LinkedHashSet<UserPost>(userPosts);
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", followingUsers=" + followingUsers
				+ ", userPosts=" + userPosts + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
