package com.search.dao;

import org.springframework.data.repository.CrudRepository;

import com.search.entity.Keyword;

public interface KeywordDao extends CrudRepository<Keyword, String> {

}
