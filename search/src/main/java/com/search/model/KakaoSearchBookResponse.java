package com.search.model;

import java.util.List;

public class KakaoSearchBookResponse {
	private Meta meta;
	private List<Document> documents;

	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

}
