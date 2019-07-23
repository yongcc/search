package com.search.model;

import com.search.common.StatusCode;

public class Result {
	private String code;
	private String message;

	private Object data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public static Result successInstance() {
		Result result = new Result();
		result.setCode(StatusCode.OK);
		return result;
	}
}
