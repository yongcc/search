package com.search.model;

public class KakaoSearchBookParam {
	private String query; // 검색을 원하는 질의어 O String
	private String sort; // 결과 문서 정렬 방식 X (accuracy) accuracy (정확도순) or latest (최신순)
	private Integer page; // 결과 페이지 번호 X(기본 1) 1-100 사이 Integer
	private Integer size; // 한 페이지에 보여질 문서의 개수 X(기본 10) 1-50 사이 Integer
	private String target; // 검색 필드 제한 X title (제목에서 검색) or isbn (ISBN에서 검색) or publisher (출판사에서 검색) or person(인명에서 검색)

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		if(query != null) sb.append("query=").append(query);
		if(sort != null) sb.append("sort=").append(sort);
		if(page != null) sb.append("page=").append(page);
		if(size != null) sb.append("size=").append(size);
		if(target != null) sb.append("target=").append(target);
		return sb.toString();
	}
}
