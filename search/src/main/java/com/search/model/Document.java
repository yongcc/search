package com.search.model;

import java.util.List;

public class Document {
	private String title; // 도서 제목 String
	private String contents; // 도서 소개 String
	private String url; // 도서 상세 URL String
	private String isbn; // 국제 표준 도서번호(ISBN10 ISBN13) (ISBN10,ISBN13 중 하나 이상 존재하며, ' '(공백)을 구분자로 출력됩니다) String
	private String datetime; // 도서 출판날짜. ISO 8601. [YYYY]-[MM]-[DD]T[hh]:[mm]:[ss].000+[tz] String
	private List<String> authors; // 도서 저자 리스트 Array of String
	private String publisher; // 도서 출판사 String
	private List<String> translators; // 도서 번역자 리스트 Array of String
	private Integer price; // 도서 정가 Integer
	private Integer sale_price; // 도서 판매가 Integer
	private String thumbnail; // 도서 표지 썸네일 URL String
	private String status; // 도서 판매 상태 정보(정상, 품절, 절판 등), 상황에 따라 변동 가능성이 있으므로 문자열 처리 지양, 단순 노출요소로 활용을 권장합니다. String

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<String> getTranslators() {
		return translators;
	}

	public void setTranslators(List<String> translators) {
		this.translators = translators;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getSale_price() {
		return sale_price;
	}

	public void setSale_price(Integer sale_price) {
		this.sale_price = sale_price;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
