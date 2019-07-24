package com.search.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.search.dao.UserDao;
import com.search.entity.User;
import com.search.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public Iterable<User> getAllUsers() {
		return userDao.findAll();
	}

	@Override
	public Optional<User> getUser(String id) {
		return id == null ? Optional.empty() : userDao.findById(id);
	}
	
	@Override
	public User getUser(String id, String password) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return getUser(id).filter(user -> passwordEncoder.matches(password, user.getPwd())).orElse(null);
	}

	@Override
	public User mergeUser(User user) {
		return user == null ? null : userDao.save(user);
	}

	@Override
	public void removeUser(String id) {
		if (id == null) {
			return;
		}

		userDao.deleteById(id);
	}
}
