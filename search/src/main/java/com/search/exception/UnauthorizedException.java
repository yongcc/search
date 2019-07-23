package com.search.exception;

public class UnauthorizedException extends RuntimeException {
	private static final long serialVersionUID = -3317619055234625617L;

	public UnauthorizedException() {
		super("계정 권한이 유효하지 않습니다. 다시 로그인을 해주세요.");
	}
}
