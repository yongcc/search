package com.search.model;

import java.util.List;

public class SearchBookResult {
	private Integer totalCount;
	private Integer maxPage;
	private List<SearchBook> searchBooks;

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(Integer maxPage) {
		this.maxPage = maxPage;
	}

	public List<SearchBook> getSearchBooks() {
		return searchBooks;
	}

	public void setSearchBooks(List<SearchBook> searchBooks) {
		this.searchBooks = searchBooks;
	}

}
