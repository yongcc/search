package com.search.model;

public class NaverSearchBookParam {
	private String query; // string - - 검색을 원하는 문자열로서 UTF-8로 인코딩한다. 상세검색시 생략가능
	private Integer display; // integer N 10(기본값), 100(최대) 검색 결과 출력 건수 지정 -
	private Integer start; // integer N 1(기본값), 1000(최대) 검색 시작 위치로 최대 1000까지 가능 -
	private String sort; // string N sim(기본값), date 정렬 옵션: sim(유사도순), date(출간일순), count(판매량순)
	private String d_titl; // string N - 책 제목 검색 상세 검색만 해당
	private String d_auth; // string N - 저자명 검색 상세 검색만 해당
	private String d_cont; // string N - 목차 검색 상세 검색만 해당
	private String d_isbn; // string N - isbn 검색 상세 검색만 해당
	private String d_publ; // string N - 출판사 검색 상세 검색만 해당
	private String d_dafr; // string N (ex.20000203) 출간 시작일 상세 검색만 해당
	private String d_dato; // string N (ex.20000203) 출간 종료일 상세 검색만 해당
	private String d_catg; // string N - 책 검색 카테고리(카테고리 목록 다운로드) 상세 검색만 해당

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getD_titl() {
		return d_titl;
	}

	public void setD_titl(String d_titl) {
		this.d_titl = d_titl;
	}

	public String getD_auth() {
		return d_auth;
	}

	public void setD_auth(String d_auth) {
		this.d_auth = d_auth;
	}

	public String getD_cont() {
		return d_cont;
	}

	public void setD_cont(String d_cont) {
		this.d_cont = d_cont;
	}

	public String getD_isbn() {
		return d_isbn;
	}

	public void setD_isbn(String d_isbn) {
		this.d_isbn = d_isbn;
	}

	public String getD_publ() {
		return d_publ;
	}

	public void setD_publ(String d_publ) {
		this.d_publ = d_publ;
	}

	public String getD_dafr() {
		return d_dafr;
	}

	public void setD_dafr(String d_dafr) {
		this.d_dafr = d_dafr;
	}

	public String getD_dato() {
		return d_dato;
	}

	public void setD_dato(String d_dato) {
		this.d_dato = d_dato;
	}

	public String getD_catg() {
		return d_catg;
	}

	public void setD_catg(String d_catg) {
		this.d_catg = d_catg;
	}

	public String getQueryString() {
		StringBuilder sb = new StringBuilder();
		sb.append("?");
		if(query != null) sb.append("query=").append(query);
		if(display != null) sb.append("&display=").append(display);
		if(start != null) sb.append("&start=").append(start);
		if(sort != null) sb.append("&sort=").append(sort);
		if(d_titl != null) sb.append("&d_titl=").append(d_titl);
		if(d_auth != null) sb.append("&d_auth=").append(d_auth);
		if(d_cont != null) sb.append("&d_cont=").append(d_cont);
		if(d_isbn != null) sb.append("&d_isbn=").append(d_isbn);
		if(d_publ != null) sb.append("&d_publ=").append(d_publ);
		if(d_dafr != null) sb.append("&d_dafr=").append(d_dafr);
		if(d_dato != null) sb.append("&d_dato=").append(d_dato);
		if(d_catg != null) sb.append("&d_catg=").append(d_catg);
		return sb.toString();
	}

}
