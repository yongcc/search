package com.search.service;

import java.util.Optional;

import com.search.entity.User;

public interface UserService {

	/**
	 * 유저 조회
	 * @param id
	 * @return
	 */
	Optional<User> getUser(String id);

	/**
	 * 유저 insert
	 * @param user
	 * @return
	 */
	User mergeUser(User user);

	/**
	 * 유저 조회
	 * @param id
	 * @param password
	 * @return
	 */
	User getUser(String id, String password);

}
