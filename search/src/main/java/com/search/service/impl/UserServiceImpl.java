package com.search.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.dao.UserDao;
import com.search.entity.User;
import com.search.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public Iterable<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public Optional<User> getUser(String id) {
		if (id == null) {
			return Optional.empty();
		}

		return userDao.findById(id);
	}

	@Override
	public User mergeUser(User user) {
		if (user == null) {
			return null;
		}

		return userDao.save(user);
	}

	@Override
	public void removeUser(String id) {
		if (id == null) {
			return;
		}

		userDao.deleteById(id);
	}
}
