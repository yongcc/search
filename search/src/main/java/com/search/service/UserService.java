package com.search.service;

import java.util.Optional;

import com.search.entity.User;

public interface UserService {

	Iterable<User> getAllUsers();

	Optional<User> getUser(String id);

	User mergeUser(User user);

	void removeUser(String id);

}
