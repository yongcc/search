package com.search.dao;

import org.springframework.data.repository.CrudRepository;

import com.search.entity.User;

public interface KeywordDao extends CrudRepository<User, String> {

}
