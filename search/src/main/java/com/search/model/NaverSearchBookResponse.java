package com.search.model;

import java.util.List;

public class NaverSearchBookResponse {
	private String lastBuildDate; // datetime 검색 결과를 생성한 시간이다.
	private Integer total; // integer 검색 결과 문서의 총 개수를 의미한다.
	private Integer start; // integer 검색 결과 문서 중, 문서의 시작점을 의미한다.
	private Integer display; // integer 검색된 검색 결과의 개수이다.

	private List<Item> items;

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
