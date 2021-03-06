package com.search.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.search.dao.UserDao;
import com.search.entity.User;
import com.search.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

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

}
