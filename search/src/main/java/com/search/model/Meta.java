package com.search.model;

public class Meta {
	private Integer total_count; // 전체 검색된 문서수	Integer
	private Integer pageable_count; // 검색결과로 제공 가능한 문서수	Integer
	private Boolean is_end; // 현재 페이지가 마지막 페이지인지 여부. 값이 false이면 page를 증가시켜 다음 페이지를 요청할 수 있음	Boolean

	public Integer getTotal_count() {
		return total_count;
	}

	public void setTotal_count(Integer total_count) {
		this.total_count = total_count;
	}

	public Integer getPageable_count() {
		return pageable_count;
	}

	public void setPageable_count(Integer pageable_count) {
		this.pageable_count = pageable_count;
	}

	public Boolean getIs_end() {
		return is_end;
	}

	public void setIs_end(Boolean is_end) {
		this.is_end = is_end;
	}

}
