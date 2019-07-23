package com.search.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(name = "TB_KEYWORD", indexes = { @Index(columnList = "keyword") })
public class Keyword {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer seq;
	@Column
	private String keyword;

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
